package denoflionsx.PluginsforForestry.API.Events;

public class PfFEvent {

    // obj will be either null or something relevant to the event.
    // So an ItemStack, LiquidStack, or some other reference.
    
    // See interfaces for the managers to see what they send.
    
    private String origin;
    private String msg;
    private Object obj;

    public PfFEvent(String origin, String msg, Object obj) {
        this.origin = origin;
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }

    public String getOrigin() {
        return origin;
    }

}
