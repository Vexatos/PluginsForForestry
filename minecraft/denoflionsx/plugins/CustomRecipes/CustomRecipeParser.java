package denoflionsx.plugins.CustomRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.ItemStack;

public class CustomRecipeParser {
    
    public static void parse(ArrayList<String> input){
        for (String s : input){
            ItemStack output = null;
            HashMap<String, ItemStack> z = new HashMap();
            String[] split = s.split(",");
            String gridTop = split[0];
            String gridMiddle = split[1];
            String gridBottom = split[2];
            for (int i = 3; i != split.length; i++){
                String[] s2 = split[i].split(">");
                if (s2[0].toLowerCase().equals("output")){
                    String s3[] = s2[1].split("|");
                    output = new ItemStack(Integer.valueOf(s3[0]),Integer.valueOf(s3[1]),Integer.valueOf(s3[2]));
                }else{
                    String s3[] = s2[1].split("|");
                    ItemStack temp = new ItemStack(Integer.valueOf(s3[0]),1,Integer.valueOf(s3[1]));
                    z.put(s2[0],temp);
                }
            }
            ArrayList<Object> obj = new ArrayList();
            obj.add(gridTop);
            obj.add(gridMiddle);
            obj.add(gridBottom);
            Iterator q = z.entrySet().iterator();
            while (q.hasNext()){
                Map.Entry pairs = (Map.Entry) q.next();
                char c = pairs.getKey().toString().charAt(0);
                ItemStack item = (ItemStack)pairs.getValue();
                obj.add(Character.valueOf(c));
                obj.add(item);
            }
            GameRegistry.addRecipe(output, obj.toArray());
        }
    }
    
}
