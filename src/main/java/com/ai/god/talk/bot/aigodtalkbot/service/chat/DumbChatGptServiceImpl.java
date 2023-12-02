package com.ai.god.talk.bot.aigodtalkbot.service.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DumbChatGptServiceImpl implements ChatGptService {

    @Nullable
    @Override
    public String sendMessage(String message) {
        return message + " response!";
    }
}
