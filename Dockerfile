FROM openjdk:21

COPY out/artifacts/javaJunior_jar/javaJunior.jar /tmp/javaJunior.jar
WORKDIR /tmp
CMD ["java", "-jar", "/tmp/javaJunior.jar"]

#FROM ubuntu:latest
#LABEL authors="clidc"
#
#ENTRYPOINT ["top", "-b"]