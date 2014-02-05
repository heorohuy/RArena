package rarena.ticking;


public interface IRegularTickSender {

	public void registerForTicking(IRegularTickReceiver receiver, int interval, boolean onTickStart);
	
}
