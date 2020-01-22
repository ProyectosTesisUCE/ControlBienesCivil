package uce.edu.ec.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uce.edu.ec.app.model.Banner;

public interface IBannersService {

	//Insertar
	void insertar(Banner banner);

	//Listar todos
	List<Banner> buscarTodos();
	
	//Listar todos con paginación
	Page<Banner> buscarTodos(Pageable page);
	
	//Lista de banner activos para presentar en el Home.jsp
	List<Banner> buscarActivos();
	
	//Buscar por id
	Banner buscarPorId(int idBanner);
	
	//Eliminar
	void eliminar (int idBannner);
	
	//Controlar los repetidos
	boolean existePorTitulo(String titulo);

}
