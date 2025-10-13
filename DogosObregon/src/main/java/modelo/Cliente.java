package modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_cliente")
    private int numCliente;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "ap_paterno", length = 50)
    private String apPaterno;
    
    @Column(name = "ap_materno", length = 50)
    private String apMaterno;

    @Column(name = "fch_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    // Relación 1:N con Pedido
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    // Mapeo de atributo multivaluado: Telefonos
    @ElementCollection
    @CollectionTable(name = "cliente_telefonos", joinColumns = @JoinColumn(name = "num_cliente"))
    @Column(name = "telefono")
    private List<String> telefonos;
    
    // Mapeo de atributo multivaluado: Preferencias
    @ElementCollection
    @CollectionTable(name = "cliente_preferencias", joinColumns = @JoinColumn(name = "num_cliente"))
    @Column(name = "preferencia")
    private List<String> preferencias;

    // Constructores, Getters y Setters
    public Cliente() {}

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }
    
    
}