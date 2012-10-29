package denoflionsx.plugins.Buildcraft.Modules;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.PfFEvents;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Forestry.Utility.LiquidContainerSystem;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.items.PfFContainer;
import denoflionsx.Enums.EnumContainers.Containers;
import denoflionsx.core.PfFModuleTemplate;

public class milkModule extends PfFModuleTemplate {

    public static PfFContainer milk;
    public static boolean disableBCMilk = false;
    public ItemIDManager id = new ItemIDManager(1, "PfFMilk");
    //public static ItemIDManager IDs = new ItemIDManager(2);

    public milkModule(String name, String parent) {
        super(name, parent);
        PfFEvents.specialEvent.register(this);
    }

    @Override
    public void doSetup() {
        milk = new PfFContainer(Integer.valueOf(this.config.getOption("Milk_ItemID")), "liquidmilk");
        milk.metaMap.put("Milk", 0);
        milk.metaMap.put("Milk Capsule", 1);
        milk.metaMap.put("Milk Can", 2);
        milk.metaMap.put("Milk Capsule_Red", 3);
        milk.metaMap.put("Milk Bottle", 4);
        milk.metaMap.put("Milk Cell", 5);
        milk.add("milk", milk.metaMap.get("Milk"), 1, "Milk");
        milk.add("milkcap", milk.metaMap.get("Milk Capsule"), Containers.CAPSULE.getTexture(), "Milk Capsule");
        milk.add("milkcan", milk.metaMap.get("Milk Can"), Containers.CAN.getTexture(), "Milk Can");
        milk.add("milkcap_red", milk.metaMap.get("Milk Capsule_Red"), Containers.CAPSULE_RED.getTexture(), "Milk Capsule");
        milk.add("milkbottle", milk.metaMap.get("Milk Bottle"), Containers.BOTTLE.getTexture(), "Milk Bottle");
        LiquidContainerSystem.createWithOverride(milk, milk.shiftedIndex, PfFManagers.ItemManager.getItem("milkbottle"), true);

        LiquidContainerSystem.registerMilkBucket(milk.shiftedIndex);
        milk.setAllRenderColor(Colors.Values.WHITE.getColor());
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void barrel(EventSpecial event) {
        if (!event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            return;
        }
        PfFManagers.ContainerManager.addLiquid("Milk", PfFManagers.ItemManager.getItem("milk"), PfFManagers.ColorManager.getColor(Colors.Values.WHITE.toString()));
    }

    @Override
    public void recipes() {
        if (denLib.convertToBoolean(this.config.getOption("MilkInBiogas")) && !disableBCMilk) {
            int b;
            if (denLib.convertToBoolean(this.config.getOption("MilkLosesHeat"))) {
                b = 2;
            } else {
                b = 1;
            }
            FuelManager.bronzeEngineFuel.put(milk.shiftedIndex, new EngineBronzeFuel(new ItemStack(milk), Integer.valueOf(this.config.getOption("MilkMJt")), Integer.valueOf(this.config.getOption("MilkBurnTime")), b));
        }
    }

    @Override
    public void defaults() {
        this.config.addDefault("# These options are for an alternative to Minefactory for tankable milk.");
        this.config.addDefault("# These options do nothing if the MFR plugin is on");
        this.config.addDefault("Milk_ItemID=" + id.getItemIDs().get(0));
        this.config.addDefault("MilkInBiogas=true");
        this.config.addDefault("MilkMJt=1");
        this.config.addDefault("MilkBurnTime=40000");
        this.config.addDefault("# If you think Milk is OP, change the next option to true");
        this.config.addDefault("MilkLosesHeat=false");
    }
}
