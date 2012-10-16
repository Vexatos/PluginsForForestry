package denoflionsx.core;

public class defaults {
    
    public static final int MachineDefault = 3333;

    public static void setup() {
        core.PfFCore.config.addDefault("UpdateCheck=true");
        core.PfFCore.config.addDefault("Machine_BlockID=" + MachineDefault);
    }
}
