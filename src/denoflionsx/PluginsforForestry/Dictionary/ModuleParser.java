package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
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

        public LiquidStack getLiquidStack(int amount) {
            // The parsing system removes whitespace so I need to find the proper tag that HAS spaces.
            // I fucking love BiMaps.
            for (String name : LRLiquids.LRLiquids.inverse().values()) {
                if (denLib.StringUtils.removeSpaces(name).equals(this.getLiquidTag())) {
                    return denLib.LiquidStackUtils.getNewStackCapacity(LRLiquids.LRLiquids.get(name), amount);
                }
            }
            return null;
        }
    }
}
