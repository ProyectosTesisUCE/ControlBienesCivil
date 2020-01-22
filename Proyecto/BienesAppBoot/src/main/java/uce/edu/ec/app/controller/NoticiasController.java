package uce.edu.ec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uce.edu.ec.app.model.Noticia;
import uce.edu.ec.app.service.INoticiasService;

@Controller
@RequestMapping(value = "/noticias")
public class NoticiasController {

	@Value("${noticia.guardar}")
	private String mensajeGuardar;
	
	@Value("${noticia.eliminar}")
	private String mensajeEliminar;
	
	@Value("${noticia.editar}")
	private String mensajeEdicion;
	
	@Value("${noticia.repetida}")
	private String mensajeRepetido;
	
	// Inyectamos una instancia desde nuestro Root ApplicationContext
	@Autowired
	private INoticiasService serviceNoticias;

	private String edicion = "";

	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Noticia> listaNoticias = serviceNoticias.buscarTodas(page);
		model.addAttribute("noticias", listaNoticias);
		return "noticias/listNoticias";
	}

	/**
	 * Metodo para mostrar el formulario para crear una noticia
	 * 
	 * @param noticia
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}

	/**
	 * Metodo para guardar el registro de la Noticia
	 * 
	 * @param noticia
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result, Model model,
			RedirectAttributes attributes, @RequestParam("titulo") String titulo) {
		// Insertamos la noticia

		if (edicion == "") {
			//nuevo
			if (serviceNoticias.existePorTitulo(titulo)) {
				model.addAttribute("alerta", mensajeRepetido + titulo);
				return "noticias/formNoticia";
			} else {
				serviceNoticias.guardar(noticia);
				attributes.addFlashAttribute("mensaje", mensajeGuardar);
				return "redirect:/noticias/indexPaginate";
			}
		} else {
			//edición
			serviceNoticias.guardar(noticia);
			attributes.addFlashAttribute("mensaje", mensajeEdicion);
			edicion = "";
			return "redirect:/noticias/indexPaginate";
		}

	}

	/**
	 * Metodo para eliminar una noticia
	 * 
	 * @param idNoticia
	 * @param model
	 * @param attributes
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		serviceNoticias.eliminar(idNoticia);
		attributes.addFlashAttribute("mensaje", mensajeEliminar);

		return "redirect:/noticias/indexPaginate";
	}

	/**
	 * Metodo para mostrar el formulario de Editar
	 * 
	 * @param idNoticia
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/edit/{id}")
	public String editar(@PathVariable("id") int idNoticia, Model model) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		model.addAttribute("noticia", noticia);
		edicion = "si";
		return "noticias/formNoticia";
	}

	@RequestMapping(value = "/cancel")
	public String mostrarAcerca() {
		return "redirect:/noticias/indexPaginate";
	}
}
