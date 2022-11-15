package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class PedidosProveedor {
    public PedidosProveedor(int idPedido, int idProveedor) {
        super();
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
    }
    int idPedido;
    int idProveedor;
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
    public int hashCode() {
        return Objects.hash(idPedido, idProveedor);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        PedidosProveedor other = (PedidosProveedor) obj;
        return idPedido == other.idPedido && idProveedor == other.idProveedor;
    }
}
