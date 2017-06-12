//package net.thedragonteam.armorplus.compat.minetweaker.lavainfuser;
//
//import net.minecraft.item.ItemStack;
//import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager;
//
//public class LavaInfuserRecipe {
//    public ItemStack input;
//    public ItemStack output;
//    public double xp;
//
//    public LavaInfuserRecipe(ItemStack input, ItemStack output, double xp) {
//        this.input = input;
//        this.output = output;
//        this.xp = xp;
//        LavaInfuserManager.getInstance().addInfusingRecipe(input, output, xp);
//    }
//
//    public LavaInfuserRecipe(ItemStack input, ItemStack output) {
//        this(input, output, 0.0D);
//    }
//
//    public String toCommandString() {
//        return String.format("mods.armorplus.LavaInfuser.addRecipe(%s, %s, %s)", this.output, this.input, this.xp);
//    }
//
//    public ItemStack getRecipeOutput() {
//        return this.output;
//    }
//}
