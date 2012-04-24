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
 * <b>SocketOutputStream</b> will handle writing primitive data types or strings
 * through a <b>Socket</b> which will then be read by <b>SocketInputStream</b>
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
     * Note that the parameter is an <b>int</b> (bigger size than a byte). This
     * function will write a <b>byte</b> to the stream.
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
     * This function calls <code>writeInt(value.length)</code> before writing
     * the bytes to the stream. This extra <b>int</b> is automatically read by
     * the <code>SocketInputStream.readInt()</code> on the other end of the
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
     * This function calls <code>write(value, 0, value.length)</code>;
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
     * Writes a <b>Boolean</b> to the stream.
     * 
     * @param value The <b>Boolean</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeBoolean(Boolean value) throws IOException
    {
        output.writeBoolean(value);
    }

    /**
     * Writes a <b>Byte</b> to the stream.
     * 
     * @param value The <b>Byte</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeByte(Byte value) throws IOException
    {
        output.writeByte(value);
    }

    /**
     * Writes a <b>byte[]</b> to the stream.
     * 
     * This function calls <code>writeBytes(value.getBytes())</code>, which
     * means that the size of the string will be written to the stream before
     * the actual bytes of the array. This extra <b>int</b> will be
     * automatically read by the <b>SocketInputStream</b> when using
     * <code>readBytes()</code>.
     * 
     * @param value The <b>byte[]</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeBytes(byte[] value) throws IOException
    {
        write(value);
    }
    
    /**
     * Writes a <b>Character</b> to the stream.
     * 
     * @param value The <b>Character</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeChar(Character value) throws IOException
    {
        output.writeChar(value);
    }

    /**
     * Writes a <b>Double</b> to the stream.
     * 
     * @param value The <b>Double</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeDouble(Double value) throws IOException
    {
        output.writeDouble(value);
    }

    /**
     * Writes a <b>Float</b> to the stream.
     * 
     * @param value The <b>Float</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeFloat(Float value) throws IOException
    {
        output.writeFloat(value);
    }

    /**
     * Writes a <b>Integer</b> to the stream.
     * 
     * @param value The <b>Integer</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeInt(Integer value) throws IOException
    {
        output.writeInt(value);
    }
    
    /**
     * Writes a <b>Long</b> to the stream.
     * 
     * @param value The <b>Long</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeLong(Long value) throws IOException
    {
        output.writeLong(value);
    }

    /**
     * Writes a <b>Short</b> to the stream.
     * 
     * @param value The <b>Short</b> value to write to the stream.
     * @throws IOException 
     */
    public void writeShort(Short value) throws IOException
    {
        output.writeShort(value);
    }

    /**
     * Writes a <b>String</b> to the stream.
     * 
     * This function calls <code>writeBytes(value.getBytes())</code>, which
     * means that the size of the string will be written to the stream before
     * the actual bytes of the string. This extra <b>int</b> will be
     * automatically read by the <b>SocketInputStream</b> when using
     * <code>readString()</code>.
     * 
     * @param value The <b>String</b> value to write to the stream.
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
