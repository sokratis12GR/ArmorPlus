/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.events;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class GlobalEventArmorPlus {

  //  @SubscribeEvent
  //  public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
  //      if (event.getModID().equals(ArmorPlus.MODID)) {
  //          //        ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); // Sync config values
  //      }

  //      syncConfig();
  //      LogHelper.info("Refreshing configuration file");
  //  }

  //  private static void syncConfig() {
  //      if (configuration.hasChanged()) configuration.save();
  //  }

}