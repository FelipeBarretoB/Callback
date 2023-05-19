module Demo
{

    sequence<string> StringSeq;

    interface Callback
    {
        void printMsg(string s);
    }

    interface ChatManager
    {

        void subscribe(Callback* callback, string hostname);

        StringSeq getState();

        void sendMessage(string msg, string hostname);
    }

    
}