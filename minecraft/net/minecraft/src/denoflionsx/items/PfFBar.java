package net.minecraft.src.denoflionsx.items;

public class PfFBar extends PfFContainer{

    public PfFBar(int par1, String name) {
        super(par1, name);
    }

    @Override
    public int getIconFromDamageForRenderPass(int par1, int par2) {
       return this.getIconFromDamage(par1);
    }
    
    
    
}
