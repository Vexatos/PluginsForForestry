package denoflionsx.LiquidRoundup.ForestryIntegration;

import denoflionsx.denLib.denLib;
import forestry.api.storage.IBackpackDefinition;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BackpackTemplate implements IBackpackDefinition {

    private ArrayList<ItemStack> valid;
    private String name;
    private Color color;

    public BackpackTemplate(ArrayList<ItemStack> valid, String name, Color color) {
        this.valid = valid;
        this.name = name;
        this.color = color;
    }

    @Override
    public void addValidItem(ItemStack validItem) {
        valid.add(validItem);
    }

    @Override
    public String getKey() {
        return denLib.toLowerCaseNoSpaces(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrimaryColour() {
        return color.getRGB();
    }

    @Override
    public int getSecondaryColour() {
        return 0xffffff;
    }

    @Override
    public ArrayList<ItemStack> getValidItems(EntityPlayer player) {
        return valid;
    }

    @Override
    public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
        for (ItemStack i : getValidItems(player)) {
            if (itemstack.isItemEqual(i)) {
                return true;
            }
        }
        return false;
    }
}
