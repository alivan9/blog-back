pipeline {
    agent any
    options { timestamps() }

    parameters {
        string(name: 'GITHUB_BRANCH', defaultValue: 'master', description: 'GitHub Branch name')
        string(name: 'GITHUB_URL', defaultValue: "https://github.com/Jorge-Urquiza/blog-back.git", description: 'GitHub URL project')
        string(name: 'TARGET_PATH', defaultValue: "C:\\deploy\\blog-back", description: 'Target folder path for JAR')
    }

    environment {
        DB_HOST = 'localhost'
        DB_PORT = '3306'
        DB_NAME = 'blog'
        DB_USER = 'root'
        DB_PASS = ''
    }

    stages {
        stage('Checkout github project') {
            steps {
                git branch: "${params.GITHUB_BRANCH}", url: "${params.GITHUB_URL}"
            }
        }
        stage('Clean-Up old JARs') {
            steps {
                script {
                    emptyFolder("target")
                }
            }
        }
        stage('Build & Test') {
            steps {
                bat '.\\mvnw.cmd -B clean verify'
            }
        }
        stage('Archive JAR') {
            steps {
                script {
                    def listOut = bat(returnStdout: true, script: '@echo off\r\ndir /b target\\*.jar').trim()
                    def jarLines = listOut.readLines()
                    if (jarLines == null || jarLines.isEmpty()) {
                        error 'No se encontró ningún JAR en target/*.jar'
                    }
                    env.JAR_PATH = ("target\\" + jarLines[0].trim())
                    env.BUILD_NAME = "blog-back-${env.BUILD_NUMBER}.jar"
                }
                bat """
                    copy /Y "${env.JAR_PATH}" "target\\${env.BUILD_NAME}"
                    certutil -hashfile "target\\${env.BUILD_NAME}" SHA256 > "target\\${env.BUILD_NAME}.sha256.txt"
                """
                archiveArtifacts artifacts: "target/${env.BUILD_NAME}, target/${env.BUILD_NAME}.sha256.txt", fingerprint: true
            }
        }
        stage('Clean-Up deploy folder') {
            steps {
                script {
                    powershell """
                        if (-Not (Test-Path '${params.TARGET_PATH}')) {
                            New-Item -ItemType Directory -Path '${params.TARGET_PATH}' | Out-Null
                        }
                        Remove-Item -Path '${params.TARGET_PATH}\\*' -Include *.* -Force -ErrorAction SilentlyContinue
                    """
                    println("Deploy folder preparado en ${params.TARGET_PATH}")
                }
            }
        }
        stage('Copy new JAR to deploy folder') {
            steps {
                script {
                    copyFile("${WORKSPACE}\\target\\${env.BUILD_NAME}", "${params.TARGET_PATH}")
                }
            }
        }
        stage('Smoke run (MySQL local)') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                    timeout(time: 40, unit: 'SECONDS') {
                        withEnv([
                            "SPRING_PROFILES_ACTIVE=default",
                            "SPRING_DATASOURCE_URL=jdbc:mysql://${env.DB_HOST}:${env.DB_PORT}/${env.DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                            "SPRING_DATASOURCE_USERNAME=${env.DB_USER}",
                            "SPRING_DATASOURCE_PASSWORD=${env.DB_PASS}",
                            "SPRING_JPA_HIBERNATE_DDL_AUTO=update",
                            "SERVER_PORT=8081"
                        ]) {
                            bat """
                                echo Ejecutando: ${params.TARGET_PATH}\\${env.BUILD_NAME}
                                java -jar "${params.TARGET_PATH}\\${env.BUILD_NAME}"
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finalizado. Artefactos en 'Artifacts'. Si el smoke marcó UNSTABLE es por timeout intencional."
        }
        failure {
            echo "El pipeline falló, revisa los logs para más detalles."
        }
    }
}

/*************************************************************************************************
*                                FUNCTIONS SECTION                                               *
**************************************************************************************************/
def emptyFolder(String path) {
    String command = """
        if (-Not (Test-Path '${path}')) {
            New-Item -ItemType Directory -Path '${path}' | Out-Null
        }
        Remove-Item -Path '${path}\\*' -Include *.* -Force -ErrorAction SilentlyContinue
    """
    powershell(returnStdout:true, script:command)
    println("CleanUp folder done at ${path}.")
}

def copyFile(String sourcePath, String targetPath) {
    String command = """
        if (-Not (Test-Path '${targetPath}')) {
            New-Item -ItemType Directory -Path '${targetPath}' | Out-Null
        }
        Copy-Item -Path '${sourcePath}' -Destination '${targetPath}' -Force
    """
    powershell(returnStdout:true, script:command)
    println("Copy file done to ${targetPath}.")
}
