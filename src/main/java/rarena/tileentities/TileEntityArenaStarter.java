package rarena.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import rarena.ArenaData;
import rarena.ArenaRegistry;

public class TileEntityArenaStarter extends TileEntity{
	
	private String arenaName = "testy";
	
	public void writeToNBT(NBTTagCompound NBTTag){
		super.writeToNBT(NBTTag);
		NBTTag.setString("ArenaName", arenaName);
	}
	
	public void readFromNBT(NBTTagCompound NBTTag){
		super.readFromNBT(NBTTag);
		arenaName = NBTTag.getString("ArenaName");
	}
	
	public void startBattle(){
		ArenaRegistry.createArena(arenaName).startBattle();
	}

}
