package denoflionsx.PluginsforForestry.Plugins.MFR.BeeAddon.Frames;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IHiveFrame;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFrameBase extends Item implements IHiveFrame {
    
    public ItemFrameBase() {
        super(PfFTuning.getInt(PfFTuning.MFR_Items.MFR_poopFrames_ItemID));
        this.setMaxDamage(240);
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
    }
    
    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return PfFTranslator.instance.translateKey("item.pff.mfr.poopframe.name");
    }
    
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Lowers every attribute of the Apiary by 1/3.");
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("@NAME@:radical_edward");
    }
    
    @Override
    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear) {
        frame.setItemDamage(frame.getItemDamage() - wear);
        return frame;
    }
    
    @Override
    public float getTerritoryModifier(IBeeGenome genome, float currentModifier) {
        return (float) currentModifier / (1 / 3);
    }
    
    @Override
    public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
        return (float) currentModifier / (1 / 3);
    }
    
    @Override
    public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
        PfF.Proxy.print("" + (float) currentModifier / (1 / 3));
        return (float) currentModifier / (1 / 3);
    }
    
    @Override
    public float getProductionModifier(IBeeGenome genome, float currentModifier) {
        return (float) currentModifier / (1 / 3);
    }
    
    @Override
    public float getFloweringModifier(IBeeGenome genome, float currentModifier) {
        return (float) currentModifier / (1 / 3);
    }
    
    @Override
    public boolean isSealed() {
        return false;
    }
    
    @Override
    public boolean isSelfLighted() {
        return false;
    }
    
    @Override
    public boolean isSunlightSimulated() {
        return false;
    }
    
    @Override
    public boolean isHellish() {
        return false;
    }
}
