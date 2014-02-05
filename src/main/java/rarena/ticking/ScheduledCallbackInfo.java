package rarena.ticking;

public class ScheduledCallbackInfo implements Comparable<ScheduledCallbackInfo>
{
	
	public final IScheduledCallback Callback;
	public final int Deadline;
	
	public ScheduledCallbackInfo(IScheduledCallback callback, int deadline)
	{
		if (callback == null)
			throw new IllegalArgumentException("callback cannot be null.");
		
		this.Callback = callback;
		this.Deadline = deadline;
	}

	@Override
	public int compareTo(ScheduledCallbackInfo other)
	{
		return this.Deadline - other.Deadline;
	}
}
