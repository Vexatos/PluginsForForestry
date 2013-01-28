package denoflionsx.LiquidRoundup.API.Objects;

import java.awt.Color;
import net.minecraftforge.liquids.LiquidStack;

public class LRLiquid {
    
    private LiquidStack liquid;
    private String name;
    private String textures[];
    private int internal;
    private Color color;

    public LRLiquid(LiquidStack liquid, String name, String[] textures, int internal, Color color) {
        this.liquid = liquid;
        this.name = name;
        this.textures = textures;
        this.internal = internal;
        this.color = color;
    }

    public int getInternal() {
        return internal;
    }

    public LiquidStack getLiquid() {
        return liquid;
    }

    public String getName() {
        return name;
    }

    public String[] getTextures() {
        return textures;
    }

    public Color getColor() {
        return color;
    }
}
