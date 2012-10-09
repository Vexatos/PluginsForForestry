package denoflionsx.plugins.Railcraft.Modules.OreCrushModule.Items;

import denoflionsx.Enums.Colors;

public class EnumDustTextures {
    
    public static final int times = 8;
    public static int row = 0;

    public static enum DUST {

        IRON(),
        GOLD(),
        COPPER(),
        TIN(),
        SILVER(),
        COAL();
        
        private int index;
        private DUST(){
            this.index = Colors.shiftRow(row++,times);
        }

        public int getIndex() {
            return index;
        }
        
        public static void init(){
            DUST.values();
        }
    }
}
