package net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels;

import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.items.PfFContainer;
import net.minecraft.src.denoflionsx.plugins.Forestry.EnumContainers;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;

// This class is to automate the creation of basic fuel liquids and the
// associated containers.
// Special liquids like milk and radioactive waste won't work with this.
public class customFuel {

    private String name = "";
    private String nameLowerCaseNoSpaces = "";
    private int MJt = 0;
    private int burnTime = 0;
    private PfFContainer fuel = null;
    private int textures[] = new int[]{};
    private int itemID = 0;
    private int isSafeFuel = 1;
    private int color = Colors.Values.WHITE.getColor();
    public static int numOfContainers = 6;
    private int redCapMeta = 3;

    public customFuel(String name, int MJt, int burnTime, int textures[], int ItemID, int color) {

        this.name = name;
        this.MJt = MJt;
        this.burnTime = burnTime;
        this.textures = textures;
        this.itemID = ItemID;
        this.nameLowerCaseNoSpaces = denLib.toLowerCaseNoSpaces(name);
        this.color = color;
        this.createItems();

    }

    public static int[] populateSprites(int liquidtex) {
        int textures[] = new int[numOfContainers];
        textures[0] = liquidtex;
        textures[1] = EnumContainers.Containers.CAPSULE.getTexture();
        textures[2] = EnumContainers.Containers.CAN.getTexture();
        textures[3] = EnumContainers.Containers.CAPSULE_RED.getTexture();
        textures[4] = EnumContainers.Containers.BUCKET.getTexture();
        textures[5] = EnumContainers.Containers.BOTTLE.getTexture();
        textures[6] = EnumContainers.Containers.CELL.getTexture();
        return textures;
    }

    private void createItems() {

        String internalNames[] = generateInternals();
        String externalNames[] = generateExternals();

        // This is using my new system for doing colors through code.
        // The spritesheet was getting to be a clusterfuck.
        fuel = new PfFContainer(this.itemID, this.nameLowerCaseNoSpaces);
        for (int i = 0; i != numOfContainers; i++) {
            // This is basically hardcoding meta values.
            String e = externalNames[i];
            if (i == this.redCapMeta) {
                e = e + "_Red";
            }
            // Meta map isn't really useful here, but the LiquidContainerSystem 
            // still uses it, so we have to properly populate it.
            fuel.metaMap.put(e, i);
            fuel.add(internalNames[i], fuel.metaMap.get(e), textures[i], externalNames[i]);

        }
        ItemStack fuelStack;
        fuel.setAllRenderColor(this.color);
        fuelStack = API.getItem(internalNames[0]);
        LiquidContainerSystem.create(fuel);
        FuelManager.bronzeEngineFuel.put(fuelStack.itemID, new EngineBronzeFuel(fuelStack, this.MJt, this.burnTime, this.isSafeFuel));

    }

    private String[] generateInternals() {

        String internalNames[] = new String[numOfContainers];
        int i = 0;
        for (String s : generateExternals()) {
            internalNames[i] = denLib.toLowerCaseNoSpaces(s);
            i++;
        }

        return internalNames;

    }

    private String[] generateExternals() {

        String externalBase = this.name;
        String externalNames[] = new String[numOfContainers];
        externalNames[0] = externalBase;
        externalNames[1] = externalBase + " Capsule";
        externalNames[2] = externalBase + " Can";
        externalNames[3] = externalNames[1];
        externalNames[4] = externalBase + " Bucket";
        externalNames[5] = externalBase + " Bottle";
        externalNames[6] = externalBase + " Cell";

        return externalNames;

    }

    public int getMJt() {
        return MJt;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return itemID;
    }

    public int[] getTextures() {
        return textures;
    }

    public int getColor() {
        return color;
    }

    public PfFContainer getFuel() {
        return fuel;
    }

    public int getIsSafeFuel() {
        return isSafeFuel;
    }

    public String getNameLowerCaseNoSpaces() {
        return nameLowerCaseNoSpaces;
    }

    public static int getNumOfContainers() {
        return numOfContainers;
    }

    public int getRedCapMeta() {
        return redCapMeta;
    }
}
