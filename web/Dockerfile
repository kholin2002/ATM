FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /web
EXPOSE 8080
COPY /build/libs/web-*.jar ./web.jar
ENTRYPOINT java -jar /web/web.jar
