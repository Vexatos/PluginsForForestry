package denoflionsx.API.Enums;

import net.minecraft.src.*;

public class EnumAnimals {

    public static ANIMALS getMatchingEnum(Entity animal) {
        if (animal instanceof EntityMooshroom) {
            return ANIMALS.MOOSHROOM;
        } else if (animal instanceof EntityCow) {
            return ANIMALS.COW;
        } else if (animal instanceof EntitySheep) {
            return ANIMALS.SHEEP;
        } else if (animal instanceof EntityPig) {
            return ANIMALS.PIG;
        } else if (animal instanceof EntitySquid) {
            return ANIMALS.SQUID;
        } else if (animal instanceof EntityChicken) {
            return ANIMALS.CHICKEN;
        }
        return ANIMALS.NULL;
    }

    public static enum ANIMALS {

        MOOSHROOM,
        COW,
        SHEEP,
        PIG,
        SQUID,
        CHICKEN,
        NULL;
    }
}
