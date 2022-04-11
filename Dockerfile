#FROM openjdk:19-jdk-alpine3.14
FROM openjdk:11.0.14.1-jre-slim

RUN apk add curl jq

#Workspace
WORKDIR /usr/share/udemy

#ADD .jar files under target from the host
#to this image
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

#In case of all other dependencies like .csv / .json / .xml
#add them as well

#ADD Suite files
ADD book-flight-module.xml              book-flight-module.xml
ADD search-module.xml                   search-module.xml

#ADD health check file
ADD healthcheck.sh                       healthcheck.sh

#parameters to pass to this file:
#BROWSER
#HUB_HOST
#MODULE

ENTRYPOINT sh healthcheck.sh 