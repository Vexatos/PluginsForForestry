package denoflionsx.plugins.Railcraft.Modules;

import denoflionsx.denLib.denLib;
import denoflionsx.items.multiItem;

public class DustItem extends multiItem{

    public DustItem(int par1, String name,EnumDustTextures.DUST tex) {
        super(par1, denLib.toLowerCaseNoSpaces(name));
        this.add(name, 0, tex.getIndex());
    }
    
}
