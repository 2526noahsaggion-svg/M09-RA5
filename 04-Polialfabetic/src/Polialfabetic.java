public class Polialfabetic {
    private static final int clauSecreta = 537182789;

    public static void permutaAlfabet(){

    };
    public static String xifraPoliAlfa(String msg){

    };
    public static String desxifraPoliAlfa(String msgXifrat){

    };
    public static void main(String[] args){
        String msgs[] = {
            "Test 01 àrbrite coixi, Perímetre",
            "Test 02 Taül, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifra[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for(int i = 0; i < msgs.length;i ++){
            intRandom(clauSecreta);
            msgsXifra[i] = xifraPoliAlfa(msgs[i]);
            System.out.println("%-34s->%s%n",msgs[i],msgsXifra[i]);
        }
        System.out.println("Desxifratge:\n--------");
        for(int i = 0; i < msgs.length; i++){
            initRamdom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.println("%-34s->%s%n",msgsXifra[i],msgs[i]);
        }
    }
}