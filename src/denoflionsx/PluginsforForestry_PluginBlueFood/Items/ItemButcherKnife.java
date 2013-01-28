package denoflionsx.PluginsforForestry_PluginBlueFood.Items;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.PfFFood;
import denoflionsx.denLib.EnumTextColor;
import denoflionsx.denLib.FMLWrapper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemButcherKnife extends ItemSword {

    public ItemButcherKnife(int par1, EnumToolMaterial par3EnumToolMaterial) {
        super(par1, par3EnumToolMaterial);
        this.setCreativeTab(PfF.Core.tab);
        this.setTextureFile(PfFFood.Core.spritesheet);
        this.setIconIndex(16);
        ItemStack i = new ItemStack(this);
        PfFManagers.Items.registerItem("butcherknife", i);
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public ItemButcherKnife recipe() {
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"XBX", "SII", "XII", Character.valueOf('S'), new ItemStack(Item.stick), Character.valueOf('I'), new ItemStack(Item.ingotIron), Character.valueOf('B'), PfFManagers.Items.getItemByTag("blacksmithinghammer")});
        return this;
    }

    public ItemButcherKnife() {
        this(FoodTuning.Items.ButcherKnife_ItemID, EnumToolMaterial.IRON);
    }

    @Override
    public int getDamageVsEntity(Entity par1Entity) {
        if (EnumAnimals.getAnimalType(par1Entity) != null) {
            return FoodTuning.Tuning.ButcherKnife_DamageVsEntity * FoodTuning.Tuning.ButcherKnife_DamageVsAnimalMultiplier;
        } else {
            return FoodTuning.Tuning.ButcherKnife_DamageVsEntity;
        }
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.stackTagCompound = new NBTTagCompound();
        }
        if (!par1ItemStack.stackTagCompound.hasKey("cooldown")) {
            par1ItemStack.stackTagCompound.setInteger("cooldown", 0);
        }
        if (par1ItemStack.stackTagCompound.getInteger("cooldown") != 0) {
            int i = par1ItemStack.stackTagCompound.getInteger("cooldown");
            i--;
            par1ItemStack.stackTagCompound.setInteger("cooldown", i);
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.hasKey("cooldown")) {
                if (par1ItemStack.stackTagCompound.getInteger("cooldown") != 0) {
                    par3List.add(EnumTextColor.RED.ColorString("Not ready yet."));
                } else {
                    par3List.add(EnumTextColor.BRIGHT_GREEN.ColorString("Ready!"));
                }
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        if (par2EntityLiving.worldObj.isRemote) {
            return false;
        }
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.hasKey("cooldown")) {
                if (par1ItemStack.stackTagCompound.getInteger("cooldown") != 0) {
                    return false;
                }
            }
        }
        par1ItemStack.damageItem(1, par3EntityLiving);
        EnumAnimals.ANIMALS animal = EnumAnimals.getAnimalType(par2EntityLiving);
        if (animal != null) {
            EntityPlayer player = (EntityPlayer) par3EntityLiving;
            ArrayList<ItemStack> drops = PfFManagers.ButcherKnife.getDropsForAnimal(animal);
            for (ItemStack i : drops) {
                player.dropPlayerItemWithRandomChoice(i.copy(), false);
            }
        }
        par1ItemStack.stackTagCompound.setInteger("cooldown", FoodTuning.Tuning.ButcherKnife_InternalCooldown * 20);
        return true;
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getIconIndex(ItemStack stack, int pass) {
        if (pass > 0) {
            if (stack.stackTagCompound != null) {
                if (stack.stackTagCompound.hasKey("cooldown")) {
                    if (stack.stackTagCompound.getInteger("cooldown") != 0) {
                        return 1;
                    }
                }
            }
        }
        return 16;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
        par1ItemStack.damageItem(2, par7EntityLiving);
        return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return "Butcher Knife";
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        if (par2ItemStack.isItemEqual(new ItemStack(Item.ingotIron))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        return 1.0f;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        return 1.0f;
    }

    @Override
    public int getItemEnchantability() {
        return 30;
    }
}
