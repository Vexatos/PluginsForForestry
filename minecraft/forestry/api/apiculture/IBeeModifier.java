package forestry.api.apiculture;

public interface IBeeModifier
{
    float getTerritoryModifier(IBeeGenome var1);

    float getMutationModifier(IBeeGenome var1, IBeeGenome var2);

    float getLifespanModifier(IBeeGenome var1, IBeeGenome var2);

    float getProductionModifier(IBeeGenome var1);
}
