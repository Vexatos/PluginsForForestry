package denoflionsx.Machine.Client;

import denoflionsx.Machine.PfFMachineContainer;
import denoflionsx.Machine.PfFMachineTileEntity;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import org.lwjgl.opengl.GL11;

public class PfFMachineGUI extends GuiContainer {

    public PfFMachineTileEntity tile;
    public int x = (width - xSize) / 2;
    public int y = (height - ySize) / 2;
    private PfFGUIElement element;

    public PfFMachineGUI(Container par1Container) {
        super(par1Container);
        PfFMachineContainer c = (PfFMachineContainer) par1Container;
        this.tile = (PfFMachineTileEntity) c.tile;
        this.element = PfFGUIElementManager.getElement(this.tile.getName());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        bindTexture(this.tile.getGUITexture());
        x = (width - xSize) / 2;
        y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if (this.element != null) {
            this.element.draw(this);
        }
    }
    
    public void bindTexture(String tex){
        int s = mc.renderEngine.getTexture(tex);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(s);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString(this.tile.getName(), 8, 6, 4210752);
    }
}
