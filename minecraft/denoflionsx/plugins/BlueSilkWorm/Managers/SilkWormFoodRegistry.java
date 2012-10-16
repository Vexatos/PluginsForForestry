package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.API.PfFManagers;
import denoflionsx.Managers.PfFFermenterManager.Fermentable;
import denoflionsx.plugins.BlueSilkWorm.Foods.SilkWormFood;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormFood;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormFoodRegistry;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class SilkWormFoodRegistry implements ISilkWormFoodRegistry{
    
    public static ArrayList<ISilkWormFood> foods = new ArrayList();

    public SilkWormFoodRegistry() {
        for (Object o : PfFManagers.FermenterManager.getAllFermentableObjects()){
            if (o instanceof Fermentable){
                Fermentable f = (Fermentable)o;
                registerFood(f.getItem(),f.getBonus());
            }
        }
    }
    
    @Override
    public int getValueForFood(ItemStack food) {
        for (ISilkWormFood f : foods){
            if (f.getFood().isItemEqual(food)){
                return f.getFoodValue();
            }
        }
        return -1;
    }

    @Override
    public boolean isValidFood(ItemStack food) {
        for (ISilkWormFood f : foods){
            if (f.getFood().isItemEqual(food)){
                return true;
            }
        }
        return false;
    }

    @Override
    public final void registerFood(ItemStack food, int value) {
        foods.add(new SilkWormFood(food,value));
    }
 
}
