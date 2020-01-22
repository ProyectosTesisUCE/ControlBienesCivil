package uce.edu.ec.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Bien;
import uce.edu.ec.app.repository.BienesRepository;

@Service
public class BienesServiceJPA implements IBienService {

	@Autowired
	BienesRepository bienesRepo;

	@Override
	public void insertar(Bien bien) {
		bienesRepo.save(bien);

	}

	@Override
	public List<Bien> buscarTodas() {
		return bienesRepo.findAll();
	}

	@Override
	public Bien buscarPorId(int idBien) {
		Optional<Bien> optional = bienesRepo.findById(idBien);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idBien) {
		bienesRepo.deleteById(idBien);

	}

	@Override
	public Page<Bien> buscarTodas(Pageable page) {
		return bienesRepo.findAllOrdenado(page);
	}

	@Override
	public Bien buscarPorAlta(String alta) {
		return bienesRepo.findByAlta(alta);
	}

	@Override
	public boolean existePorId(int id) {

		return bienesRepo.existsById(id);
	}

	@Override
	public boolean exiteRegistroPorAltaAnterior(String alta, String anterior) {
		return bienesRepo.existsByAltaAndAnterior(alta, anterior);
	}

	@Override
	public boolean existeRegistroPorALta(String alta) {
		return bienesRepo.existsByAlta(alta);
	}

	@Override
	public boolean existeRegistroPorAnterior(String anterior) {
		return bienesRepo.existsByAnterior(anterior);
	}

	@Override
	public boolean existeRegistroPorSerie(String serie) {
		return bienesRepo.existsBySerie(serie);
	}

	@Override
	public List<Bien> sinAsignacion() {
		return bienesRepo.findByControl("Activo");
	}

	@Override
	public Page<Bien> search(String input, Pageable page) {
		return bienesRepo.findByInput(input, page);
	}

	@Override
	public Page<Bien> buscarPeriodoPaginado(Date startDate, Date endDate, Pageable page) {
		return bienesRepo.findByPeriodoPaginado(startDate, endDate, page);
	}

	@Override
	public List<Bien> buscarPeriodo(Date startDate, Date endDate) {
		return bienesRepo.findByPeriodo(startDate, endDate);
	}

	@Override
	public Bien buscarPorAltaAnterior(String alta) {

		return bienesRepo.findByAnterior(alta);
	}

	@Override
	public List<Bien> searchSinPaginar(String input) {

		return bienesRepo.findByInputSinPaginar(input);
	}

	@Override
	public List<String> buscarTipo() {
		List<String> tipos = new LinkedList<>();
		tipos.add("BIENES SUJETOS A CONTROL");
		tipos.add("EQUIPO ELECTRONICO");
		tipos.add("EQUIPO DE OFICINA Y ADMINISTRACION");
		tipos.add("EQUIPOS DE PRENSA - RADIO Y TELEVISION");
		tipos.add("EQUIPOS DE SEGURIDAD - VIGILANCIA Y CONTROL");
		tipos.add("EQUIPO Y MEDIOS DE COMUNICACION");
		tipos.add("MUEBLES DE OFICINA");
		tipos.add("MUEBLES DE USO EDUCATIVO Y ESCOLAR");
		tipos.add("MUEBLES DE USO INDUSTRIAL");
		return tipos;
	}

	@Override
	public List<String> quienUsa() {
		List<String> quienUsa = new LinkedList<>();
		quienUsa.add("Administrativo");
		quienUsa.add("Docente");
		quienUsa.add("Estudiante");
		return quienUsa;
	}
	

}
