/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendan;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author SilentAssassin
 */
public class Sendan {
	////////////////// Sengle LED Strips ///////////////////////////
    public static final long UNIT1_LEFT 		= (1<<0);
    public static final long UNIT1_RIGHT 		= (1<<1);
    public static final long UNIT2_LEFT 		= (1<<2);
    public static final long UNIT2_RIGHT 		= (1<<3);
    public static final long UNIT3_UP 			= (1<<4);
    public static final long UNIT3_DOWN 		= (1<<5);
    public static final long UNIT4_UP 			= (1<<6);
    public static final long UNIT4_DOWN 		= (1<<7);
    public static final long UNIT5_UP 			= (1<<8);
    public static final long UNIT5_DOWN 		= (1<<9);
    public static final long UNIT6_UP 			= (1<<10);
    public static final long UNIT6_DOWN 		= (1<<11);
    public static final long UNIT7_UP 			= (1<<12);
    public static final long UNIT7_DOWN 		= (1<<13);
    public static final long UNIT8_UP 			= (1<<14);
    public static final long UNIT8_DOWN 		= (1<<15);
    public static final long UNIT9_UP 			= (1<<16);
    public static final long UNIT9_DOWN 		= (1<<17);
    public static final long UNIT10_UP 		= (1<<18);
    public static final long UNIT10_DOWN 		= (1<<19);
    public static final long UNIT11_UP 		= (1<<20);
    public static final long UNIT11_DOWN 		= (1<<21);
    //public static final long UNIT12_UP 		= 0;
    //public static final long UNIT12_DOWN 		= 0;
    //public static final long UNIT13_UP 		= 0;
    //public static final long UNIT13_DOWN 		= 0;
    //public static final long UNIT14_UP 		= 0;
    //public static final long UNIT14_DOWN 		= 0;
    //public static final long UNIT15_UP 		= 0;
    //public static final long UNIT15_DOWN 		= 0;
    public static final long UNIT16_UP 		= (1<<22);
    public static final long UNIT16_DOWN 		= (1<<23);
    public static final long UNIT17_UP 		= (1<<24);
    public static final long UNIT17_DOWN 		= (1<<25);
    public static final long UNIT18_UP 		= (1<<26);
    public static final long UNIT18_DOWN 		= (1<<27);
    public static final long UNIT19_UP 		= (1<<28);
    public static final long UNIT19_DOWN 		= (1<<29);
    public static final long UNIT20_UP 		= (1<<30);
    public static final long UNIT20_DOWN 		= (1<<31);
    public static final long UNIT21_UP 		= ((long)1<<32);
    public static final long UNIT21_DOWN 		= ((long)1<<33);
    public static final long UNIT22_UP 		= ((long)1<<34);
    public static final long UNIT22_DOWN 		= ((long)1<<35);
    public static final long UNIT23_UP 		= ((long)1<<36);
    public static final long UNIT23_DOWN 		= ((long)1<<37);
    public static final long UNIT24_UP 		= ((long)1<<38);
    public static final long UNIT24_DOWN 		= ((long)1<<39);
    public static final long UNIT25_LEFT 		= ((long)1<<40);
    public static final long UNIT25_RIGHT 		= ((long)1<<41);
    public static final long UNIT26_LEFT 		= ((long)1<<42);
    public static final long UNIT26_RIGHT		= ((long)1<<43);
    public static final long UNIT27_UP 		= ((long)1<<44);
    //public static final long UNIT27_DOWN 		= 0;
    //public static final long UNIT28_UP 		= 0;
    //public static final long UNIT28_DOWN 		= 0;
    //public static final long UNIT29_DOWN 		= 0;
    //public static final long UNIT30_UP 		= 0;
    //public static final long UNIT30_DOWN 		= 0;
    public static final long UNIT29_UP		 		= ((long)1<<45);
    public static final long POLYVARD				= ((long)1<<46);
    public static final long PETROL_STATION		= ((long)1<<47);
    public static final long PARK					= ((long)1<<48);
    public static final long MOSQE					= ((long)1<<49);
    public static final long CARS_PHASE1			= ((long)1<<50);
    public static final long CARS_PHASE2			= ((long)1<<51);
    public static final long CARS_PHASE3			= ((long)1<<52);
    public static final long CARS_PHASE4			= ((long)1<<53);
	//////////////////// Combined LED Strips ////////////////////////////////
    public static final long PHASE1				= UNIT1_LEFT | UNIT1_RIGHT| UNIT7_UP | UNIT7_DOWN | UNIT8_DOWN | UNIT29_UP | CARS_PHASE1
                                                | UNIT8_UP | UNIT9_DOWN | UNIT9_UP| UNIT10_DOWN | UNIT10_UP | UNIT11_DOWN | UNIT11_UP;
    public static final long PHASE2				= UNIT16_DOWN | UNIT16_UP | UNIT17_DOWN | UNIT17_UP | UNIT18_DOWN | UNIT18_UP | UNIT19_DOWN | UNIT19_UP 
                                                | UNIT20_DOWN | UNIT20_UP | UNIT26_LEFT | UNIT26_RIGHT | UNIT27_UP | CARS_PHASE2;
    public static final long PHASE3				= UNIT21_DOWN | UNIT21_UP | UNIT22_DOWN | UNIT22_UP | UNIT23_DOWN | UNIT23_UP | UNIT24_DOWN | UNIT24_UP 
                                                      | UNIT25_LEFT | UNIT25_RIGHT | CARS_PHASE3 | PETROL_STATION;
    public static final long PHASE4				= UNIT2_LEFT | UNIT2_RIGHT | UNIT3_DOWN | UNIT3_UP | UNIT4_DOWN | UNIT4_UP | UNIT5_DOWN | UNIT5_UP 
                                                         | UNIT6_DOWN | UNIT6_UP | MOSQE |CARS_PHASE4 ;
    public static final long CONSTRUCTION_WORLD	= PHASE2 | PHASE3;
    public static final long AUTOMOBILE_WORLD		= PHASE1 | PHASE4;
    public static final long ALL					= PHASE1 | PHASE2 | PHASE3 | PHASE4 | PARK | POLYVARD;
    public static final long ALL_CARS				= CARS_PHASE1 | CARS_PHASE2 | CARS_PHASE3 | CARS_PHASE4;
	
