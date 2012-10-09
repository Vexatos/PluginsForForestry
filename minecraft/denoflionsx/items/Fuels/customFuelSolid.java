package denoflionsx.items.Fuels;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.Handlers.FuelHandler;
import denoflionsx.denLib.denLib;
import denoflionsx.items.PfFBar;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import forestry.api.recipes.RecipeManagers;

public class customFuelSolid {

    // This system handles the creation of peat engine fuel.
    private String name = "";
    private String nameLowerCaseNoSpaces = "";
    private String liquidNameLowerCaseNoSpaces = "";
    private int ItemID = 0;
    private int MJt = 0;
    private int BurnTime = 0;
    private int texture;
    private boolean isShiny = false;
    private PfFBar fuel;
    private int color = 0;

    public customFuelSolid(int par1, String name, int MJt, int BurnTime, int texture, boolean isShiny, int color) {
        this.name = name;
        this.nameLowerCaseNoSpaces = denLib.toLowerCaseNoSpaces(this.name) + "bar";
        this.liquidNameLowerCaseNoSpaces = denLib.toLowerCaseNoSpaces(name);
        this.ItemID = par1;
        this.MJt = MJt;
        this.BurnTime = BurnTime;
        this.texture = texture;
        this.isShiny = isShiny;
        this.color = color;
        fuel = new PfFBar(this.ItemID, this.nameLowerCaseNoSpaces);
        fuel.metaMap.put(this.name, 0);
        fuel.add(this.nameLowerCaseNoSpaces, fuel.metaMap.get(this.name), this.texture, this.name + " Bar", this.isShiny);
        fuel.addRenderColor(0, this.color);
        FuelManager.copperEngineFuel.put(fuel.shiftedIndex, new EngineCopperFuel(PfFManagers.ItemManager.getItem(this.nameLowerCaseNoSpaces), this.MJt, this.BurnTime));
        FuelHandler.values.put(this.fuel.shiftedIndex, 6000);
        recipe();
    }

    private void recipe() {
        ArrayList<ItemStack> containers = PfFManagers.ItemManager.getContainersForLiquidNoBarrel(this.liquidNameLowerCaseNoSpaces);
        for (ItemStack i : containers){
                FMLWrapper.MODE.FML.addShapelessRecipe(PfFManagers.ItemManager.getItem(this.nameLowerCaseNoSpaces), new Object[]{PfFManagers.ItemManager.getItem("infusionbar"),i});
        }
        SqueezerHelper.add(PfFManagers.ItemManager.getItem(this.nameLowerCaseNoSpaces),PfFManagers.ItemManager.getItem("infusionbar") , 100, PfFManagers.ItemManager.getItem(this.liquidNameLowerCaseNoSpaces),1000);
        RecipeManagers.carpenterManager.addRecipe(10, new LiquidStack(PfFManagers.ItemManager.getItem(this.liquidNameLowerCaseNoSpaces).itemID,1000), null, new ItemStack(this.fuel), new Object[]{"BXX","XXX","XXX",Character.valueOf('B'),PfFManagers.ItemManager.getItem("infusionbar")});
    }
}
