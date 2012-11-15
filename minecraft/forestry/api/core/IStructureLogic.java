package forestry.api.core;

public interface IStructureLogic extends INBTTagable
{
    String getTypeUID();

    void validateStructure();
}
