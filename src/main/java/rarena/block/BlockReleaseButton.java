package rarena.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockReleaseButton extends BlockButton{
	
    protected BlockReleaseButton(int blockID)
    {
        super(blockID, false);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return Block.stone.getBlockTextureFromSide(1);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	boolean result = super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    	
    	//TODO Activation Sequence
    	
        return result;
    }
    

}
