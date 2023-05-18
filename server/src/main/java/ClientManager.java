import Demo.Callback;
import Demo.CallbackPrx;

public class ClientManager {
    private CallbackPrx callbackPrx;
    private String id;

    public ClientManager(CallbackPrx callbackPrx, String id) {
        this.callbackPrx = callbackPrx;
        this.id = id;
    }

    public CallbackPrx getCallbackPrx() {
        return callbackPrx;
    }

    public void setCallbackPrx(CallbackPrx callbackPrx) {
        this.callbackPrx = callbackPrx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
