package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.RArenaMod;
import rarena.tileentities.TileEntityPlayerRegister;

public class BlockPlayerRegister extends Block implements ITileEntityProvider{

	public BlockPlayerRegister(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int hitSide, float hitX, float hitY, float hitZ)
    {
		
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		String added = "Player " + entityPlayer.getEntityName() + " is now registered.";
		String notAdded = "Player "+ entityPlayer.getEntityName() +" already registered.";
    	if (entity != null && entity instanceof TileEntityPlayerRegister)
    	{
    		if(((TileEntityPlayerRegister)entity).registerPlayer(entityPlayer.getEntityName())){
    			RArenaMod.sendChat(entityPlayer,added);
    			return true;
    		}
    		RArenaMod.sendChat(entityPlayer,notAdded);
    	}
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPlayerRegister();
	}

}
