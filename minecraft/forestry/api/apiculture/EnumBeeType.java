package forestry.api.apiculture;

public enum EnumBeeType
{
    PRINCESS("bees.princess"),
    QUEEN("bees.queen"),
    DRONE("bees.drone");
    String name;

    private EnumBeeType(String var3)
    {
        this.name = var3;
    }

    public String getName()
    {
        return this.name;
    }
}
