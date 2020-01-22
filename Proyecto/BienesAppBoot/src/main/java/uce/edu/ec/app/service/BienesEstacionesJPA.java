package uce.edu.ec.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Bienes_Estaciones;
import uce.edu.ec.app.repository.BienesEstacionesRepository;

@Service
public class BienesEstacionesJPA implements IBienes_Estaciones {

	@Autowired
	BienesEstacionesRepository bienesEstacionesRepo;

	@Override
	public void insertar(Bienes_Estaciones bienes_Estaciones) {

		bienesEstacionesRepo.save(bienes_Estaciones);
	}

	@Override
	public List<Bienes_Estaciones> buscarTodos() {

		return bienesEstacionesRepo.findAll();
	}

	@Override
	public Bienes_Estaciones buscarPorId(int id) {
		Optional<Bienes_Estaciones> optional = bienesEstacionesRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id) {
		bienesEstacionesRepo.deleteById(id);

	}

	@Override
	public Bienes_Estaciones buscarIdPorIdBien(int id) {
		return bienesEstacionesRepo.findByBien_Id(id);
	}

	@Override
	public boolean existe(int id) {

		return bienesEstacionesRepo.existsById(id);
	}

	@Override
	public List<Bienes_Estaciones> buscarIdPorIdEstacion(int idEstacion) {

		return bienesEstacionesRepo.findByEstacion_Id(idEstacion);
	}

	@Override
	public Page<Bienes_Estaciones> buscarTodos(Pageable page) {
		return bienesEstacionesRepo.findAllOrdenado(page);
	}

	@Override
	public boolean existeRegistroPorIdBienIdEstacion(int idBien, int idEstacion) {
		return bienesEstacionesRepo.existsByBien_IdAndEstacion_Id(idBien, idEstacion);
	}

	@Override
	public Page<Bienes_Estaciones> buscarPorIdEstacion(int idEstacion, Pageable page) {
		return bienesEstacionesRepo.findByIdEstacion(idEstacion, page);
	}

	@Override
	public Page<Bienes_Estaciones> buscarPorAltaBien(String Alta, Pageable page) {
		return bienesEstacionesRepo.findByBien_Alta(Alta, page);
	}

	@Override
	public Page<Bienes_Estaciones> buscarPorAltaAnteriorBien(String anterior, Pageable page) {

		return bienesEstacionesRepo.findByBien_Anterior(anterior, page);
	}

	@Override
	public Page<Bienes_Estaciones> buscarPorEstacion_IdAndBien_Alta(int idEstacion, String alta, Pageable page) {
		return bienesEstacionesRepo.findByEstacion_IdAndBien_Alta(idEstacion, alta, page);
	}

	@Override
	public List<Bienes_Estaciones> buscarPorEstacion_IdAndBien_AltaSinPaginar(int idEstacion, String alta) {

		return bienesEstacionesRepo.findByEstacion_IdAndBien_AltaOrderByIdDesc(idEstacion, alta);
	}

	@Override
	public List<Bienes_Estaciones> buscarCambiosPorPeriodoAndIdEstacion(int idEstacion, Date startDate, Date endDate) {

		return bienesEstacionesRepo.findByEstacion_IdAndActualizacionBetweenOrderByActualizacionDesc(idEstacion,
				startDate, endDate);
	}

	@Override
	public Page<Bienes_Estaciones> buscarCambiosPorPeriodoAndIdEstacionPaginado(int idEstacion, Date startDate,
			Date endDate, Pageable page) {

		return bienesEstacionesRepo.findByEstacion_IdAndActualizacionBetweenOrderByActualizacionDesc(idEstacion,
				startDate, endDate, page);
	}

	@Override
	public List<Bienes_Estaciones> buscarPorAltaBienSinPaginar(String Alta) {
		return bienesEstacionesRepo.findByBien_AltaOrderByIdDesc(Alta);
	}

}
