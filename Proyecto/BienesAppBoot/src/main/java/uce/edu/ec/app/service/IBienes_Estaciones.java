package uce.edu.ec.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uce.edu.ec.app.model.Bienes_Estaciones;

public interface IBienes_Estaciones {

	// Insertar
	void insertar(Bienes_Estaciones bienes_Estaciones);

	// Listar todos
	List<Bienes_Estaciones> buscarTodos();

	// PAGINACION
	// Listar todos paginacion
	Page<Bienes_Estaciones> buscarTodos(Pageable page);

	// Listar registros por id de estacion
	Page<Bienes_Estaciones> buscarPorIdEstacion(int idEstacion, Pageable page);

	// Listar registros que se han cambiado en un periodo de tiempo por idEstacion
	List<Bienes_Estaciones> buscarCambiosPorPeriodoAndIdEstacion(int idEstacion, Date startDate, Date endDate);

	// Listar registros que se han cambiado en un periodo de tiempo por idEstacion
	// paginado
	Page<Bienes_Estaciones> buscarCambiosPorPeriodoAndIdEstacionPaginado(int idEstacion, Date startDate, Date endDate,
			Pageable page);

	// Buscar por alta nueva  del bien
	Page<Bienes_Estaciones> buscarPorAltaBien(String Alta, Pageable page);
	
	List<Bienes_Estaciones> buscarPorAltaBienSinPaginar(String Alta);

	// Buscar por alta anterior del bien
	Page<Bienes_Estaciones> buscarPorAltaAnteriorBien(String anterior, Pageable page);

	
	// Filtro de detalle
	Page<Bienes_Estaciones> buscarPorEstacion_IdAndBien_Alta(int idEstacion, String alta, Pageable page);
	
	List<Bienes_Estaciones> buscarPorEstacion_IdAndBien_AltaSinPaginar(int idEstacion, String alta);

	// Buscar por id
	Bienes_Estaciones buscarPorId(int id);

	// Eliminar por id
	void eliminar(int id);

	// Buscar por id del bien
	Bienes_Estaciones buscarIdPorIdBien(int id);

	// todos los bines buscados por id estacion
	List<Bienes_Estaciones> buscarIdPorIdEstacion(int idEstacion);

	// Existe un bien_estacion por id
	boolean existe(int id);

	// Controlar repetidos
	boolean existeRegistroPorIdBienIdEstacion(int idBien, int idEstacion);

}
