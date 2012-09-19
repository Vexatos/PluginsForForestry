package denoflionsx.Managers;

import denoflionsx.API.Interfaces.IPfFItemManager;
import denoflionsx.core.core;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PfFItemManager implements IPfFItemManager {

    public static HashMap<String, ItemStack> registeredItems = new HashMap();

    @Override
    public void registerItem(String s, ItemStack i) {
        registeredItems.put(s, i);
    }

    @Override
    public ArrayList<ItemStack> getAllContainersForLiquid(String liquid) {
        ArrayList<ItemStack> stacks = new ArrayList();
        for (ItemStack i : registeredItems.values()) {
            String itemName = i.getItem().getItemNameIS(i);
            if (itemName.contains(liquid) && !itemName.contains(" Bar")) {
                if (i.getItemDamage() != 0) {
                    stacks.add(i);
                }
            }
        }
        return stacks;
    }

    @Override
    public ArrayList<ItemStack> getContainersForLiquidNoBarrel(String liquid) {
        ArrayList<ItemStack> stacks = getAllContainersForLiquid(liquid);
        ArrayList<ItemStack> copy = new ArrayList();
        for (ItemStack i : stacks) {
            if (!i.getItem().getItemNameIS(i).toLowerCase().contains("barrel")) {
                copy.add(i);
            }
        }
        return copy;
    }

    @Override
    public ItemStack getNewItemStack(String s, int amount) {
        ItemStack i = getItem(s);
        Item item = i.getItem();
        int meta = i.getItemDamage();
        return new ItemStack(item, amount, meta).copy();
    }

    @Override
    public void registerItem(String s, Item i, int dmg) {
        registerItem(s, new ItemStack(i, 1, dmg));
    }

    @Override
    public void registerItem(String s, Item i) {
        registerItem(s, i, 0);
    }

    @Override
    public ItemStack getItem(String s) {
        ItemStack I = getItemQuietly(s);
        if (I != null) {
            return I;
        } else {
            System.out.println("Unable to retrieve item: " + s + " from Plugins for Forestry!");
            return null;
        }
    }

    @Override
    public ItemStack getItemQuietly(String s) {
        ItemStack I = registeredItems.get(s);
        if (I != null) {
            return I.copy();
        } else {
            return null;
        }
    }

    public HashMap<String, ItemStack> getRegisteredItems() {
        return registeredItems;
    }

    @Override
    public boolean doesItemExist(String s) {
        ItemStack I = registeredItems.get(s);
        if (I != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dumpItemsToConsole() {
        String div = "---------------------";
        core.print(div);
        core.print("Starting PfF item dump...");
        core.print(div);
        for (ItemStack i : registeredItems.values()) {
            core.print(i.getItemName());
        }
        core.print(div);
        core.print("End PfF item dump");
        core.print(div);
    }
}
