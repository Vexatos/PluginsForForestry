package net.minecraft.server.denoflionsx.plugins.Forestry.Bees;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import java.util.HashMap;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class BeeTest
{
    private static int id = 64;

    public BeeTest()
    {
        this.register();
    }

    public void register()
    {
        HashMap[] var1 = new HashMap[2];
        HashMap var2 = new HashMap();
        HashMap var3 = new HashMap();
        var2.put(new ItemStack(Item.GHAST_TEAR), Integer.valueOf(100));
        var3.put(new ItemStack(Item.BLAZE_ROD), Integer.valueOf(10));
        var1[0] = var2;
        var1[1] = var3;
        AlleleSpecies var4 = new AlleleSpecies(id, new boolean[] {false, true, false, true, true}, EnumTemperature.NORMAL, EnumHumidity.NORMAL, var1, new String[] {"Test Bee", "Testbeeis"}, new int[] {16711567, 2494249});
        AlleleManager.alleleList[id] = var4;
        MutationSpecies var5 = new MutationSpecies(tutorialTemplate(), new IAllele[] {AlleleManager.alleleList[0], AlleleManager.alleleList[9]}, new int[] {100, 100}, false);
        BeeManager.beeMutations.add(var5);
        BeeManager.hiveDrops[0].add(new HiveDropSpecies(var5));
        BeeManager.breedingManager.registerBeeTemplate(var5.getTemplate());
    }

    public static IAllele[] tutorialTemplate()
    {
        IAllele[] var0 = new IAllele[EnumBeeChromosome.values().length];
        var0[EnumBeeChromosome.SPECIES.ordinal()] = AlleleManager.alleleList[id];
        var0[EnumBeeChromosome.SPEED.ordinal()] = AlleleManager.alleleList[1106];
        var0[EnumBeeChromosome.LIFESPAN.ordinal()] = AlleleManager.alleleList[1200];
        var0[EnumBeeChromosome.FERTILITY.ordinal()] = AlleleManager.alleleList[1303];
        var0[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = AlleleManager.alleleList[1455];
        var0[EnumBeeChromosome.NOCTURNAL.ordinal()] = AlleleManager.alleleList[1024];
        var0[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = AlleleManager.alleleList[1455];
        var0[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = AlleleManager.alleleList[1024];
        var0[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = AlleleManager.alleleList[1024];
        var0[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = AlleleManager.alleleList[1500];
        var0[EnumBeeChromosome.FLOWERING.ordinal()] = AlleleManager.alleleList[1710];
        var0[EnumBeeChromosome.TERRITORY.ordinal()] = AlleleManager.alleleList[1753];
        var0[EnumBeeChromosome.EFFECT.ordinal()] = AlleleManager.alleleList[1800];
        return var0;
    }
}
