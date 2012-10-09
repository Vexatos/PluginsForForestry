package denoflionsx.Managers;

import denoflionsx.API.Interfaces.IPfFColorManager;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.core.core;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PfFColorManager implements IPfFColorManager{
    
    private static HashMap<String, PfFColor> colors = new HashMap();

    @Override
    public void addColor(String name, int r, int b, int g) {
        if (colors.get(name) == null){
            colors.put(name,new PfFColor(name,r,b,g));
        }else{
            core.print("Color " + name + " already exists!");
        }
    }

    @Override
    public void dumpColorNamesToConsole() {
        Iterator i = colors.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry pairs = (Map.Entry) i.next();
            String key = pairs.getKey().toString();
            core.print(key);
        }
    }

    @Override
    public PfFColor getColor(String name) {
        if (colors.get(name) != null){
            return colors.get(name);
        }else{
            core.print("Color " + name + " not found in ColorManager!");
            return new PfFColor("Black",0,0,0);
        }
    }
     
}
