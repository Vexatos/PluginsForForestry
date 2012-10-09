package denoflionsx.Managers;

import denoflionsx.Interfaces.IOreDictRecipeManager;
import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class OreDictSmeltManager implements IOreDictRecipeManager{

    @Override
    public void addRecipes(ItemStack output, ArrayList<ItemStack> ores) {
        for (ItemStack i : ores){
            FMLWrapper.MODE.FML.addSmelt(output, i);
        }
    }
}
