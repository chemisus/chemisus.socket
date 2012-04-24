/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket.v2;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>Session</b>
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class Session
    extends Socket
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
    private volatile boolean receiving = false;
    
    private volatile Thread thread;
    
    private final ConcurrentLinkedQueue<Callback> callbacks = new ConcurrentLinkedQueue<Callback>();

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public boolean isReceiving()
    {
        return receiving;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public Session(java.net.Socket socket)
        throws IOException
    {
        super(socket);
    }
    
    public Session(String host, int port)
        throws UnknownHostException, IOException, ConnectException
    {
        this(new java.net.Socket(host, port));
    }

    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Protected Methods                        \*/
    /*\**********************************************************************\*/
    protected void receive()
    {
        for (Callback callback : callbacks)
        {
            callback.onStarted(this);
        }
        
        while (isReceiving())
        {
            try
            {
                Packet packet = read();
                
                for (Callback callback : callbacks)
                {
                    callback.onReceived(this, packet);
                }
            }
            catch (SocketException ex)
            {
                receiving = false;
            }
            catch (IOException ex)
            {
                receiving = false;
            }
        }
                
        for (Callback callback : callbacks)
        {
            callback.onStopped(this);
        }
    }

    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    public boolean addCallback(Callback callback)
    {
        return callbacks.add(callback);
    }
    
    public boolean removeCallback(Callback callback, Class ... packets)
    {
        return callbacks.remove(callback);
    }
    
    public void start()
    {
        receiving = true;
        
        thread = new Thread("session-"+(Index++)) {
            @Override
            public void run() {
                receive();
            }
        };
        
        thread.start();
    }
    
    @Override
    public void close() throws IOException
    {
        receiving = false;
        
        super.close();
        
        try
        {
            thread.join();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
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
        public void onReceived(Session session, Packet packet);
        
        public void onStarted(Session session);
        
        public void onStopped(Session session);
    }
}
