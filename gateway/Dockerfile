FROM ubuntu:18.04

EXPOSE 8082

COPY ./target/gateway-0.0.1-SNAPSHOT.jar /opt
RUN apt update -y \
    && apt install -y openjdk-11-jre

ENTRYPOINT java -jar /opt/gateway-0.0.1-SNAPSHOT.jar -Djava.net.preferIPv4Stack=true
