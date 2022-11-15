package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Usuario {
    public Usuario(int numeroDocumento, String nombre, String tipoCliente, String tipoDocumento, String palabraClave,
            String correo, int idRol, String direccion, int puntosAcumulados, int idSucursal) {
        super();
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.tipoCliente = tipoCliente;
        this.tipoDocumento = tipoDocumento;
        this.palabraClave = palabraClave;
        this.correo = correo;
        this.idRol = idRol;
        this.direccion = direccion;
        this.puntosAcumulados = puntosAcumulados;
        this.idSucursal = idSucursal;
    }
    int numeroDocumento;
    String nombre;
    String tipoCliente;
    String tipoDocumento;
    String palabraClave;
    String correo;
    int idRol;
    String direccion;
    int puntosAcumulados;
    int idSucursal;
    @Override
    public String toString() {
        return "Usuario [numeroDocumento=" + numeroDocumento + ", nombre=" + nombre + ", tipoCliente=" + tipoCliente
                + ", tipoDocumento=" + tipoDocumento + ", palabraClave=" + palabraClave + ", correo=" + correo
                + ", idRol=" + idRol + ", direccion=" + direccion + ", puntosAcumulados=" + puntosAcumulados
                + ", idSucursal=" + idSucursal + "]";
    }
    public int getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getPalabraClave() {
        return palabraClave;
    }
    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }
    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    @Override
    public int hashCode() {
        return Objects.hash(correo, direccion, idRol, idSucursal, nombre, numeroDocumento, palabraClave,
                puntosAcumulados, tipoCliente, tipoDocumento);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(correo, other.correo) && Objects.equals(direccion, other.direccion)
                && idRol == other.idRol && idSucursal == other.idSucursal && Objects.equals(nombre, other.nombre)
                && numeroDocumento == other.numeroDocumento && Objects.equals(palabraClave, other.palabraClave)
                && puntosAcumulados == other.puntosAcumulados && Objects.equals(tipoCliente, other.tipoCliente)
                && Objects.equals(tipoDocumento, other.tipoDocumento);
    }

}
