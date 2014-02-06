package rarena;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import rarena.ticking.IScheduledCallback;
import rarena.util.Point4D;

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
	
	private static final int TICKS_PER_SECOND = 20;
	private static final int WAVE_PAUSE = 10;
	
	private static Random random = new Random();
	
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
		EntityMob monster;
		
		// Spawn mobs
		for (Point4D position : owner.getSpawnerPositions())
		{
			// TODO: Check if chunks are loaded!!! Or bad things happen
			
			World world = DimensionManager.getWorld(position.Dimension);
			monster = new EntityZombie(world);
			monster.setLocationAndAngles(position.X + 0.5, position.Y + 1, position.Z + 0.5, 1, 1);
			world.spawnEntityInWorld(monster);
			
			monsters.add(monster.entityId);
		}
	}
	
	private void endWave()
	{
		// Schedule the start of the next wave
		RArenaMod.scheduler.scheduleCallback(new StartWaveCallback(this), WAVE_PAUSE * TICKS_PER_SECOND);
		
		// TODO: We should send a message to all participants or to all players
		// indicating that the wave has ended
		
		waveCount++;
	}
}
