#De la imagen que partimos
FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk add --no-cache tzdata
ENV TZ='America/Lima'
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apk --update add fontconfig ttf-dejavu

#Directorio de trabajo
WORKDIR /

RUN mkdir app && chmod 777 app
COPY target/calculator-0.0.1-SNAPSHOT.jar /app
WORKDIR /app

#Exponemos el puerto 5000
EXPOSE 5000

#Comando que se ejecutarÃ¡ una vez ejecutemos el contendor
CMD ["java","-jar","calculator-0.0.1-SNAPSHOT.jar"]