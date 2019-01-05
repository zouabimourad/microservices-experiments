FROM java
VOLUME /tmp
ADD product-service-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -Xmx128m -jar /app.jar" ]