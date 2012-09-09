package denoflionsx.denLib;

import cpw.mods.fml.common.Loader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.*;

public class denLib {

    public static boolean detect(String mod) {
        return LOADER.FML.isModLoaded(mod);
    }
    
    public static HashMap flipHashMap(HashMap map){
        HashMap metaMapReversed = new HashMap();
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry pairs = (Map.Entry) i.next();
            metaMapReversed.put(pairs.getValue(),pairs.getKey());
        }
        return metaMapReversed;
    }

    // These get functions are for compatibility for code written before
    // the reflection helper class.
    public static Item getItem(String mod, String name) {
        return ReflectionHelper.getItem(mod, name);
    }

    public static ItemStack getItemStack(String mod, String name) {
        return ReflectionHelper.getItemStack(mod, name);
    }

    public static Block getBlock(String mod, String name) {
        return ReflectionHelper.getBlock(mod, name);
    }

    public static void print(String msg) {

        System.out.println(msg);

    }

    public static boolean convertToBoolean(String bool) {
        bool = bool.toLowerCase();
        if (bool.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static String toLowerCaseNoSpaces(String s) {
        return toNoSpaces(s).toLowerCase();
    }

    public static String Hash(String tag) {
        byte[] bytes = tag.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(bytes);
            String hash = "";
            for (byte b : md5) {
                hash = hash + String.valueOf(b);
            }
            hash = hash.replace("-", "");
            tag = hash;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tag;
    }

    public static String getTileEntityName(TileEntity t) {
        return t.getClass().toString();
    }

    public static String toNoSpaces(String s) {
        return s.replaceAll("\\s", "");
    }

    public static String addName(String name) {
        ModLoader.addLocalization("item." + denLib.toLowerCaseNoSpaces(name) + ".name", name);
        return "item." + denLib.toLowerCaseNoSpaces(name);
    }

    public static void classSnoop(String param1) {
        ReflectionHelper.classSnoop(param1);
    }

    public static enum LOADER {

        MODLOADER(),
        FML();
        private String mode;

        private LOADER() {
            this.mode = this.toString();
        }

        public boolean isModLoaded(String mod) {
            if (this.mode.equals("MODLOADER")) {
                return ModLoader.isModLoaded(mod);
            } else if (this.mode.equals("FML")) {
                return Loader.isModLoaded(mod);
            }
            return false;
        }
    }

    public static class ReflectionHelper {

        public static void setIntFromTileEntity(String theClass, String theField, TileEntity t, int set) {
            try {
                Class c = Class.forName(theClass);
                Field f = c.getField(theField);
                f.set(t, set);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public static int getStaticInt(String theClass, String theField) {
            try {
                Class c = Class.forName(theClass);
                Field f = c.getField(theField);
                int i = f.getInt(null);
                return i;
            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        
        public static void setStaticInt(String theClass, String theField, int set){
            try{
                Class c = Class.forName(theClass);
                Field f = c.getField(theField);
                f.setInt(null, set);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        public static int getIntFromTileEntity(String theClass, String theField, TileEntity t) {
            try {
                Class c = Class.forName(theClass);
                Field f = c.getField(theField);
                int i = f.getInt(t);
                return i;
            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
        }

        // This stuff got moved from the base denLib class.
        // There are still shell functions up there so shit doesn't break.
        public static Item getItem(String mod, String name) {
            boolean hooked = false;
            Item temp = null;

            try {
                temp = (Item) Class.forName(mod).getField(name).get(null);
                hooked = true;
            } catch (Exception ex) {
                print("" + ex);
                hooked = false;
            } finally {
                if (hooked) {
                    //print("Item " + temp.getItemName() + " hooked!");
                }
            }

            return temp;
        }

        public static ItemStack getNewItemStack(String mod, String name) {
            return new ItemStack(getItem(mod, name));
        }
        
        public static ItemStack getNewItemStackBlock(String mod, String name){
            return new ItemStack(getBlock(mod,name));
        }

        public static ItemStack getItemStack(String mod, String name) {
            boolean hooked = false;
            ItemStack temp = null;


            try {
                temp = (ItemStack) Class.forName(mod).getField(name).get(null);
                hooked = true;
            } catch (Exception ex) {
                print("" + ex);
                hooked = false;
            } finally {
                if (hooked) {
                    //print("Item " + temp.getItemName() + " hooked!");
                }

                return temp;
            }
        }

        public static Block getBlock(String mod, String name) {
            boolean hooked = false;
            Block temp = null;

            try {
                temp = (Block) Class.forName(mod).getField(name).get(null);
                hooked = true;
            } catch (Exception ex) {
                print("" + ex);
                hooked = false;
            } finally {
                if (hooked) {
                    //print("Block " + temp.getBlockName() + " hooked!");
                }
            }

            return temp;
        }

        public static void classSnoop(String param1) {
            try {
                Class c = Class.forName(param1);
                print("--------------------");
                print("Class Snoop Engaged");
                print("Class: " + param1);
                print("--------------------");
                Method[] m = c.getDeclaredMethods();
                Field[] f = c.getDeclaredFields();
                print("Defined Methods");
                print("--------------------");
                for (int i = 0; i < m.length; i++) {
                    print(m[i].getName());
                }
                print("--------------------");
                print("Defined Fields");
                print("--------------------");
                for (int i = 0; i < f.length; i++) {
                    print(f[i].getName());
                }
                print("--------------------");
                print("End of Snoop");
                print("--------------------");
            } catch (Exception ex) {
            }

        }
    }
}
