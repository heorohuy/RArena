package rarena;

import java.util.ArrayList;

import net.minecraft.entity.monster.EntityMob;

public class BattleData
{
	private final ArenaData owner;

	private int waveCount;
	private int killCount;
	private ArrayList<Integer> monsters;
	
	private BattleData(ArenaData owner)
	{
		this.owner = owner;
		this.monsters = new ArrayList<Integer>();
		this.waveCount = 0;
		this.killCount = 0;
	}
	
	public boolean onMonsterDeath(EntityMob monster)
	{
		if (monsters.remove((Integer) monster.entityId))
		{
			killCount++;
			if (monsters.isEmpty())
			{
				onWaveVictory();
			}
			return true;
		}
		return false;
	}
	
	private void onWaveVictory()
	{
		
	}
}
