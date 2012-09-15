package denoflionsx.plugins.BluesFood;

import denoflionsx.denLib.denLib;
import denoflionsx.plugins.pluginBase;

public class CustomFood {
    
    private ItemFoods food;
    
    public CustomFood(int id, int texture, int heal, float sat, String name, pluginBase parent){
        String name2 = denLib.toNoSpaces(name);
        parent.config.addDefault(name2 + "_ItemID=" + id);
        food = new ItemFoods(parent.config.getOptionInt(name2 + "_ItemID"),texture,heal,sat,name);
    }
}
