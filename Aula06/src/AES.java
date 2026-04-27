import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class AES {
    private static String encriptar (String texto, String chave) throws Exception {
        Cipher objCifra = Cipher.getInstance("AES/CBC");
        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
        IvParameterSpec objIv = new IvParameterSpec("0123456789001112".getBytes());
        objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
        byte[] cifra = objCifra.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cifra);
    }

    private static String decriptar(String cifra, String chave)
            throws Exception {
        Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
        IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
        objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);
        byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(cifra));
        return new String(texto, "UTF-8");
    }

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            System.out.print("Digite um texto: ");
            String texto = leitor.readLine();

            System.out.print("Digite uma chave: ");
            String chave = leitor.readLine();

            String cifra = encriptar(texto, chave);
            System.out.println(cifra);
            System.out.println(decriptar(cifra, chave));
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }

}
