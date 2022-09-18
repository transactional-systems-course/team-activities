/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar la relación SIRVEN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bar sirve una bebida y viceversa.
 * Se modela mediante los identificadores del bar y de la bebida respectivamente
 * Debe existir un bar con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * Adicionalmente contiene el horario (DIURNO, NOCTURNO, TODOS) en el cual el bar sirve la bebida
 * 
 * @author Germán Bravo
 */
public class Sirven implements VOSirven
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del bar que sirve la bebida
	 */
	private long idBar;
	
	/**
	 * El identificador de la bebida que es servida en el bar
	 */
	private long idBebida;
	
	/**
	 * El horario en que sirve la bebida en el bar (DIURNO, NOCTURNO, TODOS)
	 */
	private String horario;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Sirven () 
	{
		this.idBar = 0;
		this.idBebida = 0;
		this.horario = "";
	}

	/**
	 * Constructor con valores
	 * @param idBar - El identificador del bar. Debe exixtir un bar con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 * @param horario - El horario en el que el bar sirve la bebida (DIURNO, NOCTURNO, TODOS)
	 */
	public Sirven (long idBar, long idBebida, String horario) 
	{
		this.idBar = idBar;
		this.idBebida = idBebida;
		this.horario = horario;
	}

	/**
	 * @return El idBar
	 */
	public long getIdBar() 
	{
		return idBar;
	}

	/**
	 * @param idBar - El nuevo identificador de bar. Debe existir un bar con dicho identificador
	 */
	public void setIdBar(long idBar) 
	{
		this.idBar = idBar;
	}

	/**
	 * @return El idBebida
	 */
	public long getIdBebida() 
	{
		return idBebida;
	}

	/**
	 * @param idBebida - El nuevo identificador de bebida. Debe existir una bebida con dicho identificador
	 */
	public void setIdBebida(long idBebida) 
	{
		this.idBebida = idBebida;
	}

	/**
	 * @return El horario en que el bar sirve la bebida
	 */
	public String getHorario() 
	{
		return horario;
	}

	/**
	 * @param horario - El nuevo horario en que el bar sirve la bebida (DIURNO, NOCTURNO, TODOS)
	 */
	public void setHorario(String horario) 
	{
		this.horario = horario;
	}

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Sirven [idBar=" + idBar + ", idBebida=" + idBebida + ", horario=" + horario + "]";
	}
}
