package denoflionsx.PluginsforForestry.Dictionary.Liquids;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;
import denoflionsx.PluginsforForestry.Dictionary.ModuleParserTemplate;
import java.util.ArrayList;
import net.minecraftforge.liquids.LiquidDictionary;

public class EngineModuleParser extends ModuleParserTemplate {

    public EngineModuleParser(String[] lines) {
        super(lines);
    }

    public int getMJt() {
        return Integer.valueOf(ModuleParserTemplate.findTagAndParse(lines, "# MJt:"));
    }

    public int getBurnTime() {
        return Integer.valueOf(ModuleParserTemplate.findTagAndParse(lines, "# BurnTime:"));
    }

    public ArrayList<EngineFuel> getAllEntries() {
        ArrayList<EngineFuel> fuels = new ArrayList();
        for (String s : lines) {
            if (!s.contains("#")) {
                if (LiquidDictionary.getLiquid(s, 1) != null) {
                    fuels.add(new EngineFuel(s, this.getMJt(), this.getBurnTime()));
                }else{
                    PfF.Proxy.warning("Skipped EngineFuel mapping " + s + ". Reason: Liquid does not exist.");
                }
            }
        }
        return fuels;
    }
}
