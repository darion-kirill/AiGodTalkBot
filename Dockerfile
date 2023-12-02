FROM openjdk:17-jdk-slim

RUN mkdir -p /opt/aigodbot
COPY target/ai-god-talk-bot.jar opt/aigodbot/bot.jar

ENTRYPOINT ["java","-jar","/opt/aigodbot/bot.jar"]