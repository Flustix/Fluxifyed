﻿using DSharpPlus;
using DSharpPlus.Entities;
using Newtonsoft.Json;
using Snowly.Commands;
using Snowly.Components.Message;
using Snowly.Config;
using Snowly.Modules.Utility.Commands.Abstract;
using Snowly.Utils;

namespace Snowly.Modules.Utility.Commands;

public class SayJsonCommand : AbstractSayCommand {
    public override string Name => "say-json";
    public override string Description => "Sends a json message in a channel.";

    protected override string OptionName => "json";
    protected override string OptionDescription => "The message to send. (https://flustix.flux.moe/snowly for a generator)";

    protected override DiscordMessageBuilder CreateMessage(string content)
    {
        var message = JsonConvert.DeserializeObject<CustomMessage>(content);
        var msg = new DiscordMessageBuilder()
            .WithContent(message.Content)
            .AddEmbed(message.ToEmbed());

        return msg;
    }
}
