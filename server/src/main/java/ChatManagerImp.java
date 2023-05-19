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
        if (!checkIfSubribed(hostname)) {
            clientManager.add(new ClientManager(callback, hostname));
        }
    }

    public boolean checkIfSubribed(String hostname) {
        for (ClientManager client : clientManager) {
            if (client.getId().equals(hostname)) {
                return true;
            }
        }
        return false;
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
    public void sendMessage(String msg, String hostname, Current current) {
        new Thread(() -> {
            CallbackPrx callbackPrx = getCallbackPrx(hostname);
            System.out.println("new Message: " + msg);
            comand(msg, callbackPrx);

        }).start();
    }

    public void comand(String msg, CallbackPrx callbackPrx) {
        if (msg.contains("BC")) {
            broadCast(msg.split("BC ")[1]);
        } else if (msg.contains("list clients")) {
            getClients(callbackPrx);
        } else if (msg.contains("to ") && msg.contains(":")) {
            String hostname = msg.split("to ")[1].split(":")[0];
            String message = msg.split("to ")[1].split(":")[1];
            if (!sendTo(hostname, message.trim())) {
                callbackPrx.printMsg("no se encontró el cliente " + hostname);
            } else {
                callbackPrx.printMsg("mensaje enviado a " + hostname);
            }
            sendTo(hostname, message.trim());
        } else if (msg.toLowerCase().equals("help")) {
            callbackPrx.printMsg("BC <msg> : envia un mensaje a todos los clientes conectados");
            callbackPrx.printMsg("list clients : lista los clientes conectados");
            callbackPrx.printMsg("to <hostname>:<msg> : envia un mensaje a un cliente especifico");
            callbackPrx.printMsg("fibonacci: <numero> : retorna la serie de fibonacci hasta <numero>");;
        }else if(msg.contains("fibonacci:")){
            long result = fibo(msg);
            callbackPrx.printMsg("resultado: "+result);
        } else {
            callbackPrx.printMsg("comando no reconocido");
        }

    }

    public void broadCast(String msg) {
        for (ClientManager client : clientManager) {
            client.getCallbackPrx().printMsg(msg);
        }
    }

    public void getClients(CallbackPrx callbackPrx) {
        String clientList = "";
        for (ClientManager client : clientManager) {
            clientList += client.getId() + " ";
        }
        callbackPrx.printMsg(clientList);
    }

    public boolean sendTo(String hostname, String msg) {
        boolean found = false;
        for (ClientManager client : clientManager) {
            if (client.getId().equals(hostname) && !found) {
                client.getCallbackPrx().printMsg(msg);
                found = true;
            }
        }
        return found;
    }

    
    public long fibo(String s) {
        String result = s.split(":")[0];
        StringBuilder stb = new StringBuilder();
        result += ": ";
        long count = 0;
        count = Long.parseLong(s.replaceAll(" ", "").split(":")[1]);
        int num1 = 0, num2 = 1;
        for (int i = 1; i <= count; ++i) {

            int sumOfPrevTwo = num1 + num2;
            num1 = num2;
            num2 = sumOfPrevTwo;
            stb.append(num1 + " ");
        }
        result+=stb.toString();
        System.out.println(result);
        return num1;

    }



    public CallbackPrx getCallbackPrx(String hostname) {
        for (ClientManager client : clientManager) {
            if (client.getId().equals(hostname)) {
                System.out.println("se encontró el callback de " + hostname + "");
                return client.getCallbackPrx();
            }
        }
        System.out.println("no se encontró el callback de " + hostname + "");
        return null;
    }

}