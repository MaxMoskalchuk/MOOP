package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.Enums.Commands;
import ua.univ.lab8.Settings.Settings;

import java.util.Map;

public final class CommandFactory {
    private CommandFactory() {}

    public static AgencyCommand createAndInitCommand(String commandStr, Map<String , Object> params) {
        Settings.infoLogger.info("Command would be created using CommandFactory");

        Commands commandEnumValue = Commands.valueOf(commandStr);
        try {
            AgencyCommand command = (AgencyCommand) commandEnumValue.getCommand();
            command.setData(params);
            return command;
        } catch (Exception e) {
            Settings.errorLogger.error("Error", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}