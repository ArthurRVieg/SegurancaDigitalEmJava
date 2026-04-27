import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaROT13 {
    private static String criptografar(String texto) {
        String criptograma = "";

        for (int i = 0 ; i < texto.length() ; i++) {
            int charOriginal = texto.charAt(i);
            int charCifrado = charOriginal;

            if ((charOriginal > 97) && (charOriginal <= 122)) {
                charCifrado = (charOriginal + 13);
                if (charCifrado > 122) {
                    charCifrado -= 26;
                }
            }

            criptograma += ((char) charCifrado);
        }

        return criptograma;
    }

    public static void main(String[] args) {
        // Declaração de variáveis
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        String texto = "";
        String criptograma = "";

        // Entrada de dados
        try {
            System.out.println("Digite um texto: ");
            texto = leitor.readLine();
        } catch (Exception e) {}

        // Processamento
        criptograma = criptografar(texto);
        texto = criptografar(criptograma);

        //Saída de dados
        System.out.println("Criptograma: " + criptograma);
        System.out.println("Texto: " + texto);
    }
}
