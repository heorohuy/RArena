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
			if (arena.IsBattleInProgress())
			{
				arena.getBattleData().onMonsterDeath(monster);
			}
		}
	}

}
