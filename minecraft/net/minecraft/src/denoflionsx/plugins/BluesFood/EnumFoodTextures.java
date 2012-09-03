package net.minecraft.src.denoflionsx.plugins.BluesFood;

import net.minecraft.src.denoflionsx.denLib.Colors;

public class EnumFoodTextures {

    public static enum FOOD {

        BUTTER_LIQUID(Colors.shiftRow(15, 0)),
        BUTCHER_KNIFE(Colors.shiftRow(0, 1)),
        TOMATO(Colors.shiftRow(1, 1)),
        CORN(Colors.shiftRow(4, 1)),
        BUTTER_ITEM(Colors.shiftRow(15, 1)),
        FRIED_EGG(Colors.shiftRow(0, 2)),
        CORN_ON_COB(Colors.shiftRow(4, 2)),
        CHEESE(Colors.shiftRow(15, 2)),
        SAUSAGE(Colors.shiftRow(0,3)),
        SAUSAGE_COOKED(Colors.shiftRow(0,4));
        private int index;

        private FOOD(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
