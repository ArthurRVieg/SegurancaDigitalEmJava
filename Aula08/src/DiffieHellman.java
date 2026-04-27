import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class DiffieHellman {
    private static final BigInteger PRIMO = new BigInteger("102031405123416071809152453627382938465749676859789");
    private static final BigInteger BASE = new BigInteger("1234567890123456789012345");

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        BigInteger minhaChaveSecreta = null;
        BigInteger minhaChavePublica = null;
        BigInteger chavePublicaDoComunicante = null;
        BigInteger nossaChaveCompartilhada = null;

        try {
            System.out.print("Escolha a sua chave secreta: ");
            minhaChaveSecreta = new BigInteger(leitor.readLine());

            minhaChavePublica = BASE.modPow(minhaChaveSecreta, PRIMO);
            System.out.println("Minha chave pública: " + minhaChavePublica);

            System.out.print("Digite a chave pública do comunicante: ");
            chavePublicaDoComunicante = new BigInteger(leitor.readLine());

            nossaChaveCompartilhada = chavePublicaDoComunicante.modPow(minhaChaveSecreta, PRIMO);

            System.out.println("Chave compartilhada: " + nossaChaveCompartilhada);
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}
