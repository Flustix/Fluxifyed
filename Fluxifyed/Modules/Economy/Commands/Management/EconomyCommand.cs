﻿using DSharpPlus;
using Fluxifyed.Commands;

namespace Fluxifyed.Modules.Economy.Commands.Management;

public class EconomyCommand : ISlashCommandGroup {
    public string Name => "economy";
    public string Description => "Manage the economy of your server.";
    public Permissions? Permission => Permissions.ManageGuild;

    public IEnumerable<ISlashCommand> Subcommands => new ISlashCommand[] {
        new EconomyConfigCommand(),
        new EconomyItemsCommand()
    };
}
