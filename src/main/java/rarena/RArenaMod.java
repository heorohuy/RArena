package rarena;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RArena", name = "RArena", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class RArenaMod {

	@Instance("RArenaMod")
	public static RArenaMod instance;

	@SidedProxy(clientSide = "rarena.client.ClientProxy", serverSide = "rarena.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{

	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		//renderers
		proxy.registerRenderers();	
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	public static void sendChat(EntityPlayer player, String message)
	{
		ChatMessageComponent cmp = new ChatMessageComponent();
		cmp.addText(message);
		player.sendChatToPlayer(cmp);
	}
}
