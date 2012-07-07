package net.minecraft.src.denoflionsx.plugins.Forestry.Bees;

import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.core.*;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IMutation;

/**
 *
 * @author denoflions
 */
public class BeeTest{
    
    private static int id = 64;

    public BeeTest() {
        this.register();
    }
    
    
    
    public void register() {
        HashMap<ItemStack,Integer> items[] = new HashMap[2];
        HashMap<ItemStack,Integer> products = new HashMap();
        HashMap<ItemStack,Integer> special = new HashMap();
        products.put(new ItemStack(Item.ghastTear),100);
        special.put(new ItemStack(Item.blazeRod),10);
        items[0] = products;
        items[1] = special;
        IAllele TestBee = (IAllele)new AlleleSpecies(id,new boolean[]{false,true,false,true,true},EnumTemperature.NORMAL,EnumHumidity.NORMAL,items,new String[]{"Test Bee","Testbeeis"},new int[]{16711567,2494249});
        AlleleManager.alleleList[id] = TestBee;
        
        IMutation TestMutation = new MutationSpecies(tutorialTemplate(),new IAllele[]{AlleleManager.alleleList[0],AlleleManager.alleleList[9]}, new int[]{100,100},false);
        BeeManager.beeMutations.add(TestMutation);
        
        BeeManager.hiveDrops[0].add(new HiveDropSpecies(TestMutation));
        BeeManager.breedingManager.registerBeeTemplate(TestMutation.getTemplate());
        
    }

    public static IAllele[] tutorialTemplate(){
        IAllele[] alleles = new IAllele[EnumBeeChromosome.values().length];
        alleles[EnumBeeChromosome.SPECIES.ordinal()] = AlleleManager.alleleList[id];
        alleles[EnumBeeChromosome.SPEED.ordinal()] = AlleleManager.alleleList[1106];
        alleles[EnumBeeChromosome.LIFESPAN.ordinal()] = AlleleManager.alleleList[1200];
        alleles[EnumBeeChromosome.FERTILITY.ordinal()] = AlleleManager.alleleList[1303];
        alleles[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = AlleleManager.alleleList[1455];
        alleles[EnumBeeChromosome.NOCTURNAL.ordinal()] = AlleleManager.alleleList[1024];
        alleles[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = AlleleManager.alleleList[1455];
        alleles[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = AlleleManager.alleleList[1024];
        alleles[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = AlleleManager.alleleList[1024];
        alleles[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = AlleleManager.alleleList[1500];
        alleles[EnumBeeChromosome.FLOWERING.ordinal()] = AlleleManager.alleleList[1710];
        alleles[EnumBeeChromosome.TERRITORY.ordinal()] = AlleleManager.alleleList[1753];
        alleles[EnumBeeChromosome.EFFECT.ordinal()] = AlleleManager.alleleList[1800];
        return alleles;
    }

   
    
}
