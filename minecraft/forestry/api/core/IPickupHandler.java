package forestry.api.core;

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;

public interface IPickupHandler
{
    boolean onItemPickup(EntityPlayer var1, EntityItem var2);
}
