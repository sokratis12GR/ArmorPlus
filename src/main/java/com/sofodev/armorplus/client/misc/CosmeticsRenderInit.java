/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.misc;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.utils.RenderEntityHelper;
import com.sofodev.armorplus.common.entity.dungeon.skeletalking.EntitySkeletalKing;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.Objects.requireNonNull;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ArmorPlus.MODID)
public class CosmeticsRenderInit {

    private static final HashMap<String, RenderCosmetics> COSMETICS_FOR_PEOPLE_LIST = new HashMap<>();

    public CosmeticsRenderInit() {
        new ThreadCosmeticsFetcher();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void parse(Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            String[] values = properties.getProperty(key).split("@");
            if (values.length > 0) {
                String registryName = values[0];

                int meta;
                try {
                    meta = Integer.parseInt(values[1]);
                } catch (Exception e) {
                    meta = 0;
                }

                ItemStack stack = ItemStack.EMPTY;
                //Get the Item from the String
                ResourceLocation resLoc = new ResourceLocation(registryName);
                if (Item.REGISTRY.containsKey(resLoc)) {
                    stack = getItemStack(requireNonNull(ForgeRegistries.ITEMS.getValue(resLoc)), meta);
                } else if (Block.REGISTRY.containsKey(resLoc)) {
                    stack = getItemStack(requireNonNull(ForgeRegistries.BLOCKS.getValue(resLoc)), meta);
                }

                //Add a new Special Renderer to the list
                if (!stack.isEmpty()) {
                    COSMETICS_FOR_PEOPLE_LIST.put(key, new RenderCosmetics(stack));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    @SideOnly(Side.CLIENT)
    public static void onPlayerRender(RenderPlayerEvent.Pre event) {
        if (!COSMETICS_FOR_PEOPLE_LIST.isEmpty()) {
            for (Map.Entry<String, RenderCosmetics> entry : COSMETICS_FOR_PEOPLE_LIST.entrySet()) {
                String playerName = event.getEntityPlayer().getName();
                if (entry.getKey() != null && entry.getKey().equalsIgnoreCase(playerName)) {
                    if (playerName.equals("sokratis12GR")) {
                        EntityPlayer player = event.getEntityPlayer();
                        boolean canShow = player.isInvisible() || !player.isWearing(EnumPlayerModelParts.CAPE) || player.isPotionActive(MobEffects.INVISIBILITY);
                        if (canShow) {
                            return;
                        }
                        EntitySkeletalKing king = new EntitySkeletalKing(Minecraft.getMinecraft().world);
                        RenderEntityHelper.renderBoss(king, event.getEntityPlayer(), event.getPartialRenderTick());
                    }
                    //Render the special Item/Block
                    RenderCosmetics.render(entry.getValue(), event.getEntityPlayer(), event.getPartialRenderTick());
                    break;
                }
            }
        }
    }

}