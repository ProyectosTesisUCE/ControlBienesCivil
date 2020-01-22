package uce.edu.ec.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uce.edu.ec.app.model.Usuario;

public interface IUsuariosService {

	//Insertar
	void guardar(Usuario usuario);

	//Listar todos
	List<Usuario> buscarTodos();

	//Listar todos con Paginacion
	Page<Usuario> buscarTodos(Pageable page);
	
	//Buscar por id
	Usuario buscarPorId(int idUsuario);

	//Buscar por cuneta 
	Usuario buscarPorCuenta(String cuenta);

	//Controlar los repetidos en la insercion por cueta and email
	boolean existePorCunetaEmail(String cuenta, String email);

	//Eliminar
	void eliminar(int idUsuario);
	
	//Buscar el Usuario por cuenta and email
	Usuario buscarCuentaAndEmail(String cuenta, String email);
	
}
