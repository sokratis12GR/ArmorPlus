/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.dev;

import com.sofodev.armorplus.items.base.ItemBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

public class ItemSpawnStructure extends ItemBase {

    private final String itemName;
    private final ResourceLocation structureLocation;

    public ItemSpawnStructure(String itemName, ResourceLocation structureLocation, Properties properties) {
        super(properties);
        this.itemName = itemName;
        this.structureLocation = structureLocation;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        switch (itemName) {
            case "ender_dungeon_floor_1_spawn_item":
                return new TextComponentString("Ender Dungeon Floor 1 (ArmorPlus) Structure Spawn");
            case "tower_spawn_item":
                return new TextComponentString("Nether Tower (ArmorPlus) Structure Spawn");
            default:
                return new TextComponentString("Invalid Item");
        }
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        if (!(context.getWorld() instanceof WorldServer)) return EnumActionResult.PASS;

        WorldServer serverworld = (WorldServer) context.getWorld();

        PlacementSettings settings = new PlacementSettings();
        if (context.getFace() == EnumFacing.EAST) {
            settings.setRotation(Rotation.NONE);
        } else if (context.getFace() == EnumFacing.SOUTH) {
            settings.setRotation(Rotation.CLOCKWISE_90);
        } else if (context.getFace() == EnumFacing.WEST) {
            settings.setRotation(Rotation.CLOCKWISE_180);
        } else if (context.getFace() == EnumFacing.NORTH) {
            settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
        }
        Template template = serverworld.getSaveHandler().getStructureTemplateManager().getTemplate(structureLocation);
        template.setAuthor("sokratis12GR");
        template.addBlocksToWorld(serverworld, context.getPos(), settings);
        return EnumActionResult.SUCCESS;
    }

}
