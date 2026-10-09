import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception{
        // Obtener los bits del String
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // Generar IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauEnBytes  = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec  secretKeyEntry = new SecretKeySpec(clauEnBytes,ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeyEntry,ivSpec);
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        // Combina IV i part xifrada.
        byte[] ivXifratMsg = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, ivXifratMsg, 0, iv.length);
        System.arraycopy(msgXifrat, 0, ivXifratMsg, iv.length, msgXifrat.length);

        // return iv+msgxifrat
        return ivXifratMsg;


    }
    public static String desxifraAES (byte[] bIvIMagXifrat, String clau) throws Exception{
        // Extreure l'IV.
        byte[] ivExtret = new byte[MIDA_IV];
        System.arraycopy(bIvIMagXifrat, 0, ivExtret, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);

        // Extreure la part xifrada.
        int midaMsgXifrat = bIvIMagXifrat.length - MIDA_IV;
        byte[] msgXifrat = new byte[midaMsgXifrat];
        System.arraycopy(bIvIMagXifrat, MIDA_IV, msgXifrat, 0, midaMsgXifrat);

        // Fer hash de la clau.
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec keySpec = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        // return String desxifrat.
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Olá André como estás tu cuñado",
                "Agora illa Otto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifratas = null;
            String desxifrat = "";
            try {
                bXifratas = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifratas, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifratas));
            System.out.println("DEC: " + desxifrat);
        }
    }

}
