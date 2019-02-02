/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.items.armors.Material;
import com.sofodev.armorplus.util.EnumHelperUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.caps.abilities.AbilityData.*;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.client.utils.ToolTipUtils.*;
import static com.sofodev.armorplus.util.EnumTiers.TIER_3;
import static com.sofodev.armorplus.util.PotionUtils.PotionType.GOOD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.PotionUtils.getPotion;
import static com.sofodev.armorplus.util.Utils.setName;
import static com.sofodev.armorplus.util.Utils.setRL;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.EnumHand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.*;
import static net.minecraft.util.text.event.ClickEvent.Action.RUN_COMMAND;
import static net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorV2 extends ItemArmor implements IModdedItem {

    public static final ArmorMaterial prototype = EnumHelperUtil.addArmorMaterial("PROTOTYPE", "coal", 100,
        new int[]{2, 3, 4, 3}, 0, TIER_3);

    public final Material material;

    public EntityEquipmentSlot slot;
    private EnumAction wear = EnumHelperUtil.addAction("WEAR");

    public ItemArmorV2(Material material, EntityEquipmentSlot slot) {
        super(prototype, 0, slot);
        this.material = material;
        this.setMaxStackSize(1);
        this.createPieces(slot, material.name().toLowerCase() + "_prototype");
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    private void createPieces(EntityEquipmentSlot slot, String name) {
        String piece = name + (slot == HEAD ? "_helmet" : slot == CHEST ? "_chestplate" : slot == LEGS ? "_leggings" : slot == FEET ? "_boots" : "");
        this.setRegistryName(setRL(piece));
        this.setTranslationKey(setName(piece));
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        IAbilityHandler handler = getHandler(stack);
        if (isKeyDown() && handler != null) {
            tooltip.add(1, updateLimitToolTip(stack));
            tooltip.add(2, "");
            this.addTTAbility(tooltip, stack, AbilityData.values());
            addToolTip(tooltip, "", "__________");
        } else {
            showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

    private void addTTAbility(List<String> tooltip, ItemStack stack, AbilityData... dataList) {
        for (AbilityData data : dataList) {
            addTTAbility(tooltip, stack, data);
        }
    }

    private void addTTAbility(List<String> tooltip, ItemStack stack, AbilityData data) {
        IAbilityHandler handler = getHandler(stack);
        if (handler == null) {
            return;
        }
        // boolean canProvide = canProvide(stack, data.getID());
        //  boolean flag = contains(handler, data.getID()) && canProvide;
        String ability = getTTAbility(handler, stack, data);
        if (!ability.equals("")) {
            tooltip.add(ability);
        }
        if (isNotNull(tooltip)) {
            tooltip.set(2, notSpecial(stack));
        }
    }

    private boolean isNotNull(List<String> tooltip) {
        return tooltip.get(2) != null;
    }

    private String notSpecial(ItemStack stack) {
        IAbilityHandler handler = getHandler(stack);
        if (handler != null) {
            return !hasAbilities(handler) ? ITALIC + "No unlocked abilities" : "";
        } else return "";
    }

    private String getTTAbility(IAbilityHandler handler, ItemStack stack, AbilityData data) {
        String id = data.getSafeName();
        String name = "- " + data.getName();
        boolean canProvide = canProvide(stack, id);
        boolean flag = false;
        if (handler != null) {
            flag = contains(handler, id) && canProvide;
        }
        if (flag) {
            return DARK_GREEN + name;
        } else if (!canProvide) {
            return "";
        } else {
            return DARK_GRAY + name;
        }
    }

    private String updateLimitToolTip(ItemStack stack) {
        IAbilityHandler handler = getHandler(stack);
        return format("%sAbilities %d/%d", GOLD, handler.getAbilities().size(), handler.getLimit());
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel("prototype");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        IAbilityHandler handler = getHandler(stack);
        if (handler != null && hasAbilities(handler)) {
            ArrayList<AbilityData> potionList = stream(AbilityData.values()).filter(AbilityData::isPotion).collect(Collectors.toCollection(ArrayList::new));
            applyAbility(player, stack, potionList);
            for (EntityEquipmentSlot equipmentSlot : FLIGHT.getSlots()) {
                if (this.getEquipmentSlot() == equipmentSlot && contains(handler, FLIGHT.getSafeName()) || player.capabilities.isCreativeMode || player.isSpectator()) {
                    player.capabilities.allowFlying = true;
                } else if (!contains(handler, FLIGHT.getSafeName()) && !(player.inventory.armorInventory.get(2).getItem() instanceof ItemArmorV2)) {
                    player.capabilities.isFlying = false;
                    player.capabilities.allowFlying = false;
                }
            }
        }

    }

    private void applyAbility(EntityPlayer player, ItemStack stack, List<AbilityData> dataList) {
        for (AbilityData data : dataList) {
            applyAbility(player, stack, data);
        }
    }

    private void applyAbility(EntityPlayer player, ItemStack stack, AbilityData data) {
        IAbilityHandler handler = getHandler(stack);
        String id = data.getSafeName();
        if (handler != null && data.isPotion() && canProvide(stack, id) && contains(handler, id)) {
            addPotion(player, getPotion(data.getSafeName()), 0, GOOD);
        }
    }

    /**
     * Capabilities
     */
    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        AbilityDataHandler.Provider provider = new AbilityDataHandler.Provider();
        IAbilityHandler handler = provider.getCapability(CAPABILITY_ABILITIES, null);
        if (handler != null && material != null) {
            handler.setLimit(material.getLimit());
        }
        return provider;
    }


    //NBTShareTag
    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("super", super.getNBTShareTag(stack));
        tag.setTag("capability", CAPABILITY_ABILITIES.writeNBT(getHandler(stack), null));
        return tag;
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        super.readNBTShareTag(stack, nbt.getCompoundTag("super"));
        CAPABILITY_ABILITIES.readNBT(getHandler(stack), null, nbt.getTag("capability"));
    }

    //This is all WIP, except things to change/added/removed or disabled.
    @Override
    public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
        IAbilityHandler handler = getHandler(stack);
        if (!entity.world.isRemote && handler != null && hasRoomForAbilities(handler) && !contains(handler, NIGHT_VISION.getSafeName())) {
            ItemStack coalStack = new ItemStack(Items.COAL, 64);
            if (entity instanceof EntityPlayer && entity.swingingHand == MAIN_HAND) {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.inventory.hasItemStack(coalStack)) {
                    ClickEvent setAbility = new ClickEvent(RUN_COMMAND, "/armorplus abilities add 1 consume");
                    HoverEvent hoverInfo = new HoverEvent(SHOW_TEXT, new TextComponentTranslation("info.abilities.apply.text"));
                    Style apply = new Style().setColor(GREEN).setUnderlined(false).setClickEvent(setAbility).setHoverEvent(hoverInfo);
                    ITextComponent acquiredAbility = new TextComponentTranslation("info.abilities.cost", coalStack.getCount()
                    ).appendSibling(new TextComponentTranslation("info.abilities.apply.button").setStyle(apply));
                    player.sendStatusMessage(acquiredAbility, false);
                } else {
                    player.sendStatusMessage(new TextComponentTranslation("info.abilities.cost.missing"), false);
                }
            }
        }
        return super.onEntitySwing(entity, stack);
    }

    /////////////////////

    /**
     * Special Abilities
     */
    /////////////////////
    public static boolean hasAbilities(IAbilityHandler handler) {
        return !handler.getAbilities().isEmpty();
    }

    public static boolean hasRoomForAbilities(IAbilityHandler handler) {
        return handler.getAbilities().size() < handler.getLimit();
    }

    // public static boolean canProvideAbility(ItemStack stack, int id) {
    //     ItemArmorV2 armor = (ItemArmorV2) stack.getItem();
    //     EntityEquipmentSlot i = armor.getEquipmentSlot();
    //     if (i == HEAD) {
    //         return id == 1 || id == 2;
    //     } else if (i == CHEST) {
    //         return id == 3 || id == 4 || id == 5 || id == 9 || id == 100;
    //     } else if (i == LEGS) {
    //         return id == 8;
    //     } else if (i == FEET) {
    //         return id == 6 || id == 7;
    //     }
    //     return false;
    // }

    public static boolean contains(IAbilityHandler handler, String id) {
        List<String> list = handler.getAbilities();
        return list.contains(id);
    }
}
