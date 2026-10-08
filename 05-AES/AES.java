import javax.crypto.spec.IvParameterSpec;
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
        byte[] bytesString = msg.getBytes();
        SecureRandom random = new SecureRandom(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(bytesString);

        

    }
    public static String desxifraAES (byte[] bIvIMagXifrat, String clau) throws Exception{

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
