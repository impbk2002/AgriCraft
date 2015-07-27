package com.InfinityRaider.AgriCraft.blocks;

import com.InfinityRaider.AgriCraft.AgriCraft;
import com.InfinityRaider.AgriCraft.farming.CropPlantHandler;
import com.InfinityRaider.AgriCraft.handler.GuiHandler;
import com.InfinityRaider.AgriCraft.reference.Constants;
import com.InfinityRaider.AgriCraft.tileentity.storage.TileEntitySeedStorage;
import com.InfinityRaider.AgriCraft.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSeedStorage extends BlockCustomWood {
    public BlockSeedStorage() {
        super();
    }
    private IIcon frontIcon;
    private IIcon sideIcon;

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySeedStorage();
    }

    //this sets the block's orientation based upon the direction the player is looking when the block is placed
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if(world.getTileEntity(x, y, z) instanceof TileEntitySeedStorage) {
            TileEntitySeedStorage te = (TileEntitySeedStorage) world.getTileEntity(x, y, z);
            int direction = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            switch(direction) {
                case 0: te.setDirection(ForgeDirection.NORTH.ordinal()); break;
                case 1: te.setDirection(ForgeDirection.EAST.ordinal()); break;
                case 2: te.setDirection(ForgeDirection.SOUTH.ordinal()); break;
                case 3: te.setDirection(ForgeDirection.WEST.ordinal()); break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fX, float fY, float fZ) {
        LogHelper.debug("Right clicked block");
        if(!world.isRemote) {
            ItemStack stack = player.getCurrentEquippedItem();
            TileEntity te = world.getTileEntity(x, y, z);
            if(te==null || !(te instanceof TileEntitySeedStorage)) {
                return false;
            }
            TileEntitySeedStorage storage = (TileEntitySeedStorage) te;
            if(CropPlantHandler.isValidSeed(stack)) {
                LogHelper.debug("Trying to lock seed storage to "+stack.getDisplayName());
                storage.setLockedSeed(stack.getItem(), stack.getItemDamage());
            }
            else if(storage.hasLockedSeed()){
                player.openGui(AgriCraft.instance, GuiHandler.seedStorageID, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int data) {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(id, data);

    }

    //render methods
    //--------------
    @Override
    public int getRenderType() {return AgriCraft.proxy.getRenderId(Constants.seedStorageId);}                 //get the correct renderId

    @Override
    public boolean isOpaqueCube() {return false;}           //tells minecraft that this is not a block (no levers can be placed on it, it's transparent, ...)

    @Override
    public boolean renderAsNormalBlock() {return false;}    //tells minecraft that this has custom rendering

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int i) {return true;}

    //register icons
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.frontIcon = reg.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1)+"Front");
        this.sideIcon = reg.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1)+"Side");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconForSide(int side) {
        if(side==0) {
            return this.frontIcon;
        }
        return this.sideIcon;
    }
}
