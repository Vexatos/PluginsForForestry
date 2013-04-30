package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Proxy.PfFProxyClient;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class ItemWoodenBucketEmpty extends ItemLRBucket {

    public ItemWoodenBucketEmpty(int par1, int par2, String local) {
        super(par1, par2, local);
        this.local = PfFTranslator.instance.translateKey(local);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        try {
            this.itemIcon = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_birch");
        } catch (Exception ex) {
            // shh
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return this.itemIcon;
    }
}
