FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/let-it-tree.jar let-it-tree.jar
EXPOSE 8080
CMD ["java", "-jar", "let-it-tree.jar"]