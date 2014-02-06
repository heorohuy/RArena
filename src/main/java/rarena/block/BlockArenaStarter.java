package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.tileentities.TileEntityArenaStarter;

public class BlockArenaStarter extends Block implements ITileEntityProvider{
	
	public BlockArenaStarter(int blockID, Material material) {
		super(blockID, material);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID){
    	
		x = x;
        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
        	// Start the battle!
        	TileEntity entity = world.getBlockTileEntity(x, y, z);
        	if(entity != null && entity instanceof TileEntityArenaStarter){
        		((TileEntityArenaStarter)entity).startBattle();
        	}
        }

    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityArenaStarter();
	}

}
