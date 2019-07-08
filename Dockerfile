FROM openjdk:8

MAINTAINER "TWCAM"
# # Agregue su aplicación a la imagen acoplable 
ADD target/boarding-0.0.1.jar boarding-0.0.1.jar
ENTRYPOINT [ "java", "-jar", "boarding-0.0.1.jar" ]
EXPOSE 8081
# # Agregue el script de espera a la imagen 
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait
CMD /wait
