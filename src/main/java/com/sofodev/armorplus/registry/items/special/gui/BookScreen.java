package com.sofodev.armorplus.registry.items.special.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class BookScreen extends Screen {

    public static final ResourceLocation BOOK_TEXTURES = setRL("textures/gui/paged_book.png");

    public BookScreen() {
        super(NarratorChatListener.NO_TITLE);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        ItemRenderer itemRenderer = this.getMinecraft().getItemRenderer();
        TextureManager textureManager = this.getMinecraft().getTextureManager();
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        textureManager.bindForSetup(setRL("textures/gui/paged_book.png"));
        int x = (this.width - 255) / 2;
        int y = (this.height - 255) / 2;
        blit(matrixStack, x / 2, y, 0, 0, 512, 256, 512, 256);
        this.renderItemStack(itemRenderer, ModItems.ENDER_DRAGON_SCALE.get(), (x / 2) + 22, y + 20);
        itemRenderer.renderGuiItemDecorations(font, new ItemStack(ModItems.ENDER_DRAGON_SCALE.get()), x + 20, y + 20, "");

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void renderItemStack(ItemRenderer itemRenderer, ItemLike item, int posX, int posY) {
        ItemStack stack = new ItemStack(item);
        itemRenderer.renderAndDecorateItem(stack, posX, posY);
        itemRenderer.renderGuiItemDecorations(this.font, stack, posX, posY);
    }
}
