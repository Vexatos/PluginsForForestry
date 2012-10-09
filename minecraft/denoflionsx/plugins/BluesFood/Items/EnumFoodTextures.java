package denoflionsx.plugins.BluesFood.Items;

import denoflionsx.Enums.Colors;

public class EnumFoodTextures {

    public static enum FOOD {

        BUTTER_LIQUID(Colors.shiftRow(15, 0)),
        BUTCHER_KNIFE(Colors.shiftRow(0, 1)),
        TOMATO(Colors.shiftRow(13, 0)),
        CORN(Colors.shiftRow(14, 0)),
        BUTTER_ITEM(Colors.shiftRow(15, 1)),
        FRIED_EGG(Colors.shiftRow(0, 2)),
        CORN_ON_COB(Colors.shiftRow(14, 1)),
        CHEESE(Colors.shiftRow(15, 2)),
        SAUSAGE(Colors.shiftRow(0,3)),
        SAUSAGE_COOKED(Colors.shiftRow(0,4)),
        TENTACLE(Colors.shiftRow(2, 3)),
        CALAMARI(Colors.shiftRow(2, 4)),
        GROUND_BEEF(Colors.shiftRow(1, 3)),
        HAMBURGER(Colors.shiftRow(1, 4)),
        FLOUR(Colors.shiftRow(2, 2)),
        CUPCAKE(Colors.shiftRow(4, 2)),
        LAMBCHOP(Colors.shiftRow(3, 3)),
        COOKED_LAMBCHOP(Colors.shiftRow(3, 4));
        private int index;

        private FOOD(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
