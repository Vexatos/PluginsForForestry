package net.minecraft.src.denoflionsx.plugins;

public class pluginUpdater extends pluginBase{

    public pluginUpdater() {
        this.name = "pluginUpdater";
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded){
            this.loaded = init();
        }
    }

    
    
    @Override
    protected void defaults() {
        
    }

    @Override
    protected boolean init() {
        
        return true;
    }

    @Override
    protected void recipes() {
        
    }
    
    
    
}
