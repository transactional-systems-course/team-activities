package uniandes.isis2304.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil {
    /*
     * ****************************************************************
     * Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las
     * sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaSuperAndes.SQL;

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia general de la aplicación
     */
    private PersistenciaSuperAndes ps;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor
     *
     * @param ps - El Manejador de persistencia de la aplicación
     */
    public SQLUtil(PersistenciaSuperAndes ps) {
        this.ps = ps;
    }

    /**
     * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
     *
     * @param pm - El manejador de persistencia
     * @return El número de secuencia generado
     */
    public long nextval(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT " + ps.darSeqSuperAndes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
    }

    /**
     * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos
     *
     * @param pm - El manejador de persistencia
     * @return Un arreglo con el número de tuplas borradas en las tablas
     */
    public long[] limpiarSuperAndes(PersistenceManager pm) {
        Query qAlmacenamientoProducto = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaAlmacenamientoProducto());
        Query qCompra = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaCompra());
        Query qContenedor = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaContenedor());
        Query qDetallesProducto = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaDetallesProducto());
        Query qMedicionProducto = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaMedicionProducto());
        Query qPedido = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaPedido());
        Query qPedidosProveedor = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaPedidosProveedor());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaProducto());
        Query qProductosCompra = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaProductosCompra());
        Query qProductosProvistos = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaProductosProvistos());
        Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaPromocion());
        Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaProveedor());
        Query qReviewPedido = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaReviewPedido());
        Query qRol = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaRol());
        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaSucursal());
        Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaUsuario());

        long almacenamientoProductoEliminados = (long) qAlmacenamientoProducto.executeUnique();
        long compraEliminados = (long) qCompra.executeUnique();
        long contenedorEliminados = (long) qContenedor.executeUnique();
        long detallesProductoEliminados = (long) qDetallesProducto.executeUnique();
        long medicionProductoEliminados = (long) qMedicionProducto.executeUnique();
        long pedidoEliminados = (long) qPedido.executeUnique();
        long pedidosProveedorEliminados = (long) qPedidosProveedor.executeUnique();
        long productoEliminados = (long) qProducto.executeUnique();
        long productosCompraEliminados = (long) qProductosCompra.executeUnique();
        long productosProvistosEliminados = (long) qProductosProvistos.executeUnique();
        long promocionEliminados = (long) qPromocion.executeUnique();
        long proveedorEliminados = (long) qProveedor.executeUnique();
        long reviewPedidoEliminados = (long) qReviewPedido.executeUnique();
        long rolEliminados = (long) qRol.executeUnique();
        long sucursalEliminados = (long) qSucursal.executeUnique();
        long usuarioEliminados = (long) qUsuario.executeUnique();

        return new long[] { almacenamientoProductoEliminados, compraEliminados, contenedorEliminados,
                detallesProductoEliminados, medicionProductoEliminados,
                pedidoEliminados, pedidosProveedorEliminados, productoEliminados, productosCompraEliminados,
                productosProvistosEliminados,
                promocionEliminados, proveedorEliminados, reviewPedidoEliminados, rolEliminados, sucursalEliminados,
                usuarioEliminados };
    }

}
