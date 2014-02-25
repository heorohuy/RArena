package rarena.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.tileentities.TileEntityArenaStarter;
import rarena.tileentities.TileEntityPlayerRegister;

public class BlockPlayerRegister extends Block implements ITileEntityProvider{

	public BlockPlayerRegister(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int hitSide, float hitX, float hitY, float hitZ)
    {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
    	if (entity != null && entity instanceof TileEntityPlayerRegister)
    	{
    		return ((TileEntityPlayerRegister)entity).registerPlayer(entityPlayer.getEntityName());
    	}
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPlayerRegister();
	}

}
