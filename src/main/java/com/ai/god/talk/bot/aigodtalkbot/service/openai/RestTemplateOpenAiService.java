package com.ai.god.talk.bot.aigodtalkbot.service.openai;

import com.ai.god.talk.bot.aigodtalkbot.config.OpenAiConfig;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.request.ChatGptRequest;
import com.ai.god.talk.bot.aigodtalkbot.model.openai.response.ChatGptResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplateOpenAiService implements OpenAiService {
    private final OpenAiConfig openAiConfig;
    private final RestTemplate restTemplate;

    @Override
    public Optional<ChatGptResponse> sendRequest(ChatGptRequest request) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiConfig.getToken());

        var entity = new HttpEntity<>(request, headers);
        var response = restTemplate.postForObject(openAiConfig.getUrl(), entity, ChatGptResponse.class);

        if (Objects.isNull(response)) {
            return Optional.empty();
        }

        return Optional.of(response);
    }
}
