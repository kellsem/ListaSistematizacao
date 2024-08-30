import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciadorDeContatosGUI extends JDialog {
    private JTextField tfNome;
    private JTextField tfTelefone;
    private JTextField tfEmail;
    private JTextField tfPesquisa;
    private JButton btnAdicionar;
    private JButton btnPesquisar;
    private JButton btnRemover;
    private JButton btnListar;
    private JButton btnSair;
    private JTextArea taResultados;
    private ListaContatos listaContatos;

    public GerenciadorDeContatosGUI(JFrame parent) {
        super(parent);
        setTitle("Gerenciador de Contatos");
        setLayout(new GridBagLayout());
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        listaContatos = new ListaContatos(); // Inicializa a lista de contatos

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Campo de Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        tfNome = new JTextField(20);
        add(tfNome, gbc);

        // Campo de Telefone
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        tfTelefone = new JTextField(20);
        add(tfTelefone, gbc);

        // Campo de Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        tfEmail = new JTextField(20);
        add(tfEmail, gbc);

        // Campo de Pesquisa
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Pesquisar:"), gbc);

        gbc.gridx = 1;
        tfPesquisa = new JTextField(20);
        add(tfPesquisa, gbc);

        // Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 5, 5));

        btnAdicionar = new JButton("Adicionar Contato");
        btnPesquisar = new JButton("Pesquisar Contato");
        btnRemover = new JButton("Remover Contato");
        btnListar = new JButton("Listar Contatos");
        btnSair = new JButton("Sair");

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnPesquisar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnSair);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Área de Resultados
        taResultados = new JTextArea(10, 30);
        taResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taResultados);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Adiciona os listeners para os botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarContato();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerContato();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarContatos();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void adicionarContato() {
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String email = tfEmail.getText();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(nome, email, telefone); // Usa a classe User
        listaContatos.adicionarContato(user);
        JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso");
    }

    private void pesquisarContato() {
        String pesquisa = tfPesquisa.getText();
        User user = listaContatos.buscarContato(pesquisa); // Usa a classe User
        if (user != null) {
            taResultados.setText("Contato encontrado: " + user);
        } else {
            taResultados.setText("Contato não encontrado.");
        }
    }

    private void removerContato() {
        String pesquisa = tfPesquisa.getText();
        boolean removido = listaContatos.removerContato(pesquisa); // Usa a classe User
        if (removido) {
            JOptionPane.showMessageDialog(this, "Contato removido com sucesso");
        } else {
            JOptionPane.showMessageDialog(this, "Contato não encontrado.");
        }
    }

    private void listarContatos() {
        taResultados.setText(listaContatos.listarContatos()); // Usa a classe User
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();  // Criação do frame pai
        GerenciadorDeContatosGUI dialog = new GerenciadorDeContatosGUI(frame);
        dialog.setVisible(true);
    }
}
