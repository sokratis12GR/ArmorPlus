package com.sofodev.armorplus.registry;

import com.mojang.serialization.Codec;
import com.sofodev.armorplus.registry.item.tool.ArrowRunes;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Per-player data. NeoForge 21.1 can't sync attachments by itself, the disabled effect lists are sent to the player's own
 * client with {@link com.sofodev.armorplus.network.SyncDisabledEffectsPayload}.
 */
public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    /** Ids of the set effects a player switched off in the Armor Effects screen. Saved with the player and kept on death. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_EFFECTS = ATTACHMENT_TYPES.register("disabled_effects", disabledList());

    /** Ids of the tool effects a player switched off in the Tool Effects screen, kept apart from the armor effects. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_TOOL_EFFECTS = ATTACHMENT_TYPES.register("disabled_tool_effects", disabledList());

    /** Ids of the weapon (on-hit) effects a player switched off in the Weapon Effects screen. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_WEAPON_EFFECTS = ATTACHMENT_TYPES.register("disabled_weapon_effects", disabledList());

    /** Ids of the ranged effects a player switched off in the Ranged Effects screen. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<List<String>>> DISABLED_RANGED_EFFECTS = ATTACHMENT_TYPES.register("disabled_ranged_effects", disabledList());

    /** The ranged runes an arrow was fired with. Saved with the arrow, the server is the only side using them. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ArrowRunes>> ARROW_RUNES = ATTACHMENT_TYPES.register("arrow_runes",
            () -> AttachmentType.builder(() -> ArrowRunes.EMPTY)
                    .serialize(ArrowRunes.MAP_CODEC.codec())
                    .build());

    private static Supplier<AttachmentType<List<String>>> disabledList() {
        return () -> AttachmentType.<List<String>>builder(() -> List.of())
                .serialize(Codec.STRING.listOf())
                .copyOnDeath()
                .build();
    }
}
