package uce.edu.ec.app.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uce.edu.ec.app.model.Banner;
import uce.edu.ec.app.service.IBannersService;
import uce.edu.ec.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannersController {
	
	@Value("${bienesapp.ruta.imagenes}")
	private String ruta;
	
	@Value("${banner.guardar}")
	private String mensajeGuardar;
	
	@Value("${banner.eliminar}")
	private String mensajeEliminar;
	
	@Value("${banner.editar}")
	private String mensajeEdicion;
	
	@Value("${banner.repetida}")
	private String mensajeRepetido;
	

	@Autowired
	private IBannersService serviceBanners;

	private String edicion = "";

	/**
	 * Metodo para mostrar el listado de banners
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Banner> banners = serviceBanners.buscarTodos();
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}

	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Banner> banners = serviceBanners.buscarTodos(page);
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}

	/**
	 * Metodo para mostrar el formulario para crear un nuevo Banner
	 * 
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner, Model model) {
		return "banners/formBanner";
	}

	/**
	 * Metodo para guardar el objeto de modelo de tipo Banner
	 * 
	 * @param banner
	 * @param result
	 * @param attributes
	 * @param multiPart
	 * @param request
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart,
			@RequestParam("titulo") String titulo, Model model) {

		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			return "banners/formBanner";
		}

		if (edicion == "") {
			if (serviceBanners.existePorTitulo(titulo)) {
				model.addAttribute("alerta", mensajeRepetido + titulo);
				return "banners/formBanner";
			} else {
				if (!multiPart.isEmpty()) {
					String nombreImagen = Utileria.guardarImagen(multiPart, ruta);
					banner.setArchivo(nombreImagen);
				}

				serviceBanners.insertar(banner);
				attributes.addFlashAttribute("mensaje", mensajeGuardar);
				return "redirect:/banners/indexPaginate";
			}
		} else {
			// Edición
			if (!multiPart.isEmpty()) {
				String nombreImagen = Utileria.guardarImagen(multiPart, ruta);
				if (nombreImagen != null) { // La imagen si se subio
					banner.setArchivo(nombreImagen); // Asignamos el nombre de la imagen
				}
			}
			serviceBanners.insertar(banner);
			attributes.addFlashAttribute("mensaje", mensajeEdicion);
			edicion = "";
			return "redirect:/banners/indexPaginate";
		}

	}

	// editar por ID
	@GetMapping(value = "edit/{id}")
	public String Editar(@PathVariable("id") int idBanner, Model model) {
		Banner banner = serviceBanners.buscarPorId(idBanner);
		model.addAttribute("banner", banner);
		edicion = "si";
		return "banners/formBanner";
	}

	// Eliminar por id
	@GetMapping(value = "delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		serviceBanners.eliminar(idBanner);
		attributes.addFlashAttribute("mensaje", mensajeEliminar);
		return "redirect:/banners/indexPaginate";
	}

	@RequestMapping(value = "/cancel")
	public String mostrarAcerca() {
		return "redirect:/banners/indexPaginate";
	}

}
