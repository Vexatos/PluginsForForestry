package denoflionsx.PluginsforForestry.Enums;

public enum EnumBlockSides {
    
    TOP(1),
    BOTTOM(0),
    FRONT(2),
    BACK(3),
    LEFT(4),
    RIGHT(5);
    
    private int index;
    private EnumBlockSides(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}