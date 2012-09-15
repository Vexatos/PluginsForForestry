package denoflionsx.Managers;

import denoflionsx.API.Enums.EnumAnimals;
import denoflionsx.API.Interfaces.IPfFButcherKnifeManager;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.src.ItemStack;

public class PfFButcherKnifeManager implements IPfFButcherKnifeManager{
    
    private HashMap<EnumAnimals.ANIMALS,ArrayList<ItemStack>> bonuses = new HashMap();

    public PfFButcherKnifeManager() {
        for (EnumAnimals.ANIMALS a : EnumAnimals.ANIMALS.values()){
            ArrayList<ItemStack> bonus = new ArrayList();
            bonuses.put(a,bonus);
        }
    }

    @Override
    public void addDropToAnimal(EnumAnimals.ANIMALS animal, ItemStack bonus) {
        bonuses.get(animal).add(bonus);
    }

    @Override
    public ArrayList<ItemStack> getBonusDropTable(EnumAnimals.ANIMALS animal) {
        return bonuses.get(animal);
    }
    
    
    
}
