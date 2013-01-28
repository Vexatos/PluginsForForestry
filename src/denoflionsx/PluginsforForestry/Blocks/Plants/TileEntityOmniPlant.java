package denoflionsx.PluginsforForestry.Blocks.Plants;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOmniPlant extends TileEntity {

    private static final String tag = "OmniPlant";
    private ItemStack product;
    private int growthStage = 0;
    private static final int finalStage = 4;
    private boolean isExternal = false;

    public TileEntityOmniPlant() {
    }

    @Override
    public void updateEntity() {
    }
    
    public void setExternalStatus(boolean status){
        isExternal = status;
    }
    
    public boolean getExternalStatus(){
        return isExternal;
    }

    public TileEntityOmniPlant(ItemStack product) {
        this.product = product;
        this.growthStage = 0;
    }

    public void setGrowthStage(int growthStage) {
        this.growthStage = growthStage;
    }

    public void setProduct(ItemStack product) {
        this.product = product;
    }

    public static int getFinalStage() {
        return finalStage;
    }

    public void incrementGrowth() {
        growthStage++;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public ItemStack getProduct() {
        return product;
    }

    public static String getTag() {
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagCompound dolx = par1NBTTagCompound.getCompoundTag(tag);
        if (dolx != null) {
            NBTTagCompound p = dolx.getCompoundTag("Product");
            this.product = new ItemStack(p.getInteger("ID"),p.getInteger("Amount"),p.getInteger("Meta"));
            this.growthStage = dolx.getInteger("Growth");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagCompound dolx = new NBTTagCompound();
        NBTTagCompound p = new NBTTagCompound();
        p.setInteger("ID",product.itemID);
        p.setInteger("Amount",product.stackSize);
        p.setInteger("Meta",product.getItemDamage());
        dolx.setCompoundTag("Product", p);
        dolx.setInteger("Growth", this.growthStage);
        par1NBTTagCompound.setCompoundTag(tag, dolx);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tileTag = new NBTTagCompound();
        this.writeToNBT(tileTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tileTag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
        this.readFromNBT(pkt.customParam1);
    }
}
