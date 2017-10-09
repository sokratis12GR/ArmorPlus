package net.thedragonteam.armorplus.compat.tinkers;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@Mod.EventBusSubscriber(modid = "tconstruct")
public class TinkersConstructEventHandler {

    @SubscribeEvent
    public static void onModifierRegister(TinkerRegisterEvent.ModifierRegisterEvent event) {
        new TinkerRegisterEvent.ModifierRegisterEvent(TiCModifiers.theUltimateMaterial);
        new TinkerRegisterEvent.ModifierRegisterEvent(TiCModifiers.infusedLavaCrystalModifier);
    }
}
