import java.util.List;
import java.util.ArrayList;

public class ListaContatos {
    private List<User> contatos;

    public ListaContatos() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(User contato) {
        contatos.add(contato);
    }

    public User buscarContato(String nome) {
        for (User contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
    }

    public boolean removerContato(String nome) {
        User contato = buscarContato(nome);
        if (contato != null) {
            contatos.remove(contato);
            return true;
        }
        return false;
    }

    public String listarContatos() {
        StringBuilder sb = new StringBuilder();
        for (User contato : contatos) {
            sb.append(contato.toString()).append("\n");
        }
        return sb.toString();
    }
}
