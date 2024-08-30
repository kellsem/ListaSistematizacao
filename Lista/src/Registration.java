import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration extends JFrame {

    private JPanel RegistrarPainel;
    private JTextField tfNome;
    private JTextField tfTelefone;
    private JTextField tfEmail;
    private JLabel btnRemover;
    private JLabel btnListar;
    private JLabel btnSair;
    private JTextArea taResultados;
    private JTextField textField2;
    private JLabel listaContatos;
    private JTextArea textArea1;
    private JButton removerbutton;
    private JButton btnPesquisar;
    private JButton Sair;
    private JButton adicionarButton;
    private JLabel pesquisarLabel;
    private JButton pesquisarButton;
    private JLabel btnAdicionar;
    private JLabel tfRegistrar;
    private JTextField tfPesquisa;
    private JTextField textField1;
    private JButton button2;
    private JButton button1;
    private Connection connection;

    public Registration() {
        setTitle("Registro de Usuário");
        setContentPane(RegistrarPainel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Inicializa a conexão com o banco de dados
        try {
            String url = "jdbc:mysql://localhost:3306/contatos";
            String user = "root";
            String password = "9153R@y#";
            connection = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Adiciona ActionListener ao botão de adicionar
        adicionarButton.addActionListener(e -> registerUser());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerUser() {
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO users (nome, email, telefone) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao registrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Registration::new);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
