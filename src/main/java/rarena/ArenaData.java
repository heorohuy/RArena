package rarena;

import java.util.ArrayList;

import rarena.util.Point4D;

public class ArenaData
{
	private final String arenaName;
	private boolean battleInProgress;
	private BattleData battleData;
	private ArrayList<Point4D> spawnerPositions;
	
	public ArenaData(String arenaName)
	{
		this.arenaName = arenaName;
		this.battleInProgress = false;
		this.spawnerPositions = new ArrayList<Point4D>();
		this.battleData = null;
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
	
}
