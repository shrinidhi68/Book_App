FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/Recommendations-0.0.1-SNAPSHOT.jar /usr/src/Recommendations-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Recommendations-0.0.1-SNAPSHOT.jar"]
