package rarena.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import rarena.ArenaData;
import rarena.ArenaRegistry;

public class TileEntityPlayerRegister extends TileEntity
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
	
	public boolean registerPlayer(EntityPlayer player){
		ArenaData arena = ArenaRegistry.createArena(arenaName);
		arena.broadcastMessage(player.getDisplayName() + " has joined the arena.");
		return arena.registerPlayer(player);
	}

}
