package denoflionsx.plugins.Forestry.Modules.newFuels;

import denoflionsx.API.PfFManagers;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.FuelHandler;
import denoflionsx.denLib.denLib;
import denoflionsx.items.PfFBar;

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
        ArrayList<ItemStack> containers = new ArrayList();
        ArrayList<String> container_names = new ArrayList();
        container_names.add("Capsule");
        container_names.add("Can");
        container_names.add("Capsule_Red");
        container_names.add("Cell");
        for (String s : container_names) {
            ItemStack item = PfFManagers.ItemManager.getItemQuietly(this.liquidNameLowerCaseNoSpaces + denLib.toLowerCaseNoSpaces(s));
            if (item != null) {
                containers.add(item);
            }
        }
        for (ItemStack i : containers) {
            FMLWrapper.MODE.FML.addShapelessRecipe(PfFManagers.ItemManager.getItem(this.nameLowerCaseNoSpaces), new Object[]{PfFManagers.ItemManager.getItem("infusionbar"), i});
        }
    }
}
