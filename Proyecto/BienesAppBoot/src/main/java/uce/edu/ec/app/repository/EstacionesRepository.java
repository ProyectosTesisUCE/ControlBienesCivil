package uce.edu.ec.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Estacion;

@Repository
public interface EstacionesRepository extends JpaRepository<Estacion, Integer> {

	// Boolean si existe por lugar
	boolean findByLugar(String lugar);

	// Controlar Repetidos en el insercion
	boolean existsByUbicacionAndLugar(String ubicacion, String lugar);

	// Buscar todos los registros por lugar and Paginado
	Page<Estacion> findByLugar(String lugar, Pageable page);
	
	//Lista estaciones ordenadas por fecha
	@Query( value = "SELECT * FROM ESTACIONES ORDER BY id DESC", nativeQuery = true)
	Page<Estacion> findAllOrdenado(Pageable page);

}
