package uniandes.isis2304.superandes.persistencia;

import java.util.List;
import java.math.BigDecimal;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Contenedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto Contenedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
public class SQLContenedor {
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
    public SQLContenedor(PersistenciaSuperAndes ps) {
        this.ps = ps;
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información del
     * índice de ocupación de las bodegas en la base de datos de SuperAndes.
     *
     * @param pm - El manejador de persistencia
     * @return Indice de ocupación de bodega
     */
    public String consultarIndiceOcupacionBodega(PersistenceManager pm, String idBodega) {
        String strQ = "SELECT ID,\n" +
                "TIPO_CONTENEDOR,\n" +
                "(SUM(MEDIDA)/CAPACIDAD_MAXIMA_PESO)*100 AS PORCENTAJE_ALMACENAMIENTO\n" +
                "FROM (\n" +
                "SELECT *\n" +
                "FROM CONTENEDOR\n" +
                "INNER JOIN (\n" +
                "SELECT *\n" +
                "FROM ALMACENAMIENTO_PRODUCTO\n" +
                ") ALM\n" +
                "ON CONTENEDOR.ID = ALM.ID_CONTENEDOR_ACTUAL\n" +
                "INNER JOIN (\n" +
                "SELECT *\n" +
                "FROM MEDICION_PRODUCTO\n" +
                ") MED\n" +
                "ON MED.ID = ALM.ID\n" +
                ")\n" +
                "WHERE (ID_SUCURSAL = '1')\n" +
                "GROUP BY ID,\n" +
                "TIPO_CONTENEDOR,\n" +
                "CAPACIDAD_MAXIMA_PESO";
        Query q = pm.newQuery(SQL, strQ);
        List<Object[]> results = q.executeList();
        // TODO: create for in interface.
        return results.get(0)[2].toString() + "%";
    }
}
