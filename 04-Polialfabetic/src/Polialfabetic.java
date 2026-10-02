import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    private static final List<Character> alfabetMayusc = List.of('A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F',
            'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U',
            'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z');
    private static final long clauSecreta = 922337;
    private static Random numXifra;
    private static List<Character> alfabeRandom;

    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbrite coixi, Perímetre",
            "Test 02 Taül, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifra[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifra[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s->%s%n", msgs[i], msgsXifra[i]);
        }
        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifra[i]);
            System.out.printf("%-34s->%s%n", msgsXifra[i], msg);
        }
    }

    public static void initRandom(long clau) {
        numXifra = new Random(clau);
    }

    public static void permutaAlfabet() {
        alfabeRandom = new ArrayList<>(alfabetMayusc);
        Collections.shuffle(alfabeRandom, numXifra);
    };

    public static String xifraPoliAlfa(String msg) {
        return procedimient(msg, true);
    };

    public static String desxifraPoliAlfa(String msgXifrat) {
        return procedimient(msgXifrat,false );
    };

    private static String procedimient(String cadena, boolean xifrar) {
        String result = "";
        int posicio;
        char caracter;
        for (int i = 0; i < cadena.length(); i++) {
           char letra  = cadena.charAt(i);
           if(Character.isLetter(letra) == false ){result+=letra; continue;}
           permutaAlfabet();
           boolean esMinuscula = Character.isLowerCase(letra);
           char letraMayusc = Character.toUpperCase(letra);
           if(xifrar){
                posicio = alfabetMayusc.indexOf(letraMayusc);
                caracter = alfabeRandom.get(posicio);
                result += esMinuscula ? Character.toLowerCase(caracter): caracter;
           } else {
                posicio = alfabeRandom.indexOf(letraMayusc);
                caracter = alfabetMayusc.get(posicio);
                result += esMinuscula ? Character.toLowerCase(caracter) : caracter;
           } 
        }
        return result;
    }
}