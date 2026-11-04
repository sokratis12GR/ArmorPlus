package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<RuneAltarMenu>> RUNE_ALTAR = MENUS.register("rune_altar",
            () -> new MenuType<>(RuneAltarMenu::new, FeatureFlags.VANILLA_SET));
}
