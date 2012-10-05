package denoflionsx.plugins.Railcraft.Modules.OreCrushModule.Items;

import denoflionsx.items.multiItem;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class multiItemDust extends multiItem{

    public multiItemDust(int par1, String name) {
        super(par1, name);
    } 

    @Override
    public void add(String name, int dmg, int texid) {
        super.add(name, dmg, texid);
        String[] split = name.split(" ");
        String dust = "dust" + split[0];
        OreDictionary.registerOre(dust, new ItemStack(this,1,dmg));
    }
    
}
