package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Sucursal {
    public Sucursal(int id, String ciudad, String direccion, String nombre, String sizeSucursal,
            String segmentoObjetivo) {
        super();
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.sizeSucursal = sizeSucursal;
        this.segmentoObjetivo = segmentoObjetivo;
    }
    int id;
    String ciudad;
    String direccion;
    String nombre;
    String sizeSucursal;
    String segmentoObjetivo;
    @Override
    public int hashCode() {
        return Objects.hash(ciudad, direccion, id, nombre, segmentoObjetivo, sizeSucursal);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sucursal other = (Sucursal) obj;
        return Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion) && id == other.id
                && Objects.equals(nombre, other.nombre) && Objects.equals(segmentoObjetivo, other.segmentoObjetivo)
                && Objects.equals(sizeSucursal, other.sizeSucursal);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSizeSucursal() {
        return sizeSucursal;
    }
    public void setSizeSucursal(String sizeSucursal) {
        this.sizeSucursal = sizeSucursal;
    }
    public String getSegmentoObjetivo() {
        return segmentoObjetivo;
    }
    public void setSegmentoObjetivo(String segmentoObjetivo) {
        this.segmentoObjetivo = segmentoObjetivo;
    }
    @Override
    public String toString() {
        return "Sucursal [id=" + id + ", ciudad=" + ciudad + ", direccion=" + direccion + ", nombre=" + nombre
                + ", sizeSucursal=" + sizeSucursal + ", segmentoObjetivo=" + segmentoObjetivo + "]";
    }
}
