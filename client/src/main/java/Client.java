import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            int i = 0;
            while(!x.equals("exit")){
                System.out.println(twoway.printString(x+": "+i));
                System.err.println(i);
                //x = sc.nextLine();
                i++;
            }

            sc.close();
        }
    }
}