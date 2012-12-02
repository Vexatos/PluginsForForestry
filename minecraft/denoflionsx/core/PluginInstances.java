package denoflionsx.core;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.Loader.pluginLoader;
import denoflionsx.plugins.BluesFood.pluginBlueFood;
import denoflionsx.plugins.Buildcraft.pluginBuildcraft;
import denoflionsx.plugins.Core.pluginCoreItems;
import denoflionsx.plugins.FarmCraftory.pluginFarmCraftory;
import denoflionsx.plugins.Forestry.pluginForestry;
import denoflionsx.plugins.IC2.pluginIC2;
import denoflionsx.plugins.Millenaire.pluginMillenaire;
import denoflionsx.plugins.Railcraft.pluginRailcraft;
import denoflionsx.plugins.Updater.pluginUpdater;

public class PluginInstances {

    public static pluginLoader Loader = new pluginLoader("Loader", EnumModIDs.MODS.FORESTRY.getID());
    public static pluginForestry Forestry = new pluginForestry("pluginForestry", EnumModIDs.MODS.FORESTRY.getID());
    public static pluginBuildcraft Buildcraft = new pluginBuildcraft("pluginBuildcraft", EnumModIDs.MODS.BUILDCRAFT_CORE.getID());
    public static pluginBlueFood BlueFood = new pluginBlueFood("pluginBlueFood", EnumModIDs.MODS.FORESTRY.getID());
    public static pluginIC2 IC2 = new pluginIC2("pluginIc2", EnumModIDs.MODS.IC2.getID());
    public static pluginRailcraft Railcraft = new pluginRailcraft("pluginRailcraft", EnumModIDs.MODS.RAILCRAFT.getID());
    public static pluginFarmCraftory FarmCraftory = new pluginFarmCraftory("pluginFarmCraftory", EnumModIDs.MODS.FARMCRAFTORY.getID());
    //public static pluginBlueSilkWorm BlueSilkWorm = new pluginBlueSilkWorm("pluginBlueSilkWorm", EnumModIDs.MODS.FORESTRY.getID());
    //public static pluginForestryAPS ForestryAPS = new pluginForestryAPS("pluginForestryAPS", EnumModIDs.MODS.FORESTRY.getID());
    public static pluginMillenaire Millenaire = new pluginMillenaire();
    //public static pluginForgottenNature FN = new pluginForgottenNature();
    public static pluginCoreItems CoreItems = new pluginCoreItems("pluginCoreItems", EnumModIDs.MODS.FORESTRY.getID());
    public static pluginUpdater Updater = new pluginUpdater("pluginUpdater", EnumModIDs.MODS.FORESTRY.getID());
}
