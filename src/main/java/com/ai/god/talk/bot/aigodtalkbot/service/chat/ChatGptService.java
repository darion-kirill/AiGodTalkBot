package com.ai.god.talk.bot.aigodtalkbot.service.chat;

import org.springframework.lang.Nullable;

public interface ChatGptService {

    @Nullable
    String sendMessage(String message);
}
