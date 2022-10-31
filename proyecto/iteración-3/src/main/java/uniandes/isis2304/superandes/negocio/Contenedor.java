package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Contenedor {
    public Contenedor(int id, String tipoContenedor, String tipoProducto, long capacidadMaximaVolumen,
            long capacidadMaximaPeso, int nivelAbastecimiento, int idSucursal) {
        super();
        this.id = id;
        this.tipoContenedor = tipoContenedor;
        this.tipoProducto = tipoProducto;
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
        this.capacidadMaximaPeso = capacidadMaximaPeso;
        this.nivelAbastecimiento = nivelAbastecimiento;
        this.idSucursal = idSucursal;
    }
    int id;
    String tipoContenedor;
    String tipoProducto;
    long capacidadMaximaVolumen;
    long capacidadMaximaPeso;
    int nivelAbastecimiento;
    int idSucursal;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoContenedor() {
        return tipoContenedor;
    }
    public void setTipoContenedor(String tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }
    public String getTipoProducto() {
        return tipoProducto;
    }
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    public long getCapacidadMaximaVolumen() {
        return capacidadMaximaVolumen;
    }
    public void setCapacidadMaximaVolumen(long capacidadMaximaVolumen) {
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
    }
    public long getCapacidadMaximaPeso() {
        return capacidadMaximaPeso;
    }
    public void setCapacidadMaximaPeso(long capacidadMaximaPeso) {
        this.capacidadMaximaPeso = capacidadMaximaPeso;
    }
    public int getNivelAbastecimiento() {
        return nivelAbastecimiento;
    }
    public void setNivelAbastecimiento(int nivelAbastecimiento) {
        this.nivelAbastecimiento = nivelAbastecimiento;
    }
    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    @Override
    public String toString() {
        return "Contenedor [id=" + id + ", tipoContenedor=" + tipoContenedor + ", tipoProducto=" + tipoProducto
                + ", capacidadMaximaVolumen=" + capacidadMaximaVolumen + ", capacidadMaximaPeso=" + capacidadMaximaPeso
                + ", nivelAbastecimiento=" + nivelAbastecimiento + ", idSucursal=" + idSucursal + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(capacidadMaximaPeso, capacidadMaximaVolumen, id, idSucursal, nivelAbastecimiento,
                tipoContenedor, tipoProducto);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contenedor other = (Contenedor) obj;
        return capacidadMaximaPeso == other.capacidadMaximaPeso
                && capacidadMaximaVolumen == other.capacidadMaximaVolumen && id == other.id
                && idSucursal == other.idSucursal && nivelAbastecimiento == other.nivelAbastecimiento
                && Objects.equals(tipoContenedor, other.tipoContenedor)
                && Objects.equals(tipoProducto, other.tipoProducto);
    }
    
}
