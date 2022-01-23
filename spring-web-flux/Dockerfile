#FROM maven:3.6 AS build  
#COPY src /usr/src/app/src  
#COPY pom.xml /usr/src/app  
#RUN mvn -f /usr/src/app/pom.xml package

#FROM openjdk:8
#COPY --from=build /usr/src/app/target/spring-boot-web-flux-0.0.1-SNAPSHOT.jar /usr/app/spring-boot-web-flux-0.0.1-SNAPSHOT.jar 
#EXPOSE 8080  
#ENTRYPOINT ["java","-jar","/usr/app/spring-boot-web-flux-0.0.1-SNAPSHOT.jar"]  

FROM openjdk:8
LABEL Isa SAYAR <isa.sayar1725@gmail.com>
ADD target/spring-boot-web-flux-0.0.1-SNAPSHOT.jar spring-boot-web-flux-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-web-flux-0.0.1-SNAPSHOT.jar"]
