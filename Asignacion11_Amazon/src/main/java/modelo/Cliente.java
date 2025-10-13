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
    @Column(name = "id_cliente")
    private int id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "ap_paterno", length = 50)
    private String apPaterno;
    
    @Column(name = "ap_materno", length = 50)
    private String apMaterno;

    @Column(name = "fch_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ElementCollection
    @CollectionTable(name = "clientes_telefonos", joinColumns = @JoinColumn(name = "id_cliente"))
    @Column(name = "telefono", length = 15)
    private List<String> telefonos;

    @ElementCollection
    @CollectionTable(name = "clientes_correos", joinColumns = @JoinColumn(name = "id_cliente"))
    @Column(name = "correo", length = 100)
    private List<String> correos;

    public Cliente() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getCorreos() {
        return correos;
    }

    public void setCorreos(List<String> correos) {
        this.correos = correos;
    }

    
    
}