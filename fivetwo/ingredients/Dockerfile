FROM ovstetun/java8

ADD target/universal/stage /stage

WORKDIR /stage

EXPOSE 8080

ENTRYPOINT ["./bin/ingredients"]
