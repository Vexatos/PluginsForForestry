package denoflionsx.plugins.BlueSilkWorm.Interfaces;

public interface ISilkWormLifeSpanCategory {
    
    public String getCategoryLabel();
    
    public int getCategoryMin();
    
    public int getCategoryMax();
    
    public boolean isInRange(int test);
    
}
