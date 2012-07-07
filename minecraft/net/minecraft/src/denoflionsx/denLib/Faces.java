
package net.minecraft.src.denoflionsx.denLib;

public enum Faces {
   
    BOTTOM(0), TOP(1), NORTH(2), SOUTH(3), EAST(4), WEST(5);
    
    private int value;
    
    private Faces(int value){
        this.value = value;
    }
    
    public int get(){
        return this.value;
    }
    
}
