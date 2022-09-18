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
 * Clase para modelar la relación GUSTAN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bebedor gusta de una bebida y viceversa.
 * Se modela mediante los identificadores del bebedor y de la bebida respectivamente.
 * Debe existir un bebedor con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * 
 * @author Germán Bravo
 */
public class Gustan implements VOGustan
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del bebedor que gusta de la bebida
	 */
	private long idBebedor;

	/**
	 * El identificador de la bebida que gusta al bebedor
	 */
	private long idBebida;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Gustan() 
	{
		this.idBebedor = 0;
		this.idBebida = 0;
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 */
	public Gustan(long idBebedor, long idBebida) 
	{
		this.idBebedor = idBebedor;
		this.idBebida = idBebida;
	}

	/**
	 * @return El idBebedor
	 */
	public long getIdBebedor() 
	{
		return idBebedor;
	}

	/**
	 * @param idBebedor - El nuevo idBebedor. Debe existir un bebedor con dicho identificador
	 */
	public void setIdBebedor(long idBebedor) 
	{
		this.idBebedor = idBebedor;
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
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Gustan [idBebedor=" + idBebedor + ", idBebida=" + idBebida + "]";
	}
	
}
