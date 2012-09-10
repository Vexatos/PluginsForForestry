
package denoflionsx.Managers;

import denoflionsx.API.IPfFExtractorTargetRegistry;
import java.util.HashMap;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public class PfFExtractorTargetManager implements IPfFExtractorTargetRegistry{

    private static HashMap<Integer, Target> targets = new HashMap();
    
    @Override
    public void addBlock(int id, int meta) {
        targets.put(id,new Target(id,meta));
    }

    @Override
    public void addBlock(int id) {
        addBlock(id,0);
    }

    @Override
    public void addBlock(Block b) {
        addBlock(b.blockID,0);
    }

    @Override
    public void addBlock(Block b, int meta) {
        addBlock(b.blockID,meta);
    }
    

    @Override
    public void addItemStack(ItemStack i) {
        addBlock(i.itemID,i.getItemDamage());
    }

    @Override
    public boolean isValidTarget(int id, int meta) {
        if (targets.get(id) != null){
            if (targets.get(id).isEqual(id, meta)){
                return true;
            }
        }
        return false;
    }
    
    

    @Override
    public boolean isValidTarget(int id) {
        return isValidTarget(id,0);
    }
    
    public static class Target{
        private int id;
        private int meta;

        public Target(int id, int meta) {
            this.id = id;
            this.meta = meta;
        }

        public Target(int id) {
            this.id = id;
            this.meta = 0;
        }
        
        public boolean isEqual(int id, int meta){
            if (this.id == id && this.meta == meta){
                return true;
            }else{
                return false;
            }
        }    
    }
    
}
