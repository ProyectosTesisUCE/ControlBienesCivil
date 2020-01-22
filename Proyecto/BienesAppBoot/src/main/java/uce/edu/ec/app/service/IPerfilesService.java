package uce.edu.ec.app.service;

import uce.edu.ec.app.model.Perfil;

public interface IPerfilesService {

	void guardar(Perfil perfil);

	Perfil buscarPorCuneta(String cuenta);
	
	void eliminar(int idPerfil);
}
