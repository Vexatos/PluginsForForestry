package forestry.api.apiculture;

import forestry.api.genetics.IAllele;
import forestry.api.genetics.IChromosome;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IBeeInterface
{
    boolean isBee(ItemStack var1);

    boolean isDrone(ItemStack var1);

    boolean isMated(ItemStack var1);

    IBee getBee(ItemStack var1);

    IBee getBee(World var1, IBeeGenome var2);

    IBee getBee(World var1, IBeeGenome var2, IBee var3);

    ItemStack getBeeStack(IBee var1, EnumBeeType var2);

    IChromosome[] templateAsChromosomes(IAllele[] var1);

    IChromosome[] templateAsChromosomes(IAllele[] var1, IAllele[] var2);

    IBeeGenome templateAsGenome(IAllele[] var1);

    IBeeGenome templateAsGenome(IAllele[] var1, IAllele[] var2);
}
