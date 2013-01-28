package denoflionsx.LiquidRoundup.Items;

import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.Mod.Items.ItemBase;
import denoflionsx.denLib.denLib;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidBase extends ItemBase{

    public LiquidBase(int par1) {
        super(par1);
    }
    
    public LiquidStack addNewLiquid(String name, int internalID){
        this.add(name, internalID, internalID);
        return new LiquidStack(this.itemID,1000,internalID);
    }

    @Override
    public void add(String name, int meta, int texture) {
        Property p = LiquidRoundup.Core.config.get("names.liquids", denLib.toLowerCaseNoSpaces(name), name);
        super.add(p.value, meta, texture);
    }
}
