FROM openjdk:17-slim

WORKDIR /app

COPY target/blog-0.0.1-SNAPSHOT.jar /app/blog.jar

EXPOSE 3000

CMD ["java", "-jar", "/app/blog.jar"]