package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBarrel extends PfFBase{
    
    public final String cTexture = "/denoflionsx/PluginsforForestry/gfx/containers/barrel.png";
    public final String oTexture = "/denoflionsx/PluginsforForestry/gfx/containers/overlays/barrel_overlay.png";
    
    public ItemBarrel(int par1) {
        super(par1);
        this.add("Barrel", 0, 47);
        this.setMaxStackSize(64);
    }

    public ItemBarrel() {
        this(CoreTuning.Items.barrel);
    }
    
    public final void createRecipe(){
        ItemStack rings = PfFManagers.Items.getItemByTag("ironring");
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"WRW","WGW","WRW",Character.valueOf('W'),Block.planks, Character.valueOf('R'), rings, Character.valueOf('G'), Block.glass});
    } 
}
