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

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar el concepto BEBEDOR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Bebedor implements VOBebedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del bebedor
	 */
	private long id;	
	
	/**
	 * El nombre del bebedor
	 */
	private String nombre;
	
	/**
	 * La ciudad del bebedor
	 */
	private String ciudad;
	
	/**
	 * El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 */
	private String presupuesto;
	
	/**
	 * Las visitas realizadas por el bebedor. 
	 * Cada visita es una tripleta de objetos [Bar, Timestamp, String], que representan el bar, la fecha 
	 * y el horario en que el bebedor realizó la visita
	 */
	private List<Object []> visitasRealizadas;

	/**
	 * Las bebidas que le gustan el bebedor. 
	 * Cada visita es una pareja de objetos [Bebida, String], que representan la bebida y el nombre del 
	 * tipo de bebida que le gustan al bebedor 
	 */
	private List<Object []> bebidasQueLeGustan;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Bebedor() 
	{
		this.id = 0;
		this.nombre = "";
		this.ciudad = "";
		this.presupuesto = "";
		visitasRealizadas = new LinkedList<Object []> ();
		bebidasQueLeGustan = new LinkedList<Object []> ();
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bebedor
	 * @param nombre - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 */
	public Bebedor(long id, String nombre, String ciudad, String presupuesto) 
	{
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.presupuesto = presupuesto;
		
		// Estos valores no se conocen en el momento de la construcción del bebedor
		visitasRealizadas = new LinkedList<Object []> ();
		bebidasQueLeGustan = new LinkedList<Object []> ();
	}

	/**
	 * @return El id del bebedor
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del bebedor
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del bebedor
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del bebedor
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return La ciudad del bebedor
	 */
	public String getCiudad() 
	{
		return ciudad;
	}

	/**
	 * @param ciudad - La nueva ciudad del bebedor
	 */
	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}

	/**
	 * @return El presupuesto del bebedor
	 */
	public String getPresupuesto() 
	{
		return presupuesto;
	}

	/**
	 * @param presupuesto - El nuevo presupuesto del bebedor
	 */
	public void setPresupuesto(String presupuesto) 
	{
		this.presupuesto = presupuesto;
	}

	/**
	 * @return La lista de visitasRealizadas por el bebedor 
	 */
	public List<Object []> getVisitasRealizadas() 
	{
		return visitasRealizadas;
	}

	/**
	 * @param visitasRealizadas - La nueva lista de visitas del bebedor
	 */
	public void setVisitasRealizadas (List<Object []> visitasRealizadas) 
	{
		this.visitasRealizadas = visitasRealizadas;
	}

	/**
	 * @return Las bebidasQueLeGustan al bebedor
	 */
	public List<Object[]> getBebidasQueLeGustan() 
	{
		return bebidasQueLeGustan;
	}

	/**
	 * @param bebidasQueLeGustan - La nueva lista de bebidas que le gustan al bebedor
	 */
	public void setBebidasQueLeGustan(List<Object[]> bebidasQueLeGustan) 
	{
		this.bebidasQueLeGustan = bebidasQueLeGustan;
	}

	/**
	 * @return Una cadena de caracteres con la información básica del bebedor
	 */
	@Override
	public String toString() 
	{
		return "Bebedor [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", presupuesto=" + presupuesto + "]";
	}

	/**
	 * @return Una cadena de caracteres con la información COMPLEtA del bebedor.
	 * Además de la información básica, contiene las visitas realizadas (una por línea) y 
	 * las bebidas que le gustan al bebedor (una por línea)
	 */
	public String toStringCompleto () 
	{
		String resp =  this.toString();
		resp += "\n --- Visitas realizadas\n";
		int i = 1;
		for (Object [] visita : visitasRealizadas)
		{
			Bar bar = (Bar) visita [0];
			Timestamp fecha = (Timestamp) visita [1];
			String horario = (String) visita [2];
			resp += i++ + ". " + "[" +bar.toString() + ", fecha= " + fecha + ", horario= " + horario + "]\n";
		}
		resp += "\n\n --- Bebidas que le gustan\n";
		i = 1;
		for (Object [] gusta : bebidasQueLeGustan)
		{
			Bebida bebida = (Bebida) gusta [0];
			String tipoBebida = (String) gusta [1];
			resp += i++ + ". " + "[" + bebida.toString() + ", Tipo Bebida= " + tipoBebida + "]\n";
		}
		return resp;
	}

}
