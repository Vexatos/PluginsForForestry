package denoflionsx.PluginsforForestry_PluginBlueFood.Core;

import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Enchants.EnchantBeastSlaying;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food.*;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemButcherKnife;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemUncooked;
import denoflionsx.PluginsforForestry_PluginBlueFood.gfx.FoodGfx;

public class PfFFoodCore implements IPfFCore {

    public String spritesheet;
    private int eventID;

    @Override
    public void preloadTextures() {
        PfF.Proxy.print("Starting Blue's Food module.");
        spritesheet = PfF.Proxy.preloadTextures(FoodGfx.pack + "/Food/food_sheet.png");
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
    }

    @Override
    public void setupBlocks() {
    }

    @PfFSubscribe
    public void onConfigEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            this.setupConfig();
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }

    @Override
    public void setupConfig() {
        FoodTuning.tuning_enabled = true;
    }

    @Override
    public void setupItems() {
        if (FoodTuning.Enables.CookedEgg_Enabled) {
            FoodList.egg = new ItemCookedEgg().recipe();
        }
        if (FoodTuning.Enables.Cupcake_Enabled) {
            FoodList.cupcake = new ItemCupcake();
        }
        if (FoodTuning.Enables.ButcherKnife_Enabled) {
            if (FoodTuning.Enchantments.BeastSlaying_Enabled) {
                FoodList.beastslaying = new EnchantBeastSlaying();
            }
            FoodList.knife = new ItemButcherKnife().recipe();
            FoodList.uncooked = new ItemUncooked().Recipes();
            ButcherList.setup_list = true;
            FoodList.cookedsausage = new ItemCookedSausage().recipe();
            FoodList.cheeseburger = new ItemCheeseBurger().recipe();
            FoodList.calimari = new ItemCalimari().recipe();
            FoodList.cookedlambchop = new ItemCookedLambChop().recipe();
        }
    }

    @Override
    public void lateCode() {
        if (FoodList.cupcake != null) {
            FoodList.cupcake.recipe();
        }
    }
}
