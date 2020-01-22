package uce.edu.ec.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uce.edu.ec.app.model.Noticia;
import uce.edu.ec.app.repository.NoticiasRepository;

// Registramos esta clase como un Bean en nuestro Root ApplicationContext.
@Service
public class NoticiasServiceJPA implements INoticiasService {

	// Inyectamos una instancia desde nuestro Root ApplicationContext.
	@Autowired
	private NoticiasRepository noticiasRepo;

	@Override
	public List<Noticia> buscarUltimas() {
		List<Noticia> noticias = noticiasRepo.findTop3ByEstatusOrderByIdDesc("Activa");
		return noticias;
	}

	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}

	@Override
	public List<Noticia> buscarTodas() {
		return noticiasRepo.findAll();
	}

	@Override
	public void eliminar(int idNoticia) {
		// noticiasRepo.delete(idNoticia); // Spring 4.3
		noticiasRepo.deleteById(idNoticia);
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> optional = noticiasRepo.findById(idNoticia);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public boolean existePorTitulo(String titulo) {
		return noticiasRepo.existsByTitulo(titulo);
	}

	@Override
	public Page<Noticia> buscarTodas(Pageable page) {
		return noticiasRepo.findAllOrdenado(page);
	}

}
