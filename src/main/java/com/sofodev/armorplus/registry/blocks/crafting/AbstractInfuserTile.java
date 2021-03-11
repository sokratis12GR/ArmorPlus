package com.sofodev.armorplus.registry.blocks.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public abstract class AbstractInfuserTile extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private int infusionTime;
    private int recipesUsed;
    private int infusingTime;
    private int totalInfusingTime;
    protected final IIntArray infuserData = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return AbstractInfuserTile.this.infusionTime;
                case 1:
                    return AbstractInfuserTile.this.recipesUsed;
                case 2:
                    return AbstractInfuserTile.this.infusingTime;
                case 3:
                    return AbstractInfuserTile.this.totalInfusingTime;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    AbstractInfuserTile.this.infusionTime = value;
                    break;
                case 1:
                    AbstractInfuserTile.this.recipesUsed = value;
                    break;
                case 2:
                    AbstractInfuserTile.this.infusingTime = value;
                    break;
                case 3:
                    AbstractInfuserTile.this.totalInfusingTime = value;
            }

        }

        @Override
        public int size() {
            return 4;
        }
    };

    private final Map<ResourceLocation, Integer> itemInfusionList = Maps.newHashMap();
    protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;

    protected AbstractInfuserTile(TileEntityType<?> tileTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
        super(tileTypeIn);
        this.recipeType = recipeTypeIn;
    }

    public static Map<Item, Integer> getInfusionTimes() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemInfuseTime(map, Items.LAVA_BUCKET, 20000);
        addItemInfuseTime(map, ModItems.LAVA_CRYSTAL, 22000);
        addItemInfuseTime(map, ModItems.INFUSED_LAVA_CRYSTAL, 24000);
        return map;
    }

    private static void addItemTagBurnTime(Map<Item, Integer> map, Tag<Item> itemTag, int infusionTime) {
        itemTag.getAllElements().forEach(item -> map.put(item, infusionTime));
    }

    private static void addItemInfuseTime(Map<Item, Integer> map, IItemProvider itemProvider, int infusionTime) {
        map.put(itemProvider.asItem(), infusionTime);
    }

    private static void addItemInfuseTime(Map<Item, Integer> map, RegistryObject<Item> registryObject, int infusionTime) {
        map.put(registryObject.get(), infusionTime);
    }

    private boolean isInfusing() {
        return this.infusionTime > 0;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);
        this.infusionTime = nbt.getInt("InfusionTime");
        this.infusingTime = nbt.getInt("InfusingTime");
        this.totalInfusingTime = nbt.getInt("InfusingTimeTotal");
        this.recipesUsed = this.getInfusionTime(this.items.get(1));
        int i = nbt.getShort("RecipesUsedSize");

        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(nbt.getString("RecipeLocation" + j));
            int k = nbt.getInt("RecipeAmount" + j);
            this.itemInfusionList.put(resourcelocation, k);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        nbt.putInt("InfusionTime", this.infusionTime);
        nbt.putInt("InfusingTime", this.infusingTime);
        nbt.putInt("InfusingTimeTotal", this.totalInfusingTime);
        ItemStackHelper.saveAllItems(nbt, this.items);
        nbt.putShort("RecipesUsedSize", (short) this.itemInfusionList.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.itemInfusionList.entrySet()) {
            nbt.putString("RecipeLocation" + i, entry.getKey().toString());
            nbt.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return nbt;
    }

    @Override
    public void tick() {
        boolean flag = this.isInfusing();
        boolean flag1 = false;
        if (this.isInfusing()) {
            --this.infusionTime;
        }
        if (world == null) return;
        if (!this.world.isRemote) {
            ItemStack itemstack = this.items.get(1);
            if (this.isInfusing() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);
                if (!this.isInfusing() && this.canSmelt(irecipe)) {
                    this.infusionTime = this.getInfusionTime(itemstack);
                    this.recipesUsed = this.infusionTime;
                    if (this.isInfusing()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem())
                            this.items.set(1, itemstack.getContainerItem());
                        else if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.items.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isInfusing() && this.canSmelt(irecipe)) {
                    ++this.infusingTime;
                    if (this.infusingTime == this.totalInfusingTime) {
                        this.infusingTime = 0;
                        this.totalInfusingTime = this.totalInfusingTime();
                        this.getInfusionRecipe(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.infusingTime = 0;
                }
            } else if (!this.isInfusing() && this.infusingTime > 0) {
                this.infusingTime = MathHelper.clamp(this.infusingTime - 2, 0, this.totalInfusingTime);
            }

            if (flag != this.isInfusing()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isInfusing()), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }

    }

    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (!this.items.get(0).isEmpty() && recipeIn != null) {
            ItemStack itemstack = recipeIn.getRecipeOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.items.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    private void getInfusionRecipe(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.items.get(0);
            ItemStack itemstack1 = recipe.getRecipeOutput();
            ItemStack itemstack2 = this.items.get(2);
            if (itemstack2.isEmpty()) {
                this.items.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (!this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    protected int getInfusionTime(ItemStack stack) {
        return stack.isEmpty() ? 0 : stack.getBurnTime();
    }

    protected int totalInfusingTime() {
        return this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).map(AbstractCookingRecipe::getCookTime).orElse(200);
    }

    public static boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
    public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, stack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            return item == Items.BUCKET;
        }

        return true;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            this.totalInfusingTime = this.totalInfusingTime();
            this.infusingTime = 0;
            this.markDirty();
        }

    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.items.get(1);
            return isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null) {
            this.itemInfusionList.compute(recipe.getId(), (rl, value) -> 1 + (value == null ? 0 : value));
        }

    }

    @Override
    @Nullable
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void onCrafting(PlayerEntity player) {
    }

    public void noNameForThisYet(PlayerEntity player) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for (Map.Entry<ResourceLocation, Integer> entry : this.itemInfusionList.entrySet()) {
            player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                giveExperience(player, entry.getValue(), ((AbstractCookingRecipe) recipe).getExperience());
            });
        }

        player.unlockRecipes(list);
        this.itemInfusionList.clear();
    }

    private static void giveExperience(PlayerEntity player, int expValue, float infusionTime) {
        if (infusionTime == 0.0F) {
            expValue = 0;
        } else if (infusionTime < 1.0F) {
            int i = MathHelper.floor((float) expValue * infusionTime);
            if (i < MathHelper.ceil((float) expValue * infusionTime) && Math.random() < (double) ((float) expValue * infusionTime - (float) i)) {
                ++i;
            }

            expValue = i;
        }

        while (expValue > 0) {
            int j = ExperienceOrbEntity.getXPSplit(expValue);
            expValue -= j;
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.getPosX(), player.getPosY() + 0.5D, player.getPosZ() + 0.5D, j));
        }

    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        this.items.forEach(helper::accountStack);
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    /**
     * invalidates a tile entity
     */
    @Override
    public void remove() {
        super.remove();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }
}