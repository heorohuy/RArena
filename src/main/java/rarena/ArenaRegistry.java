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
		Point4D deathPoint;
		for (ArenaData arena : arenas.values())
		{
			if (arena.isBattleInProgress())
			{
				deathPoint = arena.getDeathPoint();
				arena.onPlayerDeath(player, deathPoint.X, deathPoint.Y, deathPoint.Z, deathPoint.Dimension);
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
