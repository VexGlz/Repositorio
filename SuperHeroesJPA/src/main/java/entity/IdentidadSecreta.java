package entity;
import jakarta.persistence.Embeddable; 

/**
 *
 * @author Erick
 */
@Embeddable 
public class IdentidadSecreta {
    private String nombreReal;
    private String ciudad;
    private String ocupacion;

    public IdentidadSecreta() {
    }

    public IdentidadSecreta(String nombreReal, String ciudad, String ocupacion) {
        this.nombreReal = nombreReal;
        this.ciudad = ciudad;
        this.ocupacion = ocupacion;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public String toString() {
        return "IdentidadSecreta{" + "nombreReal=" + nombreReal + ", ciudad=" + ciudad + ", ocupacion=" + ocupacion + '}';
    }
}