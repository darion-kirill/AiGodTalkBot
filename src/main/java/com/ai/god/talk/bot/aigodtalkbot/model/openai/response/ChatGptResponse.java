package com.ai.god.talk.bot.aigodtalkbot.model.openai.response;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {
    private List<ChatGptResponseChoice> choices;
    private Long created;
    private String id;
    private String model;
    private String object;
    private Object systemFingerprint;
    private ChatGptResponseUsage usage;
}
