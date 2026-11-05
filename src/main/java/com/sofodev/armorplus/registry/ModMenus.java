package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.rune.RitualMenu;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<RuneAltarMenu>> RUNE_ALTAR = MENUS.register("rune_altar",
            () -> IMenuTypeExtension.create(RuneAltarMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<RitualMenu>> RITUAL_ALTAR = MENUS.register("ritual_altar",
            () -> IMenuTypeExtension.create(RitualMenu::new));
}
