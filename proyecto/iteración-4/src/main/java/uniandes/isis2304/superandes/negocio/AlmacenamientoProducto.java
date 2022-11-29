package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class AlmacenamientoProducto {
    public AlmacenamientoProducto(int id, int idContenedorActual, int existenciasActuales, int nivelReorden) {
        super();
        this.id = id;
        this.idContenedorActual = idContenedorActual;
        this.existenciasActuales = existenciasActuales;
        this.nivelReorden = nivelReorden;
    }
    int id;
    int idContenedorActual;
    int existenciasActuales;
    int nivelReorden;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdContenedorActual() {
        return idContenedorActual;
    }
    public void setIdContenedorActual(int idContenedorActual) {
        this.idContenedorActual = idContenedorActual;
    }
    public int getExistenciasActuales() {
        return existenciasActuales;
    }
    public void setExistenciasActuales(int existenciasActuales) {
        this.existenciasActuales = existenciasActuales;
    }
    public int getNivelReorden() {
        return nivelReorden;
    }
    public void setNivelReorden(int nivelReorden) {
        this.nivelReorden = nivelReorden;
    }
    @Override
    public int hashCode() {
        return Objects.hash(existenciasActuales, id, idContenedorActual, nivelReorden);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        AlmacenamientoProducto other = (AlmacenamientoProducto) obj;
        return existenciasActuales == other.existenciasActuales && id == other.id
                && idContenedorActual == other.idContenedorActual && nivelReorden == other.nivelReorden;
    }

}
