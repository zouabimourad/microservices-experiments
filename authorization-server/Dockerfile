FROM java
VOLUME /tmp
ADD authorization-server-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Xmx128m -jar /app.jar" ]