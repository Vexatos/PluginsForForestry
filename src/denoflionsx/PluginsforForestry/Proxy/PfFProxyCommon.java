package denoflionsx.PluginsforForestry.Proxy;

public class PfFProxyCommon extends PfFProxy {
    
    @Override
    public void sendMessageToPlayer(String msg) {
        this.print(msg);
    }
}
