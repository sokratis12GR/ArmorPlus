/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.dev.DevBoots;
import net.thedragonteam.armorplus.armors.dev.DevChestplate;
import net.thedragonteam.armorplus.armors.dev.DevHelmet;
import net.thedragonteam.armorplus.armors.dev.DevLeggings;
import net.thedragonteam.armorplus.armors.origin.coal.CoalBoots;
import net.thedragonteam.armorplus.armors.origin.coal.CoalChestplate;
import net.thedragonteam.armorplus.armors.origin.coal.CoalHelmet;
import net.thedragonteam.armorplus.armors.origin.coal.CoalLeggings;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldBoots;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldChestplate;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldHelmet;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldLeggings;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisBoots;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisChestplate;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisHelmet;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisLeggings;
import net.thedragonteam.armorplus.armors.origin.lava.LavaBoots;
import net.thedragonteam.armorplus.armors.origin.lava.LavaChestplate;
import net.thedragonteam.armorplus.armors.origin.lava.LavaHelmet;
import net.thedragonteam.armorplus.armors.origin.lava.LavaLeggings;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianBoots;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianChestplate;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianHelmet;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianLeggings;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneBoots;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneChestplate;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneHelmet;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneLeggings;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonBoots;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonChestplate;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonHelmet;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonLeggings;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianBoots;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianChestplate;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianHelmet;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianLeggings;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenBoots;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenChestplate;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenHelmet;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenLeggings;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeBoots;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeChestplate;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeHelmet;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeLeggings;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarBoots;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarChestplate;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarHelmet;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarLeggings;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateBoots;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateChestplate;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateHelmet;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeBoots;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltBoots;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeBoots;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynBoots;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronBoots;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronLeggings;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalBoots;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalChestplate;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalHelmet;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalLeggings;
import net.thedragonteam.armorplus.armors.v2.steel.SteelBoots;
import net.thedragonteam.armorplus.armors.v2.steel.SteelChestplate;
import net.thedragonteam.armorplus.armors.v2.steel.SteelHelmet;
import net.thedragonteam.armorplus.armors.v2.steel.SteelLeggings;
import net.thedragonteam.armorplus.items.arrows.ItemCoalArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLapisArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLavaArrow;
import net.thedragonteam.armorplus.items.arrows.ItemRedstoneArrow;
import net.thedragonteam.armorplus.items.battleaxes.*;
import net.thedragonteam.armorplus.items.books.ArmorPlusBook;
import net.thedragonteam.armorplus.items.books.ArmorPlusInfoBook;
import net.thedragonteam.armorplus.items.bows.*;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.*;
import net.thedragonteam.armorplus.items.materials.*;
import net.thedragonteam.armorplus.items.swords.*;
import net.thedragonteam.armorplus.items.theultimate.*;
import net.thedragonteam.armorplus.util.NameUtil;

