package com.sofodev.armorplus.registry;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    /**
     * Ids of the set/rune effects a player switched off in the Armor Effects screen. Saved with the player, kept on death
     * and synced to the player's own client for the screen.
     */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_EFFECTS = ATTACHMENT_TYPES.register("disabled_effects",
            () -> AttachmentType.<List<String>>builder(() -> List.of())
                    .serialize(Codec.STRING.listOf().fieldOf("disabled"))
                    .copyOnDeath()
                    .sync(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()))
                    .build());

    /** Ids of the tool rune effects a player switched off in the Tool Effects screen, kept apart from the armor effects. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_TOOL_EFFECTS = ATTACHMENT_TYPES.register("disabled_tool_effects",
            () -> AttachmentType.<List<String>>builder(() -> List.of())
                    .serialize(Codec.STRING.listOf().fieldOf("disabled"))
                    .copyOnDeath()
                    .sync(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()))
                    .build());

    /** Ids of the weapon (on-hit) effects a player switched off in the Weapon Effects screen, kept apart like the tool effects. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_WEAPON_EFFECTS = ATTACHMENT_TYPES.register("disabled_weapon_effects",
            () -> AttachmentType.<List<String>>builder(() -> List.of())
                    .serialize(Codec.STRING.listOf().fieldOf("disabled"))
                    .copyOnDeath()
                    .sync(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()))
                    .build());
}
