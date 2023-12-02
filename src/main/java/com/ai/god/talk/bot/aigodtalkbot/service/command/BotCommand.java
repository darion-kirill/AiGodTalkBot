package com.ai.god.talk.bot.aigodtalkbot.service.command;

import com.ai.god.talk.bot.aigodtalkbot.model.bot.BotCommandType;
import org.springframework.lang.Nullable;

public interface BotCommand {
    @Nullable String execute(String sourceMessage);
    boolean isSupported(String commandName);
}
