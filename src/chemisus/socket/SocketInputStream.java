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
    /**
     * Get the amount of bytes available in the stream for reading.
     * 
     * @return <b>int</b> The amount of bytes available in the stream for
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
     * @return <b>boolean</b>
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
     * Reads a <b>byte</b> from the stream.
     * 
     * @return <b>int</b> the value of the byte that was read from the stream.
     * @throws IOException 
     */
    @Override
    public int read() throws IOException
    {
        return input.read();
    }

    /**
     * Reads a <b>Boolean</b> from the stream.
     * 
     * @return <b>Boolean</b> the value of the Boolean read from the stream.
     * @throws IOException 
     */
    public Boolean readBoolean() throws IOException
    {
        return input.readBoolean();
    }
    
    /**
     * Reads a <b>Byte</b> from the stream.
     * 
     * @return <b>Byte</b> the value of the Byte read from the stream.
     * @throws IOException 
     */
    public Byte readByte() throws IOException
    {
        return input.readByte();
    }
    
    /**
     * Reads a <b>byte[]</b> from the stream.
     * 
     * Since <b>SocketOutputStream</b> will send the size of the byte array 
     * before the bytes themselves, this function will automatically read in
     * that size <i>n</i> first, then read in the following <i>n</i> bytes.
     * 
     * @return <b>byte[]</b> the value of the byte[] read from the stream.
     * @throws IOException 
     */
    public byte[] readBytes() throws IOException
    {
        byte[] bytes = new byte[readInt()];
        
        input.read(bytes, 0, bytes.length);
        
        return bytes;
    }
    
    /**
     * Reads a <b>Character</b> from the stream.
     * 
     * @return <b>Character</b> the value of the Character read from the stream.
     * @throws IOException 
     */
    public Character readChar() throws IOException
    {
        return input.readChar();
    }
    
    /**
     * Reads a <b>Double</b> from the stream.
     * 
     * @return <b>Double</b> the value of the Double read from the stream.
     * @throws IOException 
     */
    public Double readDouble() throws IOException
    {
        return input.readDouble();
    }
    
    /**
     * Reads a <b>Float</b> from the stream.
     * 
     * @return <b>Float</b> the value of the Float read from the stream.
     * @throws IOException 
     */
    public Float readFloat() throws IOException
    {
        return input.readFloat();
    }
    
    /**
     * Reads a <b>Integer</b> from the stream.
     * 
     * @return <b>Integer</b> the value of the Integer read from the stream.
     * @throws IOException 
     */
    public Integer readInt() throws IOException
    {
        return input.readInt();
    }
    
    /**
     * Reads a <b>Long</b> from the stream.
     * 
     * @return <b>Long</b> the value of the Long read from the stream.
     * @throws IOException 
     */
    public Long readLong() throws IOException
    {
        return input.readLong();
    }
    
    /**
     * Reads a <b>Short</b> from the stream.
     * 
     * @return <b>Short</b> the value of the Short read from the stream.
     * @throws IOException 
     */
    public Short readShort() throws IOException
    {
        return input.readShort();
    }
    
    /**
     * Reads a <b>String</b> from the stream.
     * 
     * This function returns a new string by calling
     * <code>new String(readBytes())</code>. Since <b>SocketOutputStream</b>
     * will send the size of the string before the string itself, this function
     * will automatically read in that size first.
     * 
     * @return <b>String</b> the value of the String read from the stream.
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
     * This function saves a spot in the stream, and when <code>reset()</code>
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
     * @return <b>long</b> The actual number of bytes skipped.
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
     * @return <b>long</b> The actual number of bytes skipped.
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
