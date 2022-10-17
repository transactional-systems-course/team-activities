package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class ReviewPedido {
    public ReviewPedido(int id, long porcentajeCalificacionCalidad, String comentariosCalificacionCalidad, int idPedido,
            int idProveedor) {
        super();
        this.id = id;
        this.porcentajeCalificacionCalidad = porcentajeCalificacionCalidad;
        this.comentariosCalificacionCalidad = comentariosCalificacionCalidad;
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
    }
    int id;
    long porcentajeCalificacionCalidad;
    String comentariosCalificacionCalidad;
    int idPedido;
    int idProveedor;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getPorcentajeCalificacionCalidad() {
        return porcentajeCalificacionCalidad;
    }
    public void setPorcentajeCalificacionCalidad(long porcentajeCalificacionCalidad) {
        this.porcentajeCalificacionCalidad = porcentajeCalificacionCalidad;
    }
    public String getComentariosCalificacionCalidad() {
        return comentariosCalificacionCalidad;
    }
    public void setComentariosCalificacionCalidad(String comentariosCalificacionCalidad) {
        this.comentariosCalificacionCalidad = comentariosCalificacionCalidad;
    }
    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    @Override
    public String toString() {
        return "ReviewPedido [id=" + id + ", porcentajeCalificacionCalidad=" + porcentajeCalificacionCalidad
                + ", comentariosCalificacionCalidad=" + comentariosCalificacionCalidad + ", idPedido=" + idPedido
                + ", idProveedor=" + idProveedor + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(comentariosCalificacionCalidad, id, idPedido, idProveedor, porcentajeCalificacionCalidad);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReviewPedido other = (ReviewPedido) obj;
        return Objects.equals(comentariosCalificacionCalidad, other.comentariosCalificacionCalidad) && id == other.id
                && idPedido == other.idPedido && idProveedor == other.idProveedor
                && porcentajeCalificacionCalidad == other.porcentajeCalificacionCalidad;
    }
}
