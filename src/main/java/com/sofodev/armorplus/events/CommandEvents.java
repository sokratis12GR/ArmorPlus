package com.sofodev.armorplus.events;

import com.sofodev.armorplus.commands.ArmorPlusCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class CommandEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        ArmorPlusCommand.register(event.getDispatcher());
    }
}
