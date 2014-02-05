package rarena.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArenaStarter extends TileEntity{
	
	private String arenaName;
	
	public void writeToNBT(NBTTagCompound NBTTag){
		super.writeToNBT(NBTTag);
		NBTTag.setString("ArenaName", arenaName);
	}
	
	public void readFromNBT(NBTTagCompound NBTTag){
		super.readFromNBT(NBTTag);
		arenaName = NBTTag.getString("ArenaName");
	}

}
