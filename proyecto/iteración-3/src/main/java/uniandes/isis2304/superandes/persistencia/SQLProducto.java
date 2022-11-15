package uniandes.isis2304.superandes.persistencia;

import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto Producto de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
public class SQLProducto {
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
    public SQLProducto(PersistenciaSuperAndes ps) {
        this.ps = ps;
    }

    /**
     * TODO: CRUD
     */

    public long darDatosProductos(PersistanceManager pm, String idSucursal) {
        Query q = pm.newQuery(SQL,
        "SELECT * FROM"+
        "(((SELECT CODIGO_BARRAS, NOMBRE, SUM(VALOR_COMPRA_TOTAL) AS DINERO_ADQUIRIDO, NULL AS CANTIDADES_ADQUIRIDAS, 'MAYORES INGRESOS' AS CATEGORIA, "+
        "ID_SUCURSAL FROM DATOS_COMPRAS"+
        "GROUP BY CODIGO_BARRAS, NOMBRE, ID_SUCURSAL)"+
        "UNION ALL"+
        "(SELECT CODIGO_BARRAS, NOMBRE, NULL AS DINERO_ADQUIRIDO, SUM(CANT_UNIDADES_COMPRADAS) AS CANTIDADES_ADQUIRIDAS, 'MENOR DEMANDA' AS CATEGORIA, ID_SUCURSAL "+
        "FROM DATOS_COMPRAS"+
        "GROUP BY CODIGO_BARRAS, NOMBRE, ID_SUCURSAL))"+
        "UNION"+
        "(SELECT * FROM MAYOR_DEMANDA)"+
        ")"+
        "WHERE ID_SUCURSAL=?"+
        "ORDER BY ID_SUCURSAL;"
        );
        return (long) q.executeUnique();
    }
}
