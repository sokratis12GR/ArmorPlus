/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import static net.minecraft.item.Item.getByNameOrId;
import static net.minecraft.item.Item.getItemFromBlock;

public class ItemStackUtils {
    public static ItemStack getTICItemStack(String name, int meta) {
        return getItemStack(getByNameOrId("tconstruct:" + name), meta);
    }

    public static ItemStack getAPItemStack(String name, int meta) {
        return getItemStack(getByNameOrId("armorplus:" + name), meta);
    }

    public static ItemStack getAPItemStack(String name) {
        return getItemStack(getByNameOrId("armorplus:" + name), 0);
    }

    public static ItemStack getItemStack(String modid, String itemName, int meta) {
        return getItemStack(getByNameOrId(modid + ":" + itemName), meta);
    }

    public static ItemStack getItemStack(String name, int meta) {
        return getItemStack(getByNameOrId(name), meta);
    }

    public static ItemStack getItemStack(String name) {
        return getItemStack(getByNameOrId(name), 0);
    }

    public static ItemStack getItemStack(String modid, String itemName) {
        return getItemStack(getByNameOrId(modid + ":" + itemName), 0);
    }

    public static ItemStack getItemStack(Item item, int amount, int meta) {
        return new ItemStack(item, amount, meta);
    }

    public static ItemStack getItemStack(Item item, int meta) {
        return getItemStack(item, 1, meta);
    }

    public static ItemStack getItemStack(Item item) {
        return new ItemStack(item, 1);
    }

    public static ItemStack getItemStack(Block block, int amount, int meta) {
        return new ItemStack(block, amount, meta);
    }

    public static ItemStack getItemStack(Block block, int meta) {
        return getItemStack(block, 1, meta);
    }

    public static ItemStack getItemStack(Block block) {
        return new ItemStack(block, 1);
    }

    public static ItemStack getEmptyStack() {
        return ItemStack.EMPTY;
    }

    public static Item getItem(ItemStack stack) {
        return stack.getItem();
    }

    public static Item getItem(Block block) {
        return getItemFromBlock(block);
    }

    public static Item getItem(String modName, String itemName) {
        return getByNameOrId(modName + ":" + itemName);
    }

    public static Item getItem(String name) {
        return getByNameOrId(name);
    }

    public static Item getEmptyItem() {
        return Items.AIR;
    }

    public static ItemStack validateCopy(ItemStack stack){
        if(isValid(stack)){
            return stack.copy();
        }
        else{
            return getNull();
        }
    }

    public static ItemStack validateCheck(ItemStack stack){
        if(isValid(stack)){
            return stack;
        }
        else{
            return getNull();
        }
    }

    public static boolean isValid(ItemStack stack){
        return stack != null && !stack.isEmpty();
    }

    public static ItemStack getNull(){
        return ItemStack.EMPTY;
    }

    public static int getStackSize(ItemStack stack){
        if(!isValid(stack)){
            return 0;
        }
        else{
            return stack.getCount();
        }
    }

    public static ItemStack setStackSize(ItemStack stack, int size){
        return setStackSize(stack, size, false);
    }

    public static ItemStack setStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        if(size <= 0){
            if(isValid(stack) && containerOnEmpty){
                return stack.getItem().getContainerItem(stack);
            }
            else{
                return getNull();
            }
        }
        stack.setCount(size);
        return stack;
    }

    public static ItemStack addStackSize(ItemStack stack, int size){
        return addStackSize(stack, size, false);
    }

    public static ItemStack addStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        return setStackSize(stack, getStackSize(stack)+size, containerOnEmpty);
    }

    public static boolean isIInvEmpty(NonNullList<ItemStack> slots){
        for(ItemStack stack : slots){
            if(ItemStackUtils.isValid(stack)){
                return false;
            }
        }

        return true;
    }

    public static NonNullList<ItemStack> createSlots(int size){
        return NonNullList.withSize(size, getNull());
    }
}
