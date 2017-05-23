/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.api.util.NBTHelper;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Bows;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.IntStream;

import static net.minecraft.stats.StatList.getObjectUseStats;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.armorplus.util.Utils.INSTANCE;

public class ItemSpecialBow extends ItemBow implements IModelHelper {

    public double damage;

    private ItemStack itemEasy;
    private ItemStack itemExpert;
    private Item itemBow;
    private TextFormatting formatting;
    public EnumRarity formattingName;
    public String itemName;
    public Bows bows;

    public ItemSpecialBow(Bows bows) {
        this.bows = bows;
        this.itemName = bows.getName();
        this.setMaxDamage(bows.getDurability());
        this.damage = bows.getDamage();
        this.itemEasy = bows.getRepairEasy();
        this.itemExpert = bows.getRepairExpert();
        this.formatting = bows.getTextFormatting();
        this.itemBow = bows.getBowItem();
        this.setRegistryName(bows.getName() + "_bow");
        this.setUnlocalizedName(INSTANCE.setName(bows.getName() + "_bow"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        this.maxStackSize = 1;
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                if (entityIn == null) return 0.0F;
                ItemStack itemstack = entityIn.getActiveItemStack();
                return itemstack.getCount() > 0 && itemstack.getItem() == itemBow ? (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 5.0F : 0.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        this.formattingName = addRarity("BOW", formatting, "Bow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) tooltip.add("\2479Bonus Arrow Damage: " + "\247r" + damage);
        else showInfo(tooltip, keyBindSneak, formatting);
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.INSTANCE.isItemRepairable(repair, itemEasy, itemExpert);
    }

    @Nonnull
    public ItemStack findAmmo(EntityPlayer player) {
        return this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)) ? player.getHeldItem(EnumHand.OFF_HAND) : this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)) ? player.getHeldItem(EnumHand.MAIN_HAND) : IntStream.range(0, player.inventory.getSizeInventory()).mapToObj(i -> player.inventory.getStackInSlot(i)).filter(this::isArrow).findFirst().orElse(ItemStack.EMPTY);
    }

    public float getVelocityOfArrow(ItemStack stack) {
        NBTHelper.INSTANCE.checkNBT(stack);

        NBTTagCompound tag = stack.getTagCompound();

        return tag.hasKey("velocity") ? tag.getFloat("velocity") : 3;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            boolean requiredConditions = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(player);

            int useDuration = this.getMaxItemUseDuration(stack) - timeLeft;
            useDuration = ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer) entityLiving, useDuration, !itemstack.isEmpty() || requiredConditions);
            if (useDuration < 0)
                return;

            if (!itemstack.isEmpty() || requiredConditions) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float arrowVelocity = getArrowVelocity(useDuration);

                if ((double) arrowVelocity >= 0.1D) {
                    boolean secondaryConditions = requiredConditions && itemstack.getItem() == Items.ARROW;

                    if (!world.isRemote) {
                        ItemArrow itemarrow = ((ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                        EntityArrow entityArrow = itemarrow.createArrow(world, itemstack, player);

                        float newArrowVelocity = arrowVelocity * getVelocityOfArrow(stack);
                        entityArrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, newArrowVelocity, 1.0F);

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

                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

                    if (!secondaryConditions) {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty()) {
                            player.inventory.deleteStack(itemstack);
                        }
                    }

                    player.addStat(getObjectUseStats(this));
                }
            }
        }
    }
}
