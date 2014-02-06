package rarena.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rarena.tileentities.TileEntityArenaSpawner;

public class BlockArenaSpawner extends Block implements ITileEntityProvider
{
	
	public BlockArenaSpawner(int blockID, Material material)
	{
		super(blockID, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityArenaSpawner();
	}
	
}
