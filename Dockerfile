FROM ubuntu:18.04

EXPOSE 8082

COPY ./auth/target/auth-0.0.1-SNAPSHOT.jar /opt
RUN apt update -y \
    && apt install -y openjdk-11-jre

ENTRYPOINT java -jar /opt/auth-0.0.1-SNAPSHOT.jar -Djava.net.preferIPv4Stack=true