package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.Client.Render.ItemContainerRenderer;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemLRBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemWoodenBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.denLib.Mod.Client.Render.RenderBlockFluidClassic;
import forestry.api.core.ItemInterface;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class PfFProxyClient extends PfFProxy {

    public static int liquidRenderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerClientSide() {
        MinecraftForgeClient.registerItemRenderer(LRItems.containers.get("Barrel").itemID, new ItemContainerRenderer("barrel.txt", new ItemStack(LRItems.containers.get("Barrel"))));
        MinecraftForgeClient.registerItemRenderer(LRItems.containers.get(("Capsule")).itemID, new ItemContainerRenderer("capsule.txt", ItemInterface.getItem("waxCapsule")));
        MinecraftForgeClient.registerItemRenderer(LRItems.containers.get(("Refractory Capsule")).itemID, new ItemContainerRenderer("capsule.txt", ItemInterface.getItem("refractoryEmpty")));
        for (Item i : LRItems.customBucketsFilled.values()) {
            ItemStack empty;
            if (i instanceof ItemWoodenBucket) {
                empty = LRItems.ItemStackWoodenBucketEmpty;
            } else {
                empty = LiquidContainerRegistry.EMPTY_BUCKET;
            }
            MinecraftForgeClient.registerItemRenderer(i.itemID, new ItemContainerRenderer("bucket.txt", empty));
        }
        RenderingRegistry.registerBlockHandler(liquidRenderID, new RenderBlockFluidClassic(liquidRenderID));
    }

    @SideOnly(Side.CLIENT)
    public static Icon registerIcon(IconRegister par1IconRegister, String icon) {
        PfF.Proxy.print("Registering icon " + icon);
        return par1IconRegister.registerIcon(icon);
    }
}
