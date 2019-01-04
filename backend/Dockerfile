FROM openjdk:8-jdk-alpine
COPY ./target/BlueChat-2-SNAPSHOT.jar /home/chat/backend/
#USER root
#RUN chown root /home/chat/backend/target/*
WORKDIR /home/chat/backend/
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=remote", "-jar", "BlueChat-2-SNAPSHOT.jar"]
 
