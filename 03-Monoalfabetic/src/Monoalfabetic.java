import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic{
    private static final char[] alfabetMayusc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static char[] alfabetRamdo = permutaAlfabet(alfabetMayusc);
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);
        char[] permutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }
        return permutat;
    }
    private static String procedimiento(String cadena, char[] origen, char[] desti){
        String result = "";
        for(int i = 0; i < cadena.length(); i++){
            char letra = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(letra);
            char letraMayuscula = Character.toUpperCase(letra);
            int index = -1;
            for(int j = 0; j < origen.length; j++ ){
                if(letraMayuscula == origen[j]){
                    index = j;
                    break;
                }
                
            }

            result+= index == -1 ? letra : (esMinuscula ? Character.toLowerCase(desti[index]) : desti[index]); 
        }
        return result;
    }
    public static String xifraMonoAlfa(String cadena){
        return procedimiento(cadena, alfabetMayusc,alfabetRamdo);
    }
    public static String desxifraMonoAlfa(String cadena){
        return procedimiento(cadena,alfabetRamdo,alfabetMayusc);
    }
    public static void main(String[] args) {
        for(int i = 0; i < alfabetMayusc.length; i++){
            System.out.print(alfabetMayusc[i] + (i < alfabetMayusc.length - 1 ? " " : ""));
        }
        System.out.println();

        for (int i = 0; i < alfabetRamdo.length; i++){
            System.out.print(alfabetRamdo[i] + (i < alfabetRamdo.length - 1 ? " " : ""));
        }
        System.out.println("\n");
        String[] paraulesXifrar = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String[] paraulesDesxifrades = new String[paraulesXifrar.length];
        System.out.println("Xifratge:");
        for(int i = 0; i < paraulesXifrar.length; i++){
            String frase = paraulesXifrar[i]; 
            String fraseXifrada = xifraMonoAlfa(frase);
            paraulesDesxifrades[i] = fraseXifrada;
            System.out.printf("%-35s -> %s\n",frase,fraseXifrada);
        }
        System.out.println("\nDesxifratge:");
        for(String frase: paraulesDesxifrades){
            System.out.printf("%-35s -> %s\n",frase,desxifraMonoAlfa(frase));
        }
    }
}