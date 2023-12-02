
package com.ai.god.talk.bot.aigodtalkbot.model.openai.response;

import lombok.Data;

@Data
public class ChatGptResponseUsage {
    private Long completionTokens;
    private Long promptTokens;
    private Long totalTokens;
}
