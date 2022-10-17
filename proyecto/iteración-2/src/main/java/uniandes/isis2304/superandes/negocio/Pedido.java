package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Pedido {
    public Pedido(int id, int cantidadRecompra, long precioCompraProducto, long precioTotalPedido,
            String fechaEsperadaEntrega, String fechaEntrega, String estado, int idSucursal) {
        super();
        this.id = id;
        this.cantidadRecompra = cantidadRecompra;
        this.precioCompraProducto = precioCompraProducto;
        this.precioTotalPedido = precioTotalPedido;
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
        FechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idSucursal = idSucursal;
    }
    int id;
    int cantidadRecompra;
    long precioCompraProducto;
    long precioTotalPedido;
    String fechaEsperadaEntrega;
    String FechaEntrega;
    String estado;
    int idSucursal;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCantidadRecompra() {
        return cantidadRecompra;
    }
    public void setCantidadRecompra(int cantidadRecompra) {
        this.cantidadRecompra = cantidadRecompra;
    }
    public long getPrecioCompraProducto() {
        return precioCompraProducto;
    }
    public void setPrecioCompraProducto(long precioCompraProducto) {
        this.precioCompraProducto = precioCompraProducto;
    }
    public long getPrecioTotalPedido() {
        return precioTotalPedido;
    }
    public void setPrecioTotalPedido(long precioTotalPedido) {
        this.precioTotalPedido = precioTotalPedido;
    }
    public String getFechaEsperadaEntrega() {
        return fechaEsperadaEntrega;
    }
    public void setFechaEsperadaEntrega(String fechaEsperadaEntrega) {
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
    }
    public String getFechaEntrega() {
        return FechaEntrega;
    }
    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    @Override
    public String toString() {
        return "Pedido [id=" + id + ", cantidadRecompra=" + cantidadRecompra + ", precioCompraProducto="
                + precioCompraProducto + ", precioTotalPedido=" + precioTotalPedido + ", fechaEsperadaEntrega="
                + fechaEsperadaEntrega + ", FechaEntrega=" + FechaEntrega + ", estado=" + estado + ", idSucursal="
                + idSucursal + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(FechaEntrega, cantidadRecompra, estado, fechaEsperadaEntrega, id, idSucursal,
                precioCompraProducto, precioTotalPedido);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        return Objects.equals(FechaEntrega, other.FechaEntrega) && cantidadRecompra == other.cantidadRecompra
                && Objects.equals(estado, other.estado)
                && Objects.equals(fechaEsperadaEntrega, other.fechaEsperadaEntrega) && id == other.id
                && idSucursal == other.idSucursal && precioCompraProducto == other.precioCompraProducto
                && precioTotalPedido == other.precioTotalPedido;
    }
    
}
