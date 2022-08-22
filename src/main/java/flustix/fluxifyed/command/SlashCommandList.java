package flustix.fluxifyed.command;

import flustix.fluxifyed.Main;
import flustix.fluxifyed.commands.*;
import flustix.fluxifyed.utils.messages.MessageUtils;
import flustix.fluxifyed.utils.permissions.PermissionUtils;
import flustix.fluxifyed.utils.slash.SlashCommandUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlashCommandList {
    private static final TreeMap<String, SlashCommand> commands = new TreeMap<>();

    public static void initializeList() {
        addCommand(new PingSlashCommand());
        addCommand(new ServerInfoSlashCommand());
        addCommand(new RedditSlashCommand());
    }

    private static void addCommand(SlashCommand command) {
        commands.put(command.getName(), command);
        Main.LOGGER.info("Loaded slash command " + command.getName() + "!");
    }

    public static void registerCommands(ReadyEvent event) {
        List<CommandData> slashCommandList = new ArrayList<>();

        commands.forEach((name, command) -> {
            SlashCommandData commandData = Commands.slash(name, command.getDescription());
            commandData.addOptions(command.getOptions());
            slashCommandList.add(commandData);
        });

        event.getJDA().updateCommands().addCommands(slashCommandList).complete();
    }

    public static void execute(SlashCommandInteractionEvent interaction) {
        if (commands.containsKey(interaction.getName())) {
            SlashCommand command = commands.get(interaction.getName());
            if (PermissionUtils.checkLevel(interaction.getMember(), command.getPermissionLevel())) {
                command.execute(interaction);
            } else {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("You don't have enough permissions to run this command!")
                        .addField("Required Permissions", PermissionUtils.getDescription(command.getPermissionLevel()), false)
                        .setColor(0xFF5555);

                SlashCommandUtils.reply(interaction, embed.build());
            }
        } else
            interaction.reply("This command is not implemented yet.").complete();
    }
}
