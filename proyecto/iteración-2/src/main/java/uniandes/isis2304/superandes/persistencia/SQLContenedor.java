package uniandes.isis2304.superandes.persistencia;

import java.util.List;

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
     * TODO: CRUD
     */
    public long consultarIndiceOcupacionBodega(String idBodega) {
        Query q = ps.newQuery(SQL, ""
                + "SELECT ID, TIPO_CONTENEDOR, "
                + "(SUM(MEDIDA)/CAPACIDAD_MAXIMA_PESO)*100 AS PORCENTAJE_ALMACENAMIENTO\r\n"
                + "FROM (\r\n"
                + "    SELECT * FROM CONTENEDOR\r\n"
                + "    INNER JOIN\r\n"
                + "    (SELECT * FROM ALMACENAMIENTO_PRODUCTO) ALM\r\n"
                + "    ON CONTENEDOR.ID = ALM.ID_CONTENEDOR_ACTUAL\r\n"
                + "    INNER JOIN\r\n"
                + "    (SELECT * FROM MEDICION_PRODUCTO) MED \r\n"
                + "    ON MED.ID = ALM.ID\r\n"
                + ")\r\n"
                + "WHERE (ID_SUCURSAL = ?)\r\n"
                + "GROUP BY ID, TIPO_CONTENEDOR, CAPACIDAD_MAXIMA_PESO;");
        return(long) q.executeUnique();
    }
    
    
}
