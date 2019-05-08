/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.base;

import com.sofodev.armorplus.common.registry.items.weapons.Bows;
import com.sofodev.armorplus.common.util.ArmorPlusItemUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialBow extends ItemBow {

    public double damage;
    public String itemName;
    public Bows bows;
    private ItemStack itemExpert;
    private Item itemBow;
    private TextFormatting formatting;

    public ItemSpecialBow(Bows bows, Properties properties) {
        super(properties);
        this.bows = bows;
        this.itemName = bows.getName();
        this.damage = bows.getDamage();
        this.itemExpert = bows.getRepairStack();
        this.formatting = bows.getTextFormatting();
        this.itemBow = bows.getBowItem();
        IItemPropertyGetter pull = ((stack, worldIn, entityIn) -> {
            if (entityIn == null) return 0.0F;
            ItemStack itemstack = entityIn.getActiveItemStack();
            return itemstack.getCount() > 0 && itemstack.getItem() == itemBow ? (float) (stack.getMaxDamage() - entityIn.getItemInUseCount()) / 5.0F : 0.0F;
        });
        this.addPropertyOverride(new ResourceLocation("pull"), pull);
        IItemPropertyGetter pulling = ((stack, worldIn, entityIn) -> entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F);
        this.addPropertyOverride(new ResourceLocation("pulling"), pulling);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode())) {
            tooltip.add(new TextComponentTranslation("item.armorplus.bow.desc", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }

    @Nonnull
    public ItemStack findAmmo(EntityLivingBase entityLivingBase) {
        ItemStack offHand = entityLivingBase.getHeldItemOffhand();
        ItemStack mainHand = entityLivingBase.getHeldItemMainhand();
        if (this.isArrow(mainHand)) {
            return mainHand;
        } else if (this.isArrow(offHand)) {
            return offHand;
        }
        int bound = ((EntityPlayer) entityLivingBase).inventory.getSizeInventory();
        return IntStream.range(0, bound).mapToObj(
            i -> ((EntityPlayer) entityLivingBase).inventory.getStackInSlot(i)
        ).filter(this::isArrow).findFirst().orElse(ItemStack.EMPTY);
    }

    public float getVelocityOfArrow(ItemStack stack) {
        Utils.checkNBT(stack);

        NBTTagCompound tag = stack.getTag();

        return tag.contains("velocity") ? tag.getFloat("velocity") : 3;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, world, entityLiving, timeLeft);
        //Player
        //  if (!(entityLiving instanceof EntityPlayer)) {
        //      return;
        //  }
        //  EntityPlayer player = (EntityPlayer) entityLiving;
        //  boolean requiredConditions = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
        //  ItemStack itemstack = this.findAmmo(player);

        //  int useDuration = this.getMaxDamage(stack) - timeLeft;
        //  useDuration = ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer) entityLiving, useDuration, !itemstack.isEmpty() || requiredConditions);
        //  if (useDuration < 0) return;

        //  if (itemstack.isEmpty() && !requiredConditions) {
        //      return;
        //  }
        //  if (itemstack.isEmpty()) {
        //      itemstack = new ItemStack(Items.ARROW);
        //  }

        //  float arrowVelocity = getArrowVelocity(useDuration);

        //  if ((double) arrowVelocity >= 0.1D) {
        //      boolean secondaryConditions = requiredConditions && itemstack.getItem() instanceof ItemArrow;

        //      this.spawnArrow(world, player, itemstack, stack, arrowVelocity, secondaryConditions);
        //      world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

        //      if (!secondaryConditions) {
        //          itemstack.shrink(1);

        //          if (itemstack.isEmpty()) {
        //              player.inventory.deleteStack(itemstack);
        //          }
        //      }

        //      player.addStat(StatList.ITEM_USED.get(this));
        //  }
    }

    private void spawnArrow(World world, EntityLivingBase player, ItemStack itemstack, ItemStack stack, float arrowVelocity, boolean secondaryConditions) {
        if (!world.isRemote) {
            ItemArrow itemarrow = ((ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
            EntityArrow entityArrow = itemarrow.createArrow(world, itemstack, player);

            float newArrowVelocity = arrowVelocity * getVelocityOfArrow(stack);
            entityArrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, newArrowVelocity, 1.0F);

            if (newArrowVelocity == 0) {
                world.playSound(null, player.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 0.4F, 1.0F);
                return;
            }

            int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            entityArrow.setDamage(entityArrow.getDamage() + damage + (powerLevel > 0 ? powerLevel * 0.5 + 0.5 : 0));

            int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

            if (punchLevel > 0) {
                entityArrow.setKnockbackStrength(punchLevel);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                entityArrow.setFire(100);
            }

            stack.damageItem(1, player);

            if (secondaryConditions) {
                entityArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            }

            world.spawnEntity(entityArrow);
        }
    }
}
