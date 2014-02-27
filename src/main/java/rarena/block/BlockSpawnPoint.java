package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.tileentities.TileEntitySpawnPoint;

public class BlockSpawnPoint extends Block implements ITileEntityProvider{

	public BlockSpawnPoint(int blockID, Material material) {
		super(blockID, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySpawnPoint();
	}

}
