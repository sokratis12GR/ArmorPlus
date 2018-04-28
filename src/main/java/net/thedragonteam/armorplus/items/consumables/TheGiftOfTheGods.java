package net.thedragonteam.armorplus.items.consumables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.armorplus.util.ToolTipUtils;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;
import net.thedragonteam.thedragonlib.util.LogHelper;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.text.MessageFormat.format;
import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.armorplus.ModConfig.DebugConfig.debugMode;
import static net.thedragonteam.armorplus.ModConfig.DebugConfig.debugModeTGOTG;
import static net.thedragonteam.armorplus.ModConfig.MainConfig.tgotg;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.TextUtils.setTextTranslation;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class TheGiftOfTheGods extends BaseItem {

    private EnumRarity golden = addRarity("GOLD", GOLD, "GOLD");
    private Random random = new Random();

    public TheGiftOfTheGods() {
        super("the_gift_of_the_gods");
        int maxUsable = tgotg.maxUses - 1;
        this.setMaxDamage(maxUsable);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return golden;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        String[] blackListedItems = tgotg.blackListedItems;

        NBTTagCompound nbt = (playerIn.getHeldItem(hand).hasTagCompound()) ? playerIn.getHeldItem(hand).getTagCompound() : new NBTTagCompound();

        nbt.setInteger("Clicked", (nbt.hasKey("Clicked")) ? nbt.getInteger("Clicked") + 1 : 1);
        playerIn.getHeldItem(hand).setTagCompound(nbt);

        int count;
        Item item;
        do if (tgotg.enableWhiteList) {
            item = Item.getByNameOrId(tgotg.whiteListedItems[random.nextInt(tgotg.whiteListedItems.length + 1)]);
        } else {
            count = 256 + random.nextInt(32000 - 256);
            item = Item.getItemById(count);
        }
        while (item == null || getItemStack(item).isEmpty() || (tgotg.enableBlackList && item == ItemStackUtils.getItem(Arrays.toString(blackListedItems))));

        ItemStack heldMainHand = playerIn.getHeldItemMainhand(), heldOffHand = playerIn.getHeldItemOffhand();
        ItemStack heldStack = playerIn.getHeldItem(hand);
        CooldownTracker tracker = playerIn.getCooldownTracker();
        boolean flag = debugMode && debugModeTGOTG;
        if (!worldIn.isRemote) {
            if (tgotg.enable) {
                this.setCooldown(tracker, heldMainHand, heldStack, flag);
                this.setCooldown(tracker, heldOffHand, heldStack, flag);
                playerIn.dropItem(item, 1);
                playerIn.sendStatusMessage(setTextTranslation("status.armorplus.tgotg.gained_item", item.getItemStackDisplayName(playerIn.getHeldItem(hand)), item.getRegistryName()), false);
                if (debugMode && debugModeTGOTG)
                    LogHelper.info(format("Item's Registry Name: {0};\n Item''s Creative Tab: {1}\n{2}{3}", item.getRegistryName(), item.getCreativeTab(),
                        " ; Item's Unlocalized Name: " + item.getUnlocalizedName() + ";\n Does the Item have Subtypes: " + item.getHasSubtypes() + "\n",
                        " ; Item's Max Damage: " + getMaxDamage(getItemStack(item))));
            }
            playerIn.getHeldItem(hand).damageItem(1, playerIn);
        }
        return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(hand));
    }

    private void setCooldown(CooldownTracker tracker, ItemStack handStack, ItemStack heldStack, boolean flag) {
        int cd = 0;
        Item handItem = handStack.getItem();
        Item heldItem = heldStack.getItem();
        if (!handStack.isEmpty() && handItem == heldItem) {
            if (!flag && !tracker.hasCooldown(heldItem)) {
                tracker.setCooldown(handItem, tgotg.cooldownTicks);
            } else if (flag) {
                tracker.setCooldown(handItem, cd);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        int maxUses = tgotg.maxUses;
        tooltip.add(format("{0}{1}This item can summon items which can potentially cause crashes", ITALIC, RED));
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\u00a79Ability: \u00a7rGrants Random Item");
            tooltip.add("\u00a79Max Uses: \u00a7r" + maxUses);
            tooltip.add("\u00a73Use: \u00a7rRight-Click");
        } else {
            ToolTipUtils.showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

}