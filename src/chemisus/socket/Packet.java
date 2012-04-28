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
 * The {@link Packet} class will be used by the {@link Socket} or
 * {@link Session} class to read and write data across a connection, as well as
 * making sure that the data is organized and easily used by other classes.
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
    /**
     * Register a packet class globally.
     * 
     * Use this if you know that each socket is going to have access to this
     * packet type.
     * 
     * @param c The class of the packet to register.
     * @return {@link boolean}
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>The class was successfully added.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>The class was not successfully added.</td>
     *  </tr>
     * </table>
     */
    public static boolean Register(Class ... classes)
    {
        try
        {
            for (Class c : classes)
            {
                int index = ((Packet)c.newInstance()).getPacketIndex();

                if (Packets.containsKey(index) || Packets.containsValue(c))
                {
                    return false;
                }

                Packets.put(index, c);
            }

            return true;
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

        return false;
    }
    
    /**
     * Creates an instance of a packet based on the index that is read from the
     * stream.
     * 
     * @param input The stream to read the index from.
     * @return {@link Packet} the packet that was created.
     */
    public static Packet Instance(InputStream input) throws IOException
    {
        return Instance(new DataInputStream(input).readInt());
    }
    
    /**
     * Creates an instance of a packet based on the index supplied.
     * 
     * @param index The index representing which packet to create.
     * @return {@link Packet} the packet that was created.
     */
    public static Packet Instance(int index)
    {
        try
        {
            if (Packets.containsKey(index))
            {
                return (Packet)Packets.get(index).newInstance();
            }
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
    /**
     * The output stream.
     */
    private SocketOutputStream output;

    /**
     * The input stream.
     */
    private SocketInputStream input;

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    /**
     * Should return a unique index of the packet. No other packet classes
     * should have this index if they are to be registered globally.
     * 
     * @return {@link int} the unique index of the packet class.
     */
    public abstract int getPacketIndex();

    /**
     * Get the output stream.
     * 
     * @return {@link SocketOutputStream} the output stream.
     */
    private SocketOutputStream getOutput()
    {
        return output;
    }
    
    /**
     * Get the input stream.
     * 
     * @return {@link SocketInputStream} the input stream.
     */
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
    /**
     * Override this to write data.
     * 
     * @throws IOException 
     */
    protected abstract void write() throws IOException;

    /**
     * Override this to read data.
     * 
     * @throws IOException 
     */
    protected abstract void read() throws IOException;

    /**
     * Write a Boolean value to the stream.
     * 
     * @param value The Boolean value to write.
     * @throws IOException 
     */
    protected void writeBoolean(Boolean value) throws IOException
    {
        getOutput().writeBoolean(value);
    }
    
    /**
     * Write a Byte value to the stream.
     * 
     * @param value The Byte value to write.
     * @throws IOException 
     */
    protected void writeByte(Byte value) throws IOException
    {
        getOutput().writeByte(value);
    }

    /**
     * Write a byte[] value to the stream.
     * 
     * @param value The byte[] value to write.
     * @throws IOException 
     */
    protected void writeBytes(byte[] value) throws IOException
    {
        getOutput().writeBytes(value);
    }

    /**
     * Write a Character value to the stream.
     * 
     * @param value The Character value to write.
     * @throws IOException 
     */
    protected void writeChar(Character value) throws IOException
    {
        getOutput().writeChar(value);
    }

    /**
     * Write a Double value to the stream.
     * 
     * @param value The Double value to write.
     * @throws IOException 
     */
    protected void writeDouble(Double value) throws IOException
    {
        getOutput().writeDouble(value);
    }

    /**
     * Write a Float value to the stream.
     * 
     * @param value The Float value to write.
     * @throws IOException 
     */
    protected void writeFloat(Float value) throws IOException
    {
        getOutput().writeFloat(value);
    }

    /**
     * Write a Integer value to the stream.
     * 
     * @param value The Integer value to write.
     * @throws IOException 
     */
    protected void writeInt(Integer value) throws IOException
    {
        getOutput().writeInt(value);
    }

    /**
     * Write a Long value to the stream.
     * 
     * @param value The Long value to write.
     * @throws IOException 
     */
    protected void writeLong(Long value) throws IOException
    {
        getOutput().writeLong(value);
    }

    /**
     * Write a Short value to the stream.
     * 
     * @param value The Short value to write.
     * @throws IOException 
     */
    protected void writeShort(Short value) throws IOException
    {
        getOutput().writeShort(value);
    }

    /**
     * Write a String value to the stream.
     * 
     * @param value The String value to write.
     * @throws IOException 
     */
    protected void writeString(String value) throws IOException
    {
        getOutput().writeString(value);
    }

    /**
     * Read a Boolean value from the stream.
     * 
     * @return The Boolean value read.
     * @throws IOException 
     */
    protected Boolean readBoolean() throws IOException
    {
        return getInput().readBoolean();
    }
    
    /**
     * Read a Byte value from the stream.
     * 
     * @return The Byte value read.
     * @throws IOException 
     */
    protected Byte readByte() throws IOException
    {
        return getInput().readByte();
    }

    /**
     * Read a byte[] value from the stream.
     * 
     * @return The byte[] value read.
     * @throws IOException 
     */
    protected byte[] readBytes() throws IOException
    {
        return getInput().readBytes();
    }

    /**
     * Read a Character value from the stream.
     * 
     * @return The Character value read.
     * @throws IOException 
     */
    protected Character readChar() throws IOException
    {
        return getInput().readChar();
    }

    /**
     * Read a Double value from the stream.
     * 
     * @return The Double value read.
     * @throws IOException 
     */
    protected Double readDouble() throws IOException
    {
        return getInput().readDouble();
    }

    /**
     * Read a Float value from the stream.
     * 
     * @return The Float value read.
     * @throws IOException 
     */
    protected Float readFloat() throws IOException
    {
        return getInput().readFloat();
    }

    /**
     * Read a Integer value from the stream.
     * 
     * @return The Integer value read.
     * @throws IOException 
     */
    protected Integer readInt() throws IOException
    {
        return getInput().readInt();
    }

    /**
     * Read a Long value from the stream.
     * 
     * @return The Long value read.
     * @throws IOException 
     */
    protected Long readLong() throws IOException
    {
        return getInput().readLong();
    }

    /**
     * Read a Short value from the stream.
     * 
     * @return The Short value read.
     * @throws IOException 
     */
    protected Short readShort() throws IOException
    {
        return getInput().readShort();
    }

    /**
     * Read a String value from the stream.
     * 
     * @return The String value read.
     * @throws IOException 
     */
    protected String readString() throws IOException
    {
        return getInput().readString();
    }
    
    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    /**
     * Write the packet data to the stream.
     * 
     * @param output The output stream to write the packet data to
     * @throws IOException 
     */
    public final void write(SocketOutputStream output) throws IOException
    {
        this.output = output;
        
        write();
    }
    
    /**
     * Read packet data from the stream.
     * 
     * @param input The packet to read data from.
     * @throws IOException 
     */
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
