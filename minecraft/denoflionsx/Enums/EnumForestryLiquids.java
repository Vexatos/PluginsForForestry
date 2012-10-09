package denoflionsx.Enums;

import forestry.api.core.ItemInterface;
import net.minecraft.src.ItemStack;

public enum EnumForestryLiquids {
    
    BIOMASS(ItemInterface.getItem("liquidBiomass")),
    APPLEJUICE(ItemInterface.getItem("liquidJuice")),
    HONEY(ItemInterface.getItem("liquidHoney")),
    SEEDOIL(ItemInterface.getItem("liquidSeedOil")),
    BIOFUEL(ItemInterface.getItem("liquidBiofuel"));
    
    private ItemStack liquid;
    private EnumForestryLiquids(ItemStack item){
        liquid = item;
    }

    public ItemStack getLiquid() {
        return liquid;
    }  
}
