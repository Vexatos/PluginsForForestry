package denoflionsx.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.HashMap;
import denoflionsx.Enums.Colors;
import denoflionsx.items.Fuels.customFuel;
import net.minecraft.src.ItemStack;

public class PfFContainer extends multiItem {

    private boolean multiPass = true;
    protected HashMap<Integer, Integer> renderColors = new HashMap();

    public PfFContainer(int par1, String name) {
        super(par1, name);
    }

    public void addRenderColor(int dmg, int color) {
        renderColors.put(dmg, color);
    }

    public void setAllRenderColor(int color) {
        for (int i = 0; i != customFuel.numOfContainers; i++) {

            renderColors.put(i, color);

        }
    }

    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        return getColorFromDamage(par1ItemStack.getItemDamage(),par2);
    }
    
    

    @SideOnly(Side.CLIENT)
    public int getColorFromDamage(int par1, int par2) {
        if (par2 > 0) {
            if (this.renderColors.get(par1) != null) {
                return this.renderColors.get(par1);
            }else{
                return Colors.Values.WHITE.getColor();
            }
        } else {
            return Colors.Values.WHITE.getColor();
        }
    }

    @Override
    public int getIconFromDamageForRenderPass(int par1, int par2) {
        if (par2 > 0) {
            return super.getIconFromDamageForRenderPass(par1, par2) + 1;
        }
        return super.getIconFromDamageForRenderPass(par1, par2);
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return this.multiPass;
    }
}
