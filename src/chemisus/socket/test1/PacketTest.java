/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2012, Terrence Howard
 * @package     chemisus.socket.test;
 */
package chemisus.socket.test1;

import chemisus.socket.Packet;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>PacketTest</b>
 * 
 * @author      Terrence Howard <chemisus@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public class PacketTest
    extends Packet
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
    public static String MD5(String md5)
    {
        try 
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        
            byte[] array = md.digest(md5.getBytes());
            
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < array.length; i++)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            
            return sb.toString();
        }
        catch (java.security.NoSuchAlgorithmException ex)
        {
            Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private static final boolean[] booleans = {true, false, true, false, false, true};
    
    private static final byte[] bytes = {1, 2, 12, 24, 120, 127};
    
    private static final char[] chars = {'A', 'z', 'q', 't', 'W'};
    
    private static final double[] doubles = {Math.PI, Math.E, Math.acos(Math.PI), Math.exp(Math.PI)};
    
    private static final float[] floats = {0.12f, 0.14f, 8.19f, 10.12f, (float)Math.PI};
    
    private static final int[] ints = {4, 5, 0, 12, Integer.MAX_VALUE, Integer.MIN_VALUE};
    
    private static final short[] shorts = {Short.MAX_VALUE, Short.MIN_VALUE, 12, 39, 20};

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    @Override
    public int getPacketIndex()
    {
        return 1234;
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
    @Override
    protected void write() throws IOException
    {
        for (boolean value : booleans)
        {
            writeBoolean(value);
        }

        for (byte value : bytes)
        {
            writeByte(value);
        }

        for (char value : chars)
        {
            writeChar(value);
        }

        for (double value : doubles)
        {
            writeDouble(value);
        }

        for (float value : floats)
        {
            writeFloat(value);
        }

        for (int value : ints)
        {
            writeInt(value);
        }

        for (short value : shorts)
        {
            writeShort(value);
        }
        
        BigInteger bigint;
        
        for (int i = 0; i < 3; i++)
        {
            bigint = new BigInteger(1000, new Random(System.nanoTime()));
            
            String string = bigint.toString();
            
//            String string = "this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.this is some message here.";

            writeString(string);

            writeString(MD5(string));
        }
    }

    @Override
    protected void read() throws IOException
    {
        for (boolean value : booleans)
        {
            if (readBoolean() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read boolean failed.");
            }
        }

        for (byte value : bytes)
        {
            if (readByte() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read byte failed.");
            }
        }

        for (char value : chars)
        {
            if (readChar() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read char failed.");
            }
        }

        for (Double value : doubles)
        {
            if (Double.compare(readDouble(), value) != 0)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read double failed. " + value.toString());
            }
        }

        for (float value : floats)
        {
            if (readFloat() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read float failed.");
            }
        }

        for (int value : ints)
        {
            if (readInt() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read int failed.");
            }
        }

        for (short value : shorts)
        {
            if (readShort() != value)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read short failed.");
            }
        }

        for (int i = 0; i < 3; i++)
        {
            if (MD5(readString()).compareTo(readString()) != 0)
            {
                Logger.getLogger(PacketTest.class.getName()).log(Level.SEVERE, "read string failed.");
            }
        }
    }


    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/


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
