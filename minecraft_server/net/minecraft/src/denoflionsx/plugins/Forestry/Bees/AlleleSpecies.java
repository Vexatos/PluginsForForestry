package net.minecraft.src.denoflionsx.plugins.Forestry.Bees;

import java.util.HashMap;
import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

/**
 *
 * @author denoflions
 */
public class AlleleSpecies implements IAlleleBeeSpecies{
    
    private int id;
    private boolean isSecret;
    private boolean isCounted;
    private boolean hasEffect;
    private boolean isDominant;
    private boolean isJubilant;
    private int PrimaryColor;
    private int SecondaryColor;
    private String name;
    private String species;
    private EnumTemperature Temp;
    private HashMap<ItemStack,Integer> products = new HashMap();
    private HashMap<ItemStack,Integer> special = new HashMap();
    private EnumHumidity Hum;

    public AlleleSpecies(int par1, boolean[] par2, EnumTemperature par3, EnumHumidity par4 ,HashMap<ItemStack, Integer> par5[], String[] par6, int[] par7) {
        this.id = par1;
        this.isSecret = par2[0];
        this.isCounted = par2[1];
        this.hasEffect = par2[2];
        this.isDominant = par2[3];
        this.isJubilant = par2[4];
        this.Temp = par3;
        this.Hum = par4;
        this.products = par5[0];
        this.special = par5[1];
        this.name = par6[0];
        this.species = par6[1];
        this.PrimaryColor = par7[0];
        this.SecondaryColor = par7[1];
        
    }
    
    @Override
    public HashMap<ItemStack, Integer> getProducts() {
        return this.products;
    }

    @Override
    public HashMap<ItemStack, Integer> getSpecialty() {
        return this.special;
    }

    @Override
    public boolean isJubilant(World world, int biomeid, int x, int y, int z) {
        return this.isJubilant;
    }

    @Override
    public Achievement getAchievement() {
        return null;
    }

    @Override
    public String getBinomial() {
        return this.species;
    }

    @Override
    public EnumHumidity getHumidity() {
        return this.Hum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrimaryColor() {
        return this.PrimaryColor;
    }

    @Override
    public int getSecondaryColor() {
        return this.SecondaryColor;
    }

    @Override
    public EnumTemperature getTemperature() {
        return this.Temp;
    }

    @Override
    public boolean hasEffect() {
        return this.hasEffect;
    }

    @Override
    public boolean isCounted() {
        return this.isCounted;
    }

    @Override
    public boolean isSecret() {
        return this.isSecret;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isDominant() {
        return this.isDominant;
    }
    
    
    
}
