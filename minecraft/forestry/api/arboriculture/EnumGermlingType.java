package forestry.api.arboriculture;

public enum EnumGermlingType
{
    SAPLING("Sapling"),
    BLOSSOM("Blossom"),
    POLLEN("Pollen"),
    GERMLING("Germling");
    String name;

    private EnumGermlingType(String var3)
    {
        this.name = var3;
    }

    public String getName()
    {
        return this.name;
    }
}
