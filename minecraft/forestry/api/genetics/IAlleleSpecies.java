package forestry.api.genetics;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import net.minecraft.src.Achievement;

public interface IAlleleSpecies extends IAllele
{
    String getName();

    String getDescription();

    int getBodyType();

    int getPrimaryColor();

    int getSecondaryColor();

    EnumTemperature getTemperature();

    EnumHumidity getHumidity();

    boolean hasEffect();

    boolean isSecret();

    boolean isCounted();

    String getBinomial();

    String getAuthority();

    IBranch getBranch();

    @Deprecated
    Achievement getAchievement();
}
