package denoflionsx.PluginsforForestry.Blocks.Farm;

import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFarmPart extends ItemBlock {

    private HashMap<Integer, String> names = new HashMap();
    private HashMap<Integer, Integer> textures = new HashMap();
    private ArrayList<ItemStack> stacks = new ArrayList();

    public ItemBlockFarmPart(int par1) {
        super(par1);
        this.add("Sludge Collector", 0, 0);
    }

    private void add(String name, int meta, int texture) {
        names.put(meta, name);
        textures.put(meta, texture);
        ItemStack i = new ItemStack(this, 1, meta);
        stacks.add(i);
        FMLWrapper.MODE.FML.addNameItemStack(name, i);
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return names.get(par1ItemStack.getItemDamage());
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (ItemStack i : stacks) {
            par3List.add(i);
        }
    }

    @Override
    public int getIconFromDamage(int par1) {
        return textures.get(par1);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }
}
