import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

public class RSA {
    private static KeyPair gerarChavesAsssimetricas() throws Exception {
        KeyPairGenerator objGerador = KeyPairGenerator.getInstance("RSA");
        objGerador.initialize(4096);
        return objGerador.generateKeyPair();
    }

    private static String encriptarRSA(byte[] chave, KeyPair parDeChaves) throws Exception {
        Cipher objCifra = Cipher.getInstance("RSA");
        objCifra.init(Cipher.ENCRYPT_MODE, parDeChaves.getPublic());
        byte[] cifra = objCifra.doFinal(chave);
        return Base64.getEncoder().encodeToString(cifra);
    }

    private static byte[] descriptarRSA(String cifra, KeyPair parDeChaves) throws Exception {
        Cipher objCifra = Cipher.getInstance("RSA");
        objCifra.init(Cipher.DECRYPT_MODE, parDeChaves.getPrivate());
        return objCifra.doFinal(Base64.getDecoder().decode(cifra));
    }

    private static String encriptarAES(String texto, byte[] chave) throws Exception {
        Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey objChave = new SecretKeySpec(chave, "AES");
        IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
        objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
        byte[] cifra = objCifra.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cifra);
    }

    private  static String dencriptarAES(String cifra, byte[] chave) throws Exception {
        Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey objChave = new SecretKeySpec(chave, "AES");
        IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
        objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);
        byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(cifra));
        return new String(texto, "UTF-8");
    }

    private static byte[] calcularSHA256(byte[] chave) throws Exception {
        return MessageDigest.getInstance("SHA-256").digest(chave);
    }

    private static byte[] randomizarChaveSimetrica() throws Exception {
        byte[] chave = new byte[100];
        for (int i = 0 ; i < chave.length ; i++) {
            chave[i] = ((byte) (256 * Math.random()));
        }
        return chave;
    }

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        try {
            KeyPair parDeChaves = gerarChavesAsssimetricas();
            byte[] chaveSimetrica = randomizarChaveSimetrica();

            System.out.print("Digite um texto: ");
            String texto = leitor.readLine();

            System.out.println(encriptarRSA(chaveSimetrica, parDeChaves));
            System.out.println(encriptarAES(texto, calcularSHA256(chaveSimetrica)));

            System.out.print("Digite a cifra da chave: ");
            String chave = leitor.readLine();

            System.out.print("Digite a cifra do texto: ");
            String cifra = leitor.readLine();

            System.out.println(dencriptarAES(cifra, calcularSHA256(descriptarRSA(chave, parDeChaves))));
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}
