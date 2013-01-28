package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.Mod.Items.ItemBase;
import denoflionsx.denLib.denLib;
import net.minecraft.item.ItemStack;

public class PfFBase extends ItemBase {

    public PfFBase(int par1) {
        super(par1);
        this.setTextureFile(PfF.Core.spritesheet);
        this.setCreativeTab(PfF.Core.tab);
    }

    @Override
    public void add(String name, int meta, int texture) {
        super.add(name, meta, texture);
        PfFManagers.Items.registerItem(denLib.toLowerCaseNoSpaces(name), new ItemStack(this,1,meta));
    }
}
