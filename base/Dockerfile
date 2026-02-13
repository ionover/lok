FROM azul/zulu-openjdk-alpine:25-jre
LABEL MAINTAINER="Ionov <ionov.vy@mail.ru>"

COPY ./target/waliotTest.jar waliotTest.jar

EXPOSE 8080

ENTRYPOINT exec java -Xmx${XMX_SIZE} -jar waliotTest.jar
