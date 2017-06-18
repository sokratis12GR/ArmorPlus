package net.thedragonteam.armorplus.compat.tinkers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import gnu.trove.set.hash.TLinkedHashSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.compat.tinkers.parts.APItemCore;
import net.thedragonteam.armorplus.compat.tinkers.parts.APItemPart;
import net.thedragonteam.thedragonlib.util.LogHelper;
import org.apache.commons.lang3.tuple.Pair;
import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.*;
import slimeknights.tconstruct.tools.AbstractToolPulse;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by sokratis12GR on 5/9/2017.
 */
public class TinkersUtils extends AbstractToolPulse{

    /**
     * This set contains all known tools
     */
    private static final Set<APItemCore> items = new TLinkedHashSet<>();
    private static final Set<IToolPart> itemParts = new TLinkedHashSet<>();
    private static final Set<APItemCore> toolStationCrafting = Sets.newLinkedHashSet();
    private static final Set<APItemCore> toolForgeCrafting = Sets.newLinkedHashSet();
    private static final List<ItemStack> stencilTableCrafting = Lists.newLinkedList();
    private static final Set<Item> patternItems = Sets.newHashSet();
    private static final Set<Item> castItems = Sets.newHashSet();
    private static List<Pair<Item, APItemPart>> itemPartPatterns = Lists.newLinkedList();
    private static Shard shardItem;

    /**
     * Register a tool, making it known to tinkers' systems.
     * All toolparts used to craft the tool will be registered as well.
     */
    public static void registerItem(APItemCore tool) {
        items.add(tool);

        for (PartMaterialType pmt : tool.getRequiredComponents()) {
            for (IToolPart tp : pmt.getPossibleParts()) {
                registerItemPart(tp);
            }
        }
    }

    public static Set<APItemCore> getTools() {
        return ImmutableSet.copyOf(items);
    }

    /**
     * Used for the sharpening kit. Allows to register a toolpart that is not part of a tool.
     */
    public static void registerItemPart(IToolPart part) {
        itemParts.add(part);
        if (part instanceof Item) {
            if (part.canBeCrafted()) {
                addPatternForItem((Item) part);
            }
            if (part.canBeCasted()) {
                addCastForItem((Item) part);
            }
        }
    }

    public static Set<IToolPart> getItemParts() {
        return ImmutableSet.copyOf(itemParts);
    }

    /**
     * Adds a tool to the Crafting UI of both the Tool Station as well as the Tool Forge
     */
    public static void registerItemCrafting(APItemCore tool) {
        registerToolStationCrafting(tool);
        registerToolForgeCrafting(tool);
    }

    /**
     * Adds a tool to the Crafting UI of the Tool Station
     */
    public static void registerToolStationCrafting(APItemCore tool) {
        toolStationCrafting.add(tool);
    }

    public static Set<APItemCore> getToolStationCrafting() {
        return ImmutableSet.copyOf(toolStationCrafting);
    }

    /**
     * Adds a tool to the Crafting UI of the Tool Forge
     */
    public static void registerToolForgeCrafting(APItemCore tool) {
        toolForgeCrafting.add(tool);
    }

    public static Set<APItemCore> getToolForgeCrafting() {
        return ImmutableSet.copyOf(toolForgeCrafting);
    }

    /**
     * Adds a new pattern to craft to the stenciltable. NBT sensitive. Has to be a Pattern.
     */
    public static void registerStencilTableCrafting(ItemStack stencil) {
        if (!(stencil.getItem() instanceof IPattern)) {
            LogHelper.INSTANCE.error(String.format(
                    "Stencil Table Crafting has to be a pattern (%s)", stencil.toString()));
            return;
        }
        stencilTableCrafting.add(stencil);
    }

    public static List<ItemStack> getStencilTableCrafting() {
        return ImmutableList.copyOf(stencilTableCrafting);
    }

    public static void setShardItem(Shard shard) {
        if (shard == null) return;
        shardItem = shard;
    }

    public static Shard getShard() {
        return shardItem;
    }

    public static ItemStack getShard(Material material) {
        ItemStack out = material.getShard();
        if (out.isEmpty()) {
            out = shardItem.getItemstackWithMaterial(material);
        }
        return out;
    }

    /**
     * Registers a pattern for the given item
     */
    public static void addPatternForItem(Item item) {
        patternItems.add(item);
    }

    /**
     * Registers a cast for the given item
     */
    public static void addCastForItem(Item item) {
        castItems.add(item);
    }

    /**
     * All items that have a pattern
     */
    public static Collection<Item> getPatternItems() {
        return ImmutableList.copyOf(patternItems);
    }

    /**
     * All items that have a cast
     */
    public static Collection<Item> getCastItems() {
        return ImmutableList.copyOf(castItems);
    }

    public static <T extends APItemCore> T registerItem(T item, String unlocName) {
        items.add(item);
        return TinkerPulse.registerItem(item, unlocName);
    }

    public static APItemPart registerItemPart(APItemPart part, String name) {
        return registerItemPart(part, name, TinkerTools.pattern);
    }

    public static <T extends Item & IPattern> APItemPart registerItemPart(APItemPart part, String name, T pattern) {
        APItemPart ret = registerItem(part, name);

        if(pattern != null) {
            itemPartPatterns.add(Pair.of(pattern, ret));
        }

        itemParts.add(ret);

        return ret;
    }
}
