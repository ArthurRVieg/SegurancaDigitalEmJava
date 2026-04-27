import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visao extends JFrame {
    public static final long serialVersionUID = 1L;

    private JTextArea txtTexto = new JTextArea();
    private JScrollPane jspTexto = new JScrollPane(txtTexto);

    private JTextField txtResumo = new JTextField();

    private JButton btnCalcular = new JButton("Calcular");

    public static void main(String[] args) {
        new Visao().setVisible(true);
    }
    // Método construtor da classe
    public Visao() {
        // Configuração da janela
        setTitle("Cálculo do resumo Uniderecional SHA-256");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Configuração da área da texto
        jspTexto.setBounds(10, 10, 465, 280);
        add(jspTexto);
        txtTexto.setLineWrap(true);

        // Configuração da caixa de resumo
        txtResumo.setBounds(10, 300, 465, 20);
        add(txtResumo);

        // Configuração do botão
        btnCalcular.setBounds(190, 330, 100, 20);
        add(btnCalcular);
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    txtResumo.setText(SHA256.calcularHash(txtTexto.getText()));
                } catch (Exception erro) {}
            }
        });
    }
}
