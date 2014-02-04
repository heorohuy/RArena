package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArenaStarter extends Block implements ITileEntityProvider{
	
	public BlockArenaStarter(int blockID, Material material) {
		super(blockID, material);
		// TODO Auto-generated constructor stub
	}
	
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID){
    	
        if(world.isBlockIndirectlyGettingPowered(x, y, z)){
        	//TODO Start the RArena.
        }

    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
