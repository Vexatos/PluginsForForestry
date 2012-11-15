package forestry.api.apiculture;

import java.util.ArrayList;
import net.minecraft.src.World;

public interface IBeekeepingMode extends IBeeModifier
{
    String getName();

    ArrayList getDescription();

    float getWearModifier();

    int getFinalFertility(IBee var1, World var2, int var3, int var4, int var5);

    boolean isFatigued(IBee var1);

    boolean isNaturalOffspring(IBee var1);

    boolean mayMultiplyPrincess(IBee var1);
}
