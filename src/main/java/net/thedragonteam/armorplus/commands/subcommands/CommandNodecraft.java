package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.util.Arrays;

import static net.minecraft.util.text.TextFormatting.BLUE;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CommandNodecraft extends CommandSubBase {

    public CommandNodecraft() {
        super("nodecraft");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        ClickEvent promoLink = new ClickEvent(OPEN_URL, "https://nodecraft.com/r/thedragonteam");
        Style linkStyle = new Style().setColor(BLUE).setUnderlined(true).setClickEvent(promoLink);
        sendMessages(sender, formatText(TextFormatting.AQUA, "commands.armorplus.nodecraft.about.text"), formatText(
            "commands.armorplus.nodecraft.promo.start.text", ""
        ).appendSibling(formatText(
            "commands.armorplus.nodecraft.promo.middle.text"
        ).setStyle(linkStyle)).appendSibling(formatText(
            "commands.armorplus.nodecraft.promo.end.text", "30%")
        ));
    }

    private void sendMessages(ICommandSender sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(sender::sendMessage);
    }
}
