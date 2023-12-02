
package com.ai.god.talk.bot.aigodtalkbot.model.openai.request;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptRequest {
    private List<ChatGtpRequestMessage> messages;
    private String model;
    private Double temperature;
}
