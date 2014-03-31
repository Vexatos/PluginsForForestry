package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleParser extends ModuleParserTemplate {

    public ModuleParser(String[] lines) {
        super(lines);
    }

    public int getAmount() {
        return Integer.valueOf(findTagAndParse(lines, "# Amount:"));
    }

    public ArrayList<SqueezeObject> getAllEntires() {
        ArrayList<SqueezeObject> l = new ArrayList();
        for (String s : lines) {
            if (!s.contains("#")) {
                l.add(new SqueezeObject(denLib.StringUtils.removeSpaces(s), this.getAmount(), this.getType()));
            }
        }
        return l;
    }

    public static class SqueezeObject {

        private String tag;
        private int amount;
        private String liquidTag;

        public SqueezeObject(String tag, int amount, String liquidTag) {
            this.tag = tag;
            this.amount = amount;
            this.liquidTag = liquidTag;
        }

        public int getAmount() {
            return amount;
        }

        public String getTag() {
            return tag;
        }

        public ArrayList<ItemStack> getItemStackFromDictionary() {
            return OreDictionary.getOres(tag);
        }

        public String getLiquidTag() {
            return liquidTag;
        }

        public FluidStack getFluidStack(int amount) {
            return FluidRegistry.getFluidStack(this.liquidTag, amount);
        }
    }
}
