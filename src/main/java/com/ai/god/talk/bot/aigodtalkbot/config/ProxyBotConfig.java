package com.ai.god.talk.bot.aigodtalkbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("bot.proxy")
public class ProxyBotConfig {
    private String host;
    private Integer port;
    private String user;
    private String password;
}