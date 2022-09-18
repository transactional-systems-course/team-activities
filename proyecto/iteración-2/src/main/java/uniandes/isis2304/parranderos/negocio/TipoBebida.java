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
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class TipoBebida implements VOTipoBebida
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de bebida
	 */
	private long id;

	/**
	 * El nombre del tipo de bebida
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoBebida() 
	{
		this.id = 0;
		this.nombre = "Default";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de bebida
	 * @param nombre - El nombre del tipo de bebida
	 */
	public TipoBebida(long id, String nombre) 
	{
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return El id del tipo de bebida
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de bebida
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de bebida
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del tipo de bebida
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "TipoBebida [id=" + id + ", nombre=" + nombre + "]";
	}

	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		TipoBebida tb = (TipoBebida) tipo;
		return id == tb.id && nombre.equalsIgnoreCase (tb.nombre);
	}

}
