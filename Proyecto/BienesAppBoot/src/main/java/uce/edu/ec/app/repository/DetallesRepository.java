package uce.edu.ec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uce.edu.ec.app.model.Detalle;

@Repository
public interface DetallesRepository extends JpaRepository<Detalle, Integer> {

}
