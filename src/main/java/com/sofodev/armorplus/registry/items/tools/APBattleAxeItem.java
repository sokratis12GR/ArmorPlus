package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.BATTLE_AXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;

public class APBattleAxeItem extends AxeItem implements Tool {

    private final IAPTool mat;

    public APBattleAxeItem(IAPTool mat) {
        super(mat.get(), mat.get().getAttackDamageBonus() + BATTLE_AXE.getDmg(), BATTLE_AXE.getAttackSpeed(), new Item.Properties().tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level.isClientSide && mat.config().enableWeaponEffects.get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        addBuffInformation(mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects.get());
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    public IAPTool getMat() {
        return this.mat;
    }
}