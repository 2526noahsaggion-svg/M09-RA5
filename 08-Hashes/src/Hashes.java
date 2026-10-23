import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashes {
    public int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt){
        try{
            String input = salt + pw;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes());
            
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        
    } 
    public String getPBKDF2AmbSalt(String pw, String salt){
        
    }
    public String forcaBruta(String alg, String hash, String salt){

    }
    public String getInterval(long t1, long t2){

    }
    public static void main(String[] args) {
        String salt = "qpoweiruañslkdfjk";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {h.getSHA512AmbSalt(pw, salt)};
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