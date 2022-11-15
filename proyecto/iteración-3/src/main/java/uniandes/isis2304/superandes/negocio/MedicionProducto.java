package uniandes.isis2304.superandes.negocio;

import java.util.Objects;

public class MedicionProducto {
    int id;
    long medida;
    String unidadMedida;
    long especificacionEmpacado;
    String unidadEspecificacionEmpacado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getMedida() {
		return medida;
	}
	public void setMedida(long medida) {
		this.medida = medida;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public long getEspecificacionEmpacado() {
		return especificacionEmpacado;
	}
	public void setEspecificacionEmpacado(long especificacionEmpacado) {
		this.especificacionEmpacado = especificacionEmpacado;
	}
	public String getUnidadEspecificacionEmpacado() {
		return unidadEspecificacionEmpacado;
	}
	public void setUnidadEspecificacionEmpacado(String unidadEspecificacionEmpacado) {
		this.unidadEspecificacionEmpacado = unidadEspecificacionEmpacado;
	}

	@Override
	public String toString() {
		return "MedicionProducto [id=" + id + ", medida=" + medida + ", unidadMedida=" + unidadMedida
				+ ", especificacionEmpacado=" + especificacionEmpacado + ", unidadEspecificacionEmpacado="
				+ unidadEspecificacionEmpacado + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(especificacionEmpacado, id, medida, unidadEspecificacionEmpacado, unidadMedida);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		MedicionProducto other = (MedicionProducto) obj;
		return especificacionEmpacado == other.especificacionEmpacado && id == other.id && medida == other.medida
				&& Objects.equals(unidadEspecificacionEmpacado, other.unidadEspecificacionEmpacado)
				&& Objects.equals(unidadMedida, other.unidadMedida);
	}


}