	private long status = 0 ;
	private byte crc = 0;
	private CRC8 crc8Engine ;
           public Sendan()
           {
               status = 0 ;
               crc8Engine = new CRC8();
               crc = 0;
           }
	public byte[] switchOn(long target)
    {
        status |= target ;
        byte buffer[] = new byte[10];
        buffer[0] = 'a';
        byte statusBytes[] = longToBytes(status);
        System.arraycopy(statusBytes, 0, buffer, 1, 8);
        crc8Engine.reset();
        crc8Engine.update(buffer, 0, 9);
        crc = (byte) crc8Engine.getValue();
        buffer[9] = crc ;
        return  buffer;
    }
	
	public byte[] switchOff(long target )
	{
            status &= (~target) ;
            byte buffer[] = new byte[10];
            buffer[0] = 'a';
            byte statusBytes[] = longToBytes(status);
            System.arraycopy(statusBytes, 0, buffer, 1, 8);
            crc8Engine.reset();
            crc8Engine.update(buffer, 0, 9);
            crc = (byte) crc8Engine.getValue();
            buffer[9] = crc ;
            return  buffer;	
	}
	public boolean checkStatus(long target)
	{
            return  ((status &(target)) == target);	
	}
        
	public byte[] getStatus()
	{
            byte buffer[] = new byte[10];
            buffer[0] = 'a';
            byte statusBytes[] = longToBytes(status);
        System.arraycopy(statusBytes, 0, buffer, 1, 8);
            crc8Engine.reset();
            crc8Engine.update(buffer, 0, 9);
            crc = (byte) crc8Engine.getValue();
            buffer[9] = crc ;
            return  buffer;	
	}
	public boolean setStatus(byte[] packet)
	{
            crc8Engine.reset();
            crc8Engine.update(Arrays.copyOfRange(packet, 0, 9));
            byte tempCRC = (byte)crc8Engine.getValue() ;
            if(packet[9]== tempCRC)
            {
                status = bytesToLong(Arrays.copyOfRange(packet, 1, 9));
                crc = tempCRC ;
                return true;
            }
            return false;
	}
        
private byte[] longToBytes(long x) 
{
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong(x);
    return buffer.array();
}

private long bytesToLong(byte[] bytes) 
{
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.put(bytes);
    buffer.flip();//need flip 
    return buffer.getLong();
}
     
}
