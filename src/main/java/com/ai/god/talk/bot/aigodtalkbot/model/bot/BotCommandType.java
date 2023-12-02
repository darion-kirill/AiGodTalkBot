package com.ai.god.talk.bot.aigodtalkbot.model.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotCommandType {
    START("/start");

    private final String commandName;
}
