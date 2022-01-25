FROM openjdk:17
MAINTAINER mikhailov.net
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
COPY target/library*-spring-boot.jar library.jar
ENTRYPOINT ["java","-jar","/library.jar"]