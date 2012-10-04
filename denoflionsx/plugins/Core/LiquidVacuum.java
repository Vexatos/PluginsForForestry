package denoflionsx.plugins.Core;

import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.Buildcraft.TankManager;
import denoflionsx.plugins.Buildcraft.goldGear;
import denoflionsx.plugins.Forestry.Helpers.pipette;
import forestry.api.core.ItemInterface;
import net.minecraft.src.*;

public class LiquidVacuum extends multiItem {

    public static boolean mushroombagEnabled = false;

    public LiquidVacuum(int par1) {
        super(par1, "liquidvacuum");
        this.setMaxStackSize(1);
        this.metaMap.put("Liquid Vacuum", 0);
        this.add("liquidvacuum", 0, EnumToolTextures.ToolTextures.LIQUIDVACUUM.getIndex(), "Liquid Vacuum");
        this.setTabToDisplayOn(CreativeTabs.tabTools);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (mushroombagEnabled) {
            if (entity instanceof EntityMooshroom) {
                player.dropPlayerItemWithRandomChoice(PfFManagers.ItemManager.getItem("mushroombag").copy(), false);
                return true;
            }
        }
        if (entity instanceof EntityCow) {
            player.dropPlayerItemWithRandomChoice(PfFManagers.ItemManager.getItem("milkbag").copy(), false);
            return true;
        }
        return true;
    }

    public static class Recipes {

        public static boolean isRecipeCheap = false;
        public static boolean useBCRecipe = false;

        public static Object[] getCheapRecipe() {
            return new Object[]{
                        "LLL",
                        "SIb",
                        "SIB",
                        Character.valueOf('L'), new ItemStack(Item.leather),
                        Character.valueOf('S'), new ItemStack(Item.silk),
                        Character.valueOf('I'), new ItemStack(Item.ingotIron),
                        Character.valueOf('b'), new ItemStack(Item.glassBottle),
                        Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                        Character.valueOf('B'), new ItemStack(Item.bucketEmpty)};
        }

        public static Object[] getBCExpensiveRecipe() {
            return new Object[]{
                        "LbL",
                        "ITI",
                        "GBG",
                        Character.valueOf('L'), Item.leather,
                        Character.valueOf('b'), Item.glassBottle,
                        Character.valueOf('I'), Block.blockSteel,
                        Character.valueOf('T'), TankManager.TankBlock,
                        Character.valueOf('G'), goldGear.goldGear,
                        Character.valueOf('B'), Item.bucketEmpty};
        }

        public static Object[] getExpensiveRecipe() {
            return new Object[]{
                        "PpP",
                        "MbM",
                        "GBG",
                        Character.valueOf('P'), ItemInterface.getItem("propolis"),
                        Character.valueOf('p'), pipette.pipette,
                        Character.valueOf('M'), ItemInterface.getItem("sturdyCasing"),
                        Character.valueOf('b'), Item.glassBottle,
                        Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                        Character.valueOf('B'), Item.bucketEmpty};
        }
    }
}
