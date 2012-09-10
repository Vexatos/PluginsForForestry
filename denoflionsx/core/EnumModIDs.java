package denoflionsx.core;

public class EnumModIDs {
    
    public static enum MODS{
        MFR("MFReloaded", "powercrystals.minefactoryreloaded.common.MineFactoryReloadedCore"),
        FARMCRAFTORY("FarmCraftory","FarmCraftory"),
        BUILDCRAFT_CORE("BuildCraft|Core","buildcraft.BuildCraftCore"),
        BUILDCRAFT_ENERGY("BuildCraft|Energy","buildcraft.BuildCraftEnergy"),
        BUILDCRAFT_TRANSPORT("BuildCraft|Transport","buildcraft.BuildCraftTransport"),
        BUILDCRAFT_FACTORY("BuildCraft|Factory","buildcraft.BuildCraftFactory"),
        RAILCRAFT("Railcraft","railcraft.common.core.Railcraft");
        
        private String mod;
        private String theClass;
        
        private MODS(String mod, String theClass){
            this.mod = mod;
            this.theClass = theClass;
        }
        
        public String getID(){
            return this.mod;
        }
        
        public String gettheClass(){
            return this.theClass;
        }
    }
    
}
