package com.ai.god.talk.bot.aigodtalkbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("bot.openai")
public class OpenAiConfig {
    private String token;
    private String url;
    private String role;
    private String model;
    private Double temperature;
}