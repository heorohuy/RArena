package rarena;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RArena", name = "RArena", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class RArenaMod {
	
	//Blocks
	public static Block BlockArenaSpawner;
	public static Block BlockArenaStarter;

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
		
		//Block
		BlockArenaSpawner = (new Block(4000, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockArenaSpawner").setCreativeTab(CreativeTabs.tabBlock).setTextureName("lapis_block");
		BlockArenaStarter = (new Block(4001, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(new CustomStepSound("mob.chicken.say", 1.0F, 1.0F)).setUnlocalizedName("blockArenaStarter").setCreativeTab(CreativeTabs.tabBlock).setTextureName("emerald_block");
		
		GameRegistry.registerBlock(BlockArenaSpawner,"Arena Spawner");
		GameRegistry.registerBlock(BlockArenaStarter,"Arena Starter");

		LanguageRegistry.addName(BlockArenaSpawner,"Arena Spawner");
		LanguageRegistry.addName(BlockArenaStarter,"Arena Starter");
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
