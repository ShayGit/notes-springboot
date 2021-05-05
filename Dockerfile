
FROM openjdk:11
ADD target/notes-mysql.jar notes-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "notes-mysql.jar"]