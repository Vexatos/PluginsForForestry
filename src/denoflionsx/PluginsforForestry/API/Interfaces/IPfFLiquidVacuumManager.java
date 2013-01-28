package denoflionsx.PluginsforForestry.API.Interfaces;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

/*
 * Sends PfFEvent:
 * origin: LiquidVacuum
 * msg: animal enum name
 * obj: ItemStack
 */

public interface IPfFLiquidVacuumManager {
    
    public void addDropToAnimal(EnumAnimals.ANIMALS animal, ItemStack drop);
    
    public ArrayList<ItemStack> getDropsForAnimal(EnumAnimals.ANIMALS animal);
    
    public boolean doesAnimalMatch(EnumAnimals.ANIMALS animal);
    
}
