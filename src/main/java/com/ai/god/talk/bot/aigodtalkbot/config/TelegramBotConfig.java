package com.ai.god.talk.bot.aigodtalkbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("bot.telegram")
public class TelegramBotConfig {
    private String name;
    private String token;
    private List<String> users;
}
