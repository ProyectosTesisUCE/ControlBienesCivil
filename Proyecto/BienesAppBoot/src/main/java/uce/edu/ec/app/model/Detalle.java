package uce.edu.ec.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Detalles")
public class Detalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String asignado;
	private String marca;
	private String modelo;
	private String estado;
	private String estatus;
	private String tipo;
	private String guarda_almacen;
	private String causionado;

	/**
	 * Constructor sin parametros
	 */
	public Detalle() {
		this.estatus = "Activo";

	}

	public String getCausionado() {
		return causionado;
	}

	public void setCausionado(String causionado) {
		this.causionado = causionado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsignado() {
		return asignado;
	}

	public void setAsignado(String asignado) {
		this.asignado = asignado;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGuarda_almacen() {
		return guarda_almacen;
	}

	public void setGuarda_almacen(String guarda_almacen) {
		this.guarda_almacen = guarda_almacen;
	}

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", asignado=" + asignado + ", marca=" + marca + ", modelo=" + modelo + ", estado="
				+ estado + ", estatus=" + estatus + ", tipo=" + tipo + ", guarda_almacen=" + guarda_almacen
				+ ", causionado=" + causionado + "]";
	}

}
