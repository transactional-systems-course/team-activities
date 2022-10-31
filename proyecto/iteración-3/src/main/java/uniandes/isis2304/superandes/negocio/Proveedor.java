package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Proveedor {
    public Proveedor(int nit, String nombre, long calificacionPromedio) {
        super();
        this.nit = nit;
        this.nombre = nombre;
        this.calificacionPromedio = calificacionPromedio;
    }
    int nit;
    String nombre;
    long calificacionPromedio;
    public int getNit() {
        return nit;
    }
    public void setNit(int nit) {
        this.nit = nit;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public long getCalificacionPromedio() {
        return calificacionPromedio;
    }
    public void setCalificacionPromedio(long calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }
    @Override
    public String toString() {
        return "Proveedor [nit=" + nit + ", nombre=" + nombre + ", calificacionPromedio=" + calificacionPromedio + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(calificacionPromedio, nit, nombre);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Proveedor other = (Proveedor) obj;
        return calificacionPromedio == other.calificacionPromedio && nit == other.nit
                && Objects.equals(nombre, other.nombre);
    }
}
