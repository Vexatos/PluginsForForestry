package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Dictionary.ReflectionModuleParser.ClassAndField;
import denoflionsx.denLib.Lib.denLib;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PfFReflectionParser {

    public HashMap<String, ArrayList<ClassAndField>> map = new HashMap();
    public static PfFReflectionParser instance;
    private String typesList;
    private String masterList;

    public PfFReflectionParser(String typesList, String masterList) {
        this.typesList = typesList;
        this.masterList = masterList;
    }

    public void parse() {
        for (String type : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, this.typesList, instance)) {
            map.put(type, new ArrayList<ClassAndField>());
            PfF.Proxy.print("Creating new reflection list: " + type);
        }
        for (String module : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, this.masterList, instance)) {
            ReflectionModuleParser parse = new ReflectionModuleParser(denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, module, instance));
            PfF.Proxy.print("Reading module " + parse.getTitle() + " by " + parse.getAuthor());
            map.get(parse.getType()).addAll(parse.getClassAndFieldEntries());
        }
    }
    
    public ItemStack[] getEntriesAsItemStacksForType(String type){
        ArrayList<ClassAndField> c = map.get(type);
        ArrayList<ItemStack> stacks = new ArrayList();
        for (ClassAndField f : c){
            Object o = denLib.ReflectionHelper.getStaticField(f.getClass(), f.getFieldName());
            if (o instanceof Item){
                Item i = (Item) o;
                ItemStack i1 = new ItemStack(i, 1, f.getMeta());
                stacks.add(i1);
            }else if (o instanceof Block){
                Block b = (Block) o;
                ItemStack b1 = new ItemStack(b, 1, f.getMeta());
                stacks.add(b1);
            }else if (o instanceof ItemStack){
                ItemStack i = (ItemStack) o;
                stacks.add(i.copy());
            }
        }
        return stacks.toArray(new ItemStack[stacks.size()]);
    }

    public static void createInstance() {
        instance = new PfFReflectionParser("VALID_TYPES.txt", "ReflectionModules.txt");
        instance.parse();
    }
}
