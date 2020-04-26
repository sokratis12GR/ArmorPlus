package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.registry.ModDimensions;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.common.config.ModConfig.EntitiesConfig.skeletal_king;
import static com.sofodev.armorplus.common.config.ModConfig.MainConfig.maps;
import static com.sofodev.armorplus.common.util.TextUtils.translatedText;
import static java.lang.String.format;
import static net.minecraft.util.text.TextFormatting.*;

public class ItemCombinedMap extends ItemBase {

    public final Variants variant;

    public ItemCombinedMap(Variants variant) {
        super(variant.getName());
        this.variant = variant;
        this.setMaxDamage(1);
    }

    public DimensionType getDimension() {
        return variant.getType();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (maps.allowSpawnOfBosses && variant == Variants.MAP_OF_SACRIFICE && !world.isRemote) {
            EntitySkeletalKing king = new EntitySkeletalKing(world);
            king.setPosition(player.posX, player.posY, player.posZ);
            king.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(player)), null);
            world.spawnEntity(king);
            king.forceSpawn = true;
            heldStack.damageItem(2, player);
        }

        return new ActionResult(EnumActionResult.PASS, heldStack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (variant == Variants.MAP_OF_SACRIFICE) {
            tooltip.add(format("%s%s", GREEN, translatedText("item.armorplus.map_of_sacrifice.summon")));
            tooltip.add(format("%s%s", BLUE, translatedText("item.armorplus.map_of_sacrifice.usage")));
            tooltip.add(format("%s(%s %s%s)%s - %s(%s %s%s)", RED, translatedText("item.armorplus.map_of_sacrifice.health"), skeletal_king.health, RED,
                RESET, AQUA, translatedText("item.armorplus.map_of_sacrifice.armor"), skeletal_king.armor, AQUA));
            tooltip.add(format("%s%s%s %s", GRAY, ITALIC, translatedText("item.armorplus.map_of_sacrifice.allowed"), maps.allowSpawnOfBosses));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public enum Variants {
        MAP(null),
        MAP_OF_OVERWORLD(DimensionType.OVERWORLD),
        MAP_OF_THE_NETHER(DimensionType.NETHER),
        MAP_OF_THE_END(DimensionType.THE_END),
        MAP_OF_SACRIFICE(null),
        MAP_OF_THE_REALM(ModDimensions.REALM),
        MAP_OF_ARENA(ModDimensions.ARENA),
        MAP_OF_THE_SHAPER(null),
        MAP_OF_THE_ELDER(null);

        private final DimensionType type;

        Variants(DimensionType type) {
            this.type = type;
        }

        public DimensionType getType() {
            return type;
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
