package uce.edu.ec.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bienes")
public class Bien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String alta;
	private String anterior;
	private String descripcion;
	private String serie;
	private Date fecha_ingreso;
	private Double costo = 0.0;
	private int vida_util;
	private Date garantia;
	private String color;
	private String material;
	private String control;

	@OneToOne
	@JoinColumn(name = "id_Detalle")
	private Detalle detalle;

	@OneToMany(mappedBy = "bien", fetch = FetchType.EAGER)
	private List<Bienes_Estaciones> registro;

	public Bien() {

		this.fecha_ingreso = new Date();
		this.control = "Activo";
	}

	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getVida_util() {
		return vida_util;
	}

	public void setVida_util(int vida_util) {
		this.vida_util = vida_util;
	}

	public Date getGarantia() {
		return garantia;
	}

	public void setGarantia(Date garantia) {
		this.garantia = garantia;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	@Override
	public String toString() {
		return " Alta nueva:" + alta + ", Alta Anterior:" + anterior + ", descripcion:" + descripcion + ", serie:"
				+ serie + "\n";
	}
	/*
	 * @Override public String toString() { return "Bien [id=" + id + ", alta=" +
	 * alta + ", anterior=" + anterior + ", descripcion=" + descripcion + ", serie="
	 * + serie + ", fecha_ingreso=" + fecha_ingreso + ", costo=" + costo +
	 * ", vida_util=" + vida_util + ", garantia=" + garantia + ", color=" + color +
	 * ", material=" + material + ", control=" + control + ", detalle=" + detalle +
	 * "]"; }
	 */

}
