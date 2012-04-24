/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket.v2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * <b>SocketInputStream</b> reads the data sent by <b>SocketOutputStream</b>
 * through a network socket.
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class SocketInputStream
    extends InputStream
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private final DataInputStream input;

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public SocketInputStream(InputStream input)
    {
        this.input = new DataInputStream(input);
    }

    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Protected Methods                        \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    @Override
    public int read() throws IOException
    {
        return input.read();
    }
    
    public Boolean readBoolean() throws IOException
    {
        return input.readBoolean();
    }
    
    public Byte readByte() throws IOException
    {
        return input.readByte();
    }
    
    public byte[] readBytes() throws IOException
    {
        byte[] bytes = new byte[readInt()];
        
        input.read(bytes, 0, bytes.length);
        
        return bytes;
    }
    
    public Character readChar() throws IOException
    {
        return input.readChar();
    }
    
    public Double readDouble() throws IOException
    {
        return input.readDouble();
    }
    
    public Float readFloat() throws IOException
    {
        return input.readFloat();
    }
    
    public Integer readInt() throws IOException
    {
        return input.readInt();
    }
    
    public Long readLong() throws IOException
    {
        return input.readLong();
    }
    
    public Short readShort() throws IOException
    {
        return input.readShort();
    }
    
    public String readString() throws IOException
    {
        return new String(readBytes());
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
