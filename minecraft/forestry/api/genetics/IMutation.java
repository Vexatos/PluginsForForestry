package forestry.api.genetics;

public interface IMutation
{
    IAllele getAllele0();

    IAllele getAllele1();

    IAllele[] getTemplate();

    int getBaseChance();

    boolean isPartner(IAllele var1);

    IAllele getPartner(IAllele var1);

    boolean isSecret();
}
