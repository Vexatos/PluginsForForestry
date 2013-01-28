package denoflionsx.LiquidRoundup.APIWrappers;

import denoflionsx.LiquidRoundup.APIWrappers.Buildcraft.Buildcraft;
import denoflionsx.LiquidRoundup.APIWrappers.Forestry.Forestry;
import denoflionsx.LiquidRoundup.APIWrappers.Ic2.Ic2;
import denoflionsx.LiquidRoundup.APIWrappers.Railcraft.Railcraft;
import denoflionsx.LiquidRoundup.APIWrappers.ThermalExpansion.ThermalExpansion;

public class APIWrappers {
    
    public static Forestry forestry = new Forestry();
    public static ThermalExpansion TE = new ThermalExpansion();
    public static Railcraft railcraft = new Railcraft();
    public static Buildcraft buildcraft = new Buildcraft();
    public static Ic2 ic2 = new Ic2();

}
