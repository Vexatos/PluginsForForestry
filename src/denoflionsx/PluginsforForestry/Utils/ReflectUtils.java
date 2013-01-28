package denoflionsx.PluginsforForestry.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.denLib;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class ReflectUtils {

    public static void getAllReferences(String clazz, ArrayList<ItemStack> addtome) {
        getAllItemReferences(clazz, addtome);
        getAllBlockReferences(clazz, addtome);
        getAllItemStackReferences(clazz, addtome);
    }

    public static void getAllItemStackReferences(String clazz, ArrayList<ItemStack> addtome) {
        Field q = null;
        try {
            ArrayList<String> fieldList = new ArrayList();
            Class c = Class.forName(clazz);
            for (Field f : c.getDeclaredFields()) {
                q = f;
                f.setAccessible(true);
                Object o = null;
                try {
                    o = f.get(null);
                } catch (Exception ex2) {
                    PfF.Proxy.print("Failed to reflect " + f.getName() + "! (This warning is harmless)");
                }
                if (o instanceof ItemStack) {
                    fieldList.add(f.getName());
                }
            }
            for (String s : fieldList) {
                ItemStack item = denLib.ReflectionHelper.getItemStack(clazz, s);
                try{
                    item.getItem().getSubItems(0, CreativeTabs.tabMaterials, addtome);
                }catch(Exception ex){
                    int id = item.itemID;
                    Block b = Block.blocksList[id];
                    b.getSubBlocks(0, CreativeTabs.tabMaterials, addtome);
                }
                addtome.add(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getAllItemReferences(String clazz, ArrayList<ItemStack> addtome) {
        try {
            ArrayList<String> fieldList = new ArrayList();
            Class c = Class.forName(clazz);
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                Object o = null;
                try {
                    o = f.get(null);
                } catch (Exception ex2) {
                    PfF.Proxy.print("Failed to reflect " + f.getName() + "! (This warning is harmless)");
                }
                if (o instanceof Item) {
                    fieldList.add(f.getName());
                }
            }
            for (String s : fieldList) {
                Item item = denLib.ReflectionHelper.getItem(clazz, s);
                item.getSubItems(0, CreativeTabs.tabMaterials, addtome);
                addtome.add(new ItemStack(item));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getAllBlockReferences(String clazz, ArrayList<ItemStack> addtome) {
        try {
            ArrayList<String> fieldList = new ArrayList();
            Class c = Class.forName(clazz);
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                Object o = null;
                try {
                    o = f.get(null);
                } catch (Exception ex2) {
                    PfF.Proxy.print("Failed to reflect " + f.getName() + "! (This warning is harmless)");
                }
                if (o instanceof Block) {
                    fieldList.add(f.getName());
                }
            }
            for (String s : fieldList) {
                Block block = denLib.ReflectionHelper.getBlock(clazz, s);
                block.getSubBlocks(0, CreativeTabs.tabMaterials, addtome);
                addtome.add(new ItemStack(block));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
