package uce.edu.ec.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Bienes_Estaciones;

@Repository
public interface BienesEstacionesRepository extends JpaRepository<Bienes_Estaciones, Integer> {

	// Buscar el registro de asignacion por id del bien
	Bienes_Estaciones findByBien_Id(int idBien);

	// Listar los bienes por id de estacion
	List<Bienes_Estaciones> findByEstacion_Id(int id);

	// Listar los bienes asignados mediante el id de la estacion //paginado
	@Query(value = "SELECT * FROM BIENES_ESTACIONES WHERE ID_ESTACION = ?1 ORDER BY ACTUALIZACION DESC", nativeQuery = true)
	Page<Bienes_Estaciones> findByIdEstacion(@Param("ID_ESTACION") int id_estacion, Pageable page);

	// Buscar los bienes que fueron cambiados en algun periodo //Paginado
	Page<Bienes_Estaciones> findByEstacion_IdAndActualizacionBetweenOrderByActualizacionDesc(int idEstacion,
			Date startDate, Date endDate, Pageable page);

	// Buscar los bienes que fueron cambiados en algun periodo
	List<Bienes_Estaciones> findByEstacion_IdAndActualizacionBetweenOrderByActualizacionDesc(int idEstacion,
			Date startDate, Date endDate);

	// Lista todas las asignaciones oredenados
	@Query(value = "SELECT * FROM BIENES_ESTACIONES be, ESTACIONES e WHERE be.id_estacion = e.id  ORDER BY be.registro DESC", nativeQuery = true)
	Page<Bienes_Estaciones> findAllOrdenado(Pageable page);

	// Verificar si existe el registro por medio de los parametros idBien and
	// idEstacion
	boolean existsByBien_IdAndEstacion_Id(int idBien, int idEstacion);

	// Buscar por Alta nueva
	Page<Bienes_Estaciones> findByBien_Alta(String alta, Pageable page);
	
	List<Bienes_Estaciones>findByBien_AltaOrderByIdDesc(String alta);

	// Buscar por Alta anterior
	Page<Bienes_Estaciones> findByBien_Anterior(String anterior, Pageable page);

	// Buscar registro por id de estacion and alta del bien //Paginado
	Page<Bienes_Estaciones> findByEstacion_IdAndBien_Alta(int idEstacion, String alta, Pageable page);

	// Buscar registro por id de estacion and alta del bien orden Descendente
	List<Bienes_Estaciones> findByEstacion_IdAndBien_AltaOrderByIdDesc(int idEstacion, String alta);

}
