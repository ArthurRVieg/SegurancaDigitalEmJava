import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Vigenere {
    private static String encriptar(String texto, String chave) {
        String cifra = "";

        for (int i = 0 ; i < texto.length() ; i++) {
            int letraTexto = texto.charAt(i);
            int letraChave = chave.charAt(i % chave.length());
            int letraCifra = (letraTexto ^ letraChave);

            String temp = Integer.toHexString(letraCifra);
            if(temp.length() == 1) temp = ("0" + temp);

            cifra += temp;
        }

        return cifra;
    }
    private static String decriptar(String cifra, String chave) {
        String texto = "";

        for (int i = 0 ; i < cifra.length() ; i+=2) {
            int letraCifra = Integer.parseInt(cifra.substring(i, i + 2), 16);
            int letraChave = chave.charAt((i / 2) % chave.length());
            int letraTexto = (letraCifra ^letraChave);

            texto += ((char) letraTexto);
        }

        return  texto;
    }

    public static void main(String[] args) {
        //Declaração de variáveis
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        String texto = "";
        String chave = "";
        String cifra = "";

        // Processo de encriptação
        try {
            // Processo de encriptação
            System.out.print("Digite o texto: ");
            texto = leitor.readLine();

            System.out.print("Digite uma chave: ");
            chave = leitor.readLine();

            cifra = encriptar(texto, chave);
            System.out.println(cifra);

            //  Processo de decriptação
            System.out.print("Digite o cifra: ");
            cifra = leitor.readLine();

            System.out.print("Digite uma chave: ");
            chave = leitor.readLine();

            texto = decriptar(cifra, chave);
            System.out.println(texto);

        } catch (Exception erro) {
            System.out.println("Fudeu !!!!! " + erro);
        }
    }
}