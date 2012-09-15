package denoflionsx.API.Interfaces;

import denoflionsx.API.Objects.PfFColor;

public interface IPfFColorManager {
    
    public void addColor(String name, int r, int b, int g);
    
    public PfFColor getColor(String name);
    
    public void dumpColorNamesToConsole();
    
}
