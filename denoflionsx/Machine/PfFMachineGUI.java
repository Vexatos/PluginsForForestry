package denoflionsx.Machine;

import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.TileEntity;
import org.lwjgl.opengl.GL11;

public class PfFMachineGUI extends GuiContainer {

    public static final String texture = "/denoflionsx/oven_food_gui.png";
    private PfFMachineTileEntity tile;

    public PfFMachineGUI(TileEntity tile, Container par1Container) {
        super(par1Container);
        this.tile = (PfFMachineTileEntity) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        int tex = mc.renderEngine.getTexture(this.tile.gadget.getTextureFile());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString(this.tile.gadget.getName(), 8, 6, 4210752);
    }
}
