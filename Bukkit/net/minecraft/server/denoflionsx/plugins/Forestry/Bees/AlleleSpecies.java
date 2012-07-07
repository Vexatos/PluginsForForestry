package net.minecraft.server.denoflionsx.plugins.Forestry.Bees;

import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import java.util.HashMap;
import net.minecraft.server.Achievement;
import net.minecraft.server.World;

public class AlleleSpecies implements IAlleleBeeSpecies
{
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
    private HashMap products = new HashMap();
    private HashMap special = new HashMap();
    private EnumHumidity Hum;

    public AlleleSpecies(int var1, boolean[] var2, EnumTemperature var3, EnumHumidity var4, HashMap[] var5, String[] var6, int[] var7)
    {
        this.id = var1;
        this.isSecret = var2[0];
        this.isCounted = var2[1];
        this.hasEffect = var2[2];
        this.isDominant = var2[3];
        this.isJubilant = var2[4];
        this.Temp = var3;
        this.Hum = var4;
        this.products = var5[0];
        this.special = var5[1];
        this.name = var6[0];
        this.species = var6[1];
        this.PrimaryColor = var7[0];
        this.SecondaryColor = var7[1];
    }

    public HashMap getProducts()
    {
        return this.products;
    }

    public HashMap getSpecialty()
    {
        return this.special;
    }

    public boolean isJubilant(World var1, int var2, int var3, int var4, int var5)
    {
        return this.isJubilant;
    }

    public Achievement getAchievement()
    {
        return null;
    }

    public String getBinomial()
    {
        return this.species;
    }

    public EnumHumidity getHumidity()
    {
        return this.Hum;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPrimaryColor()
    {
        return this.PrimaryColor;
    }

    public int getSecondaryColor()
    {
        return this.SecondaryColor;
    }

    public EnumTemperature getTemperature()
    {
        return this.Temp;
    }

    public boolean hasEffect()
    {
        return this.hasEffect;
    }

    public boolean isCounted()
    {
        return this.isCounted;
    }

    public boolean isSecret()
    {
        return this.isSecret;
    }

    public int getId()
    {
        return this.id;
    }

    public boolean isDominant()
    {
        return this.isDominant;
    }
}
