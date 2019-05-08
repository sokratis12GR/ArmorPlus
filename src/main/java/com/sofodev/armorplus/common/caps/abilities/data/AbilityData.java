/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.caps.abilities.data;

import com.sofodev.armorplus.common.caps.abilities.CapabilityAbility.IArmorAbilityHandler;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemArmorV2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.common.caps.abilities.CapabilityAbility.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.common.caps.abilities.ImplementedAbilities.ARMOR_ABILITY_REGISTRY;
import static com.sofodev.armorplus.common.caps.abilities.ImplementedAbilities.NONE;
import static com.sofodev.armorplus.common.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.common.util.PotionUtils.getPotion;
import static net.minecraft.init.MobEffects.REGENERATION;
import static net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class AbilityData extends ForgeRegistryEntry<AbilityData> {

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
    private final MaterialType[] materials;

    public AbilityData(ResourceLocation rl, String translatableName, EquipmentSlot slot, MaterialType... materials) {
        this.setRegistryName(rl);
        this.name = translatableName;
        this.slot = slot.getSlots();
        this.materials = materials;
    }

    public AbilityData(String rl, String translatableName, EquipmentSlot slot, MaterialType... materials) {
        this(new ResourceLocation(rl), translatableName, slot, materials);
    }

    public AbilityData(String rl, String translatableName, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, EntityEquipmentSlot d, MaterialType... materials) {
        this(rl, translatableName, new EquipmentSlot(a, b, c, d), materials);
    }

    public AbilityData(String rl, String translatableName, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, MaterialType... materials) {
        this(rl, translatableName, new EquipmentSlot(a, b, c), materials);
    }

    public AbilityData(String rl, String translatableName, EntityEquipmentSlot a, EntityEquipmentSlot b, MaterialType... materials) {
        this(rl, translatableName, new EquipmentSlot(a, b), materials);
    }

    public AbilityData(String rl, String translatableName, EntityEquipmentSlot a, MaterialType... materials) {
        this(rl, translatableName, new EquipmentSlot(a), materials);
    }

    public String getName() {
        return new TextComponentTranslation(name).getFormattedText();
    }

    public String getSafeName() {
        return getName().toLowerCase().replace(" ", "_");
    }

    public EntityEquipmentSlot[] getSlots() {
        return slot;
    }

    public MaterialType[] getMaterials() {
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
     * Applies a potion to the player onArmorTick internally (directly via {@link ItemArmorV2#onArmorTick}), is NOT affected by {@link AbilityData#onArmorTick(ItemStack, World, EntityPlayer)}
     */
    public AbilityData applyPotionToPlayer(EntityPlayer player) {
        AbilityPotion potion = new AbilityPotion().setResourceLocation(this.getRegistryName());
        if (isPotion() && this.getRegistryName() != null) {
            Potion actualPotion = getPotion(potion.getResourceLocation());
            //Here we hardcode check if the player doesn't have the regeneration ability because it needs ticking time to be able to apply its effect, if its re-applied instantly the ability doesnt go into effect.
            if (actualPotion != null || !(player.isPotionActive(REGENERATION))) {
                addPotion(player, actualPotion, potion.getDuration(), potion.getAmplifier(), potion.isAmbientIn(), potion.getType());
            }
        }
        return this;
    }

    // These two are unique for ItemArmorV2, if you want to inherit its properties call AbilityData.provideArmorAbilities inside onArmorTick for you item (type: armor)

    /**
     * Triggers when the armor piece ticks {@link ItemArmorV2#onArmorTick}
     * <p>
     * Caution: This event doesn't check whether or not the Ability can be provided by other EntityEquipmentSlots, you will have to make sure to check if it can be provided {@link ItemArmorV2#onArmorTick(ItemStack, World, EntityPlayer)}
     */
    public void onSpecialArmorTick(ItemStack stack, World world, EntityPlayer player) {
        //No need for abstraction
    }

    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        //No need for abstraction
    }

    /**
     * Triggers when the player kills an entity {@link LivingDeathEvent}
     */
    public void onPlayerKillEntity(ItemStack stack, LivingDeathEvent event) {
        //No need for abstraction
    }

    /**
     * Triggers when the player breaks a block, make sure to use checks to not overload the player for each broken block {@link BreakEvent}
     */
    public void onPlayerBreakBlock(ItemStack stack, BreakEvent event) {
        //No need for abstraction
    }

    /**
     * Triggers when the wearer is set as hurt {@link LivingHurtEvent}
     */
    public void onPlayerPreDamaged(ItemStack stack, LivingHurtEvent event) {
        //No need for abstraction
    }

    /**
     * Triggers just before the wearer is being hurt {@link LivingDamageEvent}
     */
    public void onPlayerDamaged(ItemStack stack, LivingDamageEvent event) {
        //No need for abstraction
    }

    /**
     * Triggers when the wearer jumps {@link LivingJumpEvent}
     */
    public void onPlayerJump(ItemStack stack, LivingJumpEvent event) {
        //No need for abstraction
    }

    /**
     * @param resourceLocation the registry name of the ability
     * @return AbilityData corresponding to the {@param resourceLocation} from {@link AbilityData#getRegistryName()}
     */
    public static AbilityData getData(ResourceLocation resourceLocation) {
        return ARMOR_ABILITY_REGISTRY.getValue(resourceLocation);
    }

    /**
     * @param safeID the safeID of the ability
     * @return AbilityData corresponding to the {@param safeID} from {@link AbilityData#getSafeName()}
     */
    public static AbilityData getData(String safeID) {
        return ARMOR_ABILITY_REGISTRY.getValues().stream().filter(dataEntry -> dataEntry.getSafeName().equals(safeID)).findFirst().orElse(NONE);
    }

    /**
     * @param data the ability data that we want to get the resource location from
     * @return the resource location for the given {@param data} ability
     */
    public static ResourceLocation getResourceLocation(AbilityData data) {
        return ARMOR_ABILITY_REGISTRY.getKey(data);
    }

    /**
     * @param stack       The {@link ItemStack} that we will be checking for availability
     * @param abilityData the ability data that we will use to check if it can be provided by the ItemStack
     * @return true if the given {@param stack} can provide the ability, we check if that's the case using first,
     * ItemStack's {@link MaterialType} for match, then we get the equipment slot that the ItemStack is for and if it matches our ability's requirements.
     * We also return straight up false if the{@link ItemStack#getItem()} isn't instance of {@link ItemArmorV2}
     * @author Sokratis Fotkatzikis
     */
    public static boolean canProvide(ItemStack stack, AbilityData abilityData) {
        if (stack.isEmpty()) return false;
        Item item = stack.getItem();
        ISpecialItem special = ((ISpecialItem) item);
        if (special.isSpecialArmor(stack)) {
            return hasEqualMaterials(abilityData, special, hasEqualSlots(abilityData, special));
        }
        return false;
    }

    private static boolean hasEqualMaterials(AbilityData abilityData, ISpecialItem special, boolean additionalChecks) {
        for (MaterialType mat : abilityData.getMaterials()) {
            if (mat != null && mat == special.getMaterial()) {
                return additionalChecks;
            }
        }
        return false;
    }

    private static boolean hasEqualSlots(AbilityData abilityData, ISpecialItem armor) {
        return Arrays.stream(abilityData.getSlots()).anyMatch(equipmentSlot -> equipmentSlot != null && equipmentSlot == armor.getSlot());
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
    public static boolean hasAbilities(IArmorAbilityHandler handler) {
        return !handler.getAbilities().isEmpty();
    }

    /**
     * @param handler the ability handler
     * @return true if there is enough space to add an additional ability and is within the limit, otherwise false
     */
    public static boolean hasRoomForAbilities(IArmorAbilityHandler handler) {
        return handler.getAbilities().size() < handler.getLimit();
    }

    public static boolean contains(ItemStack stack, ResourceLocation rl) {
        if (stack.isEmpty()) return false;
        return stack.getCapability(CAPABILITY_ABILITIES).map(handler -> {
                List<AbilityData> list = handler.getAbilities();
                return list.contains(getData(rl));
            }
        ).orElse(false);
    }

    public static boolean contains(ItemStack stack, AbilityData data) {
        return contains(stack, data.getRegistryName());
    }

    public static boolean contains(ItemStack stack, String safeID) {
        return contains(stack, getData(safeID));
    }

    /**
     * Used inside {@link ItemArmorV2#onArmorTick(ItemStack, World, EntityPlayer)} to provide armor abilities.
     * Call this on your own (onArmorTick) if you use a custom {@link ItemArmor}
     */
    public static void provideArmorAbilities(ItemStack stack, World world, EntityPlayer player) {
        if (stack.isEmpty()) return;
        stack.getCapability(CAPABILITY_ABILITIES).filter(AbilityData::hasAbilities).ifPresent(handler -> {
                for (AbilityData data : handler.getAbilities()) {
                    if (contains(stack, data)) {
                        if (canProvide(stack, data)) {
                            if (data.isPotion()) {
                                data.applyPotionToPlayer(player);
                            }
                            data.onArmorTick(stack, world, player);
                        }
                        data.onSpecialArmorTick(stack, world, player);
                    }
                }
            }
        );
    }
}
