package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Promocion {
    public Promocion(int id, long rebajaEnPrecio, String tipoPromocion, String fechaInicio, String fechaFin,
            int idProducto, int cantUnidadesDisponibles, int totalUnidadesOfrecidas) {
        super();
        this.id = id;
        this.rebajaEnPrecio = rebajaEnPrecio;
        this.tipoPromocion = tipoPromocion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idProducto = idProducto;
        this.cantUnidadesDisponibles = cantUnidadesDisponibles;
        this.totalUnidadesOfrecidas = totalUnidadesOfrecidas;
    }
    int id;
    long rebajaEnPrecio;
    String tipoPromocion;
    String fechaInicio;
    String fechaFin;
    int idProducto;
    int cantUnidadesDisponibles;
    int totalUnidadesOfrecidas;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getRebajaEnPrecio() {
        return rebajaEnPrecio;
    }
    public void setRebajaEnPrecio(long rebajaEnPrecio) {
        this.rebajaEnPrecio = rebajaEnPrecio;
    }
    public String getTipoPromocion() {
        return tipoPromocion;
    }
    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getCantUnidadesDisponibles() {
        return cantUnidadesDisponibles;
    }
    public void setCantUnidadesDisponibles(int cantUnidadesDisponibles) {
        this.cantUnidadesDisponibles = cantUnidadesDisponibles;
    }
    public int getTotalUnidadesOfrecidas() {
        return totalUnidadesOfrecidas;
    }
    public void setTotalUnidadesOfrecidas(int totalUnidadesOfrecidas) {
        this.totalUnidadesOfrecidas = totalUnidadesOfrecidas;
    }
    @Override
    public int hashCode() {
        return Objects.hash(cantUnidadesDisponibles, fechaFin, fechaInicio, id, idProducto, rebajaEnPrecio,
                tipoPromocion, totalUnidadesOfrecidas);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Promocion other = (Promocion) obj;
        return cantUnidadesDisponibles == other.cantUnidadesDisponibles && Objects.equals(fechaFin, other.fechaFin)
                && Objects.equals(fechaInicio, other.fechaInicio) && id == other.id && idProducto == other.idProducto
                && rebajaEnPrecio == other.rebajaEnPrecio && Objects.equals(tipoPromocion, other.tipoPromocion)
                && totalUnidadesOfrecidas == other.totalUnidadesOfrecidas;
    }
    @Override
    public String toString() {
        return "Promocion [id=" + id + ", rebajaEnPrecio=" + rebajaEnPrecio + ", tipoPromocion=" + tipoPromocion
                + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idProducto=" + idProducto
                + ", cantUnidadesDisponibles=" + cantUnidadesDisponibles + ", totalUnidadesOfrecidas="
                + totalUnidadesOfrecidas + "]";
    }
}
