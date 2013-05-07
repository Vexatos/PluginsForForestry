package denoflionsx.PluginsforForestry.Dictionary.Liquids;

import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.IEngineMapping;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.BiogasMapping;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.CombustionMapping;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import java.util.HashMap;

public class PfFEngineParser {
    
    public static PfFEngineParser instance;
    private HashMap<String, ArrayList<EngineFuel>> liquidMap = new HashMap();
    private String list;
    
    public static void createInstance(){
        instance = new PfFEngineParser("LiquidDictionaryModules.txt");
    }
    
    public PfFEngineParser(String list) {
        this.list = list;
    }
    
    public void parse() {
        for (EngineMappings s1 : EngineMappings.values()) {
            String s = s1.toString();
            liquidMap.put(s, new ArrayList<EngineFuel>());
            PfF.Proxy.print("Creating new engine registry: " + s);
        }
        for (String s : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, list, this)) {
            if (s.equals("")) {
                continue;
            }
            EngineModuleParser p = new EngineModuleParser(denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, s, this));
            for (EngineMappings s1 : EngineMappings.values()) {
                if (p.getType().equals(s1.toString())) {
                    for (EngineFuel f : p.getAllEntries()) {
                        s1.mapFuel(f);
                        PfF.Proxy.print("Mapping " + f.getLiquidTag() + " to " + s1.toString() + " engine.");
                    }
                }
            }
        }
    }
    
    public static enum EngineMappings {
        
        biogas(new BiogasMapping()),
        combustion(new CombustionMapping());
        private IEngineMapping mapping;
        
        private EngineMappings(IEngineMapping mapping) {
            this.mapping = mapping;
        }
        
        public void mapFuel(EngineFuel fuel) {
            this.mapping.MapEngineFuel(fuel);
        }
    }
}
