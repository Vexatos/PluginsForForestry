package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.core.ItemInterface;
import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLiquidVacuum extends PfFBase {

    public ItemLiquidVacuum(int par1) {
        super(par1);
    }

    public ItemLiquidVacuum() {
        this(CoreTuning.Items.liqvac);
        this.add("Liquid Vacuum", 0, 16);
        setupDropTable();
    }

    public final void createRecipe() {
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"gBg", "GHG", "gGg", Character.valueOf('H'), ItemInterface.getItem("sturdyCasing"), Character.valueOf('G'), new ItemStack(Item.leather), Character.valueOf('g'), ItemInterface.getItem("gearBronze"), Character.valueOf('B'), new ItemStack(Item.bucketEmpty)});
    }

    public final void setupDropTable() {
        PfFManagers.LiquidVacuum.addDropToAnimal(EnumAnimals.ANIMALS.SQUID, new ItemStack(Item.dyePowder));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, net.minecraft.entity.Entity entity) {
        EnumAnimals.ANIMALS animal = EnumAnimals.getAnimalType(entity);
        ArrayList<ItemStack> drops = PfFManagers.LiquidVacuum.getDropsForAnimal(animal);
        for (ItemStack d : drops) {
            player.dropPlayerItemWithRandomChoice(d.copy(), false);
        }
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving entity) {
        EnumAnimals.ANIMALS animal = EnumAnimals.getAnimalType(entity);
        ArrayList<ItemStack> drops = PfFManagers.LiquidVacuum.getDropsForAnimal(animal);
        for (ItemStack d : drops) {
            if (!entity.worldObj.isRemote) {
                entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, d.copy()));
            }
        }
        return super.itemInteractionForEntity(par1ItemStack, entity);
    }
}
