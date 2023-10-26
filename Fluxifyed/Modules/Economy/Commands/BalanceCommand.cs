﻿using DSharpPlus.Entities;
using Fluxifyed.Commands;
using Fluxifyed.Components.Message;
using Fluxifyed.Config;
using Fluxifyed.Constants;
using Fluxifyed.Database;
using Fluxifyed.Modules.Economy.Utils;
using Fluxifyed.Utils;

namespace Fluxifyed.Modules.Economy.Commands;

public class BalanceCommand : ISlashCommand {
    public string Name => "balance";
    public string Description => "Check your balance.";

    public void Handle(DiscordInteraction interaction) {
        if (interaction.Channel.IsPrivate) return;

        var user = EcoUtils.GetUser(interaction.Guild.Id, interaction.User.Id);
        var guild = Configs.GetGuildConfig(interaction.Guild.Id);

        interaction.ReplyEmbed(new CustomEmbed {
                Title = $"{FormatUtils.FormatName(interaction.User.GetNickname())} Balance",
                Fields = new List<CustomEmbedField> {
                    new() {
                        Name = $"{guild.CurrencySymbol} Balance",
                        Value = $"**{user.Balance}** {guild.CurrencyName}",
                        Inline = true
                    },
                    new() {
                        Name = ":star: Streak",
                        Value = $"**{user.ActualStreak}**",
                        Inline = true
                    },
                    new() {
                        Name = ":clock1: Next Daily",
                        Value = $"**{(user.TimeUntilDaily > 0 ? FormatUtils.FormatTime(user.TimeUntilDaily, false) : "Now")}**"
                    }
                },
                Color = Colors.Random
            }
        );
    }
}
