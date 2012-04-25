/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * {@link Server} opens a port for incoming connection requests.
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
    /**
     * The running count of server threads running. Each thread will be named
     * `server-N` where N is the value of Count.
     */
    private static volatile int Count = 0;

    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    /**
     * The server that will be listening for connections.
     */
    private final ServerSocket server;

    /**
     * The thread that will be used to accept the connections.
     */
    private Thread thread;

    /**
     * The value that will determine if the accepting thread should continue
     * or not.
     */
    private volatile boolean accepting = false;

    /**
     * The objects that are listening to this server.
     */
    private ConcurrentLinkedQueue<Callback> callbacks = new ConcurrentLinkedQueue<Callback>();

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    /**
     * Gets if this server is currently accepting connection requests or not.
     * 
     * @return {@link boolean}
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>The server is accepting connection requests.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>The server is not accepting connection requests.</td>
     *  </tr>
     * </table>
     */
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
    /**
     * Accept connection requests until {@link isAccepting()} returns
     * false.
     * 
     * <p>This function will trigger {@link OnStart()} before it starts accepting
     * connections requests.</p>
     * 
     * <p>This function will trigger {@link OnStop()} after it stops accepting
     * connection requests.</p>
     * 
     * <p>This function will trigger {@link OnRequest()} when a connection has
     * been requested.</p>
     */
    private void accept() 
    {
        OnStart();
        
        while (accepting)
        {
            try
            {
                OnRequest(server.accept());
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

        OnStop();
    }

    /*\**********************************************************************\*/
    /*\                             Protected Methods                        \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    /**
     * Add an object to the callback list.
     * 
     * @param callback The object that will be added to the callbacks list.
     * @return {@link boolean}
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>The object has been successfully added as a callback.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>The object was not successfully added as a callback.</td>
     *  </tr>
     * </table>
     */
    public boolean addCallback(Callback callback)
    {
        return callbacks.add(callback);
    }

    /**
     * Remove an object from the callback list.
     * 
     * @param callback The object that will removed from the callbacks list.
     * @return {@link boolean}
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>The object has been successfully removed as a callback.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>The object was not successfully removed as a callback.</td>
     *  </tr>
     * </table>
     */
    public boolean removeCallback(Callback callback)
    {
        return callbacks.remove(callback);
    }
    
    /**
     * Start accepting connections.
     */
    public void start()
    {
        accepting = true;
        
        thread = new Thread("server-"+(Count++)) {
            @Override
            public void run() {
                accept();
            }
        };

        thread.start();
    }

    /**
     * Stop accepting connections.
     */
    public void stop()
    {
        accepting = false;

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

    /**
     * Stop accepting connections and close the socket.
     */
    public void close() throws IOException
    {
        stop();
        
        server.close();
    }

    /*\**********************************************************************\*/
    /*\                             Event Triggers                           \*/
    /*\**********************************************************************\*/
    private void OnStart()
    {
        for (Callback callback : callbacks)
        {
            callback.onStarted(this);
        }
    }
    
    private void OnStop()
    {
        for (Callback callback : callbacks)
        {
            callback.onStopped(this);
        }
    }
    
    private void OnRequest(java.net.Socket socket)
    {
        for (Callback callback : callbacks)
        {
            callback.onRequest(this, socket);
        }
    }

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
