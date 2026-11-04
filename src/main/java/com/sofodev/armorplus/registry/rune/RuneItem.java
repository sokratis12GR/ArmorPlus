package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.RegistryContext;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

/**
 * An armor, weapon or tool rune holding one buff (see {@link RuneData}), extracted in the Rune Altar or found in the world,
 * and socketed into equipment in an Anvil.
 */
public class RuneItem extends Item {

    private final RuneKind kind;

    public RuneItem(RuneKind kind) {
        super(RegistryContext.itemProperties().stacksTo(1).rarity(Rarity.RARE));
        this.kind = kind;
    }

    public RuneKind getKind() {
        return kind;
    }

    public static ItemStack create(RuneKind kind, String entry) {
        ItemStack stack = new ItemStack(switch (kind) {
            case ARMOR -> ModItems.ARMOR_RUNE.get();
            case WEAPON -> ModItems.WEAPON_RUNE.get();
            case TOOL -> ModItems.TOOL_RUNE.get();
        });
        stack.set(ModDataComponents.RUNE.get(), new RuneData(kind, entry));
        return stack;
    }

    @Override
    public Component getName(ItemStack stack) {
        RuneData rune = Runes.runeData(stack);
        BuffInstance buff = rune == null ? null : Runes.parse(rune);
        if (buff == null) return super.getName(stack);
        return Component.translatable(getDescriptionId() + ".named", buff.getTranslatedName().append(BuffNames.levelSuffix(buff)).withStyle(LIGHT_PURPLE));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return Runes.runeData(stack) != null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        RuneData rune = Runes.runeData(stack);
        if (rune == null) {
            tooltip.accept(translate(GRAY, "tooltip.armorplus.rune.empty"));
        } else {
            Set<EquipmentSlot> slots = Runes.runeSlots(rune);
            // runes of effects that belong to all four pieces fit anywhere, so only list real restrictions
            tooltip.accept(!slots.isEmpty() && slots.size() < 4
                    ? translate(GOLD, "tooltip.armorplus.rune.kind.armor_slot", BuffNames.slotNames(slots))
                    : translate(GOLD, "tooltip.armorplus.rune.kind." + kind.getSerializedName()));
            BuffInstance buff = Runes.resolve(rune);
            if (buff != null) {
                tooltip.accept(translate(GREEN, switch (kind) {
                    case ARMOR -> "tooltip.armorplus.provides";
                    case WEAPON -> "tooltip.armorplus.applies";
                    case TOOL -> "tooltip.armorplus.while_held";
                }));
                addBuffLines(tooltip, List.of(buff), kind == RuneKind.WEAPON ? ToolTipUtils::durationPrefix : b -> null);
                tooltip.accept(translate(GRAY, "tooltip.armorplus.rune.socket_hint"));
            } else {
                tooltip.accept(translate(DARK_GRAY, "tooltip.armorplus.rune.inactive", rune.buffId()));
            }
        }
        super.appendHoverText(stack, context, display, tooltip, flag);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}