public class ModItems {
    /**
     * Swords
     * Float damageVsEntity + 4.0F
     * public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial("MATERIAL", int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability);
     */
    public static Item.ToolMaterial swordCoalMaterial = EnumHelper.addToolMaterial("swordCoalMaterial", 1, 59, 1.0F, 0.5F, 15);
    public static Item.ToolMaterial swordLapisMaterial = EnumHelper.addToolMaterial("swordLapisMaterial", 1, 250, 1.0F, 1.0F, 30);
    public static Item.ToolMaterial swordRedstoneMaterial = EnumHelper.addToolMaterial("swordRedstoneMaterial", 1, 200, 1.0F, 1.5F, 20);
    public static Item.ToolMaterial swordEmeraldMaterial = EnumHelper.addToolMaterial("swordEmeraldMaterial", 1, 1561, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial swordObsidianMaterial = EnumHelper.addToolMaterial("swordObsidianMaterial", 1, 1500, 1.0F, 4.0F, 20);
    public static Item.ToolMaterial swordLavaMaterial = EnumHelper.addToolMaterial("swordLavaMaterial", 1, 1750, 1.0F, 4.5F, 20);
    public static Item.ToolMaterial swordSuperStarMaterial = EnumHelper.addToolMaterial("swordSuperStarMaterial", 1, 1750, 0.0F, 6.0F, 20);
    public static Item.ToolMaterial swordGuardianMaterial = EnumHelper.addToolMaterial("swordGuardianMaterial", 1, 500, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial swordEnderDragonMaterial = EnumHelper.addToolMaterial("swordEnderDragonMaterial", 1, 2000, 1.0F, 8.0F, 20);
    public static Item.ToolMaterial battleAxeCoalMaterial = EnumHelper.addToolMaterial("battleAxeCoalMaterial", 1, 59, 1.0F, 2.5F, 15);
    public static Item.ToolMaterial battleAxeLapisMaterial = EnumHelper.addToolMaterial("battleAxeLapisMaterial", 1, 250, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial battleAxeRedstoneMaterial = EnumHelper.addToolMaterial("battleAxeRedstoneMaterial", 1, 200, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial battleAxeEmeraldMaterial = EnumHelper.addToolMaterial("battleAxeEmeraldMaterial", 1, 1561, 1.0F, 5.5F, 20);
    public static Item.ToolMaterial battleAxeObsidianMaterial = EnumHelper.addToolMaterial("battleAxeObsidianMaterial", 1, 1500, 1.0F, 6.0F, 20);
    public static Item.ToolMaterial battleAxeLavaMaterial = EnumHelper.addToolMaterial("battleAxeLavaMaterial", 1, 1750, 1.0F, 6.5F, 20);
    public static Item.ToolMaterial battleAxeSuperStarMaterial = EnumHelper.addToolMaterial("battleAxeSuperStarMaterial", 1, 1750, 0.0F, 8.0F, 20);
    public static Item.ToolMaterial battleAxeGuardianMaterial = EnumHelper.addToolMaterial("battleAxeGuardianMaterial", 1, 500, 1.0F, 5.0F, 30);
    public static Item.ToolMaterial battleAxeEnderDragonMaterial = EnumHelper.addToolMaterial("battleAxeEnderDragonMaterial", 1, 2000, 1.0F, 10.0F, 20);

    public static ItemArmor.ArmorMaterial devArmor = EnumHelper.addArmorMaterial("devArmor", ArmorPlus.MODID + ":" + "dev_armor", 100000, new int[]
            {100, 100, 100, 100}, 100, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 100.0F);

    public static ItemArmor.ArmorMaterial coalArmor = EnumHelper.addArmorMaterial("coalArmor", ArmorPlus.MODID + ":" + "coal_armor", 7, new int[]
            {1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial emeraldArmor = EnumHelper.addArmorMaterial("emeraldArmor", ArmorPlus.MODID + ":" + "emerald_armor", 35, new int[]
            {3, 6, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial lapisArmor = EnumHelper.addArmorMaterial("lapisArmor", ArmorPlus.MODID + ":" + "lapis_armor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial lavaArmor = EnumHelper.addArmorMaterial("lavaArmor", ArmorPlus.MODID + ":" + "lava_armor", 45, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial obsidianArmor = EnumHelper.addArmorMaterial("obsidianArmor", ArmorPlus.MODID + ":" + "obsidian_armor", 40, new int[]
            {3, 7, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

    public static ItemArmor.ArmorMaterial redstoneArmor = EnumHelper.addArmorMaterial("redstoneArmor", ArmorPlus.MODID + ":" + "redstone_armor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial steelArmor = EnumHelper.addArmorMaterial("steelArmor", ArmorPlus.MODID + ":" + "steel_armor", 15, new int[]
            {2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static ItemArmor.ArmorMaterial electricalArmor = EnumHelper.addArmorMaterial("electricalArmor", ArmorPlus.MODID + ":" + "electrical_armor", 19, new int[]
            {3, 6, 7, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static ItemArmor.ArmorMaterial chickenArmor = EnumHelper.addArmorMaterial("chickenArmor", ArmorPlus.MODID + ":" + "chicken_armor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial slimeArmor = EnumHelper.addArmorMaterial("slimeArmor", ArmorPlus.MODID + ":" + "slime_armor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial enderDragonArmor = EnumHelper.addArmorMaterial("enderDragonArmor", ArmorPlus.MODID + ":" + "ender_dragon_armor", 60, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial guardianArmor = EnumHelper.addArmorMaterial("guardianArmor", ArmorPlus.MODID + ":" + "guardian_armor", 50, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial superStarArmor = EnumHelper.addArmorMaterial("superStarArmor", ArmorPlus.MODID + ":" + "super_star_armor", 50, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial theUltimateArmor = EnumHelper.addArmorMaterial("theUltimateArmor", ArmorPlus.MODID + ":" + "the_ultimate_armor", 160, new int[]
            {10, 20, 30, 15}, 1, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.0F);

    public static ItemArmor.ArmorMaterial arditeArmor = EnumHelper.addArmorMaterial("arditeArmor", ArmorPlus.MODID + ":" + "ardite_armor", 55, new int[]
            {4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial cobaltArmor = EnumHelper.addArmorMaterial("cobaltArmor", ArmorPlus.MODID + ":" + "cobalt_armor", 44, new int[]
            {3, 7, 9, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial knightSlimeArmor = EnumHelper.addArmorMaterial("knightSlimeArmor", ArmorPlus.MODID + ":" + "knight_slime_armor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static ItemArmor.ArmorMaterial manyullynArmor = EnumHelper.addArmorMaterial("manyullynArmor", ArmorPlus.MODID + ":" + "manyullyn_armor", 66, new int[]
            {5, 10, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);

    public static ItemArmor.ArmorMaterial pigIronArmor = EnumHelper.addArmorMaterial("pigIronArmor", ArmorPlus.MODID + ":" + "pig_iron_armor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static Chainmail chainmail;
    public static GuardianScale guardianScale;
    public static WitherBone witherBone;
    public static EnderDragonScale enderDragonScale;
    public static TheUltimateMaterial theUltimateMaterial;
    public static LavaCrystal lavaCrystal;
    public static TheGiftOfTheGods theGiftOfTheGods;
    public static ArmorPlusBook armorPlusBook;
    public static SteelIngot steelIngot;
    public static ElectricalIngot electricalIngot;
    public static RedstoneApple redstoneApple;
    public static ArmorPlusInfoBook armorPlusInfoBook;
    public static NBTItem nbtItem;

    public static DevHelmet devHelmet;
    public static DevChestplate devChestplate;
    public static DevLeggings devLeggings;
    public static DevBoots devBoots;

    public static CoalHelmet coalHelmet;
    public static CoalChestplate coalChestplate;
    public static CoalLeggings coalLeggings;
    public static CoalBoots coalBoots;

    public static EmeraldHelmet emeraldHelmet;
    public static EmeraldChestplate emeraldChestplate;
    public static EmeraldLeggings emeraldLeggings;
    public static EmeraldBoots emeraldBoots;

    public static LapisHelmet lapisHelmet;
    public static LapisChestplate lapisChestplate;
    public static LapisLeggings lapisLeggings;
    public static LapisBoots lapisBoots;

    public static LavaHelmet lavaHelmet;
    public static LavaChestplate lavaChestplate;
    public static LavaLeggings lavaLeggings;
    public static LavaBoots lavaBoots;

    public static ObsidianHelmet obsidianHelmet;
    public static ObsidianChestplate obsidianChestplate;
    public static ObsidianLeggings obsidianLeggings;
    public static ObsidianBoots obsidianBoots;

    public static RedstoneHelmet redstoneHelmet;
    public static RedstoneChestplate redstoneChestplate;
    public static RedstoneLeggings redstoneLeggings;
    public static RedstoneBoots redstoneBoots;

    public static SteelHelmet steelHelmet;
    public static SteelChestplate steelChestplate;
    public static SteelLeggings steelLeggings;
    public static SteelBoots steelBoots;

    public static ElectricalHelmet electricalHelmet;
    public static ElectricalChestplate electricalChestplate;
    public static ElectricalLeggings electricalLeggings;
    public static ElectricalBoots electricalBoots;

    public static ChickenHelmet chickenHelmet;
    public static ChickenChestplate chickenChestplate;
    public static ChickenLeggings chickenLeggings;
    public static ChickenBoots chickenBoots;

    public static SlimeHelmet slimeHelmet;
    public static SlimeChestplate slimeChestplate;
    public static SlimeLeggings slimeLeggings;
    public static SlimeBoots slimeBoots;

    public static EnderDragonHelmet enderDragonHelmet;
    public static EnderDragonChestplate enderDragonChestplate;
    public static EnderDragonLeggings enderDragonLeggings;
    public static EnderDragonBoots enderDragonBoots;

    public static GuardianHelmet guardianHelmet;
    public static GuardianChestplate guardianChestplate;
    public static GuardianLeggings guardianLeggings;
    public static GuardianBoots guardianBoots;

    public static SuperStarHelmet superStarHelmet;
    public static SuperStarChestplate superStarChestplate;
    public static SuperStarLeggings superStarLeggings;
    public static SuperStarBoots superStarBoots;

    public static TheUltimateHelmet theUltimateHelmet;
    public static TheUltimateChestplate theUltimateChestplate;
    public static TheUltimateLeggings theUltimateLeggings;
    public static TheUltimateBoots theUltimateBoots;

    public static ArditeHelmet arditeHelmet;
    public static ArditeChestplate arditeChestplate;
    public static ArditeLeggings arditeLeggings;
    public static ArditeBoots arditeBoots;

    public static CobaltHelmet cobaltHelmet;
    public static CobaltChestplate cobaltChestplate;
    public static CobaltLeggings cobaltLeggings;
    public static CobaltBoots cobaltBoots;

    public static ManyullynHelmet manyullynHelmet;
    public static ManyullynChestplate manyullynChestplate;
    public static ManyullynLeggings manyullynLeggings;
    public static ManyullynBoots manyullynBoots;

    public static PigIronHelmet pigIronHelmet;
    public static PigIronChestplate pigIronChestplate;
    public static PigIronLeggings pigIronLeggings;
    public static PigIronBoots pigIronBoots;

    public static KnightSlimeHelmet knightSlimeHelmet;
    public static KnightSlimeChestplate knightSlimeChestplate;
    public static KnightSlimeLeggings knightSlimeLeggings;
    public static KnightSlimeBoots knightSlimeBoots;

    public static CoalSword coalSword;
    public static LapisSword lapisSword;
    public static RedstoneSword redstoneSword;
    public static EmeraldSword emeraldSword;
    public static ObsidianSword obsidianSword;
    public static LavaSword lavaSword;
    public static SuperStarSword superStarSword;
    public static GuardianSword guardianSword;
    public static EnderDragonSword enderDragonSword;

    public static CoalBattleAxe coalBattleAxe;
    public static LapisBattleAxe lapisBattleAxe;
    public static RedstoneBattleAxe redstoneBattleAxe;
    public static EmeraldBattleAxe emeraldBattleAxe;
    public static ObsidianBattleAxe obsidianBattleAxe;
    public static LavaBattleAxe lavaBattleAxe;
    public static SuperStarBattleAxe superStarBattleAxe;
    public static GuardianBattleAxe guardianBattleAxe;
    public static EnderDragonBattleAxe enderDragonBattleAxe;

    public static CoalBow coalBow;
    public static LapisBow lapisBow;
    public static RedstoneBow redstoneBow;
    public static EmeraldBow emeraldBow;
    public static ObsidianBow obsidianBow;
    public static LavaBow lavaBow;
    public static SuperStarBow superStarBow;
    public static GuardianBow guardianBow;
    public static EnderDragonBow enderDragonBow;

    public static TheUltimateHelmetLeft theUltimateHelmetLeft;
    public static TheUltimateHelmetMiddle theUltimateHelmetMiddle;
    public static TheUltimateHelmetRight theUltimateHelmetRight;
    public static TheUltimateChestplateLeft theUltimateChestplateLeft;
    public static TheUltimateChestplateMiddle theUltimateChestplateMiddle;
    public static TheUltimateChestplateRight theUltimateChestplateRight;
    public static TheUltimateLeggingsLeft theUltimateLeggingsLeft;
    public static TheUltimateLeggingsMiddle theUltimateLeggingsMiddle;
    public static TheUltimateLeggingsRight theUltimateLeggingsRight;
    public static TheUltimateBootsLeft theUltimateBootsLeft;
    public static TheUltimateBootsMiddle theUltimateBootsMiddle;
    public static TheUltimateBootsRight theUltimateBootsRight;

    public static DevTool devTool;
    public static TheDragonTeamItem theDragonTeamItem;
    public static ModdedCityItem moddedCityItem;
    public static JonBamsItem jonBamsItem;

    public static Item coalArrow, lapisArrow, redstoneArrow, lavaArrow;

    public ModItems() {
        register();
    }

    public static void init() {
        theDragonTeamItem = new TheDragonTeamItem();
        moddedCityItem = new ModdedCityItem();
        jonBamsItem = new JonBamsItem();
        chainmail = new Chainmail();
        guardianScale = new GuardianScale();
        witherBone = new WitherBone();
        enderDragonScale = new EnderDragonScale();
        theUltimateMaterial = new TheUltimateMaterial();
        lavaCrystal = new LavaCrystal();
        theGiftOfTheGods = new TheGiftOfTheGods();
        armorPlusBook = new ArmorPlusBook();
        steelIngot = new SteelIngot();
        electricalIngot = new ElectricalIngot();
        redstoneApple = new RedstoneApple();
        armorPlusInfoBook = new ArmorPlusInfoBook();
        nbtItem = new NBTItem();
        devHelmet = new DevHelmet();
        devChestplate = new DevChestplate();
        devLeggings = new DevLeggings();
        devBoots = new DevBoots();
        coalHelmet = new CoalHelmet();
        coalChestplate = new CoalChestplate();
        coalLeggings = new CoalLeggings();
        coalBoots = new CoalBoots();
        emeraldHelmet = new EmeraldHelmet();
        emeraldChestplate = new EmeraldChestplate();
        emeraldLeggings = new EmeraldLeggings();
        emeraldBoots = new EmeraldBoots();
        lapisHelmet = new LapisHelmet();
        lapisChestplate = new LapisChestplate();
        lapisLeggings = new LapisLeggings();
        lapisBoots = new LapisBoots();
        lavaHelmet = new LavaHelmet();
        lavaChestplate = new LavaChestplate();
        lavaLeggings = new LavaLeggings();
        lavaBoots = new LavaBoots();
        obsidianHelmet = new ObsidianHelmet();
        obsidianChestplate = new ObsidianChestplate();
        obsidianLeggings = new ObsidianLeggings();
        obsidianBoots = new ObsidianBoots();
        redstoneHelmet = new RedstoneHelmet();
        redstoneChestplate = new RedstoneChestplate();
        redstoneLeggings = new RedstoneLeggings();
        redstoneBoots = new RedstoneBoots();
        steelHelmet = new SteelHelmet();
        steelChestplate = new SteelChestplate();
        steelLeggings = new SteelLeggings();
        steelBoots = new SteelBoots();
        electricalHelmet = new ElectricalHelmet();
        electricalChestplate = new ElectricalChestplate();
        electricalLeggings = new ElectricalLeggings();
        electricalBoots = new ElectricalBoots();
        chickenHelmet = new ChickenHelmet();
        chickenChestplate = new ChickenChestplate();
        chickenLeggings = new ChickenLeggings();
        chickenBoots = new ChickenBoots();
        slimeHelmet = new SlimeHelmet();
        slimeChestplate = new SlimeChestplate();
        slimeLeggings = new SlimeLeggings();
        slimeBoots = new SlimeBoots();
        enderDragonHelmet = new EnderDragonHelmet();
        enderDragonChestplate = new EnderDragonChestplate();
        enderDragonLeggings = new EnderDragonLeggings();
        enderDragonBoots = new EnderDragonBoots();
        guardianHelmet = new GuardianHelmet();
        guardianChestplate = new GuardianChestplate();
        guardianLeggings = new GuardianLeggings();
        guardianBoots = new GuardianBoots();
        superStarHelmet = new SuperStarHelmet();
        superStarChestplate = new SuperStarChestplate();
        superStarLeggings = new SuperStarLeggings();
        superStarBoots = new SuperStarBoots();
        theUltimateHelmet = new TheUltimateHelmet();
        theUltimateChestplate = new TheUltimateChestplate();
        theUltimateLeggings = new TheUltimateLeggings();
        theUltimateBoots = new TheUltimateBoots();
        arditeHelmet = new ArditeHelmet();
        arditeChestplate = new ArditeChestplate();
        arditeLeggings = new ArditeLeggings();
        arditeBoots = new ArditeBoots();
        cobaltHelmet = new CobaltHelmet();
        cobaltChestplate = new CobaltChestplate();
        cobaltLeggings = new CobaltLeggings();
        cobaltBoots = new CobaltBoots();
        manyullynHelmet = new ManyullynHelmet();
        manyullynChestplate = new ManyullynChestplate();
        manyullynLeggings = new ManyullynLeggings();
        manyullynBoots = new ManyullynBoots();
        pigIronHelmet = new PigIronHelmet();
        pigIronChestplate = new PigIronChestplate();
        pigIronLeggings = new PigIronLeggings();
        pigIronBoots = new PigIronBoots();
        knightSlimeHelmet = new KnightSlimeHelmet();
        knightSlimeChestplate = new KnightSlimeChestplate();
        knightSlimeLeggings = new KnightSlimeLeggings();
        knightSlimeBoots = new KnightSlimeBoots();
        coalSword = new CoalSword(swordCoalMaterial);
        lapisSword = new LapisSword(swordLapisMaterial);
        redstoneSword = new RedstoneSword(swordRedstoneMaterial);
        emeraldSword = new EmeraldSword(swordEmeraldMaterial);
        obsidianSword = new ObsidianSword(swordObsidianMaterial);
        lavaSword = new LavaSword(swordLavaMaterial);
        superStarSword = new SuperStarSword(swordSuperStarMaterial);
        guardianSword = new GuardianSword(swordGuardianMaterial);
        enderDragonSword = new EnderDragonSword(swordEnderDragonMaterial);
        coalBattleAxe = new CoalBattleAxe(battleAxeCoalMaterial);
        lapisBattleAxe = new LapisBattleAxe(battleAxeLapisMaterial);
        redstoneBattleAxe = new RedstoneBattleAxe(battleAxeRedstoneMaterial);
        emeraldBattleAxe = new EmeraldBattleAxe(battleAxeEmeraldMaterial);
        obsidianBattleAxe = new ObsidianBattleAxe(battleAxeObsidianMaterial);
        lavaBattleAxe = new LavaBattleAxe(battleAxeLavaMaterial);
        superStarBattleAxe = new SuperStarBattleAxe(battleAxeSuperStarMaterial);
        guardianBattleAxe = new GuardianBattleAxe(battleAxeGuardianMaterial);
        enderDragonBattleAxe = new EnderDragonBattleAxe(battleAxeEnderDragonMaterial);
        coalBow = new CoalBow();
        lapisBow = new LapisBow();
        redstoneBow = new RedstoneBow();
        emeraldBow = new EmeraldBow();
        obsidianBow = new ObsidianBow();
        lavaBow = new LavaBow();
        superStarBow = new SuperStarBow();
        guardianBow = new GuardianBow();
        enderDragonBow = new EnderDragonBow();
        theUltimateHelmetLeft = new TheUltimateHelmetLeft();
        theUltimateHelmetMiddle = new TheUltimateHelmetMiddle();
        theUltimateHelmetRight = new TheUltimateHelmetRight();
        theUltimateChestplateLeft = new TheUltimateChestplateLeft();
        theUltimateChestplateMiddle = new TheUltimateChestplateMiddle();
        theUltimateChestplateRight = new TheUltimateChestplateRight();
        theUltimateLeggingsLeft = new TheUltimateLeggingsLeft();
        theUltimateLeggingsMiddle = new TheUltimateLeggingsMiddle();
        theUltimateLeggingsRight = new TheUltimateLeggingsRight();
        theUltimateBootsLeft = new TheUltimateBootsLeft();
        theUltimateBootsMiddle = new TheUltimateBootsMiddle();
        theUltimateBootsRight = new TheUltimateBootsRight();
        devTool = new DevTool();
        coalArrow = new ItemCoalArrow().setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        NameUtil.setNames(coalArrow, "coal_arrow");
        lapisArrow = new ItemLapisArrow().setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        NameUtil.setNames(lapisArrow, "lapis_arrow");
        redstoneArrow = new ItemRedstoneArrow().setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        NameUtil.setNames(redstoneArrow, "redstone_arrow");
        lavaArrow = new ItemLavaArrow().setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        NameUtil.setNames(lavaArrow, "lava_arrow");
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        theDragonTeamItem.initModel();
        moddedCityItem.initModel();
        jonBamsItem.initModel();

        chainmail.initModel();
        guardianScale.initModel();
        witherBone.initModel();
        enderDragonScale.initModel();
        theUltimateMaterial.initModel();
        lavaCrystal.initModel();
        theGiftOfTheGods.initModel();
        armorPlusBook.initModel();
        steelIngot.initModel();
        electricalIngot.initModel();
        redstoneApple.initModel();
        armorPlusInfoBook.initModel();

        devHelmet.initModel();
        devChestplate.initModel();
        devLeggings.initModel();
        devBoots.initModel();

        coalHelmet.initModel();
        coalChestplate.initModel();
        coalLeggings.initModel();
        coalBoots.initModel();

        emeraldHelmet.initModel();
        emeraldChestplate.initModel();
        emeraldLeggings.initModel();
        emeraldBoots.initModel();

        lapisHelmet.initModel();
        lapisChestplate.initModel();
        lapisLeggings.initModel();
        lapisBoots.initModel();

        lavaHelmet.initModel();
        lavaChestplate.initModel();
        lavaLeggings.initModel();
        lavaBoots.initModel();

        obsidianHelmet.initModel();
        obsidianChestplate.initModel();
        obsidianLeggings.initModel();
        obsidianBoots.initModel();

        redstoneHelmet.initModel();
        redstoneChestplate.initModel();
        redstoneLeggings.initModel();
        redstoneBoots.initModel();

        steelHelmet.initModel();
        steelChestplate.initModel();
        steelLeggings.initModel();
        steelBoots.initModel();

        electricalHelmet.initModel();
        electricalChestplate.initModel();
        electricalLeggings.initModel();
        electricalBoots.initModel();

        chickenHelmet.initModel();
        chickenChestplate.initModel();
        chickenLeggings.initModel();
        chickenBoots.initModel();

        slimeHelmet.initModel();
        slimeChestplate.initModel();
        slimeLeggings.initModel();
        slimeBoots.initModel();

        enderDragonHelmet.initModel();
        enderDragonChestplate.initModel();
        enderDragonLeggings.initModel();
        enderDragonBoots.initModel();

        guardianHelmet.initModel();
        guardianChestplate.initModel();
        guardianLeggings.initModel();
        guardianBoots.initModel();

        superStarHelmet.initModel();
        superStarChestplate.initModel();
        superStarLeggings.initModel();
        superStarBoots.initModel();

        theUltimateHelmet.initModel();
        theUltimateChestplate.initModel();
        theUltimateLeggings.initModel();
        theUltimateBoots.initModel();

        arditeHelmet.initModel();
        arditeChestplate.initModel();
        arditeLeggings.initModel();
        arditeBoots.initModel();

        cobaltHelmet.initModel();
        cobaltChestplate.initModel();
        cobaltLeggings.initModel();
        cobaltBoots.initModel();

        manyullynHelmet.initModel();
        manyullynChestplate.initModel();
        manyullynLeggings.initModel();
        manyullynBoots.initModel();

        pigIronHelmet.initModel();
        pigIronChestplate.initModel();
        pigIronLeggings.initModel();
        pigIronBoots.initModel();

        knightSlimeHelmet.initModel();
        knightSlimeChestplate.initModel();
        knightSlimeLeggings.initModel();
        knightSlimeBoots.initModel();

        coalSword.initModel();
        lapisSword.initModel();
        redstoneSword.initModel();
        emeraldSword.initModel();
        obsidianSword.initModel();
        lavaSword.initModel();
        superStarSword.initModel();
        guardianSword.initModel();
        enderDragonSword.initModel();
        coalBattleAxe.initModel();
        lapisBattleAxe.initModel();
        redstoneBattleAxe.initModel();
        emeraldBattleAxe.initModel();
        obsidianBattleAxe.initModel();
        lavaBattleAxe.initModel();
        superStarBattleAxe.initModel();
        guardianBattleAxe.initModel();
        enderDragonBattleAxe.initModel();
        coalBow.initModel();
        lapisBow.initModel();
        redstoneBow.initModel();
        emeraldBow.initModel();
        obsidianBow.initModel();
        lavaBow.initModel();
        superStarBow.initModel();
        guardianBow.initModel();
        enderDragonBow.initModel();

        theUltimateHelmetLeft.initModel();
        theUltimateHelmetMiddle.initModel();
        theUltimateHelmetRight.initModel();
        theUltimateChestplateLeft.initModel();
        theUltimateChestplateMiddle.initModel();
        theUltimateChestplateRight.initModel();
        theUltimateLeggingsLeft.initModel();
        theUltimateLeggingsMiddle.initModel();
        theUltimateLeggingsRight.initModel();
        theUltimateBootsLeft.initModel();
        theUltimateBootsMiddle.initModel();
        theUltimateBootsRight.initModel();

        devTool.initModel();
        nbtItem.initModel();
    }

    private void register() {
        registerItem(coalArrow);
        registerItem(lapisArrow);
        registerItem(redstoneArrow);
        registerItem(lavaArrow);
    }

    private void registerItem(Item item) {
        GameRegistry.register(item);
    }

}
