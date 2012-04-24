/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket.v1;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>Socket</b> accepts or creates a network connection. This socket can then
 * read and write <b>Packet</b>s.
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class Socket
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/
    private static final int COUNT = 0;

    private static final int TRANSFER = 1;

    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private final java.net.Socket socket;
    
    private final Semaphore write = new Semaphore(1);
    
    private final Semaphore read = new Semaphore(1);
    
    private final BufferedOutputStream buffer;
    
    private final DataOutputStream output;
    
    private final DataInputStream input;
    
    private final long[] in = {0, 0};
    
    private final long[] out = {0, 0};

    private final byte[] heads = {
        (byte)255,
        (byte)254,
        (byte)253,
        (byte)252
    };

    private final byte[] tails = {
        (byte)155,
        (byte)154,
        (byte)153,
        (byte)152
    };
            
    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public long getInCount()
    {
        return in[COUNT];
    }

    public long getOutCount()
    {
        return out[COUNT];
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public Socket(java.net.Socket socket)
        throws IOException
    {
        this.socket = socket;
        
        buffer = new BufferedOutputStream(socket.getOutputStream());
        
        output = new DataOutputStream(buffer);
        
        input = new DataInputStream(socket.getInputStream());
    }
    
    public Socket(String host, int port)
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
    protected void writeHead()
        throws IOException
    {
        for (byte head : heads)
        {
            output.writeByte(head);
        }
    }
    
    protected void writeIndex()
        throws IOException
    {
        output.writeLong(out[COUNT]++);
    }
    
    protected void writePacket(Packet packet)
        throws IOException
    {
        packet.write(output);
    }

    protected void writeTail()
        throws IOException
    {
        for (byte tail : tails)
        {
            output.writeByte(tail);
        }
    }
    
    protected boolean readHead()
        throws IOException
    {
        boolean correct = true;
        
        boolean fixed;

        do
        {
            fixed = true;

            for (byte head : heads)
            {
                if (head != input.readByte())
                {
                    fixed = false;
                    
                    correct = false;

                    break;
                }
            }
        } while (!fixed);
        
        return correct;
    }
    
    protected boolean readIndex()
        throws IOException
    {
        return input.readLong() == in[COUNT]++;
    }
    
    protected boolean readTail()
        throws IOException
    {
        boolean correct = true;

        for (byte tail : tails)
        {
            if ((byte)input.read() != tail)
            {
                correct = false;
            }
        }

        return correct;
    }
    
    protected Packet readPacket()
        throws IOException
    {
        Packet packet = Packet.Instance(input.readInt());

        packet.read(socket.getInputStream());
        
        return packet;
    }
    
    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    public void write(Packet packet)
        throws InterruptedException, IOException
    {
        try
        {
            write.acquire();

            writeHead();
            
            writeIndex();

            writePacket(packet);
            
            writeTail();
            
            output.flush();
        }
        finally
        {
            write.release();
        }
    }
    
    public Packet read()
        throws InterruptedException, IOException
    {
        try
        {
            read.acquire();

            if (!readHead())
            {
                Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, "readHead() failed.");
            }

            if (!readIndex())
            {
                Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, "readIndex() failed.");
            }

            Packet packet = readPacket();

            if (!readTail())
            {
                Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, "readTail() failed.");
            }
            
            return packet;
        }
        finally
        {
            read.release();
        }
    }
    
    public void close() throws IOException
    {
        socket.close();
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
    
    
}
