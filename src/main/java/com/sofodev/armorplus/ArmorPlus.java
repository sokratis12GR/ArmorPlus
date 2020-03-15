package com.sofodev.armorplus;

import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.APToolProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sofodev.armorplus.utils.Utils.setName;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.14.4-13.0.0.1";

    private static ArmorPlus instance;

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;

    public static final ItemGroup AP_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("armors")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CHESTPLATES[7].get());
        }
    };
    public static final ItemGroup AP_ITEM_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("items")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(APItems.INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final ItemGroup AP_BLOCK_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("blocks")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.OBSIDIAN);
        }
    };
    public static final ItemGroup AP_WEAPON_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("weapons")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_AXE);
        }
    };

    public ArmorPlus() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ArmorPlus getInstance() {
        return instance;
    }
}