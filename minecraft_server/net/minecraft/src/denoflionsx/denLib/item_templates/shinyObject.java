package net.minecraft.src.denoflionsx.denLib.item_templates;

//import net.minecraft.src.EnumRarity;

public class shinyObject {
    
    private boolean isShiny = false;
    //private EnumRarity rare = EnumRarity.common;

    public shinyObject() {
    }

    public shinyObject(boolean param1) {
        
        this.isShiny = param1;
        //this.rare = param2;
        
    }
    
    public boolean getShiny(){
        
        return this.isShiny;
        
    }
    
    //public EnumRarity getRare(){return this.rare;}
    
}
