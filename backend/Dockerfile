FROM openjdk:11
COPY . .
RUN ./gradlew build
ENTRYPOINT ["java","-jar","/build/libs/reviews-0.0.1-SNAPSHOT.jar"]
