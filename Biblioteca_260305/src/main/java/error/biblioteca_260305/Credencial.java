package error.biblioteca_260305;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Credencial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_credencial;

    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructores, getters y setters
    public Credencial() {}
    public Credencial(String username, String password) { this.username = username; this.password = password; }
    public int getId_credencial() { return id_credencial; }
    public void setId_credencial(int id_credencial) { this.id_credencial = id_credencial; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}