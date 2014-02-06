package rarena;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.MinecraftForge;
import rarena.block.BlockArenaSpawner;
import rarena.block.BlockArenaStarter;
import rarena.ticking.CommonTickHandler;
import rarena.tileentities.TileEntityArenaSpawner;
import rarena.tileentities.TileEntityArenaStarter;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "RArena", name = "RArena", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class RArenaMod {
	
	//Blocks
	public static Block BlockArenaSpawner;
	public static Block BlockArenaStarter;
	
	//Scheduling
	public static CommonTickHandler Scheduler;

	@Instance("RArenaMod")
	public static RArenaMod instance;

	@SidedProxy(clientSide = "rarena.client.ClientProxy", serverSide = "rarena.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event)
	{
		EventHookContainer hooks = new EventHookContainer();
		MinecraftForge.EVENT_BUS.register(hooks);
	}

	@EventHandler
	public void onInitialization(FMLInitializationEvent event)
	{
		Scheduler = new CommonTickHandler();
		//TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(Scheduler, Side.SERVER);
		
		//renderers
		proxy.registerRenderers();	
		
		//Block
		BlockArenaSpawner = (new BlockArenaSpawner(4000, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockArenaSpawner").setCreativeTab(CreativeTabs.tabBlock).setTextureName("lapis_block");
		BlockArenaStarter = (new BlockArenaStarter(4001, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(new CustomStepSound("mob.chicken.say", 1.0F, 1.0F)).setUnlocalizedName("blockArenaStarter").setCreativeTab(CreativeTabs.tabBlock).setTextureName("emerald_block");
		
		GameRegistry.registerBlock(BlockArenaSpawner,"Arena Spawner");
		GameRegistry.registerBlock(BlockArenaStarter,"Arena Starter");

		LanguageRegistry.addName(BlockArenaSpawner,"Arena Spawner");
		LanguageRegistry.addName(BlockArenaStarter,"Arena Starter");
		
		GameRegistry.registerTileEntity(TileEntityArenaSpawner.class, "TileEntityArenaSpawner");
		GameRegistry.registerTileEntity(TileEntityArenaStarter.class, "TileEntityArenaStarter");
	}

	@EventHandler
	public void onPostInitialization(FMLPostInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void onServerAboutToStart(FMLServerAboutToStartEvent event)
	{
		// Reset all our registries - in case the user enters and exits single player worlds
		ArenaRegistry.clear();
	}

	public static void sendChat(EntityPlayer player, String message)
	{
		player.sendChatToPlayer(ChatMessageComponent.createFromText(message));
	}
}
