/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.ABILITY_REGISTRY;
import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.NONE;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.PotionUtils.getPotion;
import static net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class AbilityData extends IForgeRegistryEntry.Impl<AbilityData> {

    /**
     * Ability's name
     */
    private final String name;
    /**
     * The slots that the ability is available for
     */
    private final EntityEquipmentSlot[] slot;
    /**
     * The materials that the ability is available for
     */
    private final Material[] materials;

    public AbilityData(ResourceLocation rl, String name, EntityEquipmentSlot[] slot, Material... materials) {
        this.setRegistryName(rl);
        this.name = name;
        this.slot = slot;
        this.materials = materials;
    }

    public AbilityData(String rl, String name, EntityEquipmentSlot[] slot, Material... materials) {
        this(new ResourceLocation(rl), name, slot, materials);
    }

    public AbilityData(String rl, String name, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, EntityEquipmentSlot d, Material... materials) {
        this(rl, name, new EntityEquipmentSlot[]{a, b, c, d}, materials);
    }

    public AbilityData(String rl, String name, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, Material... materials) {
        this(rl, name, a, b, c, null, materials);
    }

    public AbilityData(String rl, String name, EntityEquipmentSlot a, EntityEquipmentSlot b, Material... materials) {
        this(rl, name, a, b, null, null, materials);
    }

    public AbilityData(String rl, String name, EntityEquipmentSlot a, Material... materials) {
        this(rl, name, a, null, null, null, materials);
    }


    public String getName() {
        return name;
    }

    public String getSafeName() {
        return getName().toLowerCase().replace(" ", "_");
    }

    public EntityEquipmentSlot[] getSlots() {
        return slot;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public AbilityData getData() {
        return this;
    }

    private boolean isPotion = false;

    public boolean isPotion() {
        return isPotion;
    }

    public AbilityData setPotion(boolean potion) {
        isPotion = potion;
        return this;
    }

    /**
     * Applies a potion to the player onArmorTick internally (directly via {@link ItemArmorV2#onArmorTick}), is NOT affected by {@link AbilityData#onArmorTick(World, EntityPlayer, ItemStack)}
     */
    public AbilityData applyPotionToPlayer(EntityPlayer player) {
        AbilityPotion potion = new AbilityPotion(this.getRegistryName());
        if (isPotion() && this.getRegistryName() != null) {
            Potion actualPotion = getPotion(potion.getResourceLocation());
            if (actualPotion != null || !(player.isPotionActive(MobEffects.REGENERATION))) {
                addPotion(player, actualPotion, potion.getDuration(), potion.getAmplifier(), potion.isAmbientIn(), potion.getType());
            }
        }
        return this;
    }

    /**
     * Triggers when the armor piece ticks {@link ItemArmorV2#onArmorTick}
     */
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * Triggers when the player kills an entity {@link LivingDeathEvent}
     */
    public void onPlayerKillEntity(LivingDeathEvent event, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * Triggers when the player breaks a block, make sure to use checks to not overload the player for each broken block {@link BreakEvent}
     */
    public void onPlayerBreakBlock(BreakEvent event, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * Triggers when the wearer is set as hurt {@link LivingHurtEvent}
     */
    public void onPlayerPreDamaged(LivingHurtEvent event, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * Triggers just before the wearer is being hurt {@link LivingDamageEvent}
     */
    public void onPlayerDamaged(LivingDamageEvent event, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * Triggers when the entity jumps {@link LivingJumpEvent}
     */
    public void onJump(LivingJumpEvent event, ItemStack stack) {
        //No need for abstraction
    }

    /**
     * @param resourceLocation the registry name of the ability
     * @return AbilityData corresponding to the {@param resourceLocation} from {@link AbilityData#getRegistryName()}
     */
    public static AbilityData getData(ResourceLocation resourceLocation) {
        return ABILITY_REGISTRY.getValue(resourceLocation);
    }

    /**
     * @param safeID the safeID of the ability
     * @return AbilityData corresponding to the {@param safeID} from {@link AbilityData#getSafeName()}
     */
    public static AbilityData getData(String safeID) {
        return ABILITY_REGISTRY.getValuesCollection().stream().filter(dataEntry -> dataEntry.getSafeName().equals(safeID)).findFirst().orElse(NONE);
    }

    /**
     * @param stack       The {@link ItemStack} that we will be checking for availability
     * @param abilityData the ability data that we will use to check if it can be provided by the ItemStack
     * @return true if the given {@param stack} can provide the ability, we check if that's the case using first,
     * ItemStack's {@link Material} for match, then we get the equipment slot that the ItemStack is for and if it matches our ability's requirements.
     * We also return straight up false if the{@link ItemStack#getItem()} isn't instance of {@link ItemArmorV2}
     * @author Sokratis Fotkatzikis
     */
    public static boolean canProvide(ItemStack stack, AbilityData abilityData) {
        if (stack.getItem() instanceof ItemArmorV2) {
            ItemArmorV2 armor = ((ItemArmorV2) stack.getItem());
            for (Material mat : abilityData.getMaterials()) {
                if (mat == armor.material) {
                    for (EntityEquipmentSlot equipmentSlot : abilityData.getSlots()) {
                        if (armor.getEquipmentSlot() == equipmentSlot) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param stack The {@link ItemStack} that we will be checking for availability
     * @param rl    the ability's resource location
     * @see AbilityData#canProvide(ItemStack, String) for details
     */
    public static boolean canProvide(ItemStack stack, ResourceLocation rl) {
        return canProvide(stack, getData(rl));
    }

    /**
     * @param stack  The {@link ItemStack} that we will be checking for availability
     * @param safeID the safeID of the ability, which we will use together with the {@link AbilityData#getData(String)} to get the ability we need
     * @see AbilityData#canProvide(ItemStack, String) for details
     */
    public static boolean canProvide(ItemStack stack, String safeID) {
        return canProvide(stack, getData(safeID));
    }

    /**
     * @param handler the ability handler
     * @return true if the abilityList isn't empty, otherwise false
     */
    public static boolean hasAbilities(IAbilityHandler handler) {
        return !handler.getAbilities().isEmpty();
    }

    /**
     * @param handler the ability handler
     * @return true if there is enough space to add an additional ability and is within the limit, otherwise false
     */
    public static boolean hasRoomForAbilities(IAbilityHandler handler) {
        return handler.getAbilities().size() < handler.getLimit();
    }

    public static boolean contains(IAbilityHandler handler, ResourceLocation rl) {
        List<AbilityData> list = handler.getAbilities();
        return list.contains(getData(rl));
    }

    public static boolean contains(IAbilityHandler handler, AbilityData data) {
        return contains(handler, data.getRegistryName());
    }

    public static boolean contains(IAbilityHandler handler, String safeID) {
        return contains(handler, getData(safeID));
    }
}
