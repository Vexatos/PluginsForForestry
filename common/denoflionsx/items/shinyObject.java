package denoflionsx.items;

import net.minecraft.src.EnumRarity;

public class shinyObject {
    
    private boolean isShiny = false;
    private EnumRarity rare = EnumRarity.common;

    public shinyObject() {
        this.isShiny = false;
        this.rare = EnumRarity.common;
    }

    public shinyObject(boolean param1, EnumRarity param2) {
        
        this.isShiny = param1;
        this.rare = param2;
        
    }
    
    public boolean getShiny(){
        
        return this.isShiny;
        
    }
    
    public EnumRarity getRare(){return this.rare;}
    
}
