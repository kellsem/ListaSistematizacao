import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContatoManagement extends JFrame {
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

    public ContatoManagement() {
        listaContatos = new ListaContatos(); // Inicializa a lista de contatos

        setTitle("Gerenciamento de Contatos");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel para campos de entrada e botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Configura o GridLayout com espaçamento entre componentes

        panel.add(new JLabel("Nome:"));
        tfNome = new JTextField();
        panel.add(tfNome);

        panel.add(new JLabel("Telefone:"));
        tfTelefone = new JTextField();
        panel.add(tfTelefone);

        panel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        panel.add(tfEmail);

        panel.add(new JLabel("Pesquisar:"));
        tfPesquisa = new JTextField();
        panel.add(tfPesquisa);

        // Painel para botões
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 5, 10, 0)); // Configura o GridLayout para os botões com espaçamento

        btnAdicionar = new JButton("Adicionar Contato");
        btnPesquisar = new JButton("Pesquisar Contato");
        btnRemover = new JButton("Remover Contato");
        btnListar = new JButton("Listar Contatos");
        btnSair = new JButton("Sair");

        panelButtons.add(btnAdicionar);
        panelButtons.add(btnPesquisar);
        panelButtons.add(btnRemover);
        panelButtons.add(btnListar);
        panelButtons.add(btnSair);

        add(panel, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        taResultados = new JTextArea(10, 30);
        taResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taResultados);
        add(scrollPane, BorderLayout.SOUTH);

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

        User user = new User(nome, telefone, email);
        listaContatos.adicionarContato(user);
        JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso");
    }

    private void pesquisarContato() {
        String pesquisa = tfPesquisa.getText();
        User user = listaContatos.buscarContato(pesquisa);
        if (user != null) {
            taResultados.setText("Contato encontrado:\n" + user);
        } else {
            taResultados.setText("Contato não encontrado.");
        }
    }

    private void removerContato() {
        String pesquisa = tfPesquisa.getText();
        boolean removido = listaContatos.removerContato(pesquisa);
        if (removido) {
            JOptionPane.showMessageDialog(this, "Contato removido com sucesso");
        } else {
            JOptionPane.showMessageDialog(this, "Contato não encontrado.");
        }
    }

    private void listarContatos() {
        taResultados.setText(listaContatos.listarContatos());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContatoManagement());
    }
}
