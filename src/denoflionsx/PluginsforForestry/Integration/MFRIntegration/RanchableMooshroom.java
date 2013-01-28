package denoflionsx.PluginsforForestry.Integration.MFRIntegration;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidStack;
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;

public class RanchableMooshroom implements IFactoryRanchable{
    
    private LiquidStack soup;

    public RanchableMooshroom(LiquidStack soup) {
        this.soup = soup;
    }

    @Override
    public Class<?> getRanchableEntity() {
        return EntityMooshroom.class;
    }

    @Override
    public List<ItemStack> ranch(World world, EntityLiving entity, IInventory rancher) {
        ArrayList<ItemStack> drops = new ArrayList();
        drops.add(soup.asItemStack());
        return drops;
    }

}
