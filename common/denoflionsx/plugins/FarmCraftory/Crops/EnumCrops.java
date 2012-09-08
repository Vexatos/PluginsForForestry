package denoflionsx.plugins.FarmCraftory.Crops;

import denoflionsx.denLib.denLib;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public class EnumCrops {

    private static int singleCropID = 0;
    private static int multiCropID = 0;
    private static int treeID = 0;
    private static int growthFactor = 6;
    private static String mod = "FarmCraftory";

    public static enum SINGLE {

        TURNIP(),
        CABBAGE(),
        ONION(),
        CARROT(),
        POTATO(),
        SPINACH(),
        LEEK();
        private PlantObject plant;

        private SINGLE() {
            ItemStack p = denLib.ReflectionHelper.getNewItemStack(mod, denLib.toLowerCaseNoSpaces(this.toString()) + "Item");
            ItemStack s = denLib.ReflectionHelper.getNewItemStack(mod, denLib.toLowerCaseNoSpaces(this.toString()) + "SeedBag");
            int id = singleCropID++;
            PlantObject po = new PlantObject(p, s, id);
            this.plant = po;
        }

        public PlantObject getPlant() {
            return plant;
        }

        public ItemStack[] asArray() {
            return this.plant.asArray();
        }
    }

    public static enum MULTI {

        CUCUMBER(),
        TOMATO(),
        CORN(),
        EGGPLANT(),
        GREENPEPPER(),
        YAM(),
        STRAWBERRY(),
        PINEAPPLE();
        private PlantObject plant;

        private MULTI() {
            String name = this.toString();
            int id = multiCropID++;
            ItemStack p;
            ItemStack s;
            // Green pepper doesn't follow the case convention of the other items.
            // So we need a special handler for it.
            if (!name.equals("GREENPEPPER")) {
                p = denLib.ReflectionHelper.getNewItemStack(mod, denLib.toLowerCaseNoSpaces(this.toString()) + "Item");
                s = denLib.ReflectionHelper.getNewItemStack(mod, denLib.toLowerCaseNoSpaces(this.toString()) + "SeedBag");
            } else {
                p = denLib.ReflectionHelper.getNewItemStack(mod, "greenPepper" + "Item");
                s = denLib.ReflectionHelper.getNewItemStack(mod, "greenPepper" + "SeedBag");
            }
            this.plant = new PlantObject(p, s, id);
        }

        public PlantObject getPlant() {
            return plant;
        }
        
        public ItemStack[] asArray(){
            return this.plant.asArray();
        }
    }
    
    public static enum TREE{
        ORANGE(),
        GRAPE(),
        PEAR(),
        PEACH(),
        BANANA(),
        CHERRY();
        
        private TreeObject tree;
        
        private TREE(){
            int id = treeID++;
            tree = new TreeObject(denLib.getBlock(mod,"runeFruit"),id,denLib.ReflectionHelper.getNewItemStack(mod, denLib.toLowerCaseNoSpaces(this.toString()) + "Item"));
        }

        public TreeObject getTree() {
            return tree;
        }
       
    }

    public static class PlantObject {

        private ItemStack plant;
        private ItemStack seed;
        private int id;

        public PlantObject(ItemStack plant, ItemStack seed, int id) {
            this.plant = plant;
            this.seed = seed;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public ItemStack getPlant() {
            return plant;
        }

        public ItemStack getSeed() {
            return seed;
        }
        
        public ItemStack[] asArray(){
            return new ItemStack[]{this.plant,this.seed};
        }
    }
    
    public static class TreeObject{
        private Block fruit;
        private int baseMeta;
        private int grownMeta;
        private ItemStack fruitItem;
        private ItemStack saplingBlock;

        public TreeObject(Block fruit, int baseMeta, ItemStack fruitItem) {
            this.fruit = fruit;
            this.baseMeta = baseMeta;
            this.grownMeta = baseMeta + growthFactor;
            this.fruitItem = fruitItem;
            this.saplingBlock = denLib.ReflectionHelper.getNewItemStackBlock(mod, "runeSapling");
            this.saplingBlock.setItemDamage(this.baseMeta);
        }
        

        public int getBaseMeta() {
            return baseMeta;
        }
        
        public boolean isSapling(ItemStack i){
            if (i == null){
                return false;
            }
            if (i.isItemEqual(this.saplingBlock)){
                if (i.getItemDamage() == this.baseMeta){
                   return true; 
                }
            }
            return true;
        }
        
        public boolean isFruit(int BlockID){
            return BlockID == this.fruit.blockID;
        }
        
        public boolean isGrown(int meta){
            return this.grownMeta == meta;
        }

        public ItemStack getFruitItem() {
            return fruitItem.copy();
        }
        
        public ItemStack[] asArray(){
            return new ItemStack[]{this.fruitItem.copy()};
        }
        
    }
}
