/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TrophyPacket implements IMessage {
    public TrophyPacket() {
    }

    public String entityID;
    public float scale;

    public TrophyPacket(String entityID) {
        this.entityID = entityID;
        this.scale = 0.5f;
    }

    public TrophyPacket(String entityID, float scale) {
        this.entityID = entityID;
        this.scale = scale;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, entityID);
        buf.writeFloat(scale);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = ByteBufUtils.readUTF8String(buf);
        scale = buf.readFloat();
    }

    public String getEntityID() {
        return entityID;
    }

    public float getScale() {
        return scale;
    }
}