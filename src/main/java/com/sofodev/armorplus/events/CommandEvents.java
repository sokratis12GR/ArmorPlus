package com.sofodev.armorplus.events;

import com.sofodev.armorplus.commands.ArmorPlusCommand;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@EventBusSubscriber(modid = MODID)
public class CommandEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        ArmorPlusCommand.register(event.getDispatcher());
    }
}
