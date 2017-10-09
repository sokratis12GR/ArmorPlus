package net.thedragonteam.armorplus.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.DevItems;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class BaseDevItem extends BaseItem implements IModelHelper {

    private final DevItems devItems;

    public BaseDevItem(DevItems devItems) {
        super(devItems.getName());
        this.devItems = devItems;
        if (devItems.hasSubTypes()) {
            this.setHasSubtypes(true);
            this.setMaxDamage(0);
        }
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (devItems.hasSubTypes()) this.initModel(getRegistryName(), "_second", "cosmetics", 1);
        this.initModel(getRegistryName(), "cosmetics", 0);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            if (devItems.hasSubTypes()) {
                items.add(getItemStack(this, 0));
                items.add(getItemStack(this, 1));
            }
        }
    }

}
