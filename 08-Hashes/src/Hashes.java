import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return HexFormat.of().formatHex(md.digest((salt + pw).getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 65536, 128);
            return HexFormat.of().formatHex(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec).getEncoded());
        } catch (Exception e) {
            return null;
        }
    }
    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0;
        char[] cs = "abcdefABCDEF1234567890!".toCharArray();
        for (char a : cs) {
            for (char b : cs) {
                for (char c : cs) {
                    for (char d : cs) {
                        for (char e : cs) {
                            for (char f : cs) {
                                npass++;
                                String candidate = "" + a + b + c + d + e + f;
                                String h = alg.equals("SHA-512") ? getSHA512AmbSalt(candidate, salt) : getPBKDF2AmbSalt(candidate, salt);
                                if (hash.equals(h)) return candidate;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public String getInterval(long t1, long t2) {
        long m = t2 - t1;
        long s = m / 1000;
        long min = s / 60;
        long h = min / 60;
        long d = h / 24;
        return d + " dies / " + (h % 24) + " hores / " + (min % 60) + " minuts / " + (s % 60) + " segons / " + (m % 1000) + " millis";
    }
    public static void main(String[] args) {
        String salt = "qpoweiruañslkdfjk";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)};
        String pwTrobat = null;
        String[] algorismes = {"SHA-512","PBKDF2"};
        for(int i = 0; i < aHashes.length; i++){
            System.out.printf("================================\n");
            System.out.printf("Algorisme: %s\n",algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("-------------------");
            System.out.printf("--Inici de força bruta--\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps: %s\n", h.getInterval(t1, t2));
            System.out.printf("-----------------\n");
        }
    }
}