package forestry.api.arboriculture;

import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;
import java.util.ArrayList;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public interface ITree extends IIndividual
{
    void mate(ITree var1);

    IEffectData[] doEffect(IEffectData[] var1, World var2, int var3, int var4, int var5, int var6);

    IEffectData[] doFX(IEffectData[] var1, World var2, int var3, int var4, int var5, int var6);

    ArrayList getSuitableBiomeIds();

    ITreeGenome getGenome();

    ITreeGenome getMate();

    ITreeGenome[] getSaplings(World var1, int var2, int var3, int var4);

    boolean canStay(World var1, int var2, int var3, int var4);

    boolean canGrow(World var1, int var2, int var3, int var4);

    EnumGrowthConditions getGrowthCondition(World var1, int var2, int var3, int var4);

    WorldGenerator getTreeGenerator();
}
