package denoflionsx.plugins.Railcraft.Managers;

import denoflionsx.Interfaces.IOreDictRecipeManager;
import denoflionsx.plugins.Railcraft.RockCrusherRecipeHelper;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class RockCrusherManager implements IOreDictRecipeManager{

    @Override
    public void addRecipes(ItemStack output, ArrayList<ItemStack> ores) {
        for (ItemStack i : ores){
            RockCrusherRecipeHelper.Recipe r = new RockCrusherRecipeHelper.Recipe(i);
            r.addOutput(output, 100);
            RockCrusherRecipeHelper.add(r);
        }
    }
}
