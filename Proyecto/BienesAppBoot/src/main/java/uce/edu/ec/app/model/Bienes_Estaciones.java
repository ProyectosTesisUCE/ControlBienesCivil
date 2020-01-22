package uce.edu.ec.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bienes_Estaciones")
public class Bienes_Estaciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date registro;
	private Date actualizacion;

	@ManyToOne
	@JoinColumn(name = "id_bien")
	private Bien bien;
	@ManyToOne
	@JoinColumn(name = "id_estacion")
	private Estacion estacion;

	// Constructor valores por defecto al registrar un objeto de esta clase
	public Bienes_Estaciones(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public Date getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(Date actualizacion) {
		this.actualizacion = actualizacion;
	}

	@Override
	public String toString() {
		return "Bienes_Estaciones [id=" + id + ", registro=" + registro + ", actualizacion=" + actualizacion + ", bien=" + bien
				+ ", estacion=" + estacion + "]";
	}

}
