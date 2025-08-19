pipeline {
  agent any
  options { timestamps() }

  environment {
    DB_HOST = 'localhost'
    DB_PORT = '3306'
    DB_NAME = 'blog'      
    DB_USER = 'root'      
    DB_PASS = 'root'    
    APP_PROFILE = ''
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/Jorge-Urquiza/blog-back.git'
      }
    }

    stage('Build (sin tests)') {
      steps {
        // Build “prod-like” sin compilar/ejecutar tests
        bat '.\\mvnw.cmd -B clean package -Dmaven.test.skip=true'
      }
    }

    stage('Stamp & Archive') {
      steps {
        script {
          // 1) Detectar el JAR generado (sin plugins extra)
          def listOut = bat(returnStdout: true, script: '@echo off\r\ndir /b target\\*.jar').trim()
          def jarLines = listOut.readLines()
          if (jarLines == null || jarLines.isEmpty()) {
            error 'No se encontró ningún JAR en target/*.jar'
          }
          env.JAR_PATH = ("target\\" + jarLines[0].trim())

          // 2) Obtener GIT_SHA limpio (última línea)
          def shaOut = bat(returnStdout: true, script: '@echo off\r\ngit rev-parse --short=12 HEAD').trim()
          def lines = shaOut.readLines()
          env.GIT_SHA = lines[lines.size() - 1].trim()

          // 3) Nombre final del artefacto
          env.BUILD_NAME = "blog-back-${env.BUILD_NUMBER}-${env.GIT_SHA}.jar"
        }

        // 4) Copiar y generar checksum con rutas entre comillas
        bat """
          copy /Y "${env.JAR_PATH}" "target\\${env.BUILD_NAME}"
          certutil -hashfile "target\\${env.BUILD_NAME}" SHA256 > "target\\${env.BUILD_NAME}.sha256.txt"
        """

        // 5) Publicar artefactos
        archiveArtifacts artifacts: "target/${env.BUILD_NAME}, target/${env.BUILD_NAME}.sha256.txt", fingerprint: true
      }
    }

    stage('Smoke run (MySQL local)') {
      steps {
        // Limitamos con timeout y no rompemos el build si expira (queda UNSTABLE).
        catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
          timeout(time: 40, unit: 'SECONDS') {
            withEnv([
              "SPRING_PROFILES_ACTIVE=${APP_PROFILE}",
              "SPRING_DATASOURCE_URL=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
              "SPRING_DATASOURCE_USERNAME=${DB_USER}",
              "SPRING_DATASOURCE_PASSWORD=${DB_PASS}",
              // Cambia a 'none' si NO quieres que toque el esquema en tu máquina
              "SPRING_JPA_HIBERNATE_DDL_AUTO=update"
            ]) {
              bat """
                for %%f in (target\\*.jar) do ( set "APP_JAR=%%f" )
                echo Ejecutando: %APP_JAR%
                java -jar "%APP_JAR%"
              """
            }
          }
        }
      }
    }
  }

  post {
    always {
      echo "Listo. Artefactos en 'Artifacts'. Si el smoke marcó UNSTABLE es por timeout intencional."
    }
  }
}
