package denoflionsx.plugins.BlueSilkWorm.Foods;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormFood;
import net.minecraft.src.ItemStack;

public class SilkWormFood implements ISilkWormFood{
    
    private ItemStack food;
    private int value;

    public SilkWormFood(ItemStack food, int value) {
        this.food = food;
        this.value = value;
    }

    @Override
    public ItemStack getFood() {
        return this.food;
    }

    @Override
    public int getFoodValue() {
        return this.value;
    }

}
