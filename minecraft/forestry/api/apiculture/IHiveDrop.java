package forestry.api.apiculture;

import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IHiveDrop
{
    ItemStack getPrincess(World var1, int var2, int var3, int var4, int var5);

    ArrayList getDrones(World var1, int var2, int var3, int var4, int var5);

    ArrayList getAdditional(World var1, int var2, int var3, int var4, int var5);

    int getChance(World var1, int var2, int var3, int var4);
}
