/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.baubles;

//public abstract class ItemBauble extends BaseItem implements IBauble {
//
//    public ItemBauble(String name) {
//        super(name);
//        setMaxStackSize(1);
//   }
//
//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
//        if (!world.isRemote) {
//            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
//            for (int i = 0; i < baubles.getSlots(); i++)
//                if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
//                    baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
//                    if (!player.capabilities.isCreativeMode) {
//                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
//                    }
//                    onEquipped(player.getHeldItem(hand), player);
//                    break;
//                }
//        }
//        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
//    }
//
//    @Override
//    public EnumRarity getRarity(ItemStack par1ItemStack) {
//       return EnumRarity.RARE;
//   }
//
//    @Override
//    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
//        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75F, 1.9f);
//    }
//
//    @Override
//    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
//        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75F, 2f);
//    }
//}