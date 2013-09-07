package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Client.Render.ItemContainerRenderer;
import denoflionsx.PluginsforForestry.Client.Render.RenderThis;
import denoflionsx.PluginsforForestry.Core.PfF;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.Icon;
import net.minecraftforge.client.MinecraftForgeClient;

public class PfFProxyClient extends PfFProxy {

    public static final ArrayList<Object> hasRenderables = new ArrayList();

    @Override
    public void registerClientSide() {
        try {
            for (Object o : hasRenderables) {
                for (Field f : o.getClass().getDeclaredFields()) {
                    for (Annotation a : f.getDeclaredAnnotations()) {
                        if (a instanceof RenderThis) {
                            RenderThis r = (RenderThis) a;
                            Item i = (Item) f.get(null);
                            MinecraftForgeClient.registerItemRenderer(i.itemID, new ItemContainerRenderer(r.renderFile()));
                        }
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void registerRenderable(Object o) {
        hasRenderables.add(o);
    }

    @SideOnly(Side.CLIENT)
    public static Icon registerIcon(IconRegister par1IconRegister, String icon) {
        PfF.Proxy.print("Registering icon " + icon);
        return par1IconRegister.registerIcon(icon);
    }

    @Override
    public void sendMessageToPlayer(String msg) {
        Minecraft.getMinecraft().thePlayer.sendChatToPlayer(ChatMessageComponent.func_111066_d("[PfF]: " + msg));
    }

    @Override
    public String getLang() {
        return Minecraft.getMinecraft().gameSettings.language;
    }
}
