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
    @Override
    public void write(int value) throws IOException
    {
        output.write(value);
    }

    @Override
    public void write(byte[] value, int offset, int length) throws IOException
    {
        writeInt(value.length);
        
        output.write(value, 0, value.length);
    }
    
    @Override
    public void write(byte[] value) throws IOException
    {
        write(value, 0, value.length);
    }
    
    public void writeBoolean(Boolean value) throws IOException
    {
        output.writeBoolean(value);
    }

    public void writeByte(Byte value) throws IOException
    {
        write(value);
    }

    public void writeBytes(byte[] value) throws IOException
    {
        write(value);
    }
    
    public void writeChar(Character value) throws IOException
    {
        output.writeChar(value);
    }

    public void writeDouble(Double value) throws IOException
    {
        output.writeDouble(value);
    }

    public void writeFloat(Float value) throws IOException
    {
        output.writeFloat(value);
    }

    public void writeInt(Integer value) throws IOException
    {
        output.writeInt(value);
    }
    
    public void writeLong(Long value) throws IOException
    {
        output.writeLong(value);
    }

    public void writeShort(Short value) throws IOException
    {
        output.writeShort(value);
    }

    public void writeString(String value) throws IOException
    {
        writeBytes(value.getBytes());
    }
    
    @Override
    public void flush() throws IOException
    {
        output.flush();
    }
    
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
