package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Utils.DyeUtils;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBlacksmithHammer extends PfFBase{
    
    public ItemBlacksmithHammer(int par1) {
        super(par1);
        this.add("Blacksmithing Hammer", 0, 34);
    }

    public ItemBlacksmithHammer() {
        this(CoreTuning.Items.hammer);
        this.createRecipe();  
    }
    
    public final void createRecipe(){
        this.setContainerItem(this);
        ItemStack ink = DyeUtils.DYES.BLACK.getDye();
        ItemStack hammer = new ItemStack(this);
        ItemStack iron = new ItemStack(Item.ingotIron);
        ItemStack stic = new ItemStack(Item.stick);
        FMLWrapper.MODE.FML.addRecipe(hammer, new Object[]{"XiX","III","XSX",Character.valueOf('i'),ink,Character.valueOf('I'),iron,Character.valueOf('S'),stic});
    }

}
