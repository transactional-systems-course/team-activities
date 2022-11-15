package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto Sucursal de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
public class SQLSucursal {
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
    public SQLSucursal(PersistenciaSuperAndes ps) {
        this.ps = ps;
    }

    /**
     * TODO: CRUD
     */
    public String consultarDineroRecolectado(PersistenceManager pm, String idSucursal) {
        Query q = pm.newQuery(SQL,
                "SELECT ID_SUCURSAL,\r\n"
                + "    NOMBRE,\r\n"
                + "    SUM(VALOR_COMPRA_TOTAL) AS SUMA_VALORES\r\n"
                + "FROM (\r\n"
                + "        SELECT *\r\n"
                + "        FROM SUCURSAL\r\n"
                + "            INNER JOIN (\r\n"
                + "                SELECT NUMERO_DOCUMENTO,\r\n"
                + "                    ID_SUCURSAL\r\n"
                + "                FROM USUARIO\r\n"
                + "            ) USUARIO\r\n"
                + "            ON USUARIO.ID_SUCURSAL = SUCURSAL.ID\r\n"
                + "            INNER JOIN COMPRA\r\n"
                + "            ON USUARIO.NUMERO_DOCUMENTO = COMPRA.COMPRADOR\r\n"
                + "    )\r\n"
                + "WHERE ID_SUCURSAL = ?"
                + "GROUP BY ID_SUCURSAL, NOMBRE\r\n"
                + "ORDER BY SUMA_VALORES DESC");
        List<Object[]> results = q.executeList();
        // TODO: create for in interface.
        return results.get(0)[2].toString() + "%";
    }

    public String darCompradoresFrecuentes(PersistenceManager pm, String idSucursal) {
        String strQ = "SELECT * FROM ("+
                "SELECT NUMERO_DOCUMENTO, NOMBRE, COUNT(NUMERO_DOCUMENTO) AS COMPRAS_REALIZADAS,\n"+
                "ID_SUCURSAL, to_char(FECHA_COMPRA, 'YYYY-MM') FROM\n" +
                "( SELECT * FROM (\n" +
                "(SELECT COMPRADOR, FECHA_COMPRA FROM COMPRA)\n"+
                "INNER JOIN\n"+
                "(SELECT * FROM USUARIO)\n"+
                "ON COMPRADOR = NUMERO_DOCUMENTO))\n"+
                "WHERE ID_SUCURSAL =\n"+ idSucursal + "\n"+
                "GROUP BY NUMERO_DOCUMENTO, NOMBRE, ID_SUCURSAL, FECHA_COMPRA\n"+
                ") WHERE COMPRAS_REALIZADAS >= 2\n";
        Query q = pm.newQuery(SQL,strQ);
        
        List<Object[]> results = q.executeList();
        for (int i= 0; i < results.size(); i++) {
            for(int j=0; i< results.get(i).length; j++) {
                System.out.println(results.get(i)[j].toString());
            }
        }
        // TODO: create for in interface.
        return results.get(0)[2].toString() + "%";
    }

    public String darEntregasInfrecuentes(PersistenceManager pm, String idSucursal) {
        String strQ = "SELECT CODIGO_BARRAS, NOMBRE, CATEGORIA, ID_PEDIDO, SUCURSAL, fecha_entrega AS ULTIMA_ENTREGA\n"+
                "FROM ULTIMOS_PEDIDOS\n"+
                "INNER JOIN\n"+
                "(SELECT CODIGO_BARRAS AS CBARRAS, FECHA_ENTREGA AS PENULTIMA_ENTREGA FROM PENULTIMOS_PEDIDOS)\n"+
                "ON CBARRAS = ULTIMOS_PEDIDOS.CODIGO_BARRAS\n"+
                "WHERE PENULTIMA_ENTREGA <= FECHA_ENTREGA - INTERVAL '60' DAY\n"+
                "AND SUCURSAL =\n"+ idSucursal +"\n";
        Query q = pm.newQuery(SQL, strQ);
        String strR = "";
        List<Object[]> results = q.executeList();
        for (int i = 0; i < results.size(); i++) {
            Object[] r = results.get(i);
            for (int j = 0; i < r.length; j++) {
                strR += r[j].toString();
            }
        }
        return strR;
    }

}
