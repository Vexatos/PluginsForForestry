package denoflionsx.PluginsforForestry_PluginBlueFood.Enchants;

import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemButcherKnife;
import java.util.ArrayList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EnchantBeastSlaying extends Enchantment {

    public EnchantBeastSlaying(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, par2, par3EnumEnchantmentType);
        this.reg();
    }

    public EnchantBeastSlaying() {
        this(FoodTuning.Enchantments.BeastSlaying_EnchantID, 10, EnumEnchantmentType.weapon);
    }
    
    private void reg(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        if (par1Enchantment == this) {
            return false;
        }
        return true;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 1;
    }

    @Override
    public String getName() {
        return "Beast Slaying";
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if (stack.getItem() instanceof ItemButcherKnife){
            return true;
        }else{
            return false;
        }
    }

    @ForgeSubscribe
    public void onDeath(LivingDeathEvent ev) {
        if (!(ev.source instanceof EntityDamageSource)) {
            return;
        }
        EntityDamageSource ed = (EntityDamageSource) ev.source;
        Entity entity = ed.getEntity();
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        ItemStack weapon = player.getCurrentEquippedItem();
        int level = EnchantmentHelper.getEnchantmentLevel(FoodTuning.Enchantments.BeastSlaying_EnchantID, weapon);
        if (level == 0) {
            return;
        }
        EnumAnimals.ANIMALS animal = EnumAnimals.getAnimalType(ev.entityLiving);
        if (animal == null) {
            return;
        }
        ArrayList<ItemStack> drops = PfFManagers.ButcherKnife.getDropsForAnimal(animal);
        for (ItemStack i : drops) {
            ItemStack i2 = i.copy();
            i2.stackSize = (i2.stackSize * FoodTuning.Enchantments.BeastSlaying_BonusMultiplier) - 1;
            player.dropPlayerItemWithRandomChoice(i2, false);
        }
    }
}
