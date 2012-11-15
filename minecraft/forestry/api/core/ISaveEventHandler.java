package forestry.api.core;

import net.minecraft.src.World;

public interface ISaveEventHandler
{
    void onWorldLoad(World var1);

    void onWorldUnload(World var1);

    void onWorldSave(World var1);
}
