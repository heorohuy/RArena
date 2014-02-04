package rarena;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "RArena", name = "RArena", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class RArenaMod {
	
	//Block
	public static Block BlockArenaSpawner;

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
		//renderers
		proxy.registerRenderers();	
		
		//Block
		BlockArenaSpawner = (new Block(22, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockArenaSpawner").setCreativeTab(CreativeTabs.tabBlock).setTextureName("lapis_block");
	}

	@EventHandler
	public void onPostInitialization(FMLPostInitializationEvent event)
	{

	}

	public static void sendChat(EntityPlayer player, String message)
	{
		ChatMessageComponent cmp = new ChatMessageComponent();
		cmp.addText(message);
		player.sendChatToPlayer(cmp);
	}
}
