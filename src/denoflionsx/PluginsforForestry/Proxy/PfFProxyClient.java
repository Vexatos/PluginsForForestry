package denoflionsx.PluginsforForestry.Proxy;

import denoflionsx.LiquidRoundup.gfx.SpriteUtils;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.PluginsforForestry.Utils.ReflectUtils;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class PfFProxyClient extends PfFProxy {

//    private static boolean inject = false;
//    private static int index = -1;

    @Override
    public void addSheetToMap(String sheet) {
        super.addSheetToMap(sheet);
        SpriteUtils.addBlankSheetToMap(sheet);
    }

    @Override
    public void saveList(ArrayList<ItemStack> list, String category) {
        BackpackUtils.turnListToFile(category, list);
    }

    @Override
    public void getAllReferences(String clazz, ArrayList<ItemStack> add) {
        ReflectUtils.getAllReferences(clazz, add);
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public void makeEntityDropItem(Entity entity, ItemStack item) {
    }
}
