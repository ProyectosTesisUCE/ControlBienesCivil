package uce.edu.ec.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Estaciones")
public class Estacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ubicacion;
	private String lugar;
	private String imagen = "logoUce.jpg";
	
	@OneToMany(mappedBy = "estacion" ,fetch = FetchType.EAGER)	
	private List<Bienes_Estaciones> registro;

	public Estacion() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "Estacion [id=" + id + ", ubicacion=" + ubicacion + ", lugar=" + lugar + ", imagen=" + imagen + "]";
	}


}
