package rarena;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import rarena.util.Point4D;
import rarena.util.Teleporter;

public class ArenaData
{
	private final String arenaName;
	private BattleData battleData;
	private ArrayList<Point4D> spawnerPositions;
	private ArrayList<String> registeredPlayers;
	private Point4D spawnPoint;

	public ArenaData(String arenaName)
	{
		this.arenaName = arenaName;
		this.spawnerPositions = new ArrayList<Point4D>();
		this.registeredPlayers = new ArrayList<String>();
		this.battleData = null;
		this.spawnPoint = null;
	}

	public boolean isBattleInProgress()
	{
		return (battleData == null || battleData.hasEnded());
	}

	public BattleData getBattleData()
	{
		return battleData;
	}

	public boolean startBattle()
	{
		if (!this.isBattleInProgress() && !spawnerPositions.isEmpty())
		{
			battleData = new BattleData(this);
			battleData.start();
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

	public void registerSpawnPoint(int x, int y, int z, int dimension)
	{
		spawnPoint = new Point4D(x, y, z, dimension);
	}

	public boolean registerPlayer(EntityPlayer player)
	{
		if (!registeredPlayers.contains(player.getDisplayName()))
		{
			return registeredPlayers.add(player.getDisplayName());
		}
		return false;
	}

	public ArrayList<String> getRegisteredPlayers()
	{
		return (ArrayList<String>) registeredPlayers.clone();
	}

	public ArrayList<Point4D> getSpawnerPositions()
	{
		// Clone the list as a precaution against external changes - defensive programming style
		return (ArrayList<Point4D>) spawnerPositions.clone();
	}

	public Point4D getSpawnPoint() {
		return spawnPoint;
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
				Teleporter.teleportPlayerTo((EntityPlayerMP) player, spawnPoint);
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

		MinecraftServer server = MinecraftServer.getServer();
		ServerConfigurationManager manager = server.getServerConfigurationManager(server);

		for(String name : registeredPlayers){
			EntityPlayer player = manager.getPlayerForUsername(name);
			if(player.getDisplayName().equals(name)){
				RArenaMod.sendChat(player, message);
			}
		}
	}
}