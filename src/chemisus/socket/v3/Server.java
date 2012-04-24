/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>Server</b> opens a port for incoming connection requests.
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class Server
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/
 

    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/
    private static volatile int Index = 0;

    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private final ServerSocket server;
    
    private Thread thread;

    private volatile boolean accepting = false;
    
    private ConcurrentLinkedQueue<Callback> callbacks = new ConcurrentLinkedQueue<Callback>();

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public boolean isAccepting()
    {
        return accepting;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public Server(int port) throws IOException
    {
        server = new ServerSocket(port);
    }

    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/
    private void accept() 
    {
        for (Callback callback : callbacks)
        {
            callback.onStarted(this);
        }
        
        while (accepting)
        {
            try
            {
                java.net.Socket socket = server.accept();
                
                for (Callback callback : callbacks)
                {
                    callback.onRequest(this, socket);
                }
            }
            catch (SocketException ex)
            {
                if (isAccepting())
                {
                    Logger.getLogger(Socket.class.getName()).log(
                        Level.SEVERE, null, ex
                    );
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(Socket.class.getName()).log(
                    Level.SEVERE, null, ex
                );
            }
        }
        
        for (Callback callback : callbacks)
        {
            callback.onStopped(this);
        }
    }

    /*\**********************************************************************\*/
    /*\                             Protected Methods                        \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    public boolean addCallback(Callback callback)
    {
        return callbacks.add(callback);
    }
    
    public boolean removeCallback(Callback callback)
    {
        return callbacks.remove(callback);
    }
    
    public void start()
    {
        accepting = true;
        
        thread = new Thread("server-"+(Index++)) {
            @Override
            public void run() {
                accept();
            }
        };

        thread.start();
    }
    
    public void close() throws IOException
    {
        accepting = false;
        
        server.close();
        
        try
        {
            thread.join();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Socket.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }

    /*\**********************************************************************\*/
    /*\                             Event Triggers                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Event Handlers                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Classes                                  \*/
    /*\**********************************************************************\*/
    public interface Callback
    {
        public void onRequest(Server server, java.net.Socket socket);
        
        public void onStopped(Server server);
        
        public void onStarted(Server server);
    }
    
}
