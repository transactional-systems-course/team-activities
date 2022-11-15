package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class DetallesProducto {
    public DetallesProducto() {
        super();
        // TODO Auto-generated constructor stub
    }
    int id;
    long precio;
    long precioPorUnidadMedida;
    String presentacion;
    String CantidadPresentacion;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getPrecio() {
        return precio;
    }
    public void setPrecio(long precio) {
        this.precio = precio;
    }
    public long getPrecioPorUnidadMedida() {
        return precioPorUnidadMedida;
    }
    public void setPrecioPorUnidadMedida(long precioPorUnidadMedida) {
        this.precioPorUnidadMedida = precioPorUnidadMedida;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getCantidadPresentacion() {
        return CantidadPresentacion;
    }
    public void setCantidadPresentacion(String cantidadPresentacion) {
        CantidadPresentacion = cantidadPresentacion;
    }
    @Override
    public int hashCode() {
        return Objects.hash(CantidadPresentacion, id, precio, precioPorUnidadMedida, presentacion);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        DetallesProducto other = (DetallesProducto) obj;
        return Objects.equals(CantidadPresentacion, other.CantidadPresentacion) && id == other.id
                && precio == other.precio && precioPorUnidadMedida == other.precioPorUnidadMedida
                && Objects.equals(presentacion, other.presentacion);
    }
}
