import java.io.BufferedReader;
import java.math.*;
import java.util.Random;
import java.util.Scanner;
public class RSA {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Enter a Prime number: ");
		BigInteger p = sc.nextBigInteger();
		System.out.print("Enter another prime number:"); 
		BigInteger q = sc.nextBigInteger();
		BigInteger n = p.multiply(q);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger e = generateE(phi);
		BigInteger d = e.modInverse(phi);
		System.out.println("Encryption keys are: " + e + ", " + n);
		System.out.println("Decryption keys are: " + d + ", " + n);
		System.out.println("Enter a message to encryp: ");
		BigInteger m=sc.nextBigInteger();
		BigInteger c=m.modPow(e,n);
		System.out.println("Encrypted message: "+c);
		m=c.modPow(d,n);
		System.out.println("Decrypted message: "+m);
	}
	public static BigInteger generateE(BigInteger fiofn) {
		int y, intGCD;
		BigInteger e;
		BigInteger gcd;
		Random x = new Random();
		do { 
			y = x.nextInt(fiofn.intValue()-1);
			String z = Integer.toString(y);
			e = new BigInteger(z);
			gcd = fiofn.gcd(e);
			intGCD = gcd.intValue();
		}
		while(y <= 2 || intGCD != 1);
		return e;
	}
}