module Demo
{

    sequence<string> StringSeq;

    interface Printer
    {
        long printString(string s);
        long fibo(long a);
    }

    interface Callback
    {
        void notifyCallback();

        void callbackTest();

        void printFibo(string s);
    }

    interface ChatManager
    {

        void subscribe(Callback* callback, string hostname);

        StringSeq getState();

        void sendMessage(string msg, string hostname);

        long printString(string s);
        
        long fibo(long a);
    }

    
}