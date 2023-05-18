import java.util.Arrays;

import com.zeroc.Ice.Current;

import Demo.ChatManagerPrx;

public class CallBackImp implements Demo.Callback {

    private ChatManagerPrx server;

    CallBackImp(ChatManagerPrx m) {
        server = m;
    }

    @Override
    public void callbackTest(Current current){
        System.out.println("callack");
    }

    @Override
    public void printFibo(String result, Current current){
        System.out.println(result);
    }

    @Override
    public void notifyCallback(Current current) {
        String state[] = server.getState();

        System.out.println("Callback exec: " + Arrays.toString(state));
    }

}