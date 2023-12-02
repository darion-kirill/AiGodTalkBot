package com.ai.god.talk.bot.aigodtalkbot.service.telegram;

import com.ai.god.talk.bot.aigodtalkbot.config.TelegramBotConfig;
import com.ai.god.talk.bot.aigodtalkbot.service.chat.ChatGptService;
import com.ai.god.talk.bot.aigodtalkbot.service.command.BotCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig botConfig;
    private final ChatGptService chatGptService;
    private final List<BotCommand> commands;

    @Autowired
    public TelegramBot(TelegramBotConfig botConfig, ChatGptService chatGptService, List<BotCommand> commands) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.chatGptService = chatGptService;
        this.commands = commands;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var incomeMessage = update.getMessage();

        if (Objects.isNull(incomeMessage)) {
            log.warn("Incoming message [{}] is empty", update);
            return;
        }

        var user = incomeMessage.getFrom();
        if (Objects.isNull(user)) {
            log.warn("Incoming message [{}] doesn't contain user", update);
            return;
        }

        long chatId = update.getMessage().getChatId();
        if (botConfig.getUsers().stream().noneMatch(userName -> userName.equals(user.getUserName()))) {
            sendMessage(chatId, "Sorry, you don't have permissions to use this bot");
            return;
        }

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            sendMessage(chatId, "Wtf? You need to text something");
            return;
        }

        String messageText = update.getMessage().getText();
        var command = findCommand(messageText);
        if (Objects.nonNull(command)) {
            var resultMessage = command.execute(messageText);
            sendMessage(chatId, resultMessage);
            return;
        }

        var resultMessage = chatGptService.sendMessage(messageText);
        sendMessage(chatId, resultMessage);
    }

    private void sendMessage(Long chatId, String textToSend){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setParseMode("Markdown");
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Failed to send message on telegram", e);
        }
    }

    @Nullable
    private BotCommand findCommand(String message) {
        return commands.stream()
                .filter(command -> command.isSupported(message))
                .findFirst()
                .orElse(null);
    }
}
