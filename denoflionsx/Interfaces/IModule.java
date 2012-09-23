package denoflionsx.Interfaces;

public interface IModule {
    
    public void getParent();

    public void register();

    public void init();

    public void recipes();

    public String getName();

    public boolean isLoaded();
}
