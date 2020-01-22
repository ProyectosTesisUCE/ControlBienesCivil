package uce.edu.ec.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uce.edu.ec.app.model.Estacion;

public interface IEstacionService {

	void insertar(Estacion estacion);

	List<Estacion> busacarTodas();

	Estacion buscarPorId(int idEstacion);

	void eliminar(int idEstacion);

	List<String> buscarLugar();// Generar las salas para escoger

	Page<Estacion> buscarTodos(Pageable page);
	
	Page<Estacion> buscarPorLugar(String lugar, Pageable page);
	
	boolean existeEstacion(String ubicacion, String lugar);

}
