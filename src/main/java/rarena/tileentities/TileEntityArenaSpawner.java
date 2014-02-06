package rarena.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import rarena.ArenaRegistry;

public class TileEntityArenaSpawner extends TileEntity
{
	
	private String arenaName = "testy";
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setString("ArenaName", arenaName);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		arenaName = tag.getString("ArenaName");
		ArenaRegistry.createArena(arenaName).registerSpawner(xCoord, yCoord, zCoord, worldObj.provider.dimensionId);
	}

}
