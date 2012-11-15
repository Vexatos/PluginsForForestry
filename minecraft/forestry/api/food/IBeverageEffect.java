package forestry.api.food;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public interface IBeverageEffect
{
    int getId();

    void doEffect(World var1, EntityPlayer var2);

    String getDescription();
}
