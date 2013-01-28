package denoflionsx.PluginsforForestry.API.Objects;

import java.util.*;
import net.minecraft.item.ItemStack;

public class DictMap implements Map {

    private HashMap<String, ArrayList<Dict>> stacks = new HashMap();

    @Override
    public void clear() {
        stacks.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return stacks.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof ItemStack)) {
            return false;
        }
        ItemStack compare = (ItemStack) value;
        for (ArrayList<Dict> stack : stacks.values()) {
            for (Dict i : stack) {
                if (i.getStack().isItemEqual(compare)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set entrySet() {
        return stacks.entrySet();
    }

    @Override
    public Object get(Object key) {
        return stacks.get(key);
    }

    @Override
    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    @Override
    public Set keySet() {
        return stacks.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        if (key instanceof String) {
            if (stacks.get((String) key) != null) {

                ArrayList<Dict> s = stacks.get((String) key);
                if (value instanceof Dict) {
                    s.add((Dict) value);
                }
                stacks.put((String) key, s);
                return s;
            } else {
                ArrayList<Dict> s = new ArrayList();
                if (value instanceof Dict) {
                    s.add((Dict) value);
                    stacks.put((String) key, s);
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        stacks.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        if (stacks.get((String) key) != null) {
            Object o = stacks.get((String) key);
            stacks.remove(key);
            return o;
        }
        return null;
    }

    @Override
    public int size() {
        return stacks.size();
    }

    @Override
    public Collection values() {
        return stacks.values();
    }

    public static class Dict {

        private String name;
        private ItemStack stack;
        private int liquidAmount;

        public Dict(String name, ItemStack stack, int liquidAmount) {
            this.name = name;
            this.stack = stack;
            this.liquidAmount = liquidAmount;
        }

        public int getLiquidAmount() {
            return liquidAmount;
        }

        public String getName() {
            return name;
        }

        public ItemStack getStack() {
            return stack;
        }
    }
}
