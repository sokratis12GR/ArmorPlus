/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.caps.abilities.Material;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.util.EnumHelperUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.caps.abilities.AbilityData.*;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.ABILITY_REGISTRY;
import static com.sofodev.armorplus.client.utils.ToolTipUtils.*;
import static com.sofodev.armorplus.util.Utils.setName;
import static com.sofodev.armorplus.util.Utils.setRL;
import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorV2 extends ItemArmor implements IModdedItem {


    public final Material material;
    public EntityEquipmentSlot slot;
    private EnumAction wear = EnumHelperUtil.addAction("WEAR");

    public ItemArmorV2(Material material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), 0, slot);
        this.material = material;
        this.slot = slot;
        this.setMaxStackSize(1);
        this.createPieces(slot, material.name().toLowerCase() + "_prototype");
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    private void createPieces(EntityEquipmentSlot slot, String name) {
        String piece;
        if (slot == HEAD) {
            piece = name + "_helmet";
        } else if (slot == CHEST) {
            piece = name + "_chestplate";
        } else if (slot == LEGS) {
            piece = name + "_leggings";
        } else if (slot == FEET) {
            piece = name + "_boots";
        } else {
            piece = name + "";
        }
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
            this.addTTAbility(tooltip, stack, ABILITY_REGISTRY.getValuesCollection());
            addToolTip(tooltip, "", "__________");
        } else {
            showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

    private void addTTAbility(List<String> tooltip, ItemStack stack, Collection<AbilityData> dataList) {
        for (AbilityData data : dataList) {
            addTTAbility(tooltip, stack, data);
        }
    }

    private void addTTAbility(List<String> tooltip, ItemStack stack, AbilityData data) {
        IAbilityHandler handler = getHandler(stack);
        if (handler == null) {
            return;
        }

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
        }
        return "";
    }

    /**
     * @param handler the ability handler
     * @param stack   the stack that we will be using
     * @param data    the ability that will be checked
     * @return either an empty string or the ability's name represented by a color which describes its status
     * DARK_GREEN - Available (Obtained), RED - Conflicting/Cannot be added, DARK_GRAY - Available (Not obtained)
     */
    private String getTTAbility(IAbilityHandler handler, ItemStack stack, AbilityData data) {
        ResourceLocation id = data.getRegistryName();
        String name = "- " + data.getName();
        boolean canProvide = canProvide(stack, id);
        boolean flag;
        if (handler == null) {
            return "";
        }
        flag = contains(handler, id) && canProvide;
        if (flag) {
            return DARK_GREEN + name;
        } else if (!canProvide) {
            return "";
        } else if (!hasRoomForAbilities(handler)) {
            return RED + name;
        }
        return DARK_GRAY + name;
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
            for (AbilityData data : handler.getAbilities().stream().filter(AbilityData::isPotion).collect(Collectors.toList())) {
                if (canProvide(stack, data) && contains(handler, data) && data.isPotion()) {
                    data.applyPotionToPlayer(player);
                }
            }
            for (AbilityData data : handler.getAbilities()) {
                if (canProvide(stack, data) && contains(handler, data)) {
                    data.onArmorTick(world, player, stack);
                }
            }
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
}
