package denoflionsx.plugins.BlueSilkWorm.Items;

import denoflionsx.API.PfFManagers;
import denoflionsx.Annotations.DebugStatementInThisMethod;
import denoflionsx.denLib.EnumTextColor;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWorm;
import denoflionsx.plugins.BlueSilkWorm.Managers.SilkWormManagers;
import java.util.List;
import net.minecraft.src.ItemStack;

public class ItemSilkWorm extends multiItem implements ISilkWorm{
    
    private static final boolean needsDebugged = true;

    public ItemSilkWorm(int par1, String name) {
        super(par1, name);
        this.setMaxStackSize(1);
        this.add("Silk Worm", SilkWormGrowthStages.WORM.getMeta(), SilkWormGrowthStages.WORM.getTexture());
        this.add("Silk Cocoon",SilkWormGrowthStages.COCOON.getMeta(),SilkWormGrowthStages.COCOON.getTexture());
        this.add("Silk Moth",SilkWormGrowthStages.MOTH.getMeta(),SilkWormGrowthStages.MOTH.getTexture());
        this.add("Dead Worm",SilkWormGrowthStages.DEAD_WORM.getMeta(),SilkWormGrowthStages.DEAD_WORM.getTexture());
        this.add("Zombie Worm",SilkWormGrowthStages.ZOMBIE_WORM.getMeta(),SilkWormGrowthStages.ZOMBIE_WORM.getTexture());
        
        SilkWormManagers.Registry.registerMoth(PfFManagers.ItemManager.getItem("silkmoth"));
        SilkWormManagers.Registry.registerCocoon(PfFManagers.ItemManager.getItem("silkcocoon"));
        SilkWormManagers.Registry.registerWorm(PfFManagers.ItemManager.getItem("silkworm"));
    }

    @DebugStatementInThisMethod
    @Override
    public void addInformation(ItemStack par1ItemStack, List par2List) {
        if (par1ItemStack.stackTagCompound != null){
            String gender = SilkWormHelper.getWormGender(par1ItemStack);
            if (gender.toLowerCase().equals("female")){
                gender = EnumTextColor.PINK.ColorString(gender);
            }else if (gender.toLowerCase().equals("male")){
                gender = EnumTextColor.AQUA.ColorString(gender);
            }
            par2List.add("Gender: " + gender);
            par2List.add("Worm Stage Length: " + SilkWormHelper.getWormCocoonCategory(par1ItemStack));
            par2List.add("Cocoon Stage Length: " + SilkWormHelper.getWormMothCategory(par1ItemStack));
            if (needsDebugged){
                par2List.add("Stage Progress: " + EnumTextColor.ORANGE.ColorString(String.valueOf(SilkWormHelper.getWormCurrentLife(par1ItemStack))));
            }
        }
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public void progressWormGrowth(ItemStack worm) {
        SilkWormHelper.progressWorm(worm);
    }

    @Override
    public void setupWorm(ItemStack worm) {
        SilkWormHelper.setupWorm(worm);
    }

    @Override
    public boolean isWormValid(ItemStack worm) {
        return SilkWormHelper.isWormValid(worm);
    }

}
