package rarena;

import java.util.HashMap;

import net.minecraft.entity.monster.EntityMob;

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
