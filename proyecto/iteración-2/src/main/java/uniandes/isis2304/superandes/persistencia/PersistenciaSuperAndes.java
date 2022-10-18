package uniandes.isis2304.superandes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superandes.negocio.Bar;
import uniandes.isis2304.superandes.negocio.Pedido;

/**
 * Clase para el manejador de persistencia del proyecto SuperAndes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en
 * ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para
 * comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven,
 * SQLTipoBebida y SQLVisitan, que son
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaSuperAndes {
    /*
     * ****************************************************************
     * Constantes
     *****************************************************************/
    /**
     * Logger para escribir la traza de la ejecución
     */
    private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

    /**
     * Cadena para indicar el tipo de sentencias que se va a utilizar en una
     * consulta
     */
    public final static String SQL = "javax.jdo.query.SQL";

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/
    /**
     * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
     */
    private static PersistenciaSuperAndes instance;

    /**
     * Fábrica de Manejadores de persistencia, para el manejo correcto de las
     * transacciones
     */
    private PersistenceManagerFactory pmf;

    /**
     * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su
     * orden:
     * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
     */
    private List<String> tablas;

    /**
     * Atributo para el acceso a las sentencias SQL propias a
     * PersistenciaSuperAndes
     */
    private SQLUtil sqlUtil;

    /**
     * Atributo para el acceso a la tabla ALMACENAMIENTO_PRODUCTO de la base de
     * datos
     */
    private SQLAlmacenamientoProducto sqlAlmacenamientoProducto;

    /**
     * Atributo para el acceso a la tabla COMPRA de la base de
     * datos
     */
    private SQLCompra sqlCompra;

    /**
     * Atributo para el acceso a la tabla CONTENEDOR de la base de
     * datos
     */
    private SQLContenedor sqlContenedor;

    /**
     * Atributo para el acceso a la tabla DETALLES_PRODUCTO de la base de
     * datos
     */
    private SQLDetallesProducto sqlDetallesProducto;

    /**
     * Atributo para el acceso a la tabla MEDICION_PRODUCTO de la base de
     * datos
     */
    private SQLMedicionProducto sqlMedicionProducto;

    /**
     * Atributo para el acceso a la tabla PEDIDO de la base de
     * datos
     */
    private SQLPedido sqlPedido;

    /**
     * Atributo para el acceso a la tabla PEDIDOS_PROVEEDOR de la base de
     * datos
     */
    private SQLPedidosProveedor sqlPedidosProveedor;

    /**
     * Atributo para el acceso a la tabla PRODUCTO de la base de
     * datos
     */
    private SQLProducto sqlProducto;

    /**
     * Atributo para el acceso a la tabla PRODUCTOS_COMPRA de la base de
     * datos
     */
    private SQLProductosCompra sqlProductosCompra;
    /**
     * Atributo para el acceso a la tabla PRODUCTOS_PROVISTOS de la base de
     * datos
     */
    private SQLProductosProvistos sqlProductosProvistos;

    /**
     * Atributo para el acceso a la tabla PROMOCION de la base de
     * datos
     */
    private SQLPromocion sqlPromocion;

    /**
     * Atributo para el acceso a la tabla PROVEEDOR de la base de
     * datos
     */
    private SQLProveedor sqlProveedor;

    /**
     * Atributo para el acceso a la tabla REVIEW_PEDIDO de la base de
     * datos
     */
    private SQLReviewPedido sqlReviewPedido;

    /**
     * Atributo para el acceso a la tabla ROL de la base de
     * datos
     */
    private SQLRol sqlRol;

    /**
     * Atributo para el acceso a la tabla SUCURSAL de la base de
     * datos
     */
    private SQLSucursal sqlSucursal;

    /**
     * Atributo para el acceso a la tabla USUARIO de la base de
     * datos
     */
    private SQLUsuario sqlUsuario;

    /*
     * ****************************************************************
     * Métodos del MANEJADOR DE PERSISTENCIA
     *****************************************************************/

    /**
     * Constructor privado con valores por defecto - Patrón SINGLETON
     */
    private PersistenciaSuperAndes() {
        pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
        crearClasesSQL();

        // Define los nombres por defecto de las tablas de la base de datos
        tablas = new LinkedList<String>();
        tablas.add("SuperAndes_sequence");
        tablas.add("ALMACENAMIENTO_PRODUCTO");
        tablas.add("COMPRA");
        tablas.add("CONTENEDOR");
        tablas.add("DETALLES_PRODUCTO");
        tablas.add("MEDICION_PRODUCTO");
        tablas.add("PEDIDO");
        tablas.add("PEDIDOS_PROVEEDOR");
        tablas.add("PRODUCTO");
        tablas.add("PRODUCTOS_COMPRA");
        tablas.add("PRODUCTOS_PROVISTOS");
        tablas.add("PROMOCION");
        tablas.add("PROVEEDOR");
        tablas.add("REVIEW_PEDIDO");
        tablas.add("ROL");
        tablas.add("SUCURSAL");
        tablas.add("USUARIO");
    }

    /**
     * Constructor privado, que recibe los nombres de las tablas en un objeto Json -
     * Patrón SINGLETON
     * 
     * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de
     *                    la unidad de persistencia a manejar
     */
    private PersistenciaSuperAndes(JsonObject tableConfig) {
        crearClasesSQL();
        tablas = leerNombresTablas(tableConfig);

        String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
        log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
        pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
    }

    /**
     * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón
     *         SINGLETON
     */
    public static PersistenciaSuperAndes getInstance() {
        if (instance == null) {
            instance = new PersistenciaSuperAndes();
        }
        return instance;
    }

    /**
     * Constructor que toma los nombres de las tablas de la base de datos del objeto
     * tableConfig
     *
     * @param tableConfig - El objeto JSON con los nombres de las tablas
     * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón
     *         SINGLETON
     */
    public static PersistenciaSuperAndes getInstance(JsonObject tableConfig) {
        if (instance == null) {
            instance = new PersistenciaSuperAndes(tableConfig);
        }
        return instance;
    }

    /**
     * Cierra la conexión con la base de datos
     */
    public void cerrarUnidadPersistencia() {
        pmf.close();
        instance = null;
    }

    /**
     * Genera una lista con los nombres de las tablas de la base de datos
     *
     * @param tableConfig - El objeto Json con los nombres de las tablas
     * @return La lista con los nombres del secuenciador y de las tablas
     */
    private List<String> leerNombresTablas(JsonObject tableConfig) {
        JsonArray nombres = tableConfig.getAsJsonArray("tablas");

        List<String> resp = new LinkedList<String>();
        for (JsonElement nom : nombres) {
            resp.add(nom.getAsString());
        }

        return resp;
    }

    /**
     * Crea los atributos de clases de apoyo SQL
     */
    private void crearClasesSQL() {
        sqlAlmacenamientoProducto = new SQLAlmacenamientoProducto(this);
        sqlCompra = new SQLCompra(this);
        sqlContenedor = new SQLContenedor(this);
        sqlDetallesProducto = new SQLDetallesProducto(this);
        sqlMedicionProducto = new SQLMedicionProducto(this);
        sqlPedido = new SQLPedido(this);
        sqlPedidosProveedor = new SQLPedidosProveedor(this);
        sqlProducto = new SQLProducto(this);
        sqlProductosCompra = new SQLProductosCompra(this);
        sqlProductosProvistos = new SQLProductosProvistos(this);
        sqlPromocion = new SQLPromocion(this);
        sqlProveedor = new SQLProveedor(this);
        sqlReviewPedido = new SQLReviewPedido(this);
        sqlRol = new SQLRol(this);
        sqlSucursal = new SQLSucursal(this);
        sqlUsuario = new SQLUsuario(this);
    }

    /**
     * @return La cadena de caracteres con el nombre del secuenciador de SuperAndes
     */
    public String darSeqSuperAndes() {
        return tablas.get(0);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de
     *         AlmacenamientoProducto de
     *         SuperAndes
     */
    public String darTablaAlmacenamientoProducto() {
        return tablas.get(1);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Compra de
     *         SuperAndes
     */
    public String darTablaCompra() {
        return tablas.get(2);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Contenedor de
     *         SuperAndes
     */
    public String darTablaContenedor() {
        return tablas.get(3);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de DetallesProducto
     *         de
     *         SuperAndes
     */
    public String darTablaDetallesProducto() {
        return tablas.get(4);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de MedicionProducto
     *         de
     *         SuperAndes
     */
    public String darTablaMedicionProducto() {
        return tablas.get(5);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Pedido de
     *         SuperAndes
     */
    public String darTablaPedido() {
        return tablas.get(6);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de PedidosProveedor
     *         de
     *         SuperAndes
     */
    public String darTablaPedidosProveedor() {
        return tablas.get(7);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Producto de
     *         SuperAndes
     */
    public String darTablaProducto() {
        return tablas.get(8);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de ProductosCompra
     *         de
     *         SuperAndes
     */
    public String darTablaProductosCompra() {
        return tablas.get(9);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de
     *         ProductosProvistos de
     *         SuperAndes
     */
    public String darTablaProductosProvistos() {
        return tablas.get(10);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Promocion de
     *         SuperAndes
     */
    public String darTablaPromocion() {
        return tablas.get(11);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Proveedor de
     *         SuperAndes
     */
    public String darTablaProveedor() {
        return tablas.get(12);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de ReviewPedido de
     *         SuperAndes
     */
    public String darTablaReviewPedido() {
        return tablas.get(13);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Rol de
     *         SuperAndes
     */
    public String darTablaRol() {
        return tablas.get(14);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Sucursal de
     *         SuperAndes
     */
    public String darTablaSucursal() {
        return tablas.get(15);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de Usuario de
     *         SuperAndes
     */
    public String darTablaUsuario() {
        return tablas.get(16);
    }

    /**
     * Transacción para el generador de secuencia de SuperAndes
     * Adiciona entradas al log de la aplicación
     *
     * @return El siguiente número del secuenciador de SuperAndes
     */
    private long nextval() {
        long resp = sqlUtil.nextval(pmf.getPersistenceManager());
        log.trace("Generando secuencia: " + resp);
        return resp;
    }

    /**
     * Extrae el mensaje de la exception JDODataStoreException embebido en la
     * Exception e, que da el detalle específico del problema encontrado
     *
     * @param e - La excepción que ocurrio
     * @return El mensaje de la excepción JDO
     */
    private String darDetalleException(Exception e) {
        String resp = "";
        if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
            JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
            return je.getNestedExceptions()[0].getMessage();
        }
        return resp;
    }

    /*
     * ****************************************************************
     * Métodos para manejar PROVEEDOR
     *****************************************************************/
    /**
     * Método que consulta todas las tuplas en la tabla Pedidos con un
     * identificador dado
     *
     * @param nombreProveedor - El nombre del proveedor
     * @return la lista de objetos Pedidos
     */
    public List<Pedido> consultarPedidosProveedor(String nombreProveedor) {
        return sqlProveedor.consultarPedidosProveedor(pmf.getPersistenceManager(), nombreProveedor);
    }

    /*
     * ****************************************************************
     * Métodos para manejar BODEGA
     *****************************************************************/
    /**
     * Método que consulta el índice de ocupación de una bodega
     *
     * @param idBodega - El identificador de la bódega
     * @return el índice de ocupación de la bodega
     */
    public long consultarIndiceOcupacionBodega(String idBodega) {
        return sqlContenedor.consultarIndiceOcupacionBodega(pmf.getPersistenceManager(), idBodega);
    }

    /*
     * ****************************************************************
     * Métodos para manejar SUCURSAL
     *****************************************************************/
    /**
     * Método que consulta el dinero recolectado en una sucursal
     *
     * @param idSucursal - El identificador de la bódega
     * @return el dinero recolectado
     */
    public long consultarDineroRecolectado(String idSucursal) {
        return sqlSucursal.consultarDineroRecolectado(pmf.getPersistenceManager(), idSucursal);
    }

    /*
     * ****************************************************************
     * Métodos para manejar VENTA
     * registrarVenta
     * consultarVentasPorCliente
     *****************************************************************/

    /*
     * ****************************************************************
     * Métodos para manejar ESTANTE
     *****************************************************************/
    /**
     * Método que aprovisiona un estante de productos
     *
     * @param idEstante  - El identificador del estante
     * @param idProducto - El identificador del producto a colocar
     * @return El número de tuplas modificadas
     */
    public long aprovisionarEstante(String idEstante, String idProducto) {
        return sqlContenedor.aprovisionarEstante(pmf.getPersistenceManager(), idEstante, idProducto);
    }

    /**
     * Método que consulta el índice de ocupación de un estante
     *
     * @param idEstante - El identificador del estante
     * @return el índice de ocupación del estante
     */
    public long consultarIndiceOcupacionEstante(String idEstante) {
        return sqlContenedor.consultarIndiceOcupacionEstante(pmf.getPersistenceManager(), idEstante);
    }

    /*
     * ****************************************************************
     * Métodos para manejar PEDIDO
     * crearPedido
     * registrarLlegadaPedido
     *****************************************************************/

    /*
     * ****************************************************************
     * Métodos para manejar PROMOCION
     * registrarPromocion
     * consultarPromosPopulares
     *****************************************************************/

    /*
     * ****************************************************************
     * Métodos para manejar PRODUCTO
     * consultarCaracteristicaProductos
     *****************************************************************/

    /**
     * Elimina todas las tuplas de todas las tablas de la base de datos de
     * SuperAndes
     * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL
     * ORDEN ES IMPORTANTE
     *
     * @return Un arreglo con 7 números que indican el número de tuplas borradas en
     *         las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
     *         TIPOBEBIDA, BEBEDOR y BAR, respectivamente
     */
    public long[] limpiarSuperAndes() {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long[] resp = sqlUtil.limpiarSuperAndes(pm);
            tx.commit();
            log.info("Borrada la base de datos");
            return resp;
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return new long[] { -1, -1, -1, -1, -1, -1, -1 };
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
}
