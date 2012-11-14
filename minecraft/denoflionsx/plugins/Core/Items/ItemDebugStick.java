package denoflionsx.plugins.Core.Items;

import denoflionsx.Annotations.DebugStatementInThisMethod;
import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.items.multiItem;
import net.minecraft.src.*;

public class ItemDebugStick extends multiItem {

    public ItemDebugStick(int par1, String name) {
        super(par1, name);
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @DebugStatementInThisMethod
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        core.print("ID: " + id + " | " + "Meta: " + meta);
        return true;
    }

    public static class Debug {

        public static ItemIDManager id;
        public static ItemDebugStick debug;
        
        public static void create(){
            id = new ItemIDManager(1,"DebugStick");
            core.PfFCore.config.addDefault("DebugStick_ItemID=" + id.getItemIDs().get(0));
            int itemID = core.PfFCore.config.getOptionInt("DebugStick_ItemID");
            debug = new ItemDebugStick(itemID,"debugstick");
            debug.metaMap.put("Debug Stick",0);
            debug.add("debugstick", 0, EnumToolTextures.ToolTextures.DEBUGSTICK.getIndex(), "Debug Stick");
        }
    }
}
