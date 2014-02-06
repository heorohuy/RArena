package rarena;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
			if (random.nextBoolean())
			{
				monster = new EntityZombie(world);
				monster.setCurrentItemOrArmor(0, new ItemStack(Item.swordDiamond));
			}
			else
			{
				monster = new EntitySkeleton(world);
				monster.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
			}
			monster.setInvisible(random.nextInt(20) == 0);
			for (int slot = 1; slot <= 3; slot++)
			{
				monster.setCurrentItemOrArmor(slot, new ItemStack(monster.getArmorItemForSlot(slot, 3)));
			}
			monster.setCurrentItemOrArmor(4, new ItemStack(Block.pumpkin));
			monster.setPosition(position.X + 0.5, position.Y + 1, position.Z + 0.5);
			world.spawnEntityInWorld(monster);
			
			monsters.add(monster.entityId);
		}
	}
	
	private void endWave()
	{
		// Schedule the start of the next wave
		// TODO: Consider doing this through the starter tile entity instead - that would let us
		// write the state of the battle to NBT tags
		RArenaMod.Scheduler.scheduleCallback(new StartWaveCallback(this), WAVE_PAUSE * TICKS_PER_SECOND);
		
		// TODO: We should send a message to all participants or to all players
		// indicating that the wave has ended
		
		waveCount++;
	}
}
