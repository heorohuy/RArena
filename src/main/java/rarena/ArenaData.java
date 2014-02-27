package rarena;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import rarena.util.Point4D;
import rarena.util.Teleport;

public class ArenaData
{
	private final String arenaName;
	private boolean battleInProgress;
	private BattleData battleData;
	private ArrayList<Point4D> spawnerPositions;
	private ArrayList<String> registeredPlayers;
	private Point4D deathPoint;

	public ArenaData(String arenaName)
	{
		this.arenaName = arenaName;
		this.battleInProgress = false;
		this.spawnerPositions = new ArrayList<Point4D>();
		this.battleData = null;
		this.registeredPlayers = new ArrayList<String>();
		this.deathPoint = null;
	}

	public boolean isBattleInProgress()
	{
		return battleInProgress;
	}

	public BattleData getBattleData()
	{
		return battleData;
	}

	public boolean startBattle()
	{
		if (!battleInProgress && !spawnerPositions.isEmpty())
		{
			battleData = new BattleData(this);
			battleData.start();
			battleInProgress = true;
			return true;
		}
		return false;
	}

	public void registerSpawner(int x, int y, int z, int dimension)
	{
		Point4D position = new Point4D(x, y, z, dimension);
		if (!spawnerPositions.contains(position))
		{
			spawnerPositions.add(position);
		}
	}

	public void registerDeathPoint(int x, int y, int z, int dimension){
		Point4D position = new Point4D(x, y, z, dimension);
		if (deathPoint == null)
		{
			deathPoint = position;
		}
	}

	public boolean registerPlayer(EntityPlayer player){
		if(!registeredPlayers.contains(player.getDisplayName())){
			return registeredPlayers.add(player.getDisplayName());
		}

		return false;
	}
	public ArrayList<String> getRegisteredPlayers(){
		return (ArrayList<String>)registeredPlayers.clone();
	}

	public ArrayList<Point4D> getSpawnerPositions()
	{
		// Clone the list as a precaution against external changes - defensive programming style
		return (ArrayList<Point4D>) spawnerPositions.clone();
	}

	public Point4D getDeathPoint() {
		return deathPoint;

	}

	public boolean onPlayerDeath(EntityPlayer player)
	{
		if (registeredPlayers.contains(player.getDisplayName()))
		{
			player.extinguish();
			player.clearActivePotions();
			player.setHealth(player.getMaxHealth());
			if (player instanceof EntityPlayerMP)
			{
				Teleport.teleportPlayerTo((EntityPlayerMP) player, deathPoint);
			}
			registeredPlayers.remove(player.getDisplayName());
			
			if (registeredPlayers.isEmpty())
			{
				battleData.end();
			}
			
			return true;
		}
		return false;
	}
	
	public void broadcastMessage(String message){
		for(EntityPlayer player : this.registeredplayers){
			RArenaMod.sendChat(player, message);
		}
	}

}
