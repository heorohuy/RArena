package rarena.tileentities;

import rarena.ArenaRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDeathPoint extends TileEntity{
	
	private String arenaName = "testy";
	private boolean registered = false;

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
		ArenaRegistry.createArena(arenaName).registerDeathPoint(xCoord, yCoord, zCoord, worldObj.provider.dimensionId);		
		registered = true;
	}
	
	@Override
	public boolean canUpdate()
	{
		return !registered;
	}

}
