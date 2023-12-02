package com.ai.god.talk.bot.aigodtalkbot.service.chat;

import com.ai.god.talk.bot.aigodtalkbot.config.OpenAiConfig;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.request.ChatGptRequest;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.request.ChatGtpRequestMessage;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.response.ChatGptResponse;
import com.ai.god.talk.bot.aigodtalkbot.service.openai.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {
    private final OpenAiService service;
    private final OpenAiConfig openAiConfig;

    @Nullable
    @Override
    public String sendMessage(String message) {
        var request = buildRequest(message);

        try {
            var result = service.sendRequest(request);
            return result.map(this::getMessage).orElse(null);
        } catch (Exception e) {
            log.error("Error while sending request to OpenAI", e);
        }

        return null;
    }

    private ChatGptRequest buildRequest(String message) {
        var requestMessage = new ChatGtpRequestMessage();
        requestMessage.setRole(openAiConfig.getRole());
        requestMessage.setContent(message);

        var request = new ChatGptRequest();
        request.setModel(openAiConfig.getModel());
        request.setTemperature(openAiConfig.getTemperature());
        request.setMessages(List.of(requestMessage));

        return request;
    }

    @Nullable
    private String getMessage(ChatGptResponse response) {
        if (CollectionUtils.isEmpty(response.getChoices())) {
            return null;
        }

        var responseChoice = response.getChoices().stream().findFirst().get();
        var responseMessage = responseChoice.getMessage();

        if (Objects.isNull(responseMessage)) {
            return null;
        }

        return responseMessage.getContent();
    }
}
