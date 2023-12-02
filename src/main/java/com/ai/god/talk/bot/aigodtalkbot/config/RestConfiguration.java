package com.ai.god.talk.bot.aigodtalkbot.config;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.DefaultAuthenticationStrategy;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
        var restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(ProxyBotConfig proxyBotConfig) {
        var credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyBotConfig.getHost(), proxyBotConfig.getPort()),
                new UsernamePasswordCredentials(proxyBotConfig.getUser(), proxyBotConfig.getPassword().toCharArray())
        );

        var clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();
        clientBuilder.setProxy(new HttpHost(proxyBotConfig.getHost(), proxyBotConfig.getPort()));
        clientBuilder.setDefaultCredentialsProvider(credsProvider);
        clientBuilder.setProxyAuthenticationStrategy(DefaultAuthenticationStrategy.INSTANCE);

        final CloseableHttpClient client = clientBuilder.build();

        var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);

        return factory;
    }

    @Bean
    public ReactorClientHttpConnector reactorClientHttpConnector(ProxyBotConfig proxyBotConfig) {
        HttpClient httpClient =
                HttpClient.create()
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.HTTP)
                                .host(proxyBotConfig.getHost())
                                .port(proxyBotConfig.getPort())
                                .username(proxyBotConfig.getUser())
                                .password((s) -> proxyBotConfig.getPassword())
                        );

        return new ReactorClientHttpConnector(httpClient);
    }


    @Bean
    public WebClient webClient(ReactorClientHttpConnector connector) {
        return WebClient.builder().clientConnector(connector).build();
    }
}
