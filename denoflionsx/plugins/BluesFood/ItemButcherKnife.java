package denoflionsx.plugins.BluesFood;

import denoflionsx.API.Enums.EnumAnimals;
import net.minecraft.src.*;
import denoflionsx.API.PfFManagers;
import denoflionsx.denLib.denLib;
import java.util.ArrayList;

public class ItemButcherKnife extends ItemFoodTool {

    public ItemButcherKnife(int par1, int texture, int pew, int maxDamage, String name) {
        super(par1, texture, pew, maxDamage, name);
        PfFManagers.ItemManager.registerItem(denLib.toLowerCaseNoSpaces(name), this);
    }

    @Override
    public int getDamageVsEntity(Entity par1Entity) {
        // If entity is an animal kick it's ass in one shot.
        if (EnumAnimals.getMatchingEnum(par1Entity) != EnumAnimals.ANIMALS.NULL) {
            return (this.pew * 5);
        } else {
            return this.pew;
        }
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
        // If entity is an animal give us some extra loot!
        EnumAnimals.ANIMALS a = EnumAnimals.getMatchingEnum(par2EntityLiving);
        if (a != EnumAnimals.ANIMALS.NULL) {
            EntityPlayer player = (EntityPlayer) par3EntityLiving;
            ArrayList<ItemStack> bonus = PfFManagers.ButcherKnifeManager.getBonusDropTable(a);
            for (ItemStack b : bonus) {
                player.dropPlayerItem(b.copy());
            }
        }
        return true;
    }
}
