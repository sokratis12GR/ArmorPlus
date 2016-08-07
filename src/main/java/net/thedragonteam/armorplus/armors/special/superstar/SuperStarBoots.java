package net.thedragonteam.armorplus.armors.special.superstar;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

/**
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/28/2016 9:19 AM.
 */
public class SuperStarBoots extends ItemArmor {

    public static int armorPreffix = 0;

    public SuperStarBoots() {
        super(ModItems.SUPER_STAR_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.FEET);
        setMaxStackSize(1);
        setRegistryName("super_star_boots");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("SuperStarBoots");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:SuperStarBoots", "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        int superstarArmorEffectlevel = ARPConfig.superstarArmorEffectlevel + 1;
        if (ARPConfig.enableSuperStarBRegen) {
            infoList.add("\2479Ability: " + "\247rRegeneration " + superstarArmorEffectlevel);
            infoList.add("\2473Use: " + "\247rEquip A Piece");
        }
        if (ARPConfig.enableFullSuperStarArmorEffect) {
            infoList.add("\2479Ability: " + "\247rRegeneration " + superstarArmorEffectlevel);
            infoList.add("\2473Use: " + "\247rEquip The Full Set");
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableSuperStarCRegen && entity instanceof EntityLivingBase && !ARPConfig.enableFullSuperStarArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.superstarArmorEffectlevel, true, true));
            entity.removePotionEffect(MobEffects.WITHER);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.WHITE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.WITHER_BONE;
    }
}