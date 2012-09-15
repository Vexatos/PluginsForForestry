package denoflionsx.plugins;

public abstract class baseModule{

    public pluginBase parent;
    public boolean hasRegistered = false;
    public boolean hasLoaded = false;

    public baseModule(pluginBase parent) {
        this.parent = parent;
        this.parent.hasModules = true;
    }
    
    public void register(){
        if (hasRegistered){
            return;
        }
        if (!hasRegistered){
            hasRegistered = true;
        }
        this.defaults();
        this.parent.modules.add(this);
    }
    
    public void addDefault(String d){
        this.parent.config.addDefault(d);
    }
    
    public String getOption(String key){
        return this.parent.config.getOption(key);
    }
    
    public boolean getOptionBool(String key){
        return this.parent.getOptionBool(key);
    }
    
    public float getOptionFloat(String key){
        return this.parent.getOptionFloat(key);
    }
    
    public Integer getOptionInt(String key){
        return this.parent.getOptionInt(key);
    }
    
    protected abstract void init();
    
    protected abstract void defaults();
    
    protected abstract void recipes();
         
}
