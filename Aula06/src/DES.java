import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class DES {
    private static String encriptar(String texto, String chave) throws Exception {
        Cipher objCifra = Cipher.getInstance("DES");
        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
        objCifra.init(Cipher.ENCRYPT_MODE, objChave);
        byte[] cifra = objCifra.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cifra);
    }

    private static String dencriptar(String cifra, String chave) throws Exception {
        Cipher objCifra = Cipher.getInstance("DES");
        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
        objCifra.init(Cipher.DECRYPT_MODE, objChave);
        byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(cifra));
        return new String(texto, "UTF-8");
    }

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Digite um texto: ");
            String texto = leitor.readLine();

            System.out.print("Digite uma chave de 8 caracteres: ");
            String chave = leitor.readLine();

            String cifra = encriptar(texto, chave);
            System.out.println(cifra);

            System.out.println(dencriptar(cifra,chave));

        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}
