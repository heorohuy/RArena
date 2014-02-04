package rarena;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHookContainer
{
	public EventHookContainer() { }

	@ForgeSubscribe(priority = EventPriority.LOWEST)
	public boolean onLivingDeathEvent(LivingDeathEvent event)
	{
		// We use this to track whether players or mobs in the arena have died
		// Use lowest priority so that we don't intercept the event before other
		// things have a chance of resurrecting the player or mob
		Entity entity = event.entity;
		
		return true;
	}
}