package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class ProductosProvistos {
    public ProductosProvistos(int idProveedor, int idProducto) {
        super();
        this.idProveedor = idProveedor;
        this.idProducto = idProducto;
    }
    int idProveedor;
    int idProducto;
    public int getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    @Override
    public String toString() {
        return "ProductosProvistos [idProveedor=" + idProveedor + ", idProducto=" + idProducto + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idProveedor);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        ProductosProvistos other = (ProductosProvistos) obj;
        return idProducto == other.idProducto && idProveedor == other.idProveedor;
    }

}
