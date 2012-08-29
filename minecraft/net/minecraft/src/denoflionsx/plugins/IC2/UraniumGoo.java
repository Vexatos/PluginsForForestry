package net.minecraft.src.denoflionsx.plugins.IC2;

import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Core.EnumToolTextures;

public class UraniumGoo extends multiItem{

    public UraniumGoo(int par1, String name) {
        super(par1, name);
        this.metaMap.put("Uranium Goo",0);
        this.add(name,0,EnumToolTextures.ToolTextures.URANIUMGOO.getIndex(),"Uranium Goo",true);
    }
}
