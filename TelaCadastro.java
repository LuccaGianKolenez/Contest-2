import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

    private JTextField nomeTextField;
    private JTextField emailTextField;
    private JPasswordField senhaPasswordField;

    public TelaCadastro() {
        // Configurações da janela
        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Componentes
        JLabel nomeLabel = new JLabel("Nome:");
        nomeTextField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaPasswordField = new JPasswordField();

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        // Adiciona componentes à janela
        add(nomeLabel);
        add(nomeTextField);
        add(emailLabel);
        add(emailTextField);
        add(senhaLabel);
        add(senhaPasswordField);
        add(new JLabel()); // Espaço em branco
        add(cadastrarButton);

        // Exibe a janela
        setVisible(true);
    }

    private void cadastrar() {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        char[] senhaChars = senhaPasswordField.getPassword();
        String senha = new String(senhaChars);

        // Aqui você pode realizar o cadastro, por exemplo, enviando os dados para um banco de dados

        // Exemplo de saída para verificar os dados
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastro();
            }
        });
    }
}
