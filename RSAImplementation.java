import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Simple RSAImplementation public key encryption algorithm implementation.
 * <P>
 * Taken from "Paj's" website:
 * <TT>http://pajhome.org.uk/crypt/RSAImplementation/implementation.html</TT>
 * <P>
 * Adapted by David Brodrick
 */
public class RSAImplementation {
  private BigInteger n, d, e;

  private int bitlen = 1024;

  /** Create an instance that can encrypt using someone elses public key. */
  public RSAImplementation(BigInteger newn, BigInteger newe) {
    n = newn;
    e = newe;
  }

  /** Create an instance that can both encrypt and decrypt. */
  public RSAImplementation(int bits) {
    bitlen = bits;
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Encrypt the given plaintext message. */
  public synchronized String encrypt(String message) {
    return (new BigInteger(message.getBytes())).modPow(e, n).toString();
  }

  /** Encrypt the given plaintext message. */
  public synchronized BigInteger encrypt(BigInteger message) {
    return message.modPow(e, n);
  }

  /** Decrypt the given ciphertext message. */
  public synchronized String decrypt(String message) {
    return new String((new BigInteger(message)).modPow(d, n).toByteArray());
  }

  /** Decrypt the given ciphertext message. */
  public synchronized BigInteger decrypt(BigInteger message) {
    return message.modPow(d, n);
  }

  /** Generate a new public and private key set. */
  public synchronized void generateKeys() {
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Return the modulus. */
  public synchronized BigInteger getN() {
    return n;
  }

  /** Return the public key. */
  public synchronized BigInteger getE() {
    return e;
  }

  /** Trivial test program. */
  public static void main(String[] args) {
    RSAImplementation RSAImplementation = new RSAImplementation(1024);

    String text1 = "This is plain Text";
    System.out.println("Plaintext: " + text1);
    BigInteger plaintext = new BigInteger(text1.getBytes());

    BigInteger ciphertext = RSAImplementation.encrypt(plaintext);
    System.out.println("Ciphertext: " + ciphertext);
    plaintext = RSAImplementation.decrypt(ciphertext);

    String text2 = new String(plaintext.toByteArray());
    System.out.println("Plaintext: " + text2);
  }
}