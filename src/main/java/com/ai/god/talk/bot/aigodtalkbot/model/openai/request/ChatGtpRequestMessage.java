package com.ai.god.talk.bot.aigodtalkbot.model.openai.request;

import lombok.Data;

@Data
public class ChatGtpRequestMessage {
    private String content;
    private String role;
}
