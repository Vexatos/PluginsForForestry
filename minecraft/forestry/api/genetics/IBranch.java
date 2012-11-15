package forestry.api.genetics;

public interface IBranch
{
    String getUID();

    String getName();

    String getScientific();

    String getDescription();

    IAlleleSpecies[] getMembers();

    void addMember(IAlleleSpecies var1);
}
