package denoflionsx.items.Containers;

import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;
import denoflionsx.items.multiItem;
import denoflionsx.Enums.EnumContainers;

public class Containers {

    public static enum Container {

        BARREL("Barrel",EnumContainers.Containers.BARREL),
        BAR("Infusion Bar",EnumContainers.Containers.BAR);
        
        private ItemIDManager ID;
        private multiItem item;
        private String tag;
        private String tagNoSpaces;
        private int id;

        private Container(String tag, EnumContainers.Containers container) {
            this.tag = tag;
            this.tagNoSpaces = denLib.toLowerCaseNoSpaces(tag);
            ID = new ItemIDManager(1, tagNoSpaces);
            defaults();
            item = new multiItem(this.id, tagNoSpaces);
            item.metaMap.put(tag, 0);
            item.add(tagNoSpaces, item.metaMap.get(tag), container.getTexture(), tag);
        }
        
        private void defaults(){
            String tag2 = denLib.toNoSpaces(this.tag);
            core.config.addDefault(tag2 + "_ItemID=" + ID.getItemIDs().get(0));
            this.id = core.config.getOptionInt(tag2 + "_ItemID");
        }
        public static void register(){
            Containers.Container.values();
        }
    }
}
