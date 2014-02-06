package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.tileentities.TileEntityArenaSpawner;

public class BlockArenaSpawner extends Block implements ITileEntityProvider{

	public BlockArenaSpawner(int blockID, Material material) {
		super(blockID, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityArenaSpawner();
	}
	
	

}
