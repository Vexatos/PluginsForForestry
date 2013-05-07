package denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.denLib.Lib.denLib;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class EngineFuel {

    private String liquidTag;
    private int MJt;
    private int burnTime;

    public EngineFuel(String liquidTag, int MJt, int burnTime) {
        this.liquidTag = liquidTag;
        this.MJt = MJt;
        this.burnTime = burnTime;
    }

    public String getLiquidTag() {
        return liquidTag;
    }

    public LiquidStack getLiquidStack() {
        return LiquidDictionary.getLiquid(this.liquidTag, LiquidContainerRegistry.BUCKET_VOLUME);
    }

    public int getMJt() {
        return MJt;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void setMJt(int MJt) {
        this.MJt = MJt;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }
    
    public static boolean readFromConfig(EngineFuel fuel, String engine){
        String cat = denLib.StringUtils.removeSpaces("fuels." + fuel.liquidTag + "." + engine).toLowerCase();
        Property p = PfFTuning.config.get(cat, "percent_loss", 0);
        int loss = p.getInt();
        p.comment = "BurnTime - (BurnTime * percent_loss) = final burntime";
        fuel.setMJt(PfFTuning.config.get(cat, "MJt", fuel.getMJt()).getInt());
        int b = PfFTuning.config.get(cat, "BurnTime", fuel.getBurnTime()).getInt();
        fuel.setBurnTime(b - (b * loss));
        PfFTuning.config.save();
        return PfFTuning.config.get(cat, "enabled", true).getBoolean(true);
    }
}
