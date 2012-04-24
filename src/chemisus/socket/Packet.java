/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>Packet</b> can read and write data between two network sockets using a
 * <b>Socket</b> object.
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public abstract class Packet
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/
    private static final ConcurrentHashMap<Integer, Class> Packets = new ConcurrentHashMap<Integer, Class>();

    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/
    public static boolean Register(Class c)
    {
        try
        {
            int index = ((Packet)c.newInstance()).getPacketIndex();

            if (Packets.containsKey(index) || Packets.containsValue(c))
            {
                return false;
            }

            Packets.put(index, c);

            return true;
        }
        catch (InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(Packet.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
        
        return false;
    }
    
    public static Packet Instance(InputStream input) throws IOException
    {
        return Instance(new DataInputStream(input).readInt());
    }
    
    public static Packet Instance(int index)
    {
        try
        {
            return (Packet)Packets.get(index).newInstance();
        }
        catch (NullPointerException ex)
        {
            Logger.getLogger(Packet.class.getName()).log(
                Level.SEVERE, "The index was: " + index, ex
            );
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(Packet.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(Packet.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
        
        return null;
    }

    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private SocketOutputStream output;
    
    private SocketInputStream input;

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public abstract int getPacketIndex();
    
    private SocketOutputStream getOutput()
    {
        return output;
    }
    
    private SocketInputStream getInput()
    {
        return input;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Protected Methods                        \*/
    /*\**********************************************************************\*/
    protected abstract void write() throws IOException;

    protected abstract void read() throws IOException;
    
    protected void writeBoolean(Boolean value) throws IOException
    {
        getOutput().writeBoolean(value);
    }
    
    protected void writeByte(Byte value) throws IOException
    {
        getOutput().writeByte(value);
    }

    protected void writeBytes(byte[] value) throws IOException
    {
        getOutput().writeBytes(value);
    }

    protected void writeChar(Character value) throws IOException
    {
        getOutput().writeChar(value);
    }

    protected void writeDouble(Double value) throws IOException
    {
        getOutput().writeDouble(value);
    }

    protected void writeFloat(Float value) throws IOException
    {
        getOutput().writeFloat(value);
    }

    protected void writeInt(Integer value) throws IOException
    {
        getOutput().writeInt(value);
    }

    protected void writeLong(Long value) throws IOException
    {
        getOutput().writeLong(value);
    }

    protected void writeShort(Short value) throws IOException
    {
        getOutput().writeShort(value);
    }

    protected void writeString(String value) throws IOException
    {
        getOutput().writeString(value);
    }

    protected Boolean readBoolean() throws IOException
    {
        return getInput().readBoolean();
    }
    
    protected Byte readByte() throws IOException
    {
        return getInput().readByte();
    }

    protected byte[] readBytes() throws IOException
    {
        return getInput().readBytes();
    }

    protected Character readChar() throws IOException
    {
        return getInput().readChar();
    }

    protected Double readDouble() throws IOException
    {
        return getInput().readDouble();
    }

    protected Float readFloat() throws IOException
    {
        return getInput().readFloat();
    }

    protected Integer readInt() throws IOException
    {
        return getInput().readInt();
    }

    protected Long readLong() throws IOException
    {
        return getInput().readLong();
    }

    protected Short readShort() throws IOException
    {
        return getInput().readShort();
    }

    protected String readString() throws IOException
    {
        return getInput().readString();
    }
    
    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    public final void write(SocketOutputStream output) throws IOException
    {
        this.output = output;
        
        write();
    }
    
    public final void read(SocketInputStream input) throws IOException
    {
        this.input = input;
        
        read();
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
