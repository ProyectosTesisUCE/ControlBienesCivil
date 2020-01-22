package uce.edu.ec.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByCuenta (String cuenta);
	
	public boolean existsByCuentaAndEmail (String cuenta, String email);
	
	Usuario findByCuentaAndEmail(String cuenta, String email);
	
	// Lista banners ordenados por fecha
		@Query(value = "SELECT * FROM Usuarios ORDER BY id DESC", nativeQuery = true)
		Page<Usuario> findAllOrdenado(Pageable page);

}
