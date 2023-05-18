module Demo
{

    sequence<string> StringSeq;

    interface Callback
    {
        void notifyCallback();

        void callbackTest();

        void printMsg(string s);
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