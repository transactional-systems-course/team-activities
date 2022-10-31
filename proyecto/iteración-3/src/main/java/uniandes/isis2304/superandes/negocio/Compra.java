package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class Compra {
	public Compra(int id, long valorCompraTotal, String urlFacturaElectronica, String estadoCompra, String comprador,
            String fechaCompra) {
        super();
        this.id = id;
        this.valorCompraTotal = valorCompraTotal;
        this.urlFacturaElectronica = urlFacturaElectronica;
        this.estadoCompra = estadoCompra;
        this.comprador = comprador;
        this.fechaCompra = fechaCompra;
    }
    int id;
	long valorCompraTotal;
	String urlFacturaElectronica;
	String estadoCompra;
	String comprador;
	String fechaCompra;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getValorCompraTotal() {
        return valorCompraTotal;
    }
    public void setValorCompraTotal(long valorCompraTotal) {
        this.valorCompraTotal = valorCompraTotal;
    }
    public String getUrlFacturaElectronica() {
        return urlFacturaElectronica;
    }
    public void setUrlFacturaElectronica(String urlFacturaElectronica) {
        this.urlFacturaElectronica = urlFacturaElectronica;
    }
    public String getEstadoCompra() {
        return estadoCompra;
    }
    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
    public String getComprador() {
        return comprador;
    }
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }
    public String getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    @Override
    public String toString() {
        return "Compra [id=" + id + ", valorCompraTotal=" + valorCompraTotal + ", urlFacturaElectronica="
                + urlFacturaElectronica + ", estadoCompra=" + estadoCompra + ", comprador=" + comprador
                + ", fechaCompra=" + fechaCompra + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(comprador, estadoCompra, fechaCompra, id, urlFacturaElectronica, valorCompraTotal);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compra other = (Compra) obj;
        return Objects.equals(comprador, other.comprador) && Objects.equals(estadoCompra, other.estadoCompra)
                && Objects.equals(fechaCompra, other.fechaCompra) && id == other.id
                && Objects.equals(urlFacturaElectronica, other.urlFacturaElectronica)
                && valorCompraTotal == other.valorCompraTotal;
    }
	
	
    
}
