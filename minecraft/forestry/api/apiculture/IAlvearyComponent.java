package forestry.api.apiculture;

import forestry.api.core.ITileStructure;

public interface IAlvearyComponent extends ITileStructure
{
    void registerBeeModifier(IBeeModifier var1);

    void removeBeeModifier(IBeeModifier var1);

    void addTemperatureChange(float var1, float var2, float var3);

    void addHumidityChange(float var1, float var2, float var3);

    boolean hasFunction();
}
