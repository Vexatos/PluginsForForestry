package denoflionsx.PluginsforForestry.Core;

import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantProduct;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Blocks.Plants.BlockOmniPlant;
import denoflionsx.PluginsforForestry.Blocks.Plants.TileEntityOmniPlant;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Config.CoreUpdater;
import denoflionsx.PluginsforForestry.CreativeTab.PfFTab;
import denoflionsx.PluginsforForestry.Integration.XycraftIntegration.XyIntegration;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.Items.*;
import denoflionsx.PluginsforForestry.Items.Plants.*;
import denoflionsx.PluginsforForestry.Liquids.*;
import denoflionsx.PluginsforForestry.Managers.PfFOmniPlantManager;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.FermenterUtils;
import denoflionsx.PluginsforForestry.Utils.ForestryContainers;
import denoflionsx.denLib.FMLWrapper;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class PfFCore implements IPfFCore {

    public Configuration config;
    public File c;
    public String spritesheet;
    public String omnisheet;
    public PfFTab tab;
    public static ItemWoodenBucket woodenbucket;
    public static ItemBarrel barrel;
    public static ItemBlacksmithHammer hammer;
    public static ItemIronRings rings;
    public static ItemLiquidVacuum vac;
    public static ItemBags bags;
    public static IPfFLiquid soup;
    public static BlockOmniPlant Omni;
    public static ItemOmniPlantSeed seeds;
    public static IPfFLiquid lseedoil;
    public static IPfFLiquid veggiejuice;
    public static IPfFLiquid fruitjuice;
    public static IPfFLiquid pumpkinjuice;
    public static IPfFLiquid melonjuice;
    public static ItemCharm charm;
    public ArrayList<ItemStack> logs;

    @Override
    public void preloadTextures() {
        PfFManagers.Events.SystemEvents.registerListener(this);
        spritesheet = PfF.Proxy.preloadTextures("/denoflionsx/PluginsforForestry/gfx/PFF_spritesheet.png");
        omnisheet = PfF.Proxy.preloadTextures("/denoflionsx/PluginsforForestry/gfx/PFF_OmniTree.png");
        PfF.Proxy.addSheetToMap(omnisheet);
        logs = new ArrayList();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void setupBlocks() {
        FMLWrapper.MODE.FML.registerTileEntity(TileEntityOmniPlant.class, "PfFOmniPlant");
        Omni = new BlockOmniPlant(CoreTuning.Blocks.omniplant, Material.air);
    }

    @ForgeSubscribe
    public void onEvent(OreDictionary.OreRegisterEvent event) {
        try {
            String test = event.Name.substring(0, 3);
            if (test.equals("log")) {
                logs.add(event.Ore);
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    @Override
    public void setupConfig() {
        c = new File(PfF.Proxy.getConfigDir() + "PfF.cfg");
        config = new Configuration(c);
        if (!c.exists()) {
            c.mkdirs();
        } else {
            config.load();
        }
        config.save();
        CoreTuning.tuning_enabled = true;
        PfFManagers.Events.SystemEvents.raiseAlert("Core", "config ready!", config);
    }

    @Override
    public void setupItems() {
        tab = new PfFTab();
        if (CoreTuning.Enables.barrel_enabled) {
            barrel = new ItemBarrel();
            hammer = new ItemBlacksmithHammer();
            rings = new ItemIronRings().createRecipe();
            barrel.createRecipe();
        }
        if (CoreTuning.Enables.woodenbucket_enabled) {
            woodenbucket = new ItemWoodenBucket();
        }
        if (CoreTuning.Enables.liquidvac_enabled) {
            soup = new LiquidMushroomSoup().createLiquid();
            vac = new ItemLiquidVacuum();
            bags = new ItemBags();
        }
        if (CoreTuning.Enables.charm_enabled) {
            charm = new ItemCharm().recipe();
        }
        if (CoreTuning.Enables.omniplant_enabled) {
            lseedoil = new LiquidLivingSeedOil().createLiquid();
            OmniPlantList.registerList();
            seeds = new ItemOmniPlantSeed();
        }
        // These liquids aren't actually created until provoked with an event.
        if (CoreTuning.Enables.veggiejuice_enabled) {
            veggiejuice = new LiquidVeggieJuice();
            PfFManagers.Events.SystemEvents.raiseAlert("Core", "Veggie Juice", this);
            PfFManagers.Squeeze.registerSqueezable(new PlantPotato());
            PfFManagers.Squeeze.registerSqueezable(new PlantCarrot());
        }
        if (CoreTuning.Enables.fruitjuice_enabled) {
            fruitjuice = new LiquidFruitJuice();
        }
        if (CoreTuning.Enables.pumpkinjuice_enabled) {
            pumpkinjuice = new LiquidPumpkinJuice();
            PfFManagers.Events.SystemEvents.raiseAlert("Core", "Pumpkin Juice", this);
            PfFManagers.Squeeze.registerSqueezable(new PlantPumpkin());
        }
        if (CoreTuning.Enables.melonjuice_enabled) {
            melonjuice = new LiquidMelonJuice();
            PfFManagers.Events.SystemEvents.raiseAlert("Core", "Melon Juice", this);
            PfFManagers.Squeeze.registerSqueezable(new PlantMelonSlice());
            PfFManagers.Squeeze.registerSqueezable(new PlantMelonBlock());
        }
        String path = "/denoflionsx/PluginsforForestry/gfx/containers/";
        // Dynamic container creation.
        // This is sort of a pain in the ass behind the scenes.
        if (CoreTuning.Enables.woodenbucket_enabled) {
            LRManagers.Containers.registerNewContainer("Wooden Bucket", new String[]{woodenbucket.cTexture, woodenbucket.oTexture}, LiquidContainerRegistry.BUCKET_VOLUME, new ItemStack(woodenbucket), true, true, false, 1);
        }
        if (CoreTuning.Enables.barrel_enabled) {
            LRManagers.Containers.registerNewContainer("Barrel", new String[]{barrel.cTexture, barrel.oTexture}, (LiquidContainerRegistry.BUCKET_VOLUME * 10), new ItemStack(barrel), false, true, false, 64);
        }
        LRManagers.Containers.registerNewContainer("Capsule", new String[]{path + "capsule.png", path + "overlays/generic_capsule_overlay.png"}, LiquidContainerRegistry.BUCKET_VOLUME, ForestryContainers.CAPSULE.getContainer(), false, false, true, 64);
        LRManagers.Containers.registerNewContainer("Can", new String[]{path + "can.png", path + "overlays/generic_capsule_overlay.png"}, LiquidContainerRegistry.BUCKET_VOLUME, ForestryContainers.CAN.getContainer(), false, false, true, 64);
        LRManagers.Containers.registerNewContainer("Refractory Capsule", new String[]{path + "capsule_red.png", path + "overlays/generic_capsule_overlay.png"}, LiquidContainerRegistry.BUCKET_VOLUME, ForestryContainers.CAPSULE_RED.getContainer(), false, false, true, 64);
        LRManagers.Containers.registerNewContainer("Bottle", new String[]{path + "bottle.png", path + "overlays/bottle_overlay.png"}, LiquidContainerRegistry.BUCKET_VOLUME, new ItemStack(Item.glassBottle), false, true, true, 64);
        XyIntegration.integrate();
        config.save();
    }

    @Override
    public void lateCode() {
        if (bags != null) {
            bags.linkLiquidRecipes();
        }
        if (seeds != null) {
            for (OmniPlantProduct P : PfFOmniPlantManager.products) {
                if (P != null) {
                    seeds.addProduct(P.getProduce(), P.getInternalID());
                }
            }
            seeds.recipe(PfFManagers.Liquids.getNewLiquidStackByTag("livingseedoil", 1000));
        }
        for (Object o : refs) {
            IPfFLiquid l = (IPfFLiquid) o;
            PfFManagers.Squeeze.registerSqueezeLiquid(l);
            LiquidStack k2 = PfFManagers.Liquids.getLiquidStackByTag(l.getTag());
            FermenterUtils.registerFermenterBooster(k2, 1.5f);
        }
        refs = null;
        if (CoreTuning.Updates.update_check) {
            CoreUpdater.updateCheck();
        }
        if (CoreTuning.Enables.woodenbucket_enabled) {
            woodenbucket.createRecipe();
        }
        if (CoreTuning.Enables.liquidvac_enabled) {
            vac.createRecipe();
        }
    }
    private ArrayList<Object> refs = new ArrayList();

    @PfFSubscribe
    public void onRef(PfFEvent event) {
        if (event.getMsg().equals("ref")) {
            refs.add(event.getObj());
        }
    }
}
