package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvanced extends ItemBase {

    private final TextFormatting formatting;
    private final String displayName;

    public ItemAdvanced(String itemName, TextFormatting formatting, String displayName) {
        super(itemName);
        this.formatting = formatting;
        this.displayName = displayName;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity(displayName.toUpperCase().replace(" ", "_"), formatting, displayName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        super.initModel("material", 0);
    }
}
