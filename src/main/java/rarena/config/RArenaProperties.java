package rarena.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class RArenaProperties {
	
	//Block IDs
	public final int BlockArenaSpawnerID;
	public final int BlockArenaStarterID;
	public final int BlockPlayerRegisterID;
	public final int BlockSpawnPointID;
	
	
	//Singleton instance
	private static RArenaProperties instance = null;
	
	private RArenaProperties(File configFile)
	{
		//Load the configuration. This must be done in the constructor, even though I'd rather have a separate
		//function, because "blank final" variables must be initialized within the constructor.

		Configuration config = new Configuration(configFile);
		config.load();
		
		BlockArenaSpawnerID = config.getBlock("Arena Spawner Block ID", 4000).getInt();
		BlockArenaStarterID = config.getBlock("Arena Starter Block ID", 4001).getInt();
		BlockPlayerRegisterID = config.getBlock("Arena Player Register Block ID", 4002).getInt();
		BlockSpawnPointID = config.getBlock("Arena Spawn Point Block ID", 4003).getInt();
		
		config.save();
	}
	
	public static RArenaProperties initialize(File configFile)
	{
		if (instance == null)
			instance = new RArenaProperties(configFile);
		else
			throw new IllegalStateException("Cannot initialize DDProperties twice");

		return instance;
	}

	public static RArenaProperties instance()
	{
		if (instance == null)
		{
			//This is to prevent some frustrating bugs that could arise when classes
			//are loaded in the wrong order.
			throw new IllegalStateException("Instance of DDProperties requested before initialization");
		}
		return instance;
	}

}
