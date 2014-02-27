package rarena;

import java.util.HashMap;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import rarena.util.Point4D;

public class ArenaRegistry {

	private static HashMap<String, ArenaData> arenas = new HashMap<String, ArenaData>();
	
	private ArenaRegistry() { }
	
	public static void onMonsterDeath(EntityMob monster)
	{
		for (ArenaData arena : arenas.values())
		{
			if (arena.isBattleInProgress())
			{
				arena.getBattleData().onMonsterDeath(monster);
			}
		}
	}
	
	public static void onPlayerDeath(EntityPlayer player)
	{
		Point4D spawnPoint;
		for (ArenaData arena : arenas.values())
		{
			if (arena.isBattleInProgress())
			{
				spawnPoint = arena.getSpawnPoint();
				arena.onPlayerDeath(player);
			}
		}
	}

	public static void onMonsterLoaded(EntityMob monster)
	{
		for (ArenaData arena : arenas.values())
		{
			if (arena.isBattleInProgress())
			{
				arena.getBattleData().onMonsterLoaded(monster);
			}
		}
	}
	
	public static void clear()
	{
		arenas.clear();
	}
	
	public static ArenaData createArena(String name)
	{
		ArenaData arena = arenas.get(name);
		if (arena == null)
		{
			arena = new ArenaData(name);
			arenas.put(name, arena);
		}
		return arena;
	}

}
