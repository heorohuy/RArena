package rarena;

public class ArenaData
{
	private final String arenaName;
	private boolean battleInProgress;
	private BattleData battleData;
	
	public ArenaData(String arenaName)
	{
		this.arenaName = arenaName;
	}
	
	public boolean IsBattleInProgress()
	{
		return battleInProgress;
	}
	
	public BattleData getBattleData()
	{
		return battleData;
	}
	
	
}
