package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Producto {
    public Producto(int codigoBarras, String nombre, String marca, String categoria, String tipoProducto,
            String fechaVencimiento, int idPedido, int idSucursal, int infoDetalles, int infoMedicion,
            int infoAlmacenamiento) {
        super();
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.tipoProducto = tipoProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.idPedido = idPedido;
        this.idSucursal = idSucursal;
        this.infoDetalles = infoDetalles;
        this.infoMedicion = infoMedicion;
        this.infoAlmacenamiento = infoAlmacenamiento;
    }
    int codigoBarras;
    String nombre;
    String marca;
    String categoria;
    String tipoProducto;
    String fechaVencimiento;
    int idPedido;
    int idSucursal;
    int infoDetalles;
    int infoMedicion;
    int infoAlmacenamiento;
    public int getCodigoBarras() {
        return codigoBarras;
    }
    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getTipoProducto() {
        return tipoProducto;
    }
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    public int getInfoDetalles() {
        return infoDetalles;
    }
    public void setInfoDetalles(int infoDetalles) {
        this.infoDetalles = infoDetalles;
    }
    public int getInfoMedicion() {
        return infoMedicion;
    }
    public void setInfoMedicion(int infoMedicion) {
        this.infoMedicion = infoMedicion;
    }
    public int getInfoAlmacenamiento() {
        return infoAlmacenamiento;
    }
    public void setInfoAlmacenamiento(int infoAlmacenamiento) {
        this.infoAlmacenamiento = infoAlmacenamiento;
    }
    @Override
    public int hashCode() {
        return Objects.hash(categoria, codigoBarras, fechaVencimiento, idPedido, idSucursal, infoAlmacenamiento,
                infoDetalles, infoMedicion, marca, nombre, tipoProducto);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        Producto other = (Producto) obj;
        return Objects.equals(categoria, other.categoria) && codigoBarras == other.codigoBarras
                && Objects.equals(fechaVencimiento, other.fechaVencimiento) && idPedido == other.idPedido
                && idSucursal == other.idSucursal && infoAlmacenamiento == other.infoAlmacenamiento
                && infoDetalles == other.infoDetalles && infoMedicion == other.infoMedicion
                && Objects.equals(marca, other.marca) && Objects.equals(nombre, other.nombre)
                && Objects.equals(tipoProducto, other.tipoProducto);
    }
    @Override
    public String toString() {
        return "Producto [codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", marca=" + marca + ", categoria="
                + categoria + ", tipoProducto=" + tipoProducto + ", fechaVencimiento=" + fechaVencimiento
                + ", idPedido=" + idPedido + ", idSucursal=" + idSucursal + ", infoDetalles=" + infoDetalles
                + ", infoMedicion=" + infoMedicion + ", infoAlmacenamiento=" + infoAlmacenamiento + "]";
    }
}
