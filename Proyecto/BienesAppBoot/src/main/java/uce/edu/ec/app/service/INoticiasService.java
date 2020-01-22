package uce.edu.ec.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uce.edu.ec.app.model.Noticia;

public interface INoticiasService {

	void guardar(Noticia noticia);

	List<Noticia> buscarUltimas();

	List<Noticia> buscarTodas();

	Page<Noticia> buscarTodas(Pageable page);

	void eliminar(int idNoticia);

	Noticia buscarPorId(int idNoticia);

	boolean existePorTitulo(String titulo);

}
