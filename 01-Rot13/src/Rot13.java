public class Rot13{
    private static int posicio = 13; 
    public static char[] minuscules = {
    'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï',
    'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù',
    'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù',
        'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static String xifraRot13(String cod){
        return descodificador(cod, posicio);
    }
    public static String desxifraRot13(String cod){
        return descodificador(cod, -posicio);
    }
    public static String descodificador(String paraula, int posicio){ 
        String resultat = "";
        int total =  minuscules.length;
        for (int i = 0; i < paraula.length();i++){
            char c = paraula.charAt(i);
            boolean trobat = false;
            for (int j = 0; j < total; j++) {
                if (c == minuscules[j]) {
                    int Posnova = (j + posicio + total) % total;
                    resultat += minuscules[Posnova];
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                for (int l = 0; l < total; l++) {
                    if (c == majuscules[l]) {
                        int Posnova = (l + posicio + total) % total;
                        resultat += majuscules[Posnova];
                        trobat = true;
                        break;
                    }
                }
            }
            if (!trobat) {
                resultat += c;
            }
        }
        return resultat;

    }
    public static void main(String[] args) {
        String[] xifrat = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        String[] desxifrat = {
            "IÏJ",
            "FGH",
            "Òwúi, Ùá. jiúkwb",
            "Zmálx, zmá bc acñ nà?"
        };

        System.out.println("Xifrat");
        System.out.println("---------");
        for (String text : xifrat) {
            System.out.printf("%-23s => %s%n", text, xifraRot13(text));
        }

        System.out.println("\nDesxifrat");
        System.out.println("---------");
        for (String text : desxifrat) {
            System.out.printf("%-23s => %s%n", text, desxifraRot13(text));
        }
    }
}