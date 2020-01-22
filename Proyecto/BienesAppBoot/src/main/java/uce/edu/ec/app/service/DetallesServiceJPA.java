package uce.edu.ec.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Detalle;
import uce.edu.ec.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService {

	@Autowired
	DetallesRepository detallesRepo;

	@Override
	public void insertar(Detalle detalle) {
		detallesRepo.save(detalle);
	}

	@Override
	public void eliminar(int idDetalle) {
		detallesRepo.deleteById(idDetalle);
		
	}

}
