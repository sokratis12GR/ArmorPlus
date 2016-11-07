/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.api.util.NBTHelper;
import net.thedragonteam.armorplus.items.Bows;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseBow extends ItemBow {

    public double damage;

    public Item itemEasy;
    public Item itemExpert;
    public Item itemBow;
    public TextFormatting formatting;

    public BaseBow(Bows bows) {
        this.setMaxDamage(bows.getDurability());
        this.damage = bows.getDamage();
        this.itemEasy = bows.getRepairEasy();
        this.itemExpert = bows.getRepairExpert();
        this.formatting = bows.getTextFormatting();
        this.itemBow = bows.getBowItem();
        setRegistryName(bows.getName());
        setUnlocalizedName(ArmorPlus.MODID + "." + bows.getName());
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        this.maxStackSize = 1;
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                if (entityIn == null) return 0.0F;
                else {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == itemBow ? (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 5.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        tooltip.add(GameSettings.isKeyDown(keyBindSneak) ? "\2479Bonus Arrow Damage: " + "\247r" + damage : I18n.format("tooltip.shift.showinfo", formatting, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    public ItemStack findAmmo(EntityPlayer player) {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) return player.getHeldItem(EnumHand.OFF_HAND);
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) return player.getHeldItem(EnumHand.MAIN_HAND);
        else {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack)) return itemstack;
            }
            return null;
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (ARPConfig.recipes) {
            case 0:
                return repair.getItem() == itemEasy;
            case 1:
                return repair.getItem() == itemExpert;
        }
        return true;
    }

    public void setVelocityOfArrow(ItemStack stack, float velocity) {
        NBTHelper.checkNBT(stack);

        NBTTagCompound tag = stack.getTagCompound();

        tag.setFloat("velocity", velocity);
    }

    public float getVelocityOfArrow(ItemStack stack) {
        NBTHelper.checkNBT(stack);

        NBTTagCompound tag = stack.getTagCompound();

        if (tag.hasKey("velocity")) {
            return tag.getFloat("velocity");
        }

        return 3;
    }


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(player);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer) entityLiving, i, itemstack != null || flag);
            if (i < 0)
                return;

            if (itemstack != null || flag) {
                if (itemstack == null) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float arrowVelocity = getArrowVelocity(i);

                if ((double) arrowVelocity >= 0.1D) {
                    boolean flag1 = flag && itemstack.getItem() == Items.ARROW;

                    if (!world.isRemote) {
                        ItemArrow itemarrow = ((ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                        EntityArrow entityArrow = itemarrow.createArrow(world, itemstack, player);

                        float newArrowVelocity = arrowVelocity * getVelocityOfArrow(stack);
                        entityArrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, newArrowVelocity, 1.0F);

                        if (newArrowVelocity == 0) {
                            world.playSound(null, player.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 0.4F, 1.0F);
                            return;
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        entityArrow.setDamage(entityArrow.getDamage() + damage + (j > 0 ? j * 0.5 + 0.5 : 0));

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0) {
                            entityArrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            entityArrow.setFire(100);
                        }

                        stack.damageItem(1, player);

                        if (flag1) {
                            entityArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        world.spawnEntityInWorld(entityArrow);
                    }

                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

                    if (!flag1) {
                        --itemstack.stackSize;

                        if (itemstack.stackSize == 0) {
                            player.inventory.deleteStack(itemstack);
                        }
                    }

                    player.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
