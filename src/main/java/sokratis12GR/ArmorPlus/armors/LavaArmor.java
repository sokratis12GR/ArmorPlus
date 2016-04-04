package sokratis12GR.ArmorPlus.armors;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class LavaArmor
{

	public LavaArmor()
	{
	}

	public static Item helmet;
	public static Item chestplate;
	public static Item legs;
	public static Item boots;
	public Object instance;

	public void load(FMLInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(helmet, 0,
					new ModelResourceLocation("armorplus:LavaHelmet", "inventory"));
			ModelLoader.setCustomModelResourceLocation(chestplate, 0,
					new ModelResourceLocation("armorplus:LavaChestplate", "inventory"));
			ModelLoader.setCustomModelResourceLocation(legs, 0,
					new ModelResourceLocation("armorplus:LavaLeggings", "inventory"));
			ModelLoader.setCustomModelResourceLocation(boots, 0,
					new ModelResourceLocation("armorplus:LavaBoots", "inventory"));
		}
		if (ConfigHandler.enableLavaArmorRecipes)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(helmet, 1), new Object[]
			{ new ItemStack(ObsidianArmor.helmet, 1), new ItemStack(Items.lava_bucket, 1),
					new ItemStack(ObsidianArmor.helmet, 2), });
			GameRegistry.addShapelessRecipe(new ItemStack(chestplate, 1), new Object[]
			{ new ItemStack(ObsidianArmor.chestplate, 1), new ItemStack(Items.lava_bucket, 1),
					new ItemStack(ObsidianArmor.chestplate, 1), });
			GameRegistry.addShapelessRecipe(new ItemStack(legs, 1), new Object[]
			{ new ItemStack(ObsidianArmor.legs, 1), new ItemStack(Items.lava_bucket, 1),
					new ItemStack(ObsidianArmor.legs, 1), });
			GameRegistry.addShapelessRecipe(new ItemStack(boots, 1), new Object[]
			{ new ItemStack(ObsidianArmor.boots, 1), new ItemStack(Items.lava_bucket, 1),
					new ItemStack(ObsidianArmor.boots, 1), });
			helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
			chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
			legs.setCreativeTab(ArmorPlus.tabArmorPlus);
			boots.setCreativeTab(ArmorPlus.tabArmorPlus);
		}
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(helmet, 0,
					new ModelResourceLocation("armorplus:LavaHelmet", "inventory"));
			ModelLoader.setCustomModelResourceLocation(chestplate, 0,
					new ModelResourceLocation("armorplus:LavaChestplate", "inventory"));
			ModelLoader.setCustomModelResourceLocation(legs, 0,
					new ModelResourceLocation("armorplus:LavaLeggings", "inventory"));
			ModelLoader.setCustomModelResourceLocation(boots, 0,
					new ModelResourceLocation("armorplus:LavaBoots", "inventory"));
		}
	}

	public void registerRenderers() {
	}

	static
	{
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("LAVAARMOR", "LavaArmor", 45, new int[]
				{ 4, 8, 11, 6 }, 28, SoundEvents.item_armor_equip_diamond);

		int armorPreffix = 0;
		helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD)
		{
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
			{
				tooltip.add(TextHelper.getFormattedText("&6" +"Gives you Resistance and Fire Resistance"));
			}
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableLavaHResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, 0));
				}
				if (ConfigHandler.enableLavaHFireResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.fireResistance, 120, 0));
				}
			}
		}).setUnlocalizedName("LavaHelmet");
		helmet.setMaxStackSize(1);
		chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST)
		{
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
			{
				tooltip.add(TextHelper.getFormattedText("&6" +"Gives you Resistance and Fire Resistance"));
			}
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableLavaCResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, 0));
				}
				if (ConfigHandler.enableLavaCFireResistance)
				{
							if (entity instanceof EntityLivingBase)
								((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.fireResistance, 120, 0));
					}
			}
		}).setUnlocalizedName("LavaChestplate");
		chestplate.setMaxStackSize(1);
		legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS)
		{
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
			{
				tooltip.add(TextHelper.getFormattedText("&6" +"Gives you Resistance and Fire Resistance"));
			}
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableLavaLResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, 0));
				}
				if (ConfigHandler.enableLavaLFireResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.fireResistance, 120, 0));
				}
			}
		}).setUnlocalizedName("LavaLeggings");
		legs.setMaxStackSize(1);
		boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET)
		{
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
			{
				tooltip.add(TextHelper.getFormattedText("&6" +"Gives you Resistance and Fire Resistance"));
			}
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableLavaBResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, 0));
				}
				if (ConfigHandler.enableLavaBFireResistance)
				{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.fireResistance, 120, 0));
				}
			}
		}).setUnlocalizedName("LavaBoots");
		boots.setMaxStackSize(1);

		GameRegistry.registerItem(helmet, "LavaHelmet");
		GameRegistry.registerItem(chestplate, "LavaChestplate");
		GameRegistry.registerItem(legs, "LavaLeggings");
		GameRegistry.registerItem(boots, "LavaBoots");

	}

}
