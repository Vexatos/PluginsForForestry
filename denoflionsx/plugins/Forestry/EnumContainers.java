package denoflionsx.plugins.Forestry;

import denoflionsx.denLib.Colors;

public class EnumContainers {

    public static enum Containers {

        CAPSULE, CAN(Colors.shiftRow(0, 3)), CAPSULE_RED(Colors.shiftRow(0, 5)),
        BUCKET(Colors.shiftRow(0, 1)), BOTTLE(Colors.shiftRow(0, 2), (int) Math.floor((1000 / 4))),
        CELL(Colors.shiftRow(0, 6)), BAR(Colors.shiftRow(2, 7)), BARREL(Colors.shiftRow(0, 7), 10000);
        int texture;
        int quantity;

        private Containers() {
            // Default texture: Wax cell.
            this.texture = Colors.shiftRow(0, 4);
            this.quantity = 1000;
        }

        private Containers(int texture) {
            this.texture = texture;
            this.quantity = 1000;
        }

        private Containers(int texture, int quantity) {
            this.texture = texture;
            this.quantity = quantity;
        }

        public int getTexture() {
            return this.texture;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
