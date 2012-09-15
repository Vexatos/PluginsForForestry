package denoflionsx.API.Objects;

import denoflionsx.API.Enums.EnumAnimals;
import net.minecraft.src.ItemStack;

public class PfFButcherKnifeBonus {
    
    private ItemStack drop;
    private EnumAnimals.ANIMALS animal;

    public PfFButcherKnifeBonus(EnumAnimals.ANIMALS animal,ItemStack drop) {
        this.drop = drop;
        this.animal = animal;
    }

    public EnumAnimals.ANIMALS getAnimal() {
        return animal;
    }
    
    public ItemStack getDrop() {
        return drop.copy();
    }
}
