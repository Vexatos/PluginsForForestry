package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals.ANIMALS;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquidVacuumManager;
import denoflionsx.PluginsforForestry.API.Objects.Animal;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class PfFLiquidVacuumManager implements IPfFLiquidVacuumManager {

    public Animal[] Animals = new Animal[7];

    public PfFLiquidVacuumManager() {
        int i = 0;
        for (EnumAnimals.ANIMALS a : EnumAnimals.ANIMALS.values()) {
            Animals[i] = new Animal(a, new ItemStack[]{});
            i++;
        }
    }

    @Override
    public boolean doesAnimalMatch(ANIMALS animal) {
        for (Animal a : Animals) {
            if (a.getAnimal().doesEnumMatch(animal)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addDropToAnimal(ANIMALS animal, ItemStack drop) {
        for (Animal a : Animals) {
            if (a.getAnimal().doesEnumMatch(animal)) {
                a.addItemStackToDropTable(drop);
                PfFManagers.Events.SystemEvents.raiseAlert("LiquidVacuum", animal.toString(), drop);
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDropsForAnimal(ANIMALS animal) {
        for (Animal a : Animals) {
            if (a.getAnimal().doesEnumMatch(animal)) {
                return a.getDrops();
            }
        }
        return new ArrayList();
    }
}
