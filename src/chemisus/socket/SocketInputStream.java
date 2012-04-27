/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * {@link SocketInputStream} reads the data sent by {@link SocketOutputStream}
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
    /**
     * Get the amount of bytes available in the stream for reading.
     * 
     * @return {@link int} The amount of bytes available in the stream for
     * reading.
     * @throws IOException 
     */
    @Override
    public int available() throws IOException
    {
        return input.available();
    }

    /**
     * Gets if marking is supported by the stream.
     * 
     * @return {@link boolean}
     * <table>
     *  <tr>
     *      <td><i>true</i></td>
     *      <td>Marking is supported by the stream.</td>
     *  </tr>
     *  <tr>
     *      <td><i>false</i></td>
     *      <td>Marking is not supported by the stream.</td>
     *  </tr>
     * </table>
     */
    @Override
    public boolean markSupported()
    {
        return input.markSupported();
    }

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
    /**
     * Reads a {@link byte} from the stream.
     * 
     * @return {@link int} the value of the byte that was read from the stream.
     * @throws IOException 
     */
    @Override
    public int read() throws IOException
    {
        return input.read();
    }

    /**
     * Reads a {@link Boolean} from the stream.
     * 
     * @return {@link Boolean} the value of the Boolean read from the stream.
     * @throws IOException 
     */
    public Boolean readBoolean() throws IOException
    {
        return input.readBoolean();
    }
    
    /**
     * Reads a {@link Byte} from the stream.
     * 
     * @return {@link Byte} the value of the Byte read from the stream.
     * @throws IOException 
     */
    public Byte readByte() throws IOException
    {
        return input.readByte();
    }
    
    /**
     * Reads a {@link byte[]} from the stream.
     * 
     * Since {@link SocketOutputStream} will send the size of the byte array 
     * before the bytes themselves, this function will automatically read in
     * that size <i>n</i> first, then read in the following <i>n</i> bytes.
     * 
     * @return {@link byte[]} the value of the byte[] read from the stream.
     * @throws IOException 
     */
    public byte[] readBytes() throws IOException
    {
        byte[] bytes = new byte[readInt()];

        input.readFully(bytes, 0, bytes.length);
        
        return bytes;
    }
    
    /**
     * Reads a {@link Character} from the stream.
     * 
     * @return {@link Character} the value of the Character read from the stream.
     * @throws IOException 
     */
    public Character readChar() throws IOException
    {
        return input.readChar();
    }
    
    /**
     * Reads a {@link Double} from the stream.
     * 
     * @return {@link Double} the value of the Double read from the stream.
     * @throws IOException 
     */
    public Double readDouble() throws IOException
    {
        return input.readDouble();
    }
    
    /**
     * Reads a {@link Float} from the stream.
     * 
     * @return {@link Float} the value of the Float read from the stream.
     * @throws IOException 
     */
    public Float readFloat() throws IOException
    {
        return input.readFloat();
    }
    
    /**
     * Reads a {@link Integer} from the stream.
     * 
     * @return {@link Integer} the value of the Integer read from the stream.
     * @throws IOException 
     */
    public Integer readInt() throws IOException
    {
        return input.readInt();
    }
    
    /**
     * Reads a {@link Long} from the stream.
     * 
     * @return {@link Long} the value of the Long read from the stream.
     * @throws IOException 
     */
    public Long readLong() throws IOException
    {
        return input.readLong();
    }
    
    /**
     * Reads a {@link Short} from the stream.
     * 
     * @return {@link Short} the value of the Short read from the stream.
     * @throws IOException 
     */
    public Short readShort() throws IOException
    {
        return input.readShort();
    }
    
    /**
     * Reads a {@link String} from the stream.
     * 
     * This function returns a new string by calling
     * {@link new String(readBytes())}. Since {@link SocketOutputStream}
     * will send the size of the string before the string itself, this function
     * will automatically read in that size first.
     * 
     * @return {@link String} the value of the String read from the stream.
     * @throws IOException 
     */
    public String readString() throws IOException
    {
        return new String(readBytes());
    }
    
    /**
     * Closes the stream.
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException
    {
        input.close();
    }

    /**
     * Marks the current position in the stream until the <i>limit<i> number of
     * bytes have been read from the stream.
     * 
     * This function saves a spot in the stream, and when {@link reset()}
     * is called on the stream, it will go back to the position.
     * 
     * If however, when <i>limit<i> number of bytes have been read from the
     * stream, the mark will become invalid.
     * 
     * @param limit The number of bytes to be read before the mark becomes
     * invalid.
     */
    @Override
    public void mark(int limit)
    {
        input.mark(limit);
    }
    
    /**
     * Skips <i>n</i> bytes in the stream.
     * 
     * @param n The number of bytes to skip in the stream.
     * @return {@link long} The actual number of bytes skipped.
     * @throws IOException 
     */
    @Override
    public long skip(long n) throws IOException
    {
        return input.skip(n);
    }

    /**
     * Skips <i>n</i> bytes in the stream.
     * 
     * @param n The number of bytes to skip in the stream.
     * @return {@link long} The actual number of bytes skipped.
     * @throws IOException 
     */
    public int skipBytes(int n) throws IOException
    {
        return input.skipBytes(n);
    }

    /**
     * Sets the position in the stream to the index of the last time a mark was
     * called, provided that it is still valid.
     * 
     * @throws IOException 
     */
    @Override
    public void reset() throws IOException
    {
        input.reset();
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
