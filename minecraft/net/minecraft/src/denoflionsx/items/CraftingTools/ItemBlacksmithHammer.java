package net.minecraft.src.denoflionsx.items.CraftingTools;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.API.PFFItems;
import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.Core.EnumToolTextures;

public class ItemBlacksmithHammer extends Item {

    public ItemBlacksmithHammer(int par1) {
        super(par1);
        this.setTextureFile(mod_PluginsforForestry.texture);
        this.setMaxDamage(10);
        this.setMaxStackSize(1);
        if (core.client) {
            ModLoader.addLocalization("item.BlacksmithHammer.name", "Blacksmith Hammer");
        }
        PFFItems.registerItem("blacksmithhammer", this);
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return "item.BlacksmithHammer";
    }

    @Override
    public int getIconFromDamage(int par1) {
        return EnumToolTextures.ToolTextures.BLACKSMITHHAMMER.getIndex();
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving) {
        par1ItemStack.damageItem(1, par6EntityLiving);
        return true;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public Item getContainerItem() {
        return this;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return true;
    }

    public static enum BlacksmithHammer {

        HAMMER();
        private ItemIDManager ID;
        private ItemBlacksmithHammer hammer;
        private int id;

        private BlacksmithHammer() {
            ID = new ItemIDManager(1, "blacksmithhammer");
            defaults();
            hammer = new ItemBlacksmithHammer(this.id);
            recipes();
        }

        private void defaults() {
            core.config.addDefault("BlacksmithHammer_ItemID=" + this.ID.getItemIDs().get(0));
            this.id = core.config.getOptionInt("BlacksmithHammer_ItemID");
        }
        
        private void recipes(){
            ModLoader.addRecipe(API.getItem("blacksmithhammer"),new Object[]{
            "XBX",
            "III",
            "XSX",
            Character.valueOf('S'),new ItemStack(Item.stick),
            Character.valueOf('I'),new ItemStack(Item.ingotIron),
            Character.valueOf('B'),new ItemStack(Item.dyePowder)});
        }
    }

    public static void BlacksmithHammer() {
        ItemBlacksmithHammer.BlacksmithHammer.values();
    }
}
