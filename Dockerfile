FROM openjdk:14-alpine

MAINTAINER pateluday07@gmai.com

VOLUME /tmp

COPY ./build/libs/elastic-search-demo-0.0.1-SNAPSHOT.jar elastic-search-demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Delasticsearch.connection.url=elasticsearch-for-springboot-app:9200","-Djava.security.egd=file:/dev/./urandom","-jar","/elastic-search-demo-0.0.1-SNAPSHOT.jar"]

