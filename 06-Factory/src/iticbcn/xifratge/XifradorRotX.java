package iticbcn.xifratge;
public class XifradorRotX implements Xifrador{
    
    private int longitudAbecadari = 39;
    public  char[] minuscules = {
    'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï',
    'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù',
    'ü', 'v', 'w', 'x', 'y', 'z'
    };
    
    public  char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù',
        'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    public  String xifraRotX( String cadena, int desplaçament){
        return descodificador(cadena, desplaçament);
    }

    public  String desxifraRotX(String cadena, int desplaçament){
        return descodificador(cadena, -desplaçament);
    }
    public  void forcaBrutaRotX(String cadenaXifrada){
        for(int i = 0; i <= longitudAbecadari; i++){
            System.out.printf("(%d) -> %s\n",i,desxifraRotX(cadenaXifrada, i));
        }
    }
    public  String descodificador(String paraula, int posicio){ 
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
    public  void main(String[] args) {
        String[] xifrat = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        String[] desxifrat = {
            "ABC",
            "ZAÁ",
            "Ïqoc, Óú. écoèqü",
            "Úiüht, úiü wx ùxì ív?"
        };
        String missatge = "Úiüht, úiü wx ùxì ív";
        int posicio = 0;
        System.out.println("Xifrat");
        System.out.println("------");
        for (String text : xifrat) {
            System.out.printf("(%d)-%-23s => %s%n",posicio,text, xifraRotX(text,posicio));
            posicio += 2;
        }
        posicio = 0;
        System.out.println("\nDesxifrat");
        System.out.println("---------");
        for (String text : desxifrat) {
            System.out.printf("(%d)%-23s => %s%n",posicio,text, desxifraRotX(text,posicio));
            posicio += 2;
        }
        System.out.println("\nMissatge xifrat: Úiüht, úiü wx ùxì ív?");
        System.out.println("----------------");
        forcaBrutaRotX(missatge);

    }
}
