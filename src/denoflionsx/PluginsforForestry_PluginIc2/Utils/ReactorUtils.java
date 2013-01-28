package denoflionsx.PluginsforForestry_PluginIc2.Utils;

import ic2.api.IReactor;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ReactorUtils {

    private final static ArrayList<Coords> c = new ArrayList();

    static {
        c.add(new Coords(0, 0));
        c.add(new Coords(1, 0));
        c.add(new Coords(2, 0));
        //
        c.add(new Coords(0, 1));
        c.add(new Coords(1, 1));
        c.add(new Coords(2, 1));
        //
        c.add(new Coords(0, 2));
        c.add(new Coords(1, 2));
        c.add(new Coords(2, 2));
        //
        c.add(new Coords(0, 3));
        c.add(new Coords(1, 3));
        c.add(new Coords(2, 3));
        //
        c.add(new Coords(0, 4));
        c.add(new Coords(1, 4));
        c.add(new Coords(2, 4));
        //
        c.add(new Coords(0, 5));
        c.add(new Coords(1, 5));
        c.add(new Coords(2, 5));
    }

    public static void reactorToNBT(IReactor reactor, NBTTagCompound tag) {
        for (Coords q : c){
            if (reactor.getItemAt(q.getX(), q.y) != null){
                ItemStack part = reactor.getItemAt(q.getX(), q.y);
                NBTTagCompound item = new NBTTagCompound();
                part.writeToNBT(item);
                tag.setCompoundTag(q.getX() + "|" + q.getY(), item);
                reactor.setItemAt(q.getX(), q.getY(), null);
            }
        }
    }
    
    public static void NBTtoReactor(IReactor reactor, NBTTagCompound tag){
        for (Coords q : c){
            if (reactor.getItemAt(q.getX(), q.getY()) == null){
                NBTTagCompound item = tag.getCompoundTag(q.getX() + "|" + q.getY());
                ItemStack part = new ItemStack(Item.appleGold);
                part.readFromNBT(item);
                reactor.setItemAt(q.getX(), q.getY(), part);
            }
        }
    }

    private static class Coords {

        private int x;
        private int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
