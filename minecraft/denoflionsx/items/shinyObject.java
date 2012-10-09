package denoflionsx.items;

public class shinyObject {
    
    private boolean isShiny = false;

    public shinyObject() {
        this.isShiny = false;
    }
    
    public shinyObject(String server){
        this.isShiny = false;
    }

    public shinyObject(boolean param1) {   
        this.isShiny = param1;
    }
    
    
    public boolean getShiny(){
        
        return this.isShiny;
        
    }  
}
