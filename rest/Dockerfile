FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /rest
EXPOSE 8081
COPY /build/libs/rest-*.jar ./rest.jar
ENTRYPOINT java -jar /rest/rest.jar
