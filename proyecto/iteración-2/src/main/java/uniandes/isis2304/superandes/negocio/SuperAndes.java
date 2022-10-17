package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.superandes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Satisface todos los requerimientos funcionales del negocio
 */
public class SuperAndes {
	/*
	 * ****************************************************************
	 * Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());

	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes ps;

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes() {
		ps = PersistenciaSuperAndes.getInstance();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * 
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad
	 *                    de persistencia
	 */
	public SuperAndes(JsonObject tableConfig) {
		ps = PersistenciaSuperAndes.getInstance(tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia() {
		ps.cerrarUnidadPersistencia();
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar TODO
	 *****************************************************************/

	/*
	 * ****************************************************************
	 * Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de
	 * SuperAndes
	 * 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en
	 *         las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 *         TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long[] limpiarSuperAndes() {
		log.info("Limpiando la BD de SuperAndes");
		long[] borrados = ps.limpiarSuperAndes();
		log.info("Limpiando la BD de SuperAndes: Listo!");
		return borrados;
	}
}
