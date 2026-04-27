import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LeanerFeedbackShiftRegister {
    private static void inicializar (boolean[] registrador) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        String chave = "";

        try {
            System.out.print("Digite uma chave de quatro caracteres: ");
            chave = leitor.readLine();

            for (int i = 0 ; i < 4 ; i++) { // Pegar uma letra de cada vez
                int caractere = chave.charAt(i);
                String binario = Integer.toBinaryString(caractere);

                for (int j = 0 ; j < (8 - binario.length()) ; j++) { // Alinhamento do binário
                    binario = ("0" + binario);
                }

                for (int j = 0 ; j < 8 ; j++) {
                    registrador[j + (8 * i)] = (binario.charAt(j) == '1');
                }
            }
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }

    private static boolean rotacionar(boolean[] registrador, boolean tipo) {
        boolean retorno = registrador[0];
        boolean novoBit = retorno;

        if (! tipo) {
            novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[4] ^ registrador[2] ^ registrador[1] ^ registrador[0]);

        } else {
            novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[5] ^ registrador[1]);
        }

        for (int i = 0 ; i < 31 ; i ++) { // Só até o penúltimo
            registrador[i] = registrador[i + 1];
        }
        registrador[31] = novoBit;

        return retorno;
    }

    public static void main(String[] args) {
        boolean[] cabeca = new boolean[32];
        boolean[] registrador0 = new boolean[32];
        boolean[] registrador1 = new boolean[32];

        inicializar(cabeca);
        inicializar(registrador0);
        inicializar(registrador1);

        String caractere = "";
        while (true) {
            boolean bit0 = registrador0[0];
            boolean bit1 = registrador1[1];

            if (! rotacionar(cabeca, false)) {
                bit0 = rotacionar(registrador0, false);
            } else {
                bit1 = rotacionar(registrador1, true);
            }

            caractere += ((bit0 ^ bit1) ? "1" : "0");
            if (caractere.length() == 8) {
                System.out.print((char) Integer.parseInt(caractere, 2));
                caractere = "";
            }
        }
    }
}
