package uce.edu.ec.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Banner;
import uce.edu.ec.app.repository.BannersRepository;

@Service
public class BannersServiceJPA implements IBannersService {

	@Autowired
	BannersRepository bannersRepo;

	@Override
	public void insertar(Banner banner) {
		bannersRepo.save(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		return bannersRepo.findAll();
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		Optional<Banner> optional = bannersRepo.findById(idBanner);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	

	@Override
	public void eliminar(int idBannner) {
		bannersRepo.deleteById(idBannner);

	}

	@Override
	public List<Banner> buscarActivos() {
		List<Banner> estatus = bannersRepo.findByEstatus("Activo");
		return estatus;
	}

	@Override
	public boolean existePorTitulo(String titulo) {
		return bannersRepo.existsByTitulo(titulo);
	}

	@Override
	public Page<Banner> buscarTodos(Pageable page) {
		return bannersRepo.findAllOrdenado(page);
	}

}
