/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket;
 */
package chemisus.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * {@link SocketOutputStream} will handle writing primitive data types or strings
 * through a {@link Socket} which will then be read by {@link SocketInputStream}
 * on the other side.
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class SocketOutputStream
    extends OutputStream
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
    private final DataOutputStream output;
    
    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public int size()
    {
        return output.size();
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public SocketOutputStream(OutputStream output)
    {
        this.output = new DataOutputStream(output);
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
     * Writes a byte to the stream.
     * 
     * Note that the parameter is an {@link int} (bigger size than a byte). This
     * function will write a {@link byte} to the stream.
     * 
     * @param value The value of the byte to write to the stream.
     * @throws IOException 
     */
    @Override
    public void write(int value) throws IOException
    {
        output.write(value);
    }

    /**
     * Writes several bytes to the stream.
     * 
     * This function calls {@link writeInt(value.length)} before writing
     * the bytes to the stream. This extra {@link int} is automatically read by
     * the {@link SocketInputStream.readInt()} on the other end of the
     * stream.
     * 
     * @param value The array of bytes to write from.
     * @param offset The index of the byte in the array to start from.
     * @param length The number of bytes to write to the stream.
     * @throws IOException 
     */
    @Override
    public void write(byte[] value, int offset, int length) throws IOException
    {
        writeInt(value.length);
        
        output.write(value, 0, value.length);
    }

    /**
     * Writes an array of bytes to the stream.
     * 
     * This function calls {@link write(value, 0, value.length)};
     * 
     * @param value The array of bytes to write to the stream.
     * @throws IOException 
     */
    @Override
    public void write(byte[] value) throws IOException
    {
        write(value, 0, value.length);
    }

    /**
     * Writes a {@link Boolean} to the stream.
     * 
     * @param value The {@link Boolean} value to write to the stream.
     * @throws IOException 
     */
    public void writeBoolean(Boolean value) throws IOException
    {
        output.writeBoolean(value);
    }

    /**
     * Writes a {@link Byte} to the stream.
     * 
     * @param value The {@link Byte} value to write to the stream.
     * @throws IOException 
     */
    public void writeByte(Byte value) throws IOException
    {
        output.writeByte(value);
    }

    /**
     * Writes a {@link byte[]} to the stream.
     * 
     * This function calls {@link writeBytes(value.getBytes())}, which
     * means that the size of the string will be written to the stream before
     * the actual bytes of the array. This extra {@link int} will be
     * automatically read by the {@link SocketInputStream} when using
     * {@link readBytes()}.
     * 
     * @param value The {@link byte[]} value to write to the stream.
     * @throws IOException 
     */
    public void writeBytes(byte[] value) throws IOException
    {
        write(value);
    }
    
    /**
     * Writes a {@link Character} to the stream.
     * 
     * @param value The {@link Character} value to write to the stream.
     * @throws IOException 
     */
    public void writeChar(Character value) throws IOException
    {
        output.writeChar(value);
    }

    /**
     * Writes a {@link Double} to the stream.
     * 
     * @param value The {@link Double} value to write to the stream.
     * @throws IOException 
     */
    public void writeDouble(Double value) throws IOException
    {
        output.writeDouble(value);
    }

    /**
     * Writes a {@link Float} to the stream.
     * 
     * @param value The {@link Float} value to write to the stream.
     * @throws IOException 
     */
    public void writeFloat(Float value) throws IOException
    {
        output.writeFloat(value);
    }

    /**
     * Writes a {@link Integer} to the stream.
     * 
     * @param value The {@link Integer} value to write to the stream.
     * @throws IOException 
     */
    public void writeInt(Integer value) throws IOException
    {
        output.writeInt(value);
    }
    
    /**
     * Writes a {@link Long} to the stream.
     * 
     * @param value The {@link Long} value to write to the stream.
     * @throws IOException 
     */
    public void writeLong(Long value) throws IOException
    {
        output.writeLong(value);
    }

    /**
     * Writes a {@link Short} to the stream.
     * 
     * @param value The {@link Short} value to write to the stream.
     * @throws IOException 
     */
    public void writeShort(Short value) throws IOException
    {
        output.writeShort(value);
    }

    /**
     * Writes a {@link String} to the stream.
     * 
     * This function calls {@link writeBytes(value.getBytes())}, which
     * means that the size of the string will be written to the stream before
     * the actual bytes of the string. This extra {@link int} will be
     * automatically read by the {@link SocketInputStream} when using
     * {@link readString()}.
     * 
     * @param value The {@link String} value to write to the stream.
     * @throws IOException 
     */
    public void writeString(String value) throws IOException
    {
        writeBytes(value.getBytes());
    }
    
    /**
     * Flush the stream.
     * 
     * @throws IOException 
     */
    @Override
    public void flush() throws IOException
    {
        output.flush();
    }

    /**
     * Close the stream.
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException
    {
        output.close();
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
