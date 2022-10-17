package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Rol {
    public Rol(int id, String rol) {
        super();
        this.id = id;
        this.rol = rol;
    }
    int id;
    String rol;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    @Override
    public String toString() {
        return "Rol [id=" + id + ", rol=" + rol + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, rol);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rol other = (Rol) obj;
        return id == other.id && Objects.equals(rol, other.rol);
    }
}
