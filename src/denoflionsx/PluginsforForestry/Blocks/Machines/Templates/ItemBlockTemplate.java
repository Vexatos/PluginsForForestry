package denoflionsx.PluginsforForestry.Blocks.Machines.Templates;

import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTemplate extends ItemBlock{
    
    private HashMap<Integer, String> names = new HashMap();
    private ArrayList<ItemStack> stacks = new ArrayList();

    public ItemBlockTemplate(int par1) {
        super(par1);
    }
    
    public void add(String name, int meta){
        ItemStack i = new ItemStack(this,1,meta);
        names.put(meta,name);
        FMLWrapper.MODE.FML.addNameItemStack(name, i);
        stacks.add(i);
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        if (names.get(par1ItemStack.getItemDamage()) != null){
            return names.get(par1ItemStack.getItemDamage());
        }else{
            return "Unknown Block";
        }
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (ItemStack i : stacks){
            par3List.add(i);
        }
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }
    
    
    
}
