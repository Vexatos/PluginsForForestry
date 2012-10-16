package denoflionsx.Machine.Client;

import buildcraft.api.liquids.LiquidStack;

public class PfFLiquidSlot {

    private PfFMachineGUI gui;
    private int coords[] = new int[2];
    private int TankCoords[];
    private LiquidStack liquid;

    public PfFLiquidSlot(PfFMachineGUI gui, int[] TankCoords, LiquidStack liquid) {
        this.gui = gui;
        this.TankCoords = TankCoords;
        this.liquid = liquid;
    }

    public void drawTank() {
        if (liquid != null && this.gui != null) {
            if (liquid.amount > 0) {
                this.coords = IconIndexLogic.getCoorsFromIndex(this.liquid.asItemStack().getIconIndex());
                this.gui.bindTexture(this.liquid.asItemStack().getItem().getTextureFile());

                int bottom = (4 * 9) + 34 + 5;
                this.gui.drawTexturedModalRect(this.gui.x + this.TankCoords[0], this.TankCoords[1] + bottom, coords[0], coords[1], 16, 16);
            }
        }
    }
}
