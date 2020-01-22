package uce.edu.ec.app.service;

import uce.edu.ec.app.model.Detalle;

public interface IDetallesService {
	
	void insertar(Detalle detalle);
	
	void eliminar(int idDetalle);

}
