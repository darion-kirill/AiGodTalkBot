package com.ai.god.talk.bot.aigodtalkbot.service.openai;

import com.ai.god.talk.bot.aigodtalkbot.config.OpenAiConfig;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.request.ChatGptRequest;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.response.ChatGptResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class WebClientOpenAiService implements OpenAiService {
    private final OpenAiConfig openAiConfig;
    private final WebClient webClient;

    @Override
    public Optional<ChatGptResponse> sendRequest(ChatGptRequest request) {
        var response = webClient.post()
                .uri(openAiConfig.getUrl())
                .header("Authorization", "Bearer " + openAiConfig.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatGptResponse.class)
                .block();


        if (Objects.isNull(response)) {
            return Optional.empty();
        }

        return Optional.of(response);
    }
}
