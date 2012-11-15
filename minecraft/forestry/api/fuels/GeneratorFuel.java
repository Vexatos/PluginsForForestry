package forestry.api.fuels;

import java.util.HashMap;
import net.minecraftforge.liquids.LiquidStack;

public class GeneratorFuel
{
    public static HashMap fuels = new HashMap();
    public final LiquidStack fuelConsumed;
    public final int eu;
    public final int rate;

    public GeneratorFuel(LiquidStack var1, int var2, int var3)
    {
        this.fuelConsumed = var1;
        this.eu = var2;
        this.rate = var3;
    }
}
