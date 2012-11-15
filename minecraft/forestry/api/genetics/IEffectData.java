package forestry.api.genetics;

import forestry.api.core.INBTTagable;

public interface IEffectData extends INBTTagable
{
    void setInteger(int var1, int var2);

    void setFloat(int var1, float var2);

    void setBoolean(int var1, boolean var2);

    int getInteger(int var1);

    float getFloat(int var1);

    boolean getBoolean(int var1);
}
