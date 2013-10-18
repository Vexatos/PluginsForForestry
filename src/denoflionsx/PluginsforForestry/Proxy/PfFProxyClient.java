package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Core.PfF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.Icon;

public class PfFProxyClient extends PfFProxy {

    @Override
    public void registerClientSide() {
    }

    @Override
    public void registerRenderable(Object o) {
    }

    @SideOnly(Side.CLIENT)
    public static Icon registerIcon(IconRegister par1IconRegister, String icon) {
        PfF.Proxy.print("Registering icon " + icon);
        return par1IconRegister.registerIcon(icon);
    }

    @Override
    public void sendMessageToPlayer(String msg) {
        Minecraft.getMinecraft().thePlayer.sendChatToPlayer(ChatMessageComponent.createFromText("[PfF]: " + msg));
    }

    @Override
    public String getLang() {
        return Minecraft.getMinecraft().gameSettings.language;
    }
}
