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
     * @return <b>boolean</b>
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
     * Accept connection requests until <code>isAccepting()</code> returns
     * false.
     * 
     * This function will call <code>onStarted(this)</code> on each object that
     * has added itself as a callback before the server starts to accept
     * connection requests.
     * 
     * This function will call <code>onStopped(this)</code> on each object that
     * has added itself as a callback after the server stops accepting
     * connection requests.
     */
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
    /**
     * Add an object to the callback list.
     * 
     * @param callback The object that will be added to the callbacks list.
     * @return <b>boolean</b>
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
     * @return <b>boolean</b>
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
     * Stop accepting connections and close the socket.
     */
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
