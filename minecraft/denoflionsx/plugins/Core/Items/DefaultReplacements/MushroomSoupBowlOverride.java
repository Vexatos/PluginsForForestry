package denoflionsx.plugins.Core.Items.DefaultReplacements;

import denoflionsx.API.PfFManagers;
import net.minecraft.src.*;

public class MushroomSoupBowlOverride extends ItemSoup {

    public static int BowlStack = 1;

    public MushroomSoupBowlOverride() {
        super(26, 8);
        setIconCoord(8, 4);
        setItemName("mushroomStew");
        this.setContainerItem(Item.bowlEmpty);
        this.setMaxStackSize(BowlStack);
        PfFManagers.ItemManager.registerItem("mushroomsoupbowl", this);
    }

    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (BowlStack > 1) {
            --par1ItemStack.stackSize;
            par3EntityPlayer.getFoodStats().addStats(this);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
            this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
            par3EntityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Item.bowlEmpty), false);
            return par1ItemStack;
        }else{
            return super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        }
    }

    @Override
    public Item getContainerItem() {
        return Item.bowlEmpty;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
        return new ItemStack(Item.bowlEmpty);
    }
}
