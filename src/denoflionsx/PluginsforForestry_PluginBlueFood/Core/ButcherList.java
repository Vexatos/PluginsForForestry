package denoflionsx.PluginsforForestry_PluginBlueFood.Core;

import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ButcherList {

    public static boolean setup_list = false;

    static {
        PfFManagers.ButcherKnife.addDropToAnimal(EnumAnimals.ANIMALS.COW, StackUtils.getNewStack(PfFManagers.Foods.getItemByTag("groundbeef"), FoodTuning.Tuning.ButcherKnife_GroundBeefDropped));
        PfFManagers.ButcherKnife.addDropToAnimal(EnumAnimals.ANIMALS.PIG, StackUtils.getNewStack(PfFManagers.Foods.getItemByTag("sausage"), FoodTuning.Tuning.ButcherKnife_SausageDropped));
        PfFManagers.ButcherKnife.addDropToAnimal(EnumAnimals.ANIMALS.SHEEP, StackUtils.getNewStack(PfFManagers.Foods.getItemByTag("lambchop"), FoodTuning.Tuning.ButcherKnife_LambChopDropped));
        PfFManagers.ButcherKnife.addDropToAnimal(EnumAnimals.ANIMALS.SQUID, StackUtils.getNewStack(PfFManagers.Foods.getItemByTag("tentacle"), FoodTuning.Tuning.ButcherKnife_TentacleDropped));
        PfFManagers.ButcherKnife.addDropToAnimal(EnumAnimals.ANIMALS.CHICKEN, new ItemStack(Item.feather, FoodTuning.Tuning.ButcherKnife_BonusChickenFeathers));
    }
}
