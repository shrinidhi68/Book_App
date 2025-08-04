FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/favorite_db
ADD ./target/UserFavoriteService-0.0.1-SNAPSHOT.jar /usr/src/UserFavoriteService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserFavoriteService-0.0.1-SNAPSHOT.jar"]
