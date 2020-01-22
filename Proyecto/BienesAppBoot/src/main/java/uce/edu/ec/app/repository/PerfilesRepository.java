package uce.edu.ec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uce.edu.ec.app.model.Perfil;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {
	
	public Perfil findByCuenta (String cuenta);

}
