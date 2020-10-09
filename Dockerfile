FROM openjdk:14-alpine

RUN apk add --no-cache bash

RUN apk add curl

MAINTAINER pateluday07@gmai.com

VOLUME /tmp

COPY ./build/libs/elastic-search-demo-0.0.1-SNAPSHOT.jar elastic-search-demo-0.0.1-SNAPSHOT.jar

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh"]

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/elastic-search-demo-0.0.1-SNAPSHOT.jar"]

