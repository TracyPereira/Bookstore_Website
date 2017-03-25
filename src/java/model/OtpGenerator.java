
package model;

import java.util.Random;

/**
 *
 * @author Dell
 */
public class OtpGenerator {
    public static int generateOTP() {
        Random r = new Random();
        return (r.nextInt(9)+1) + (r.nextInt(9)+1)*10 + (r.nextInt(9)+1)*100 + (r.nextInt(9)+1)*1000;
    }
    public static void main(String [] args) {
        for(int i=0;i<5;i++)
            System.out.println(generateOTP());
    }
    
}
