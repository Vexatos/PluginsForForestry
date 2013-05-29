package denoflionsx.PluginsforForestry.Utils;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class FermenterUtils {

    public static void registerFermenterBooster(LiquidStack liquid, float bonus) {
        try {
            ArrayList<FermenterRecipe> r = new ArrayList();
            Class c = Class.forName("forestry.factory.gadgets.MachineFermenter");
            for (Class c2 : c.getDeclaredClasses()) {
                if (Pattern.compile("$", Pattern.LITERAL).split(c2.getName())[1].equals("RecipeManager")) {
                    ArrayList<Object> recipes = (ArrayList<Object>) c2.getField("recipes").get(null);
                    for (Class c3 : c.getDeclaredClasses()) {
                        if (Pattern.compile("$", Pattern.LITERAL).split(c3.getName())[1].equals("Recipe")) {
                            for (Object o : recipes) {
                                ItemStack resource = (ItemStack) c3.getField("resource").get(c3.cast(o));
                                int value = Integer.valueOf(String.valueOf(c3.getField("fermentationValue").get(c3.cast(o))));
                                LiquidStack l = (LiquidStack) c3.getField("liquid").get(c3.cast(o));
                                if (l == null){
                                    continue;
                                }
                                LiquidStack output = (LiquidStack) c3.getField("output").get(c3.cast(o));
                                if (output.isLiquidEqual(LiquidDictionary.getLiquid("biomass", LiquidContainerRegistry.BUCKET_VOLUME))) {
                                    if (l.isLiquidEqual(new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME))) {
                                        r.add(new FermenterRecipe(resource, value));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            PfF.Proxy.print("Adapting " + liquid.asItemStack().getItem().getLocalizedName(liquid.asItemStack()) + " to fermenter. " + r.size() + " recipes.");
            for (FermenterRecipe z : r) {
                Forestry.fermenter(z, 1.5f, liquid, "biomass");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static class FermenterRecipe {

        private ItemStack fermentable;
        private int amount;

        public FermenterRecipe(ItemStack fermentable, int amount) {
            this.fermentable = fermentable;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public ItemStack getFermentable() {
            return fermentable;
        }
    }
}
