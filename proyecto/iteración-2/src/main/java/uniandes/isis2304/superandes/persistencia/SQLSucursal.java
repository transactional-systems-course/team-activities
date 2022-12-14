package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Sucursal;

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
    public long consultarDineroRecolectado(PersistenceManager pm, String idSucursal) {
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
                + "ORDER BY SUMA_VALORES DESC;");
        return (long) q.executeUnique();
    }
}
