<h1 align="center">AiGodTalkBot</h1>
Telegram bot for making a conversation with ChatGPT

---

![version](https://img.shields.io/badge/version-0.1.0--SNAPSHOT-green)
![version](https://img.shields.io/badge/java-17-red)

## Before run
1) You need to create a properties file (application.yml) in the following directory
```agsl
/src/main/resources/
```
File structure:
```yaml
bot:
  telegram:
    name: {your_bot_name}
    token: {your_bot_token}
    users: [{some_user}, {another_user}]
  openai:
    token: {your_openai_token}
    url: https://api.openai.com/v1/chat/completions
    role: {your_openai_role}
    model: {chat_gtp_model}
    temperature: {chat_gtp_temperature}
  proxy:
    host: {your_proxy_host}
    port: {your_proxy_port}
    user: {your_proxy_user}
    password: {your_proxy_password}
```
2) Install java and maven
3) Run maven build by project:
```shell
mvn clean install
```

## Run
### Start in Docker
```shell
docker-compose up
```

### Start as service
```shell
java -jar /target/ai-got-talk-boot.jar
```
 
