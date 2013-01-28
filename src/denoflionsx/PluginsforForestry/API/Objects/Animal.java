package denoflionsx.PluginsforForestry.API.Objects;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals.ANIMALS;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.item.ItemStack;

public class Animal {
    
    private EnumAnimals.ANIMALS animal;
    private ArrayList<ItemStack> drops;

    public Animal(EnumAnimals.ANIMALS animal, ItemStack[] drops) {
        this.drops = new ArrayList();
        this.animal = animal;
        this.drops.addAll(Arrays.asList(drops));
    }

    public ArrayList<ItemStack> getDrops() {
        return drops;
    }

    public ANIMALS getAnimal() {
        return animal;
    }
    
    public void addItemStackToDropTable(ItemStack drop){
        drops.add(drop);
    }
}
