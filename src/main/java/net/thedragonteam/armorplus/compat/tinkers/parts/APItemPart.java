//package net.thedragonteam.armorplus.compat.tinkers.parts;

//public class APItemPart extends MaterialItem implements IToolPart {
//
//    protected int cost;
//
//    public APItemPart(int cost) {
//        this.setCreativeTab(ArmorPlus.tabArmorplusTinkers);
//        this.cost = cost;
//    }
//
//    /**
//     * Returns the cost to craft the item. Values match the ingot values<br>
//     * 72 = 1 shard<br>
//     * 144 = 1 ingot<br>
//     * etc.<br>
//     * Check the Material class for values
//     */
//    @Override
//    public int getCost() {
//        return cost;
//    }
//
//    /**
//     * Retruns true if the material can be used for this item part
//     *
//     * @param mat
//     */
//    @SuppressWarnings("JavaDoc")
//    @Override
//    public boolean canUseMaterial(Material mat) {
//        return true;
//    }
//
//    @Override
//    public boolean hasUseForStat(String stat) {
//        return false;
//    }
//
//    /**
//     * Return true if the item part should be registered for crafting in the stencil table, with a pattern
//     */
//    @Override
//    public boolean canBeCrafted() {
//        return true;
//    }
//
//    /**
//     * Return true if the item part should be registered for casting, using a cast
//     */
//    @Override
//    public boolean canBeCasted() {
//        return true;
//    }
//}
//