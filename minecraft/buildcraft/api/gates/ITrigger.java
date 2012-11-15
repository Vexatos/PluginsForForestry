package buildcraft.api.gates;

import net.minecraft.src.TileEntity;

public interface ITrigger
{
    int getId();

    String getTextureFile();

    int getIndexInTexture();

    boolean hasParameter();

    String getDescription();

    boolean isTriggerActive(TileEntity var1, ITriggerParameter var2);

    ITriggerParameter createParameter();
}
