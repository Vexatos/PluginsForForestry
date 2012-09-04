package denoflionsx.items;

import java.util.HashMap;
import net.minecraft.src.*;
import denoflionsx.API.PFFItems;
import denoflionsx.core.core;
import denoflionsx.mod_PluginsforForestry;

public class multiItem extends Item{

    protected HashMap<Integer, String> itemMap = new HashMap();
    protected HashMap<Integer, Integer> textureMap = new HashMap();
    protected HashMap<Integer, shinyObject> shinyMap = new HashMap();
    public HashMap<String, Integer> metaMap = new HashMap();

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
        ItemStack p;
        if (par1ItemStack == null){
            p = new ItemStack(this,1,0);
        }else{
            p = par1ItemStack;
        }
        return getMetaName(p);

    }
    
    public String getMetaName(ItemStack par1ItemStack) {
        if (par1ItemStack != null) {
            int dmg = par1ItemStack.getItemDamage();
            String dname = this.itemMap.get(Integer.valueOf(dmg));
            if (dname != null) {
                return dname;
            }
        }
        return "";
    }
    
    public void add(String name, int dmg, int texid, String displayname) {
        itemMap.put(dmg, "item." + name);
        textureMap.put(dmg, texid);
        // Default shinyObject returns normal values.
        shinyMap.put(dmg, new shinyObject());
        if (core.isClient()) {
            ModLoader.addLocalization(getMetaName(new ItemStack(this, 1, dmg)) + ".name", displayname);
        }
        // Register with API.
        PFFItems.registerItem(name, this, dmg);
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

    @Override public boolean hasEffect(ItemStack par1ItemStack) {if (this.shinyMap.get(par1ItemStack.getItemDamage()) != null){return this.shinyMap.get(par1ItemStack.getItemDamage()).getShiny();}else{return new shinyObject().getShiny();}}

    @Override
    public String getTextureFile() {
        return mod_PluginsforForestry.texture;
    }
}
