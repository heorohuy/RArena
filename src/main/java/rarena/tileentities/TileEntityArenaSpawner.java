package rarena.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import rarena.ArenaRegistry;

public class TileEntityArenaSpawner extends TileEntity
{
	private boolean registered = false;
	private String arenaName = "testy";

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setString("ArenaName", arenaName);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		arenaName = tag.getString("ArenaName");
	}
	
	@Override
	public void updateEntity()
	{
		ArenaRegistry.createArena(arenaName).registerSpawner(xCoord, yCoord, zCoord, worldObj.provider.dimensionId);		
		registered = true;
	}
	
	@Override
	public boolean canUpdate()
	{
		return !registered;
	}
}
