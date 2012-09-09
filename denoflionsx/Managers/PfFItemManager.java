package denoflionsx.Managers;

import denoflionsx.API.IPfFItemRegistry;
import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PfFItemManager implements IPfFItemRegistry{

    public HashMap<String, ItemStack> registeredItems = new HashMap();

    @Override
    public void registerItem(String s, ItemStack i) {
        this.registeredItems.put(s,i);
    }

    @Override
    public void registerItem(String s, Item i, int dmg) {
        this.registerItem(s,new ItemStack(i,dmg));
    }

    @Override
    public void registerItem(String s, Item i) {
        this.registerItem(s,i,0);
    }
    
    @Override
    public ItemStack getItem(String s) {
        ItemStack I = this.registeredItems.get(s);
        if (I != null) {
            return I;
        } else {
            System.out.println("Unable to retrieve item: " + s + " from Plugins for Forestry!");
            return null;
        }
    }

    public HashMap<String, ItemStack> getRegisteredItems() {
        return registeredItems;
    }

    @Override
    public boolean doesItemExist(String s) {
        ItemStack I = this.registeredItems.get(s);
        if (I != null){
            return true;
        }else{
            return false;
        }
    }

}
