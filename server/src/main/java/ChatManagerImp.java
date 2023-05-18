import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Current;

import Demo.CallbackPrx;

public class ChatManagerImp implements Demo.ChatManager {

    private List<String> messages;
    private List<ClientManager> clientManager;
    ChatManagerImp() {
        messages = new ArrayList<>();
        clientManager = new ArrayList<>();
    }

    @Override
    public void subscribe(CallbackPrx callback, String hostname, Current current) {
        System.out.println("subscribe");
        clientManager.add(new ClientManager(callback, hostname));
    }

    @Override
    public String[] getState(Current current) {
        System.out.println("GetState");
        String[] state = new String[messages.size()];

        for (int i = 0; i < state.length; i++) {
            state[i] = messages.get(i);
        }
        return state;
    }

    @Override
    public void sendMessage(String msg,String hostname ,Current current) {
        new Thread(() -> {
            CallbackPrx callbackPrx = getCallbackPrx(hostname);
            System.out.println("new Message: " + msg);
            long result = printString(msg, current);
            messages.add(result+"");
            callbackPrx.printFibo(msg+" = "+result);
            //callbackPrx.notifyCallback();
        }).start();
    }
    @Override
    public long printString(String s, com.zeroc.Ice.Current current)
    {
        String result=s.split(":")[0];
        StringBuilder stb= new StringBuilder();
        result+=": ";
        long x=0;
        try {
            x = Long.parseLong(s.replaceAll(" ", "").split(":")[1]);
            if(x>=0){
                for(int i=0;i<=x;i++){
                    stb.append(fibo(i,current)+" ");
                }
                result+=stb.toString();
                x= fibo(x, current);
            }else{
                result=s;
            }
        } catch (Exception e) {
            result=s;
        }
        System.out.println(result);
        return x;

    }

    //se garantiza resultados correctos hasta 45
    @Override
    public long fibo(long a, Current current){
        if(a==0){
            return a;
        }else if(a==1){
            return a;
        }else{
            return fibo(a-1, current)+fibo(a-2, current);
        }
    }

    public CallbackPrx getCallbackPrx(String hostname) {
        for (ClientManager client : clientManager) {
            if (client.getId().equals(hostname)) {
                System.out.println("se encontró el callback de "+hostname+"");
                return client.getCallbackPrx();
            }
        }
        System.out.println("no se encontró el callback de "+hostname+"");
        return null;
    }
 
}