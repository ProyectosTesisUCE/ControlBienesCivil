package uce.edu.ec.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Bien;

@Repository
public interface BienesRepository extends JpaRepository<Bien, Integer> {

	// Busqueda por periodo de registros //Paginado
	@Query(value = "SELECT * FROM Bienes WHERE fecha_ingreso BETWEEN :startDate AND :endDate ORDER BY fecha_ingreso DESC", nativeQuery = true)
	Page<Bien> findByPeriodoPaginado(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable page);

	// Busqueda por periodo de registros
	@Query(value = "SELECT * FROM Bienes WHERE fecha_ingreso BETWEEN :startDate AND :endDate ORDER BY fecha_ingreso DESC", nativeQuery = true)
	List<Bien> findByPeriodo(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// Busqueda de bines por alta //Paginado
	// @Query(value = "SELECT * FROM BIENES WHERE ALTA = ?1", nativeQuery = true)
	@Query(value = "SELECT * FROM Bienes b WHERE b.alta like %:alta% or b.anterior like %:alta% ORDER BY garantia DESC", nativeQuery = true)
	List<Bien> findByInputSinPaginar(@Param("alta") String input);

	// Busqueda de bines por alta //Paginado
	// @Query(value = "SELECT * FROM BIENES WHERE ALTA = ?1", nativeQuery = true)
	@Query(value = "SELECT * FROM Bienes b WHERE b.alta like %:alta% or b.anterior like %:alta% ORDER BY garantia DESC", nativeQuery = true)
	Page<Bien> findByInput(@Param("alta") String input, Pageable page);

	// Lista todos los bienes oredenados por fecha de ingreso descendente
	@Query(value = "SELECT * FROM BIENES b, DETALLES d WHERE b.id_detalle = d.id ORDER BY b.fecha_ingreso DESC", nativeQuery = true)
	Page<Bien> findAllOrdenado(Pageable page);

	// Busqueda de bien por alta nueva
	public Bien findByAlta(String alta);

	// Busqueda de bien por alta Anterior
	public Bien findByAnterior(String anterior);

	// valor repetido por alta nueva
	boolean existsByAlta(String alta);

	// valor repetido por alta anterior
	boolean existsByAnterior(String anterior);

	// valor repetido por serie
	boolean existsBySerie(String serie);

	// valor repetido por alta, anterior, serie
	boolean existsByAltaAndAnterior(String alta, String anterior);

	// Lista de bienes que se pueden asignar
	@Query(value = "select * from BIENES where control = ? order by id desc", nativeQuery = true)
	List<Bien> findByControl(@Param("control") String Control);

}
