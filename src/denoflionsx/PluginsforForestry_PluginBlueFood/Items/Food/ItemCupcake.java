package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class ItemCupcake extends ItemFoodTemplate {

    public ItemCupcake(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
        this.setCreativeTab(PfF.Core.tab);
    }

    public ItemCupcake() {
        this(FoodTuning.Items.Cupcake_ItemID, FoodTuning.Food.Cupcake_healAmount, (float) FoodTuning.Food.Cupcake_saturationModifer, 36, "Cupcake");
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Fabulous!");
    }

    public void recipe() {
        ItemStack bucket = new ItemStack(Item.bucketMilk);
        ItemStack Egg = new ItemStack(Item.egg);
        ItemStack Sugar = new ItemStack(Item.sugar);
        LiquidStack milk = LiquidContainerRegistry.getLiquidForFilledItem(bucket);
        if (milk != null) {
            for (LiquidContainerData d : LiquidContainerRegistry.getRegisteredLiquidContainerData()) {
                if (d.stillLiquid.isLiquidEqual(milk)) {
                    if (!d.container.getItemName().equals("item.barrel")) {
                        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this, FoodTuning.Tuning.Cupcake_AmountPerCraft, 0), new Object[]{"SSS", "MEM", "FFF", Character.valueOf('S'), Sugar, Character.valueOf('M'), d.filled.copy(), Character.valueOf('E'), Egg, Character.valueOf('F'), PfFManagers.Items.getItemByTag("flour")});
                    }
                }
            }
            APIWrappers.forestry.carpenter.addRecipe(5, StackUtils.getNewStack(milk, 2000), null, new ItemStack(this, FoodTuning.Tuning.Cupcake_AmountPerCraft, 0), new Object[]{"SSS", "XEX", "FFF", Character.valueOf('S'), Sugar, Character.valueOf('E'), Egg, Character.valueOf('F'), PfFManagers.Items.getItemByTag("flour")});
        } else {
            PfF.Proxy.print("Milk not found?");
        }
    }
}
