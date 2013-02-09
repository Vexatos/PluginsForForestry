package denoflionsx.PluginsforForestry.Items;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Interfaces.ILateRunner;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.denLib;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public class ItemBags extends PfFBase {

    public ItemBags(int par1) {
        super(par1);
    }

    public ItemBags() {
        super(CoreTuning.Items.bags);
        this.add("Milk Bag", 0, 17);
        this.add("Bag O Soup", 1, 18);
        this.add("Bag", 2, 19);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.getItemDamage() != 2) {
            par3List.add("Squeeze Me!");
        }
    }

    public final void linkLiquidRecipes() {
        // Milk
        PfFManagers.LiquidVacuum.addDropToAnimal(EnumAnimals.ANIMALS.COW, PfFManagers.Items.getItemByTag("milkbag"));
        for (LiquidRegisterEvent event : LiquidRoundup.events) {
            try {
                if (event.Liquid.asItemStack().getDisplayName().equals("Milk")) {
                    milk = event.Liquid;
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }
        if (milk != null) {
            LiquidContainerData d = new LiquidContainerData(milk, PfFManagers.Items.getItemByTag("milkbag"), PfFManagers.Items.getItemByTag("bag"));
            LiquidContainerRegistry.registerLiquid(d);
            APIWrappers.forestry.squeezer.addRecipe(5, new ItemStack[]{PfFManagers.Items.getItemByTag("milkbag")}, milk);
        }
        // Mushroom Soup
        PfFManagers.LiquidVacuum.addDropToAnimal(EnumAnimals.ANIMALS.MOOSHROOM, PfFManagers.Items.getItemByTag(denLib.toLowerCaseNoSpaces("Bag O Soup")));
        LiquidStack s = PfFManagers.Liquids.getLiquidStackByTag("mushroomsoup");
        if (s == null) {
            try {
                throw new Exception("The soup is null!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        LiquidContainerData d2 = new LiquidContainerData(s, new ItemStack(this, 1, 1), PfFManagers.Items.getItemByTag("bag"));
        LiquidContainerRegistry.registerLiquid(d2);
        APIWrappers.forestry.squeezer.addRecipe(5, new ItemStack[]{new ItemStack(this, 1, 1)}, s);

        PluginLiquidRoundup.valid.addAll(this.stacks);
        PfF.Core.lateRunners.add(new MilkTransposerFix());
    }
    private static LiquidStack milk;

    public static class MilkTransposerFix implements ILateRunner {

        @Override
        public void runLate() {
            if (milk != null) {
                APIWrappers.TE.transposer.addExtractionRecipe(160, PfFManagers.Items.getItemByTag("milkbag"), PfFManagers.Items.getItemByTag("bag"), milk, true);
            }
        }
    }
}
