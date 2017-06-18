package net.thedragonteam.armorplus.compat.tinkers.parts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.thedragonteam.armorplus.compat.tinkers.TiC;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helmet extends APItemCore {

    // helmetLeft, right, middle
    public Helmet() {
        this(APItemCore.helmetLeft(TiC.helmetLeft),
                APItemCore.helmetMiddle(TiC.helmetMiddle),
                APItemCore.helmetRight(TiC.helmetRight));
    }

    public Helmet(PartMaterialType... requiredComponents) {
        super(requiredComponents);
        this.addCategory(TiC.ARMOR);
    }

    @Override
    public NBTTagCompound buildTag(List<Material> materials) {
        return null;
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return super.isValidArmor(stack, EntityEquipmentSlot.HEAD, entity);
    }

    @Override
    public void getSubItems(@NotNull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        addDefaultSubItems(subItems);
    }

    protected void addDefaultSubItems(List<ItemStack> subItems, Material... fixedMaterials) {
        for (Material head : TinkerRegistry.getAllMaterials()) {
            List<Material> mats = IntStream.range(0, requiredComponents.length).mapToObj(i -> fixedMaterials.length > i && fixedMaterials[i] != null && requiredComponents[i].isValidMaterial(fixedMaterials[i]) ? fixedMaterials[i] : head).collect(Collectors.toCollection(() -> new ArrayList<>(requiredComponents.length)));

            ItemStack tool = buildItem(mats);
            // only valid ones
            if (hasValidMaterials(tool)) {
                subItems.add(tool);
                if (!Config.listAllMaterials) break;
            }
        }
    }
}