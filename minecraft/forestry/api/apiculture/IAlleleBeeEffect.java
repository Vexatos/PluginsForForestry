package forestry.api.apiculture;

import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IEffectData;

public interface IAlleleBeeEffect extends IAlleleEffect
{
    IEffectData doEffect(IBeeGenome var1, IEffectData var2, IBeeHousing var3);

    IEffectData doFX(IBeeGenome var1, IEffectData var2, IBeeHousing var3);

    String getIconTextureFile();

    int getIconIndex();
}
