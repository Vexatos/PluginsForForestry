package denoflionsx.items;

import java.util.HashMap;
import denoflionsx.Enums.Colors;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;

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
