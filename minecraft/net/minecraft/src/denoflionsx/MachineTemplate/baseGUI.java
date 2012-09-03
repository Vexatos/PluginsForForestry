package net.minecraft.src.denoflionsx.MachineTemplate;

import java.util.ArrayList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import org.lwjgl.opengl.GL11;

public class baseGUI extends GuiContainer {

    public baseGUI(EntityPlayer player, baseTileEntity tile, ArrayList<CoordObject> map) {
        super(new baseContainer(player.inventory,tile,map));
    }
    
    public String getTexture(){
        return "";
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        int var4 = this.mc.renderEngine.getTexture(getTexture());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        title("write something here");  
    }
    
    public void title(String t){
        this.fontRenderer.drawString(t,60, 6, 4210752);
    }
}
