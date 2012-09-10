package denoflionsx.Managers;

import denoflionsx.API.IPfFItemRegistry;
import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PfFItemManager implements IPfFItemRegistry{

    public static HashMap<String, ItemStack> registeredItems = new HashMap();

    @Override
    public void registerItem(String s, ItemStack i) {
        registeredItems.put(s,i);
    }

    @Override
    public void registerItem(String s, Item i, int dmg) {
        registerItem(s,new ItemStack(i,dmg));
    }

    @Override
    public void registerItem(String s, Item i) {
        registerItem(s,i,0);
    }
    
    @Override
    public ItemStack getItem(String s) {
        ItemStack I = registeredItems.get(s);
        if (I != null) {
            ItemStack t;
            int id = I.itemID;
            int meta = I.getItemDamage();
            t = new ItemStack(id,1,meta);
            return t.copy();
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
        ItemStack I = registeredItems.get(s);
        if (I != null){
            return true;
        }else{
            return false;
        }
    }

}
