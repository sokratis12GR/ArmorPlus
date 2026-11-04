package com.sofodev.armorplus.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

/**
 * Item model property "armorplus:socket_state": "none", "empty", "partial" or "full" (see {@link Runes#socketState(ItemStack)}).
 * The client item definitions of the ArmorPlus equipment use it to layer the socket symbol over the item in GUIs.
 */
public record SocketStateProperty() implements SelectItemModelProperty<String> {

    public static final SelectItemModelProperty.Type<SocketStateProperty, String> TYPE =
            SelectItemModelProperty.Type.create(MapCodec.unit(new SocketStateProperty()), Codec.STRING);

    @Override
    public String get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext context) {
        return Runes.socketState(stack);
    }

    @Override
    public Codec<String> valueCodec() {
        return Codec.STRING;
    }

    @Override
    public SelectItemModelProperty.Type<SocketStateProperty, String> type() {
        return TYPE;
    }
}
