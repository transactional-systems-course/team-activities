package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Promocion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto Promocion de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
public class SQLPromocion {
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
    public SQLPromocion(PersistenciaSuperAndes ps) {
        this.ps = ps;
    }
    

    /**
     * Registra una promoción nueva en la base de datos.
     * 
     * @param rebajaEnPrecio
     * @param tipoPromocion
     * @param fechaInicio
     * @param fechaFin
     * @param idProducto
     * @param cantUnidadesDisponibles
     * @param totalUnidadesOfrecidas
     * @return
     */
    
    public long registrarPromocion(PersistenceManager pm, String rebajaEnPrecio, String tipoPromocion, String fechaInicio, String fechaFin,
            String idProducto, String cantUnidadesDisponibles, String totalUnidadesOfrecidas) {
        String ret = null;
        Query q = pm.newQuery(SQL, "INSERT INTO PROMOCION (\r\n"
                + "    ID,\r\n"
                + "    REBAJA_EN_PRECIO,\r\n"
                + "    TIPO_PROMOCION,\r\n"
                + "    FECHA_INICIO,\r\n"
                + "    FECHA_FIN,\r\n"
                + "    ID_PRODUCTO,\r\n"
                + "    CANT_UNIDADES_DISPONIBLES,\r\n"
                + "    TOTAL_UNIDADES_OFRECIDAS\r\n"
                + ") VALUES (\r\n"
                + " 1,"
                + rebajaEnPrecio + ","
                + tipoPromocion  + ","
                + fechaInicio + ","
                + fechaFin + ","
                + idProducto + ","
                + cantUnidadesDisponibles + ","
                + totalUnidadesOfrecidas + ","
                + ");");
        
        return (long) q.executeUnique();
    }
    public long consultarPromosPopulares(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT ID                                                 AS ID_PROMOCION,\r\n"
                + "    ID_PRODUCTO,\r\n"
                + "    NOMBRE,\r\n"
                + "    TOTAL_UNIDADES_OFRECIDAS-CANT_UNIDADES_DISPONIBLES AS CANTIDADES_VENDIDAS\r\n"
                + "FROM (\r\n"
                + "        SELECT *\r\n"
                + "        FROM PROMOCION\r\n"
                + "            INNER JOIN PRODUCTO\r\n"
                + "            ON PRODUCTO.CODIGO_BARRAS = PROMOCION.ID_PRODUCTO\r\n"
                + "    )\r\n"
                + "ORDER BY CANTIDADES_VENDIDAS DESC FETCH FIRST 20 ROWS ONLY;");
        return (long) q.executeUnique();
    }
}


