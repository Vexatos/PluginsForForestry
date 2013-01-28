package denoflionsx.PluginsforForestry.Proxy;

import denoflionsx.denLib.Interfaces.IDenProxy;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public interface IPfFProxy extends IDenProxy{
    
    public boolean isClient();
    
    public void makeEntityDropItem(Entity entity, ItemStack item);
    
    public void getAllReferences(String clazz, ArrayList<ItemStack> add);
    
    public void saveList(ArrayList<ItemStack> list, String category);
    
    public ArrayList<ItemStack> loadList(Configuration config, String category, ArrayList<ItemStack> list);
    
    public void addSheetToMap(String sheet);
    
}
