package denoflionsx.LiquidRoundup.Managers;

import denoflionsx.LiquidRoundup.API.Interfaces.ILRContainerManager;
import denoflionsx.LiquidRoundup.API.Objects.LRContainer;
import denoflionsx.LiquidRoundup.Items.ContainerBase;
import denoflionsx.LiquidRoundup.Items.LRIDs;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.denLib;
import net.minecraft.item.ItemStack;

public class LRContainerManager implements ILRContainerManager {

    private static final int limit = 256 / 2;
    public static LRContainer[] containers = new LRContainer[limit];
    public static int current = 0;
    public static int spriteNum = -2;

    static {
        for (int i = 0; i < limit; i++) {
            containers[i] = null;
        }

    }

    @Override
    public LRContainer getContainerByName(String name) {
        for (LRContainer r : containers) {
            if (r != null) {
                if (r.getName().equals(name)) {
                    return r;
                }
            }
        }
        return null;
    }

    @Override
    public LRContainer[] getContainerList() {
        return containers;
    }

    @Override
    public void registerNewContainer(String name, String[] textures, int capacity, ItemStack empty, boolean returned, boolean blacklistLava, boolean customOnly, int maxStackSize) {
        current++;
        int id = LiquidRoundup.Core.config.getItem(denLib.toLowerCaseNoSpaces(name) + "_ItemID", LRIDs.ContainerIDs[current]).getInt();
        ContainerBase container = new ContainerBase(id);
        container.setItemName(name);
        container.setCreativeTab(LiquidRoundup.Core.tab);
        container.setTextureFile("/denoflionsx/LiquidRoundup/gfx/containersheet.png");
        container.setMaxStackSize(maxStackSize);
        int internal = getNewInternalID();
        spriteNum += 2;
        LiquidRoundup.Proxy.registerContainerTexture(textures[0], spriteNum);
        LiquidRoundup.Proxy.registerContainerTexture(textures[1], spriteNum + 1);
        container.setContainerTexture(spriteNum);
        if (returned) {
            container.setContainerItem(empty.getItem());
        }
        LRContainer LR = new LRContainer(name, textures, capacity, empty, blacklistLava, customOnly);
        LR.setStack(new ItemStack(container));
        LR.setEmpty(empty);
        containers[internal] = LR;
    }

    @Override
    public int getNewInternalID() {
        for (int i = 0; i < limit; i++) {
            if (containers[i] == null) {
                return i;
            }
        }
        throw new UnsupportedOperationException("No free container IDs!");
    }
}
