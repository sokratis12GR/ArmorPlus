package net.thedragonteam.armorplus.client.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class RenderCosmetics {

    public ItemStack renderCosmetics;

    public RenderCosmetics(ItemStack renderedItemStack) {
        this.renderCosmetics = renderedItemStack;
    }

    @SideOnly(Side.CLIENT)
    public static void render(RenderCosmetics cosmetics, EntityPlayer player, float partialTicks) {
        if (player.isInvisible() || !player.isWearing(EnumPlayerModelParts.CAPE) || player.isPotionActive(MobEffects.INVISIBILITY)) {
            return;
        }
        boolean isBlock = cosmetics.renderCosmetics.getItem() instanceof ItemBlock;

        GlStateManager.pushMatrix();

        Vec3d currentPos = Minecraft.getMinecraft().player.getPositionEyes(partialTicks);
        Vec3d playerPos = player.getPositionEyes(partialTicks);
        GlStateManager.translate(playerPos.x - currentPos.x, playerPos.y - currentPos.y, playerPos.z - currentPos.z);

        GlStateManager.translate(0.0, 2.375 - ((player.isSneaking()) ? 0.125 : 0.0) + (isBlock ? 0.0 : 0.1875), 0.0);
        GlStateManager.rotate(180f, 1.0f, 0.0f, 1.0f);

        float size = (isBlock) ? 0.5f : 0.4f;
        GlStateManager.scale(size, size, size);

        double boop = Minecraft.getSystemTime() / 1000.0;
        GlStateManager.translate(0.0, Math.sin(boop % (2 * Math.PI)) * 0.25, 0.0);
        GlStateManager.rotate((float) (boop * 40.0 % 360), 0f, 1f, 0f);

        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        if (!isBlock) GlStateManager.translate(0.0, 0.5, 0.0);
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        renderItemInWorld(cosmetics.renderCosmetics);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();

        GlStateManager.popMatrix();
    }

    @SideOnly(Side.CLIENT)
    private static void renderItemInWorld(ItemStack stack) {
        if (stack.getCount() > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

}