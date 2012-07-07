package net.minecraft.src.denoflionsx.plugins.Forestry.Bees;

import net.minecraft.src.World;
import forestry.api.apiculture.IHiveDrop;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IMutation;
/**
 *
 * @author den
 */
public class HiveDropSpecies implements IHiveDrop{
    
    private IMutation bee;
    private int chance;

    public HiveDropSpecies(IMutation bee, int chance) {
        this.bee = bee;
        this.chance = chance;
    }

    public HiveDropSpecies(IMutation bee) {
        this.bee = bee;
        this.chance = 100;
    }
    
    @Override
    public int getChance(World world, int x, int y, int z) {
        return this.chance;
    }

    @Override
    public IAllele[] getTemplate() {
        return this.bee.getTemplate();
    }

}
