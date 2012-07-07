package net.minecraft.server.denoflionsx.plugins.Forestry;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class circuitBoards
{
    public static final Item baseCircuitBoard = denLib.getItem("forestry.core.config.ForestryItem", "circuitboards");
    public static ItemStack smallCircuitBoard = new ItemStack(baseCircuitBoard, 1, 0);
    public static ItemStack mediumCircuitBoard = new ItemStack(baseCircuitBoard, 1, 1);
    public static ItemStack largeCircuitBoard = new ItemStack(baseCircuitBoard, 1, 2);
}
