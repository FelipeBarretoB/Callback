import com.zeroc.Ice.Current;

public class PrinterI implements Demo.Printer
{
    public long printString(String s, com.zeroc.Ice.Current current)
    {
        String result=s.split(":")[0];
        result+=": ";
        long x=0;
        try {
            x = Long.parseLong(s.replaceAll(" ", "").split(":")[1]);
            if(x>=0){
                for(int i=0;i<=x;i++){
                    result+=fibo(i,current)+" ";
                }
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
    public long fibo(long a, Current current){
        if(a==0){
            return a;
        }else if(a==1){
            return a;
        }else{
            return fibo(a-1, current)+fibo(a-2, current);
        }
    }


}
