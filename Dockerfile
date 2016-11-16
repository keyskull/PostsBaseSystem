FROM openjdk-8-jre-alpine
MAINTAINER Cullen Lee <a410615903@gmail.com>

COPY . /PBS/

ENTRYPOINT ["/PBS/bootstrap.sh"]