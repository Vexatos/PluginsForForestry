package forestry.api.apiculture;

import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IBee extends IIndividual
{
    void age(World var1, float var2);

    void mate(IBee var1);

    void setIsNatural(boolean var1);

    IEffectData[] doEffect(IEffectData[] var1, IBeeHousing var2);

    IEffectData[] doFX(IEffectData[] var1, IBeeHousing var2);

    boolean isAlive();

    boolean isPureBred(EnumBeeChromosome var1);

    boolean canSpawn();

    int isWorking(IBeeHousing var1);

    boolean hasFlower(IBeeHousing var1);

    ArrayList getSuitableBiomeIds();

    ItemStack[] getProduceList();

    ItemStack[] getSpecialtyList();

    ItemStack[] produceStacks(IBeeHousing var1);

    IBee spawnPrincess(IBeeHousing var1);

    IBee[] spawnDrones(IBeeHousing var1);

    void plantFlowerRandom(IBeeHousing var1);

    int getHealth();

    int getMaxHealth();

    IBeeGenome getGenome();

    IBeeGenome getMate();
}
