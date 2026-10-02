import java.util.Random;

public class Polialfabetic {
        private static final char[] alfabetMayusc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray(); 
    private static final long clauSecreta = 922337;
    private static Random numXifra;
    private static char[] alfabeRandom;

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
        alfabeRandom = alfabetMayusc.clone();
        for (int i = alfabeRandom.length - 1; i > 0; i--) {
            int j = numXifra.nextInt(i + 1);
            char temp = alfabeRandom[i];
            alfabeRandom[i] = alfabeRandom[j];
            alfabeRandom[j] = temp;
        }
    };

    public static String xifraPoliAlfa(String msg) {
        return procedimient(msg, true);
    };

    public static String desxifraPoliAlfa(String msgXifrat) {
        return procedimient(msgXifrat,false );
    };
    public static int indexOf(char[] array, char letra){
        for(int i= 0; i < array.length; i++){
            if(array[i] == letra){
                return i;
            }
        }
        return -1;
    }

    private static String procedimient(String cadena, boolean xifrar) {
        char[] caracters = cadena.toCharArray();
        char[] resultat = new char[caracters.length];
        for(int i = 0; i < caracters.length; i++){
            char letra = caracters[i];
            char letraMayusc = Character.toUpperCase(letra);
            int pos = indexOf(alfabetMayusc, letraMayusc);
            if (pos == -1) {
                resultat[i] = letra;
                continue;
            }
            permutaAlfabet();

            boolean esMinuscula = Character.isLowerCase(letra);
            char caracterSubstituit;

            if (xifrar) {
                caracterSubstituit = alfabeRandom[pos];
            } else {
                int posPermutada = indexOf(alfabeRandom, letraMayusc);
                caracterSubstituit = alfabetMayusc[posPermutada];
            }

            resultat[i] = esMinuscula ? Character.toLowerCase(caracterSubstituit) : caracterSubstituit;           
        }
        return new String(resultat);
    }
}