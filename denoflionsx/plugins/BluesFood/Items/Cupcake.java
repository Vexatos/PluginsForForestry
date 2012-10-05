package denoflionsx.plugins.BluesFood.Items;

import java.util.List;
import net.minecraft.src.ItemStack;

public class Cupcake extends ItemFoods{

    public Cupcake(int par1, int texture, int par2, float par3, String name) {
        super(par1, texture, par2, par3, name);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, List par2List) {
        par2List.add("Fabulous!");
    }
    
    
    
}
