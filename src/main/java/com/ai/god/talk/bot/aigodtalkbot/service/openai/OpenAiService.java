package com.ai.god.talk.bot.aigodtalkbot.service.openai;

import com.ai.god.talk.bot.aigodtalkbot.model.openai.request.ChatGptRequest;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.response.ChatGptResponse;

import java.util.Optional;

public interface OpenAiService {
    Optional<ChatGptResponse> sendRequest(ChatGptRequest request);
}
