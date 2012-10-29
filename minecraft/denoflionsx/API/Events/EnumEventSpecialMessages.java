package denoflionsx.API.Events;

public enum EnumEventSpecialMessages {
    
    BUCKET("Its a bucket!"),
    BARREL("Its a barrel!"),
    CAST("Its a cast!");
    
    private String msg;
    private EnumEventSpecialMessages(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    
}
