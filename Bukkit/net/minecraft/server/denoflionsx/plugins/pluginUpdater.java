package net.minecraft.server.denoflionsx.plugins;

public class pluginUpdater extends pluginBase
{
    public pluginUpdater()
    {
        this.name = "pluginUpdater";
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.loaded = this.init();
        }
    }

    protected void defaults() {}

    protected boolean init()
    {
        return true;
    }

    protected void recipes() {}
}
