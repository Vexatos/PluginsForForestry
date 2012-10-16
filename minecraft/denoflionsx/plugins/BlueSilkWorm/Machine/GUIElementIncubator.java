package denoflionsx.plugins.BlueSilkWorm.Machine;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Machine.Client.PfFGUIElement;
import denoflionsx.Machine.Client.PfFLiquidSlot;
import denoflionsx.Machine.Client.PfFMachineGUI;

public class GUIElementIncubator extends PfFGUIElement {

    private static int baseX = 151;
    private static int baseY = 45;
    private static int[] tankCoords = new int[]{93, 11};

    @Override
    public void draw(PfFMachineGUI gui) {
        TileEntityIncubator incubator = (TileEntityIncubator) gui.tile;
        gui.drawTexturedModalRect(gui.x + 151, gui.y + 45, 176, 8, 18, 12);
        if (gui.tile.tanks[0].getLiquid() != null) {
            LiquidStack liquid = gui.tile.tanks[0].getLiquid();
            PfFLiquidSlot slot = new PfFLiquidSlot(gui,tankCoords,liquid);
            slot.drawTank();
        }
    }
}
