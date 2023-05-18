import java.net.Inet4Address;
import java.util.Scanner;

//Magia negra del Ice
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

//Lo que esta en el printer.ice que no se deberia llamar printer.ice
import Demo.CallbackPrx;
import Demo.ChatManagerPrx;


public class Client
{
    public static void main(String[] args)
    {

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client")) {
            
            Demo.ChatManagerPrx chatManagerPrx = Demo.ChatManagerPrx
                    .checkedCast(communicator.propertyToProxy("ChatManager.Proxy"));
          
            try {
                
                CallBackImp callbackImp = new CallBackImp(chatManagerPrx);
                ObjectAdapter adapter = communicator.createObjectAdapter("Callback");
                ObjectPrx objectPrx = adapter.add(callbackImp, Util.stringToIdentity("Callback"));
                adapter.activate();
                String hostname = Inet4Address.getLocalHost().getHostName();
                CallbackPrx prx = CallbackPrx.uncheckedCast(objectPrx);
                chatManagerPrx.subscribe(prx, hostname);

                Scanner sc = new Scanner(System.in);
                String x = sc.nextLine();
                while(!x.equals("exit") ){
                    chatManagerPrx.sendMessage(x, hostname);
                    x = sc.nextLine();
                }
                sc.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}