package net.minecraft.src.denoflionsx.items.CraftingTools;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Core.EnumToolTextures;

public class ItemIronRing extends multiItem {

    public ItemIronRing(int par1, String name) {
        super(par1, name);
    }

    public static enum ironRing {

        IRONRING();
        
        private ItemIDManager ID;
        private multiItem ring;
        private String tag;
        private String name;
        private int amount;
        private int id;

        private ironRing() {
            this.name = "Iron Ring";
            this.tag = denLib.toLowerCaseNoSpaces(this.name);
            this.ID = new ItemIDManager(1, this.tag);
            defaults();
            this.ring = new multiItem(this.id, this.tag);
            this.ring.metaMap.put(this.name, 0);
            this.ring.add(this.tag, this.ring.metaMap.get(this.name), EnumToolTextures.ToolTextures.IRONRING.getIndex(), this.name);
            recipes();
        }

        private void recipes() {
            ItemStack hammer = API.getItem("blacksmithhammer");
            ModLoader.addRecipe(new ItemStack(this.ring, this.amount, this.ring.metaMap.get(this.name)),
                    new Object[]{"XIX",
                        "IHI",
                        "XIX",
                        Character.valueOf('H'), hammer,
                        Character.valueOf('I'), Item.ingotIron});
            ModLoader.addRecipe(API.getItem("barrel"),new Object[]{
                "PRP",
                "PGP",
                "PRP",
            Character.valueOf('P'),new ItemStack(Block.planks),
            Character.valueOf('G'),new ItemStack(Block.glass),
            Character.valueOf('R'),API.getItem(this.tag)});
        }

        private void defaults() {
            core.config.addDefault("IronRings_AmountPerCraft=" + 64);
            core.config.addDefault("IronRings_ItemID=" + this.ID.getItemIDs().get(0));
            this.amount = core.config.getOptionInt("IronRings_AmountPerCraft");
            this.id = core.config.getOptionInt("IronRings_ItemID");
        }
    }

    public static void IronRing() {
        ItemIronRing.ironRing.values();
    }
}
