package rarena.ticking;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.PriorityQueue;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler, IRegularTickSender, IEventScheduler
{
	private static final String PROFILING_LABEL = "RArena: Common Tick";
	
	private int tickCount = 0;
	private ArrayList<RegularTickReceiverInfo> receivers;
	private PriorityQueue<ScheduledCallbackInfo> callbacks;

	public CommonTickHandler()
	{
		this.receivers = new ArrayList<RegularTickReceiverInfo>();
		this.callbacks = new PriorityQueue<ScheduledCallbackInfo>();
	}

	@Override
	public void registerForTicking(IRegularTickReceiver receiver, int interval, boolean onTickStart)
	{
		RegularTickReceiverInfo info = new RegularTickReceiverInfo(receiver, interval, onTickStart);
		receivers.add(info);
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		if (type.equals(EnumSet.of(TickType.SERVER)))
		{
			for (RegularTickReceiverInfo info : receivers)
			{
				if (info.OnTickStart && tickCount % info.Interval == 0)
				{
					info.RegularTickReceiver.notifyTick();
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		for (RegularTickReceiverInfo info : receivers)
		{
			if (!info.OnTickStart && tickCount % info.Interval == 0)
			{
				info.RegularTickReceiver.notifyTick();
			}
		}
		while (!callbacks.isEmpty() && callbacks.peek().Deadline <= tickCount)
		{
			callbacks.remove().Callback.invoke();
		}
		tickCount++; //There is no need to reset the counter. Let it overflow.
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel()
	{
		return PROFILING_LABEL; //Used for profiling!
	}

	@Override
	public void scheduleCallback(IScheduledCallback callback, int interval)
	{
		if (interval <= 0)
			throw new IllegalArgumentException("interval must be at least 1.");
		
		callbacks.add(new ScheduledCallbackInfo(callback, tickCount + interval));
	}
	
}
