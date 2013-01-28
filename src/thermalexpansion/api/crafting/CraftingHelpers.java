
package thermalexpansion.api.crafting;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import thermalexpansion.api.core.ItemRegistry;

/**
 * This class adds some basic and fool-proof recipe handlers that can help you out. They will add simple recipes that follow the TE defaults - they are not the
 * only way of adding recipes. You can also use the more specific recipe functions defined in the Manager interfaces if necessary. Once again, only call these
 * during @PostInit or things may not go so rosy.
 */
public class CraftingHelpers {

    private static final ItemStack fluxSand = new ItemStack(Block.sand);

    /**
     * Ore x1 to Dust x2 conversion. Will return false if recipe already exists. 400 MJ.
     */
    public boolean addPulverizerOreToDustRecipe(ItemStack inputOre, ItemStack outputDust) {

        ItemStack ore = inputOre.copy();
        ore.stackSize = 1;

        ItemStack primaryDust = outputDust.copy();
        primaryDust.stackSize = 2;

        return CraftingManagers.pulverizerManager.addRecipe(400, ore, primaryDust);
    }

    /**
     * Ore x1 to Dust x2 conversion, 5% chance of Secondary x1 being generated. 400 MJ.
     */
    public boolean addPulverizerOreToDustRecipe(ItemStack inputOre, ItemStack outputDust, ItemStack outputSecondary) {

        ItemStack ore = inputOre.copy();
        ore.stackSize = 1;

        ItemStack primaryDust = outputDust.copy();
        primaryDust.stackSize = 2;

        ItemStack secondary = outputSecondary.copy();
        secondary.stackSize = 1;

        return CraftingManagers.pulverizerManager.addRecipe(400, ore, primaryDust, secondary, 5);
    }

    /**
     * Log x1 to Plank x6 conversion, 100% chance of Sawdust. 80 MJ.
     */
    public boolean addSawmillLogToPlankRecipe(ItemStack inputLog, ItemStack outputPlanks) {

        ItemStack log = inputLog.copy();
        log.stackSize = 1;

        ItemStack planks = outputPlanks.copy();
        planks.stackSize = 6;

        ItemStack sawdust = ItemRegistry.getItem("sawdust", 1);

        return CraftingManagers.sawmillManager.addRecipe(80, log, planks, sawdust);
    }

    /**
     * Dust x2, Sand x1 to Ingot x2, 25% chance of Slag. 80 MJ. Also, Dust x2, Rich Slag x1 to Ingot x3, 33% chance of Slag. 320 MJ.
     */
    public boolean addSmelterDustToIngotsRecipe(ItemStack inputDust, ItemStack outputIngots) {

        ItemStack dust = inputDust.copy();
        dust.stackSize = 2;

        ItemStack ingots2 = outputIngots.copy();
        ingots2.stackSize = 2;

        ItemStack ingots3 = outputIngots.copy();
        ingots3.stackSize = 3;

        ItemStack slag = ItemRegistry.getItem("slag", 1);
        ItemStack richSlag = ItemRegistry.getItem("richSlag", 1);

        if (!CraftingManagers.smelterManager.addRecipe(80, dust, fluxSand, ingots2, slag, 25)) {
            return false;
        }
        if (!CraftingManagers.smelterManager.addRecipe(320, dust, richSlag, ingots3, slag, 33)) {
            return false;
        }
        return true;
    }

    /**
     * Ore x1, Sand x1 to Ingot x2, 15% chance of Rich Slag. 320 MJ.
     */
    public boolean addSmelterOreToIngotsRecipe(ItemStack inputOre, ItemStack outputIngots) {

        ItemStack ore = inputOre.copy();
        ore.stackSize = 1;

        ItemStack ingots = outputIngots.copy();
        ingots.stackSize = 2;

        ItemStack slagRich = ItemRegistry.getItem("slagRich", 1);

        return CraftingManagers.smelterManager.addRecipe(320, ore, fluxSand, ingots, slagRich, 15);
    }
}