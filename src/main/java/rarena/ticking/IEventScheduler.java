package rarena.ticking;

public interface IEventScheduler {
	
	public void scheduleCallback(IScheduledCallback callback, int interval);

}
