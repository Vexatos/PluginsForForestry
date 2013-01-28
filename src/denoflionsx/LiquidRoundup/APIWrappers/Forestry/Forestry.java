package denoflionsx.LiquidRoundup.APIWrappers.Forestry;

import denoflionsx.LiquidRoundup.API.Annotations.Wrapper;
import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.ForestryIntegration.BackpackData;
import denoflionsx.LiquidRoundup.Interfaces.IAPIWrapper;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import forestry.api.storage.IBackpackDefinition;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

@Wrapper
public class Forestry implements IAPIWrapper {

    public carpenterManager carpenter = new carpenterManager();
    public squeezerManager squeezer = new squeezerManager();
    public fermenterManager fermenter = new fermenterManager();
    public centrifugeManager centrifuge = new centrifugeManager();
    public BronzeEngine biogas = new BronzeEngine();
    public flowerManager flowers = new flowerManager();
    public stillManager still = new stillManager();
    public backpackManager backpacks = new backpackManager();
    private boolean hasWarned = false;

    @Override
    public void warning() {
        if (!hasWarned) {
            LiquidRoundup.Proxy.print("Forestry API not found!");
            hasWarned = true;
        }
    }

    public class backpackManager {

        public Item[] registerBackpackTiers(BackpackData backpack) {
            try {
                return registerBackpackTiers(backpack.getIds(), backpack.getBackpack(), backpack.getMaterial());
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return null;
        }

        public Item[] registerBackpackTiers(int[] ids, IBackpackDefinition backpack, ItemStack ing) {
            try {
                Item[] backpacks = new Item[2];
                backpacks[0] = registerBackpack(ids[0], backpack, EnumBackpackType.T1);
                backpacks[1] = registerBackpack(ids[1], backpack, EnumBackpackType.T2);
                FMLWrapper.MODE.FML.addRecipe(new ItemStack(backpacks[0]), new Object[]{"SWS", "ICI", "SWS", Character.valueOf('S'), new ItemStack(Item.silk), Character.valueOf('W'), Block.cloth, Character.valueOf('I'), ing, Character.valueOf('C'), new ItemStack(Block.chest)});
                ItemStack woven = ItemInterface.getItem("craftingMaterial");
                woven.setItemDamage(3);
                APIWrappers.forestry.carpenter.addRecipe(5, new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME), null, new ItemStack(backpacks[1]), new Object[]{"WDW", "WBW", "WWW", Character.valueOf('D'), new ItemStack(Item.diamond), Character.valueOf('W'), woven, Character.valueOf('B'), new ItemStack(backpacks[0])});
                return backpacks;
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return null;
        }

        public Item registerBackpack(int id, IBackpackDefinition backpack, EnumBackpackType type) {
            try {
                return BackpackManager.backpackInterface.addBackpack(id, backpack, type);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return null;
        }
    }

    public class stillManager {

        public void registerRecipe(LiquidStack input, LiquidStack output) {
            try {
                RecipeManagers.stillManager.addRecipe(100, input, output);
            } catch (NoClassDefFoundError ex) {
                warning();
            } catch (NullPointerException ex) {
                String msg = "Error in stillManager: ";
                if (input == null) {
                    msg = msg.concat("input is null.");
                } else if (output == null) {
                    msg = msg.concat("output is null");
                }
                LiquidRoundup.Proxy.print(msg);
            }
        }
    }

    public class flowerManager {

        public void registerFlower(ItemStack flower) {
            try {
                FlowerManager.plainFlowers.add(flower);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class BronzeEngine {

        public void addSafeFuel(LiquidStack fuel, int MJt, int BurnTime) {
            try {
                FuelManager.bronzeEngineFuel.put(fuel.asItemStack(), new EngineBronzeFuel(fuel.asItemStack(), MJt, BurnTime, 1));
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class squeezerManager {

        public void addRecipe(int timePerItem, ItemStack[] resources, LiquidStack liquid) {
            try {
                RecipeManagers.squeezerManager.addRecipe(timePerItem, resources, liquid);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(int timePerItem, ItemStack[] resources, LiquidStack liquid, ItemStack remnants, int chance) {
            try {
                RecipeManagers.squeezerManager.addRecipe(timePerItem, resources, liquid, remnants, chance);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class fermenterManager {

        public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output, LiquidStack liquid) {
            try {
                RecipeManagers.fermenterManager.addRecipe(resource, fermentationValue, modifier, output, liquid);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output) {
            try {
                RecipeManagers.fermenterManager.addRecipe(resource, fermentationValue, modifier, output);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class centrifugeManager {

        public void addRecipe(int timePerItem, ItemStack resource, HashMap<ItemStack, Integer> products) {
            try {
                RecipeManagers.centrifugeManager.addRecipe(timePerItem, resource, products);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(int timePerItem, ItemStack resource, ItemStack[] produce, int[] chances) {
            try {
                RecipeManagers.centrifugeManager.addRecipe(timePerItem, resource, produce, chances);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(int timePerItem, ItemStack resource, ItemStack primary, ItemStack secondary, int chance) {
            try {
                RecipeManagers.centrifugeManager.addRecipe(timePerItem, resource, primary, secondary, chance);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(int timePerItem, ItemStack resource, ItemStack primary) {
            try {
                RecipeManagers.centrifugeManager.addRecipe(timePerItem, resource, primary);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class carpenterManager {

        public void addRecipe(int packagingTime, LiquidStack liquid, ItemStack box, ItemStack product, Object materials[]) {
            try {
                RecipeManagers.carpenterManager.addRecipe(packagingTime, liquid, box, product, materials);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void addRecipe(ItemStack box, ItemStack product, Object materials[]) {
            try {
                RecipeManagers.carpenterManager.addRecipe(box, product, materials);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }
}
