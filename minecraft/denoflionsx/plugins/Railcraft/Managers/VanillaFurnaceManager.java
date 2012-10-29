package denoflionsx.plugins.Railcraft.Managers;

import denoflionsx.Interfaces.IOreDictRecipeManager;
import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class VanillaFurnaceManager implements IOreDictRecipeManager{

    @Override
    public void addRecipes(ItemStack output, ArrayList<ItemStack> ores) {
        if (ores.isEmpty()){
            return;
        }
        FMLWrapper.MODE.FML.addSmelt(output,ores.get(0));
    }
}
