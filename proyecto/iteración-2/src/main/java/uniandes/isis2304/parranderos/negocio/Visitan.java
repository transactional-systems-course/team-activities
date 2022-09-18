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

/**
 * Clase para modelar la relación VISITAN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bebedor visitó un bar y viceversa.
 * Se modela mediante los identificadores del bebedor y del bar respectivamente
 * Debe existir un bebedor con el identificador dado
 * Debe existir un bar con el identificador dado 
 * Adicionalmente contiene la fecha y el horario (DIURNO, NOCTURNO, TODOS) en el cual el bebedor visitó el bar
 * 
 * @author Germán Bravo
 */
public class Visitan implements VOVisitan
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del bebedor que realiza la visita
	 */
	private long idBebedor;
	
	/**
	 * El identificador del bar visitado
	 */
	private long idBar;
	
	/**
	 * La fecha de la visita
	 */
	private Timestamp fechaVisita;
	
	/**
	 * El horario en que se realizó la visita (DIURNO, NOCTURNO, TODOS)
	 */
	private String horario;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Visitan() 
	{
		this.idBebedor = 0;
		this.idBar = 0;
		this.horario = "";
		this.fechaVisita = new Timestamp (0);
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del b ebedor. Debe existir un bebedor con dicho identificador
	 * @param idBar - El identificador del bar. Debe exixtir un bar con dicho identificador
	 * @param fechaVisita - La fecha en la cual se realiza la visita
	 * @param horario - El horario en el que el bebedor vista el bar (DIURNO, NOCTURNO, TODOS)
	 */
	public Visitan(long idBebedor, long idBar, Timestamp fechaVisita, String horario) 
	{
		this.idBebedor = idBebedor;
		this.idBar = idBar;
		this.fechaVisita = fechaVisita;
		this.horario = horario;
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
	 * @return El idBar
	 */
	public long getIdBar() 
	{
		return idBar;
	}

	/**
	 * @param idBar - El nuevo idBar. Debe exixtir un bar con dicho identificador
	 */
	public void setIdBar(long idBar) 
	{
		this.idBar = idBar;
	}

	/**
	 * @return La fechaVisita
	 */
	public Timestamp getFechaVisita() 
	{
		return fechaVisita;
	}

	/**
	 * @param fechaVisita - La nueva fecha de visita al bar por el bebedor
	 */
	public void setFechaVisita(Timestamp fechaVisita) 
	{
		this.fechaVisita = fechaVisita;
	}

	/**
	 * @return El horario
	 */
	public String getHorario() 
	{
		return horario;
	}

	/**
	 * @param horario - El nuevo horario en que se realizó la visita (DIURNO, NOCTURNO, TODOS)
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
		return "Visitan [idBebedor=" + idBebedor + ", idBar=" + idBar + ", fechaVisita=" + fechaVisita + ", horario="
				+ horario + "]";
	}
}
