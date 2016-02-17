/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendan;

import java.util.zip.CRC32;
import javafx.scene.paint.Color;

/**
 *
 * @author SilentAssassin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sendan sendan = new Sendan();
        byte[] got = {'a',0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x02,0x60};
        byte[] Ngot = {'a',0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x02,0x6E};
        byte[] buffer;
        boolean res ;
        buffer = sendan.switchOn(Sendan.PHASE4);
        res = sendan.checkStatus(Sendan.MOSQE);
        buffer = sendan.switchOff(Sendan.UNIT5_UP);
        res = sendan.checkStatus(Sendan.PHASE4);
        buffer = sendan.switchOn(Sendan.ALL);
        res = sendan.checkStatus(Sendan.PARK);
        
        
       
        
    }
    
}
