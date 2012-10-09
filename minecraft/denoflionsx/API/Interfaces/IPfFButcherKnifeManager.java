package denoflionsx.API.Interfaces;

import denoflionsx.API.Enums.EnumAnimals;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public interface IPfFButcherKnifeManager {
    
    public void addDropToAnimal(EnumAnimals.ANIMALS animal, ItemStack bonus);
    
    public ArrayList<ItemStack> getBonusDropTable(EnumAnimals.ANIMALS animal);
    
}
