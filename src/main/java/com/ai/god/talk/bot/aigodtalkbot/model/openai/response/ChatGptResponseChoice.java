
package com.ai.god.talk.bot.aigodtalkbot.model.openai.response;

import lombok.Data;

@Data
public class ChatGptResponseChoice {
    private String finishReason;
    private Long index;
    private ChatGptResponseMessage message;
}
