package denoflionsx.API.Objects;

import net.minecraft.src.ItemStack;

public class PfFLiquid {

    private String LiquidName;
    private int meta;
    private ItemStack liquid;
    private PfFColor color;

    public PfFLiquid(String LiquidName, int meta, ItemStack liquid, PfFColor color) {
        this.LiquidName = LiquidName;
        this.meta = meta;
        this.liquid = liquid;
        this.color = color;
    }

    public String getLiquidName() {
        return LiquidName;
    }

    public PfFColor getColor() {
        return color;
    }

    public ItemStack getLiquid() {
        return liquid;
    }

    public int getMeta() {
        return meta;
    }
   
}
