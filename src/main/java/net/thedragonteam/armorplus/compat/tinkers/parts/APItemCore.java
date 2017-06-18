package net.thedragonteam.armorplus.compat.tinkers.parts;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.thedragonteam.thedragonlib.util.TextUtils;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.IToolStationDisplay;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tinkering.TinkersItem;
import slimeknights.tconstruct.library.tools.IToolPart;

import java.util.List;

/**
 * Created by sokratis12GR on 5/9/2017.
 */
public class APItemCore extends TinkersItem implements IToolStationDisplay {

    public APItemCore(PartMaterialType... requiredComponents) {
        super(requiredComponents);
    }

    @Override
    public NBTTagCompound buildTag(List<Material> materials) {
        return null;
    }

    /**
     * The "title" displayed in the GUI
     *
     * @deprecated Use getLocalizedName for consistency
     */
    @Override
    public String getLocalizedToolName() {
        return TextUtils.INSTANCE.formattedText(getUnlocalizedName() + ".name");
    }

    @Override
    public String getLocalizedName() {
        return TextUtils.INSTANCE.formattedText(getUnlocalizedName() + ".name");
    }

    /**
     * Returns an List of Strings, where each String represents an information about the tool. Used to display
     * Information about the item in the GUI
     *
     * @param stack
     */
    @Override
    public List<String> getInformation(ItemStack stack) {
        return null;
    }

    public static PartMaterialType helmetLeft(IToolPart part) {
        return new PartMaterialType(part, MaterialTypesImproved.HELMET_LEFT);
    }

    public static PartMaterialType helmetMiddle(IToolPart part) {
        return new PartMaterialType(part, MaterialTypesImproved.HELMET_MIDDLE);
    }

    public static PartMaterialType helmetRight(IToolPart part) {
        return new PartMaterialType(part, MaterialTypesImproved.HELMET_RIGHT);
    }

    /**
     * Detailed info about the tool. Displayed when Shift is held
     *
     * @param stack
     * @param tooltips
     */
    @Override
    public void getTooltipDetailed(ItemStack stack, List<String> tooltips) {

    }

    /**
     * What the tool is made out of. Displayed whet Ctrl is held
     *
     * @param stack
     * @param tooltips
     */
    @Override
    public void getTooltipComponents(ItemStack stack, List<String> tooltips) {

    }
}
