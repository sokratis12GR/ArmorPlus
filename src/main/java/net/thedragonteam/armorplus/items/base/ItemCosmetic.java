package net.thedragonteam.armorplus.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Cosmetics;

import javax.annotation.Nullable;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemCosmetic extends ItemBase implements IModelHelper {

    private final Cosmetics cosmetics;

    public ItemCosmetic(Cosmetics cosmetics) {
        super(cosmetics.getName());
        this.cosmetics = cosmetics;
        if (cosmetics.hasSubTypes()) {
            this.setHasSubtypes(true);
            this.setMaxDamage(0);
        }
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (cosmetics.hasSubTypes()) this.initModel(getRegistryName(), "_second", "cosmetics", 1);
        this.initModel(getRegistryName(), "cosmetics", 0);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab) && cosmetics.hasSubTypes()) {
            items.add(getItemStack(this, 0));
            items.add(getItemStack(this, 1));
        }
    }

}
