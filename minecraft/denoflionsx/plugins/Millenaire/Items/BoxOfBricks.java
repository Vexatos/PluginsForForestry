package denoflionsx.plugins.Millenaire.Items;

import denoflionsx.Enums.Colors;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.Forestry.Helpers.CarpenterHelper;
import denoflionsx.plugins.Millenaire.Enums.EnumMillBlocks;
import denoflionsx.plugins.Millenaire.Enums.EnumMillItems;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class BoxOfBricks extends multiItem {

    public BoxOfBricks(int par1, String name) {
        super(par1, name);
        this.add("Box of Bricks", 0, Colors.shiftRow(14, 2));
        this.createRecipes();
    }

    public final void createRecipes() {
        CarpenterHelper.addRecipeNoLiquid(new ItemStack(this, 1, 0), new Object[]{"XSX",
                    "DMD",
                    "XSX",
                    Character.valueOf('S'), new ItemStack(Block.sand, 64, 0),
                    Character.valueOf('D'), new ItemStack(Block.dirt, 64, 0),
                    Character.valueOf('M'), EnumMillItems.BRICK_MOLD.getItem()});
    }

    @Override
    public final void add(String name, int dmg, int texid) {
        super.add(name, dmg, texid);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par3EntityPlayer.dropItem(EnumMillBlocks.WET_BRICK.getBlock().itemID, 64);
            par3EntityPlayer.dropItem(EnumMillBlocks.WET_BRICK.getBlock().itemID, 64);
        }
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
