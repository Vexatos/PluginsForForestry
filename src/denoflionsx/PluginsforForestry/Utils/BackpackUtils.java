package denoflionsx.PluginsforForestry.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.PfF;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class BackpackUtils {

    public static final File c = new File(PfF.Proxy.getConfigDir() + "Backpacks.cfg");
    public static final Configuration config = new Configuration(c);

    static {
        if (doesConfigExist()) {
            config.load();
        }
    }
    
    public static boolean doesConfigExist(){
        if (c.exists()) {
            return true;
        }else{
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public static void turnListToFile(String category, ArrayList<ItemStack> items) {
        int count = 0;
        for (ItemStack i : items) {
            if (i != null) {
                String clazz = "";
                try {
                    clazz = String.valueOf(i.itemID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int meta = i.getItemDamage();
                if (!clazz.equals("0")) {
                    config.get("backpacks." + category, "item" + count, clazz + "," + meta);
                    count++;
                }
            }
        }
        config.save();
    }
}
