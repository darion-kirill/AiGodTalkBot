package com.ai.god.talk.bot.aigodtalkbot.service.command.impl;

import com.ai.god.talk.bot.aigodtalkbot.model.bot.BotCommandType;
import com.ai.god.talk.bot.aigodtalkbot.service.command.BotCommand;
import org.springframework.stereotype.Component;

@Component
public class StartBotCommand implements BotCommand {

    public static final BotCommandType COMMAND_TYPE = BotCommandType.START;

    @Override
    public String execute(String sourceMessage) {
        return "Hello! Just text something for AI God";
    }

    @Override
    public boolean isSupported(String commandName) {
        return COMMAND_TYPE.getCommandName().equals(commandName);
    }
}
