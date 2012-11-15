package forestry.api.arboriculture;

import forestry.api.genetics.IAllele;
import forestry.api.genetics.IChromosome;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface ITreeInterface
{
    boolean isGermling(ItemStack var1);

    boolean isPollen(ItemStack var1);

    boolean isPollinated(ItemStack var1);

    ITree getTree(ItemStack var1);

    ITree getTree(World var1, ITreeGenome var2);

    ItemStack getGermlingStack(ITree var1, EnumGermlingType var2);

    boolean plantSapling(World var1, ITree var2, int var3, int var4, int var5);

    IChromosome[] templateAsChromosomes(IAllele[] var1);

    IChromosome[] templateAsChromosomes(IAllele[] var1, IAllele[] var2);

    ITreeGenome templateAsGenome(IAllele[] var1);

    ITreeGenome templateAsGenome(IAllele[] var1, IAllele[] var2);

    boolean setLeaves(World var1, ITree var2, int var3, int var4, int var5);
}
