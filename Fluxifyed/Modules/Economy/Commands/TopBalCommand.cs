﻿using DSharpPlus.Entities;
using Fluxifyed.Commands;
using Fluxifyed.Components.Message;
using Fluxifyed.Constants;
using Fluxifyed.Database;
using Fluxifyed.Modules.Economy.Utils;
using Fluxifyed.Utils;

namespace Fluxifyed.Modules.Economy.Commands; 

public class TopBalCommand : ISlashCommand {
    public string Name => "top-bal";
    public string Description => "Shows the top 10 richest users";
    
    public void Handle(DiscordInteraction interaction) {
        RealmAccess.Run(realm => {
            if (interaction.Channel.IsPrivate) return;
            var users = EcoUtils.GetTopBalUsers(realm, interaction.Guild.Id.ToString(), 10);
            
            var description = string.Join("\n", users.Select((user, index) => $"#{index + 1} <@{user.UserId}> - {user.Balance}:coin:"));
            if (users.Count == 0) description = "Nothing here...";
            
            interaction.ReplyEmbed(new CustomEmbed {
                Title = $"{interaction.Guild.Name} - Economy Leaderboard",
                ThumbnailUrl = interaction.Guild.IconUrl,
                Color = Colors.Random,
                Description = description
            });
        });
    }
}