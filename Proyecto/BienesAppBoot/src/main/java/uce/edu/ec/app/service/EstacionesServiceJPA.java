package uce.edu.ec.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Estacion;
import uce.edu.ec.app.repository.EstacionesRepository;

@Service
public class EstacionesServiceJPA implements IEstacionService {

	@Autowired
	EstacionesRepository estacionesRepo;

	@Override
	public void insertar(Estacion estacion) {
		estacionesRepo.save(estacion);
	}

	@Override
	public List<Estacion> busacarTodas() {

		return estacionesRepo.findAll();
	}

	@Override
	public Estacion buscarPorId(int idEstacion) {
		Optional<Estacion> optional = estacionesRepo.findById(idEstacion);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idEstacion) {
		estacionesRepo.deleteById(idEstacion);
	}

	@Override
	public List<String> buscarLugar() {
		List<String> lugar = new LinkedList<>();
		lugar.add("Sala A");
		lugar.add("Sala B");
		lugar.add("Sala C");
		lugar.add("Bodega");
		return lugar;
	}

	@Override
	public Page<Estacion> buscarTodos(Pageable page) {

		return estacionesRepo.findAllOrdenado(page);
	}

	@Override
	public boolean existeEstacion(String ubicacion, String lugar) {

		return estacionesRepo.existsByUbicacionAndLugar(ubicacion, lugar);
	}

	@Override
	public Page<Estacion> buscarPorLugar(String lugar, Pageable page) {
		return estacionesRepo.findByLugar(lugar, page);
	}

}
