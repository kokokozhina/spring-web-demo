FROM openjdk:8
ADD target/mrbot-web-1.0.jar /app/
EXPOSE 9080 443 80 22

CMD ["/usr/bin/java", "-Xmx512m", "-Dlog.level=INFO", "-jar", "/app/mrbot-web-1.0.jar"]