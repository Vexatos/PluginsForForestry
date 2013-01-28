package denoflionsx.PluginsforForestry.API.Enums;

import net.minecraft.entity.passive.*;

public class EnumAnimals {

    public static ANIMALS getAnimalType(Object animal) {
        if (animal instanceof EntityMooshroom) {
            return ANIMALS.MOOSHROOM;
        }else if (animal instanceof EntityCow){
            return ANIMALS.COW;
        }else if (animal instanceof EntityChicken){
            return ANIMALS.CHICKEN;
        }else if (animal instanceof EntitySheep){
            return ANIMALS.SHEEP;
        }else if (animal instanceof EntityPig){
            return ANIMALS.PIG;
        }else if (animal instanceof EntitySquid){
            return ANIMALS.SQUID;
        }else if (animal instanceof EntityBat){
            return ANIMALS.BAT;
        }
        return null;
    }

    public static enum ANIMALS {

        COW,
        CHICKEN,
        SHEEP,
        PIG,
        SQUID,
        BAT,
        MOOSHROOM;

        public boolean doesEnumMatch(EnumAnimals.ANIMALS animal) {
            if (animal == null){
                return false;
            }
            return animal.toString().equals(this.toString());
        }
    }
}
