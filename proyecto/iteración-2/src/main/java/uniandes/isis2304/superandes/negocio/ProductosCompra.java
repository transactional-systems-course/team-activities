package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class ProductosCompra {
    public ProductosCompra(int idCompra, int idProducto, int cantUnidadesCompradas) {
        super();
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantUnidadesCompradas = cantUnidadesCompradas;
    }
    int idCompra;
    int idProducto;
    int cantUnidadesCompradas;
    public int getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getCantUnidadesCompradas() {
        return cantUnidadesCompradas;
    }
    public void setCantUnidadesCompradas(int cantUnidadesCompradas) {
        this.cantUnidadesCompradas = cantUnidadesCompradas;
    }
    @Override
    public int hashCode() {
        return Objects.hash(cantUnidadesCompradas, idCompra, idProducto);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductosCompra other = (ProductosCompra) obj;
        return cantUnidadesCompradas == other.cantUnidadesCompradas && idCompra == other.idCompra
                && idProducto == other.idProducto;
    }
    @Override
    public String toString() {
        return "ProductosCompra [idCompra=" + idCompra + ", idProducto=" + idProducto + ", cantUnidadesCompradas="
                + cantUnidadesCompradas + "]";
    }
    
}
