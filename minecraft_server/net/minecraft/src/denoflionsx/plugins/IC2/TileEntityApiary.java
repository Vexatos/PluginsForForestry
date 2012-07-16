package net.minecraft.src.denoflionsx.plugins.IC2;

import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class TileEntityApiary extends TileEntityMachine {

    private ItemStack Bees[] = new ItemStack[2];
    private ItemStack Products[] = new ItemStack[7];
    private ItemStack Cards[] = new ItemStack[4];

    public TileEntityApiary(int par1) {
        super(par1);
    }

    @Override
    public void updateEntity() {
        
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
    }
    
    public void applyCardEffects(){
        for (ItemStack i : Cards){
            
        }
    }
}
