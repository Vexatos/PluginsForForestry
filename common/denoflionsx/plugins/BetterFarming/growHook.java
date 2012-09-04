package denoflionsx.plugins.BetterFarming;

import java.lang.reflect.Method;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import denoflionsx.core.core;

public class growHook {
    
    public static Class BlockCustomTree;
    protected static Class[] parameters = new Class[]{World.class,int.class,int.class,int.class};
    protected static Method grow;
    protected static String treeclass = "BlockCustomTree";
    
    public static void growTree(World world, int x, int y, int z, Block tree){
        
        int germling = world.getBlockId(x, y, z);
        if (germling == tree.blockID){
            world.setBlockAndMetadataWithNotify(x, y - 1, z, Block.dirt.blockID, 0);
            try{      
               grow.invoke(BlockCustomTree.cast(tree),world,x,y,z);
            }catch(Exception ex){
                ex.printStackTrace();
            }         
        }
        world.setBlockAndMetadataWithNotify(x, y - 1, z, Block.sand.blockID, 0);
        
    }
    
    public static void engage(){
        try{
            if (core.isBukkit){
                treeclass = "net.minecraft.server." + treeclass;
            }
        BlockCustomTree = Class.forName(treeclass);
        grow = BlockCustomTree.getMethod("growTree", parameters);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
}
