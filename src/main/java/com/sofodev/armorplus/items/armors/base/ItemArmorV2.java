/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.caps.abilities.ISpecialItem;
import com.sofodev.armorplus.caps.abilities.MaterialType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.caps.abilities.AbilityData.*;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.ABILITY_REGISTRY;
import static com.sofodev.armorplus.client.utils.ToolTipUtils.*;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorV2 extends ItemPlaceholderArmor implements ISpecialItem {


    public final MaterialType material;
    public EntityEquipmentSlot slot;

    public ItemArmorV2(MaterialType material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), slot, new Properties().defaultMaxDamage(1).group(ArmorPlus.tabArmorplus));
        this.material = material;
        this.slot = slot;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        IAbilityHandler handler = getHandler(stack);
        if (isKeyDown() && handler != null) {
            tooltip.add(1, new TextComponentString(updateLimitToolTip(stack)));
            tooltip.add(2, new TextComponentString(""));
            this.addTTAbility(tooltip, stack, ABILITY_REGISTRY.getValues());
            addToolTip(tooltip, "", "__________");
        } else {
            showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

    private void addTTAbility(List<ITextComponent> tooltip, ItemStack stack, Collection<AbilityData> dataList) {
        for (AbilityData data : dataList) {
            addTTAbility(tooltip, stack, data);
        }
    }

    private void addTTAbility(List<ITextComponent> tooltip, ItemStack stack, AbilityData data) {
        IAbilityHandler handler = getHandler(stack);
        if (handler == null) {
            return;
        }

        String ability = getTTAbility(handler, stack, data);
        if (!ability.equals("")) {
            tooltip.add(new TextComponentString(ability));
        }
        if (isNotNull(tooltip)) {
            tooltip.set(2, new TextComponentString(notSpecial(stack)));
        }
    }

    private boolean isNotNull(List<ITextComponent> tooltip) {
        return tooltip.get(2) != null;
    }

    private String notSpecial(ItemStack stack) {
        IAbilityHandler handler = getHandler(stack);
        if (handler != null) {
            boolean notSpecial = !canProvideAny(stack) && !hasAbilities(handler);
            return notSpecial ? ITALIC + new TextComponentTranslation("info.armorplus.items.ability.not_found").getFormattedText() : "";
        }
        return "";
    }

    public static boolean canProvideAny(ItemStack stack) {
        List<Boolean> array = ABILITY_REGISTRY.getValues().stream().map(data -> canProvide(stack, data)).collect(Collectors.toList());
        for (boolean canProvide : array) {
            if (canProvide) {
                return true;
            }
        }
        return false;
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
        if (handler == null) {
            return "";
        }
        return GOLD + new TextComponentTranslation("info.armorplus.items.ability.display_limit", handler.getAbilities().size(), handler.getLimit()).getFormattedText();
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        provideArmorAbilities(stack, world, player);
    }

    /**
     * Capabilities
     */
    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        AbilityDataHandler.Provider provider = new AbilityDataHandler.Provider();
        IAbilityHandler handler = getHandler(stack);
        if (handler != null && material != null) {
            handler.setLimit(material.getLimit());
        }
        return provider;
    }

    @Nullable
    @Override
    public NBTTagCompound getShareTag(ItemStack stack) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.put("super", super.getShareTag(stack));
        tag.put("capability", CAPABILITY_ABILITIES.writeNBT(getHandler(stack), null));
        return tag;
    }


    @Override
    public void readShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        super.readShareTag(stack, nbt.getCompound("super"));
        CAPABILITY_ABILITIES.readNBT(getHandler(stack), null, nbt.get("capability"));
    }

    @Override
    public boolean isSpecial(ItemStack stack) {
        return true;
    }

    @Override
    public MaterialType getMaterial() {
        return this.material;
    }

    @Override
    public boolean hasSpecialMaterial() {
        return true;
    }

    @Override
    public EntityEquipmentSlot getSlot() {
        return this.slot;
    }
}
