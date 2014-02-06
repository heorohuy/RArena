package rarena.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import rarena.ArenaData;
import rarena.ArenaRegistry;

public class TileEntityArenaStarter extends TileEntity
{
	
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
	
	public void startBattle()
	{
		ArenaRegistry.createArena(arenaName).startBattle();
	}

}
