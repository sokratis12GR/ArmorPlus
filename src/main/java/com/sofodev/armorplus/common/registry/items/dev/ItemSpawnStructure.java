/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.dev;

import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class ItemSpawnStructure extends ItemBase {

    private final String itemName;
    private final ResourceLocation structureLocation;

    public ItemSpawnStructure(String itemName, ResourceLocation structureLocation) {
        super(itemName);
        this.itemName = itemName;
        this.structureLocation = structureLocation;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        switch (itemName) {
            case "ender_dungeon_floor_1_spawn_item":
                return "Structure Spawn: Ender Dungeon Floor 1 (ArmorPlus)";
            case "tower_spawn_item":
                return "Structure Spawn: Nether Tower (ArmorPlus)";
            case "dungeon_placer_1":
                return "Structure Spawn: Skeletal King Dungeon (ArmorPlus)";
            case "dungeon_placer_2":
                return "Structure Spawn: Guardian Overlord Dungeon (ArmorPlus)";
            case "dungeon_placer_3":
                return "Structure Spawn: Demonic Dragon Dungeon (ArmorPlus)";
            default:
                return "Invalid Item";
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!(world instanceof WorldServer)) return EnumActionResult.PASS;

        WorldServer serverworld = (WorldServer) world;

        PlacementSettings settings = new PlacementSettings();
        if (facing == EnumFacing.EAST) {
            settings.setRotation(Rotation.NONE);
        } else if (facing == EnumFacing.SOUTH) {
            settings.setRotation(Rotation.CLOCKWISE_90);
        } else if (facing == EnumFacing.WEST) {
            settings.setRotation(Rotation.CLOCKWISE_180);
        } else if (facing == EnumFacing.NORTH) {
            settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
        }
        MinecraftServer server = serverworld.getMinecraftServer();
        Template template = serverworld.getSaveHandler().getStructureTemplateManager().getTemplate(server, structureLocation);
        template.setAuthor("sokratis12GR");
        template.addBlocksToWorld(serverworld, pos, settings);
        return EnumActionResult.SUCCESS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        super.initModel(setRL("tower_spawn_item"), 0);
    }
}
