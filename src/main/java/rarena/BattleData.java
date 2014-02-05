package rarena;

import java.util.ArrayList;

import net.minecraft.entity.monster.EntityMob;
import rarena.ticking.IScheduledCallback;

public class BattleData
{
	private static class StartWaveCallback implements IScheduledCallback
	{
		private BattleData battle;
		
		public StartWaveCallback(BattleData battle)
		{
			this.battle = battle;
		}
		
		public void invoke()
		{
			battle.startWave();
		}
	}
	
	private final ArenaData owner;
	private int waveCount;	// The number of waves completed
	private int killCount;	// The total number of mobs killed
	private ArrayList<Integer> monsters;
	
	public BattleData(ArenaData owner)
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
				endWave();
			}
			return true;
		}
		return false;
	}
	
	public void start()
	{
		startWave();
	}
	
	private void startWave()
	{
		
	}
	
	private void endWave()
	{
		// Schedule the start of the next wave
		
		// TODO: We should send a message to all participants or to all players
		// indicating that the wave has ended
	}
}
