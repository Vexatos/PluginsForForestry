package denoflionsx.PluginsforForestry.Utils;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.PfF;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.minecraft.item.ItemStack;
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
                                LiquidStack output = (LiquidStack) c3.getField("output").get(c3.cast(o));
                                if (output.isLiquidEqual(ForestryLiquids.BIOMASS.getLiquidStack())) {
                                    if (l.isLiquidEqual(Constants.water)) {
                                        r.add(new FermenterRecipe(resource, value));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            PfF.Proxy.print("Adapting " + liquid.asItemStack().getItem().getItemNameIS(liquid.asItemStack()) + " to fermenter. " + r.size() + " recipes.");
            for (FermenterRecipe z : r) {
                APIWrappers.forestry.fermenter.addRecipe(z.getFermentable(), z.getAmount(), 1.5f, ForestryLiquids.BIOMASS.getLiquidStack(), liquid);
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
