package denoflionsx.Machine.Client;

import net.minecraftforge.liquids.LiquidStack;


public class PfFLiquidSlot {

    private PfFMachineGUI gui;
    private int coords[] = new int[2];
    private int TankCoords[];
    private LiquidStack liquid;
    private int bottom = (4 * 9) + 34 - (16 / 2) - 1;

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
                int draw1 = 0;
                int draw2 = 0;
                int draw3 = 0;
                int displayAmount = (liquid.amount / 1000);
                int drawPixels = displayAmount * 4;
                if (drawPixels == 40) {
                    drawPixels--;
                }
                if (drawPixels == 0) {
                    return;
                }
                if (drawPixels <= 16) {
                    draw1 = drawPixels;
                } else if (drawPixels > 16) {
                    draw1 = 16;
                    draw2 = drawPixels - 16;
                    if (draw2 > 16) {
                        draw3 = draw2 - 16;
                        draw2 = 16;
                    }
                }
                if (draw1 > 0) {
                    this.gui.drawTexturedModalRect(this.gui.x + this.TankCoords[0], this.TankCoords[1] + bottom, coords[0], coords[1], 16, draw1);
                }
                if (draw2 > 0) {
                    this.gui.drawTexturedModalRect(this.gui.x + this.TankCoords[0], this.TankCoords[1] + (bottom - 16), coords[0], coords[1], 16, draw2);
                }
                if (draw3 > 0) {
                    this.gui.drawTexturedModalRect(this.gui.x + this.TankCoords[0], this.TankCoords[1] + (bottom - (((16 * 3) / 2) - 1)), coords[0], coords[1], 16, draw3);
                }
            }
        }
    }
}
