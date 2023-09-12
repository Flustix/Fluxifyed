﻿using Fluxifyed.Commands;

namespace Fluxifyed.Modules.Economy.Commands.Management;

public class EconomyItemsCommand : ISlashCommandGroup
{
    public string Name => "items";
    public string Description => "Manage the items in the shop.";
    public int Depth => 2;

    public IEnumerable<ISlashCommand> Subcommands => new ISlashCommand[]
    {
        new EconomyItemsAddCommand(),
        new EconomyItemsRemoveCommand(),
        new EconomyItemsListCommand()
    };
}
