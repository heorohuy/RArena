package rarena;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class EventHookContainer
{
	public EventHookContainer() { }

	@ForgeSubscribe(priority = EventPriority.LOWEST)
	public boolean onLivingDeathEvent(LivingDeathEvent event)
	{
		// We use this to track whether players or mobs in the arena have died
		// Use lowest priority so that we don't intercept the event before other
		// things have a chance of resurrecting the player or mob
		
		// Only run this code on the server
		Entity entity = event.entity;
		if (!entity.worldObj.isRemote)
		{
			if (entity instanceof EntityMob)
			{
				// Check if the mob belongs to any battles
				EntityMob monster = (EntityMob) entity;
				ArenaRegistry.onMonsterDeath(monster);
			}
		}
		
		return true;
	}
}