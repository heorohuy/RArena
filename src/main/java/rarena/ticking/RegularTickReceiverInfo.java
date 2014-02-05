package rarena.ticking;

public class RegularTickReceiverInfo {
	
	public final IRegularTickReceiver RegularTickReceiver;
	public final int Interval;
	public final boolean OnTickStart;
	
	public RegularTickReceiverInfo(IRegularTickReceiver regularTickReceiver, int interval, boolean onTickStart)
	{
		this.RegularTickReceiver = regularTickReceiver;
		this.Interval = interval;
		this.OnTickStart = onTickStart;
	}
	
}
