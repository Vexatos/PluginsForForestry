package net.minecraft.server.denoflionsx.plugins;

public abstract class baseModule
{
    public pluginBase parent;

    public baseModule(pluginBase var1)
    {
        this.parent = var1;
        this.parent.hasModules = true;
    }

    public void register()
    {
        this.defaults();
        this.parent.modules.add(this);
    }

    public void addDefault(String var1)
    {
        this.parent.config.addDefault(var1);
    }

    public String getOption(String var1)
    {
        return this.parent.config.getOption(var1);
    }

    public boolean getOptionBool(String var1)
    {
        return this.parent.getOptionBool(var1);
    }

    public Integer getOptionInt(String var1)
    {
        return this.parent.getOptionInt(var1);
    }

    protected abstract void init();

    protected abstract void defaults();

    protected abstract void recipes();
}
