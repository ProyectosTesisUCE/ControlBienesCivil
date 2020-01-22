package uce.edu.ec.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {

	List<Banner> findByEstatus(String estatus);

	boolean existsByTitulo(String titulo);

	// Lista banners ordenados por fecha
	@Query(value = "SELECT * FROM Banners ORDER BY fecha DESC", nativeQuery = true)
	Page<Banner> findAllOrdenado(Pageable page);

}
