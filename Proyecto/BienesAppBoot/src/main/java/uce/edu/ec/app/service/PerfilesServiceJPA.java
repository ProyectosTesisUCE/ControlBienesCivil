package uce.edu.ec.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Perfil;
import uce.edu.ec.app.repository.PerfilesRepository;

@Service
public class PerfilesServiceJPA implements IPerfilesService {

	@Autowired
	private PerfilesRepository perfilesRepo;

	@Override
	public void guardar(Perfil perfil) {
		perfilesRepo.save(perfil);
	}

	@Override
	public Perfil buscarPorCuneta(String cuenta) {
		return perfilesRepo.findByCuenta(cuenta);
	}

	@Override
	public void eliminar(int idPerfil) {
		perfilesRepo.deleteById(idPerfil);
		
	}

}
