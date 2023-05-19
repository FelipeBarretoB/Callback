import java.util.Arrays;

import com.zeroc.Ice.Current;

import Demo.ChatManagerPrx;

public class CallBackImp implements Demo.Callback {

    private ChatManagerPrx server;

    CallBackImp(ChatManagerPrx m) {
        server = m;
    }

    @Override
    public void printMsg(String result, Current current){
        System.out.println(result);
    }

}