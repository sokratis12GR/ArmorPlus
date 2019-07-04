package com.sofodev.armorplus.api.caps.ailments;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static com.sofodev.armorplus.api.caps.ailments.ImplementedAilments.AILMENT_REGISTRY;
import static com.sofodev.armorplus.api.caps.ailments.ImplementedAilments.NONE;

public class Ailment extends IForgeRegistryEntry.Impl<Ailment> {

    /**
     * Ability's name
     */
    private final String name;

    public Ailment(ResourceLocation rl, String translatableName) {
        this.setRegistryName(rl);
        this.name = new TextComponentTranslation(translatableName).getFormattedText();
    }

    public Ailment(String rl, String translatableName) {
        this(new ResourceLocation(rl), translatableName);
    }

    public String getName() {
        return name;
    }

    public String getSafeName() {
        return getName().toLowerCase().replace(" ", "_");
    }

    public static Ailment getAilment(ResourceLocation resourceLocation) {
        return AILMENT_REGISTRY.getValue(resourceLocation);
    }

    public static Ailment getAilment(String safeID) {
        return AILMENT_REGISTRY.getValuesCollection().stream().filter(dataEntry -> dataEntry.getSafeName().equals(safeID)).findFirst().orElse(NONE);
    }
}
