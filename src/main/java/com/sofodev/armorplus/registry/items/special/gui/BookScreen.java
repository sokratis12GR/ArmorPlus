package com.sofodev.armorplus.registry.items.special.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sofodev.armorplus.registry.APItems;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class BookScreen extends Screen {

    public static final ResourceLocation BOOK_TEXTURES = setRL("textures/gui/paged_book.png");

    public BookScreen() {
        super(NarratorChatListener.EMPTY);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        ItemRenderer itemRenderer = this.getMinecraft().getItemRenderer();
        TextureManager textureManager = this.getMinecraft().getTextureManager();
        this.renderBackground(matrixStack);
        RenderSystem.blendColor(1.0F, 1.0F, 1.0F, 1.0F);
        textureManager.bindTexture(setRL("textures/gui/paged_book.png"));
        int x = (this.width - 255) / 2;
        int y = (this.height - 255) / 2;
        blit(matrixStack, x / 2, y, 0, 0, 512, 256, 512, 256);
        this.renderItemStack(itemRenderer, APItems.ENDER_DRAGON_SCALE.get(), (x / 2) + 22, y + 20);
        itemRenderer.renderItemOverlayIntoGUI(font, new ItemStack(APItems.ENDER_DRAGON_SCALE.get()), x + 20, y + 20, "");

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void renderItemStack(ItemRenderer itemRenderer, IItemProvider item, int posX, int posY) {
        ItemStack stack = new ItemStack(item);
        itemRenderer.renderItemAndEffectIntoGUI(stack, posX, posY);
        itemRenderer.renderItemOverlays(this.font, stack, posX, posY);
    }
}
