package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRAS() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher cipherEncrypt = Cipher.getInstance("RSA");
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipherEncrypt.doFinal(msg.getBytes());
    }
    public String desxifraRSA (byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher cipherDescrypt = Cipher.getInstance("RSA");
        cipherDescrypt.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] descryptBytes = cipherDescrypt.doFinal(msgXifrat);
        return new String(descryptBytes,"UTF-8");
    }
}