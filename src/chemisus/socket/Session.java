/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket;

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
    private static volatile int Count = 0;

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
    /**
     * Gets if the session is currently receiving data or not.
     * 
     * @return <b>boolean</b>
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>The session is receiving data.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>The session is not receiving data.</td>
     *  </tr>
     * </table>
     */
    public boolean isReceiving()
    {
        return receiving;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    /**
     * Create a new session with an established connection.
     */
    public Session(java.net.Socket socket)
        throws IOException
    {
        super(socket);
    }

    /**
     * Create a new session by connecting to a server.
     * 
     * @param host The host name of the server to connect to.
     * @param port The port to connect to the server on.
     * @throws UnknownHostException
     * @throws IOException
     * @throws ConnectException 
     */
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
    /**
     * Receive packets from the server until <code>isReceiving()</code> returns
     * false.
     * 
     * This function will call <code>onStarted(this)</code> on each object that
     * has added itself as a callback before the session starts to receiving
     * packets.
     * 
     * This function will call <code>onStopped(this)</code> on each object that
     * has added itself as a callback after the session stops receiving 
     * packets.
     */
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
     * Start receiving packets.
     */
    public void start()
    {
        receiving = true;
        
        thread = new Thread("session-"+(Count++)) {
            @Override
            public void run() {
                receive();
            }
        };
        
        thread.start();
    }

    /**
     * Stop receiving packets.
     */
    public void stop()
    {
        receiving = false;
        
        try
        {
            thread.join();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Session.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }

    /**
     * Stop receiving packets and close the socket.
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException
    {
        stop();
        
        super.close();
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
