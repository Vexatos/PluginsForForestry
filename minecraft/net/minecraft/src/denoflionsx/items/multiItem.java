package net.minecraft.src.denoflionsx.items;

import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import java.util.HashMap;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.PFFItems;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.forge.ITextureProvider;

public class multiItem extends Item implements ITextureProvider {

    protected HashMap<Integer, String> itemMap = new HashMap();
    protected HashMap<Integer, Integer> textureMap = new HashMap();
    protected HashMap<Integer, Integer> stackMap = new HashMap();
    protected HashMap<Integer, Item> containerMap = new HashMap();
    protected HashMap<Integer, shinyObject> shinyMap = new HashMap();
    public HashMap<String, Integer> metaMap = new HashMap();
    protected int meta;

    public multiItem(int par1, String name) {
        super(par1);
        this.setHasSubtypes(true);
        this.setItemName(name);
        this.setMaxDamage(0);
    }

    // This function is formatted weird on purpose.
    // So my buildscript can remove it from the server build.
    @Override public int getIconFromDamage(int par1) {if (this.textureMap.get(par1) != null) {return this.textureMap.get(par1);}return 0;}

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {

        return getMetaName(par1ItemStack);

    }

    public String getMetaName(ItemStack par1ItemStack) {
        if (par1ItemStack != null) {
            int dmg = par1ItemStack.getItemDamage();
            String dname = this.itemMap.get(Integer.valueOf(dmg));
            if (dname != null) {
                this.setMaxStackSize(this.stackMap.get(dmg));
                this.meta = dmg;
                return dname;

            }
        }
        return this.itemMap.get(0);
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
        this.meta = par1ItemStack.getItemDamage();
        this.setMaxStackSize(this.stackMap.get(meta));
    }

    @Override
    public Item getContainerItem() {
        return containerMap.get(meta);
    }
    
    public void add(String name, int dmg, int texid, String displayname) {
        itemMap.put(dmg, "item." + name);
        stackMap.put(dmg, 64);
        textureMap.put(dmg, texid);
        containerMap.put(dmg, null);
        // Default shinyObject returns normal values.
        shinyMap.put(dmg, new shinyObject());
        if (core.isClient()) {
            ModLoader.addLocalization(getMetaName(new ItemStack(this, 1, dmg)) + ".name", displayname);
        }
        
        // Register with API.
        PFFItems.registerItem(name, this, dmg);
    }

    public void add(String name, int dmg, int texid, String displayname, int stack) {
        add(name, dmg, texid, displayname);
        stackMap.put(dmg, stack);
    }

    public void add(String name, int dmg, int texid, String displayname, int stack, Item con) {
        add(name, dmg, texid, displayname, stack);
        containerMap.put(dmg, con);
    }

    public void add(String name, int dmg, int texid, String displayname, int stack, Item con, boolean shiny) {
        add(name, dmg, texid, displayname, stack, con);
        this.setShiny(dmg, shiny);
    }

    public void add(String name, int dmg, int texid, String displayname, int stack, boolean shiny) {
        add(name, dmg, texid, displayname, stack);
        this.setShiny(dmg, shiny);
    }

    public void add(String name, int dmg, int texid, String displayname, boolean shiny) {
        add(name, dmg, texid, displayname);
        this.setShiny(dmg, shiny);
    }

    public void setShiny(int dmg, boolean t) {
        if (t) {
            this.shinyMap.put(dmg, new shinyObject(true, EnumRarity.epic));
        } else {
            this.shinyMap.put(dmg, new shinyObject());
        }
    }

    public ItemStack customContainer(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition obj = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (obj == null) {
            return par1ItemStack;
        }
        HashMap<String, Integer> coords = new HashMap();
        coords.put("x", obj.blockX);
        coords.put("y", obj.blockY);
        coords.put("z", obj.blockZ);
        coords.put("id", par2World.getBlockId(coords.get("x"), coords.get("y"), coords.get("z")));
        coords.put("meta", par2World.getBlockMetadata(coords.get("x"), coords.get("y"), coords.get("z")));
        LiquidContainer liq = LiquidManager.getEmptyContainer(par1ItemStack, new LiquidStack(coords.get("id"), 1));
        if (liq == null) {
            return par1ItemStack;
        }
        int index = invSearch(liq.filled, par3EntityPlayer);
        if (index != -1) {
            --par1ItemStack.stackSize;
            ++par3EntityPlayer.inventory.getStackInSlot(index).stackSize;
            par2World.setBlockAndMetadataWithNotify(coords.get("x"), coords.get("y"), coords.get("z"), 0, 0);
            return par1ItemStack;
        } else {
            --par1ItemStack.stackSize;
            int empty = emptySpace(par3EntityPlayer);
            par3EntityPlayer.inventory.setInventorySlotContents(empty, new ItemStack(liq.filled.getItem(), 1, liq.filled.getItemDamage()));
            par2World.setBlockAndMetadataWithNotify(coords.get("x"), coords.get("y"), coords.get("z"), 0, 0);
            return par1ItemStack;
        }
    }

    public int invSearch(ItemStack par1, EntityPlayer par2) {
        for (int i = 0; i < par2.inventory.getSizeInventory(); i++) {
            ItemStack t = par2.inventory.getStackInSlot(i);
            if (t != null) {
                if (t.isItemEqual(par1) && t.stackSize < this.maxStackSize) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int emptySpace(EntityPlayer par1) {
        for (int i = 0; i < par1.inventory.getSizeInventory(); i++) {
            if (par1.inventory.getStackInSlot(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override public EnumRarity getRarity(ItemStack par1ItemStack) {if (this.shinyMap.get(par1ItemStack.getItemDamage()) != null){return this.shinyMap.get(par1ItemStack.getItemDamage()).getRare();}else{return new shinyObject().getRare();}}

    @Override public boolean hasEffect(ItemStack par1ItemStack) {return this.shinyMap.get(par1ItemStack.getItemDamage()).getShiny();}

    @Override
    public String getTextureFile() {
        return mod_PluginsforForestry.texture;
    }
}
