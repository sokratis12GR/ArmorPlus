package sokratis12GR.ArmorPlus.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.blocks.base.BlockArmorPlus;
import sokratis12GR.ArmorPlus.inventory.ContainerArmorWorkshop;
import sokratis12GR.ArmorPlus.registry.ModBlocks;


public class BlockArmorWorkshop extends BlockArmorPlus {

    public static Block BlockArmorWorkshop;
    public Object instance;

    public BlockArmorWorkshop() {
        super(Material.iron);

        setRegistryName("BlockArmorWorkshop");
        setUnlocalizedName("BlockArmorWorkshop");     // Used for localization (en_US.lang)
        setCreativeTab(ArmorPlus.tabArmorPlus);
        setHardness(4.0F);
        setHarvestLevel("pickaxe", 2);
        GameRegistry.register(this);
    }

    public void registerRenderers() {
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        playerIn.openGui(ArmorPlus.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        if (worldIn.isRemote) {
            return false;
        } else {
            playerIn.displayGui(new BlockArmorWorkshop.InterfaceArmorWorkshop(worldIn, pos));
            return true;
        }
    }

    public static class InterfaceArmorWorkshop implements IInteractionObject {
        private final World world;
        private final BlockPos position;

        public InterfaceArmorWorkshop(World worldIn, BlockPos pos) {
            this.world = worldIn;
            this.position = pos;
        }

        /**
         * Get the name of this object. For players this returns their username
         */
        public String getName() {
            return null;
        }

        /**
         * Returns true if this thing is named
         */
        public boolean hasCustomName() {
            return false;
        }

        /**
         * Get the formatted ChatComponent that will be used for the sender's username in chat
         */
        public ITextComponent getDisplayName() {
            return new TextComponentTranslation(BlockArmorWorkshop.getUnlocalizedName() + ".name", new Object[0]);
        }

        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerArmorWorkshop(playerInventory, this.world, this.position);
        }

        public String getGuiID() {
            return "armorplus:BlockArmorWorkshop";
        }
    }
}
