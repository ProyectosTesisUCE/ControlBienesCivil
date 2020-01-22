package uce.edu.ec.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uce.edu.ec.app.model.Bien;
import uce.edu.ec.app.model.Bienes_Estaciones;
import uce.edu.ec.app.service.IBienService;
import uce.edu.ec.app.service.IBienes_Estaciones;
import uce.edu.ec.app.service.IDetallesService;
import uce.edu.ec.app.util.ExcelBuilder;
import uce.edu.ec.app.util.PDFBuilder;

@Controller
@RequestMapping(value = "/bienes")
public class BienesController {

	@Value("${bien.guardar}")
	private String mensajeGuardar;

	@Value("${bien.eliminar}")
	private String mensajeEliminar;

	@Value("${bien.repetido.altanueva}")
	private String mensajeRepetidoAltaNueva;

	@Value("${bien.repetido.altaanterior}")
	private String mensajeRepetidoAltaAnterior;

	@Value("${bien.noexiste.bien.altanueva}")
	private String mensajeNoExiste;

	@Value("${bien.repetido}")
	private String mensajeRepetido;

	@Autowired
	private IBienService serviceBienes;

	@Autowired
	private IDetallesService serviceDetalles;

	@Autowired
	private IBienes_Estaciones serviceAsignacion;

	private String edicion = "";

	private String busqueda = "";

	private String paginado = "";

	private String token = "";

	private Date inicio = null;

	private Date fin = null;

	private List<Bien> bienesPorPeriodo;

	private List<Bien> bienesBuscados = null;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Metodo que muestra la lista de bienes
	 * 
	 * @param model
	 * @return
	 */

	@GetMapping(value = "/index")
	public String mostarIndex(Model model) {
		List<Bien> lista = serviceBienes.buscarTodas();
		model.addAttribute("bienes", lista);
		return "bienes/listBienes";
	}

	/**
	 * Metodo que muestra la lista de bienes con paginacion
	 * 
	 * @param model
	 * @param page
	 * @return
	 */

	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		if (busqueda == "") {
			// Todos los registros
			Page<Bien> lista = serviceBienes.buscarTodas(page);
			bienesBuscados = serviceBienes.buscarTodas();
			model.addAttribute("bienes", lista);
		} else if (paginado == "si") {
			// Formulario con registro buscados
			Page<Bien> lista = serviceBienes.search(token, page);

			if (lista.isEmpty()) {
				bienesBuscados = lista.getContent();
				model.addAttribute("alerta", mensajeNoExiste + token);
				busqueda = "";
			} else {
				List<Bien> reporte = serviceBienes.searchSinPaginar(token);
				bienesBuscados = reporte;
				model.addAttribute("bienes", lista);
			}

		}

		return "bienes/listBienes";
	}

	// Redireccion formulario de busqueda por periodo
	@GetMapping(value = "/periodPaginate")
	public String mostrarPeriodoPaginado(Model model, Pageable page) {

		if (busqueda == "") {
			// Lista vacia
		} else if (paginado == "si") {
			Page<Bien> lista = serviceBienes.buscarPeriodoPaginado(inicio, fin, page);
			if (lista.isEmpty()) {
				// Mensaje de no encontrado
				model.addAttribute("alerta", "No existen Bienes registrados para el período comprendido entre: "
						+ dateFormat.format(inicio) + " & " + dateFormat.format(fin));
				busqueda = "";

			} else {
				List<Bien> bienesLista = serviceBienes.buscarPeriodo(inicio, fin);
				System.out.println("Estoy en el paginado de la busqueda");
				model.addAttribute("bienes", lista);
				bienesPorPeriodo = bienesLista;
			}

		}
		return "bienes/listPeriodo";
	}

	// Busqueda por alta
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(@RequestParam("campo") String campo) {
		System.out.println("alta: " + campo);
		busqueda = "si";
		paginado = "si";
		token = campo;
		return "redirect:/bienes/indexPaginate";
	}

	// Busqueda por alta
	@PostMapping(value = "/busquedaPrevia")
	public String busquedaPrevia(@RequestParam("campo") String campo, RedirectAttributes attributes) {
		System.out.println("alta: " + campo);

		List<Bien> reporte = serviceBienes.searchSinPaginar(campo);

		if (reporte.isEmpty()) {
			attributes.addFlashAttribute("mensaje", mensajeNoExiste + campo);
			return "redirect:/bienes/create";
		} else {
			String total = "";
			for (Bien bien : reporte) {
				total = total + "\n" + bien.toString();
			}
			attributes.addFlashAttribute("alerta", "Ya existe " + reporte.size() + " registro de biene con: " + total);

		}
		return "redirect:/bienes/create";

	}

	/*
	 * // Busqueda por alta
	 * 
	 * @PostMapping(value = "/busquedaPrevia") public String
	 * busquedaPrevia(@RequestParam("campo") String campo, RedirectAttributes
	 * attributes) { System.out.println("alta: " + campo);
	 * 
	 * if (serviceBienes.existeRegistroPorALta(campo)) { Bien bienNuevo =
	 * serviceBienes.buscarPorAlta(campo); try { if (bienNuevo.getControl() ==
	 * "Activo") { attributes.addFlashAttribute("alerta",
	 * "Ya existe un Bien con Alta Nueva: " + campo +
	 * " y no se encuentra asignado a un lugar"); } else {
	 * 
	 * Bienes_Estaciones bienes_Estaciones =
	 * serviceAsignacion.buscarIdPorIdBien(bienNuevo.getId());
	 * attributes.addFlashAttribute("alerta", mensajeRepetidoAltaNueva + campo +
	 * " ubicado en: " + bienes_Estaciones.getEstacion().getLugar());
	 * 
	 * } } catch (Exception e) { attributes.addFlashAttribute("alerta",
	 * "Ya existe un Bien con Alta Nueva: " + campo +
	 * " y no se encuentra asignado a un lugar"); } return
	 * "redirect:/bienes/create"; } else if
	 * (serviceBienes.existeRegistroPorAnterior(campo)) { Bien bienAnterior =
	 * serviceBienes.buscarPorAltaAnterior(campo); try { if
	 * (bienAnterior.getControl() == "Activo") {
	 * attributes.addFlashAttribute("alerta",
	 * "Ya existe un Bien con Alta Anterior: " + campo +
	 * " y no se encuentra asignado a un lugar"); } else {
	 * 
	 * Bienes_Estaciones bienes_Estaciones =
	 * serviceAsignacion.buscarIdPorIdBien(bienAnterior.getId());
	 * attributes.addFlashAttribute("alerta", mensajeRepetidoAltaAnterior + campo +
	 * " ubicado en: " + bienes_Estaciones.getEstacion().getLugar()); } } catch
	 * (Exception e) { attributes.addFlashAttribute("alerta",
	 * "Ya existe un Bien con Alta Anterior: " + campo +
	 * " y no se encuentra asignado a un lugar"); }
	 * 
	 * return "redirect:/bienes/create"; } else {
	 * attributes.addFlashAttribute("mensaje", "No existe el Bien con Alta Nueva: "
	 * + campo + " - ni Alta Anterior: " + campo); return "redirect:/bienes/create";
	 * } }
	 */
	// Busqueda por fecha inicio and fin
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public String buscarPeriodo(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate)
			throws ParseException {
		System.out.println("fecha inicio:" + startDate + " fecha fin:" + endDate);
		inicio = dateFormat.parse(startDate);
		fin = dateFormat.parse(endDate);
		busqueda = "si";
		paginado = "si";
		return "redirect:/bienes/periodPaginate";
	}

	/**
	 * Metodo para mostrar el formulario para crear
	 * 
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Bien bien, Model model) {
		model.addAttribute("tipo", serviceBienes.buscarTipo());
		model.addAttribute("usuario", serviceBienes.quienUsa());
		bienesBuscados = null;
		paginado = "";
		return "bienes/formBien";
	}

	// Manejo de Errores
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Bien bien, Model model, BindingResult result, RedirectAttributes attributes,
			@RequestParam(name = "alta", required = false) String alta,
			@RequestParam(name = "anterior", required = false) String anterior) {

		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "bienes/formBien";
		}

		if (edicion == "") {
			// Nuevo Bien
			// Controla repetidos
			if (serviceBienes.exiteRegistroPorAltaAnterior(alta, anterior)) {
				model.addAttribute("alerta",
						mensajeRepetido + " Alta Nueva: " + alta + " y Alta Anterior: " + anterior);
				model.addAttribute("tipo", serviceBienes.buscarTipo());
				model.addAttribute("usuario", serviceBienes.quienUsa());
				return "bienes/formBien";

			} else {
				// Guarda sin Comporovacion
				serviceDetalles.insertar(bien.getDetalle());
				serviceBienes.insertar(bien);
				attributes.addFlashAttribute("mensaje", mensajeGuardar);
				return "redirect:/bienes/indexPaginate";
			}

		} else {
			// Edicion
			serviceDetalles.insertar(bien.getDetalle());
			serviceBienes.insertar(bien);
			try {
				Bienes_Estaciones bienes_Estaciones = serviceAsignacion.buscarIdPorIdBien(bien.getId());
				bienes_Estaciones.setActualizacion(new Date());
				serviceAsignacion.insertar(bienes_Estaciones);
			} catch (Exception e) {

				edicion="";
			}

			attributes.addFlashAttribute("mensaje",
					"El Bien con: Alta nueva: " + alta + " y Alta Anterior: " + anterior + " fue editado");
			edicion = "";
			return "redirect:/bienes/indexPaginate";

		}

	}

	// editar por ID
	@GetMapping(value = "edit/{id}")
	public String Editar(@PathVariable("id") int idBien, Model model) {
		Bien bien = serviceBienes.buscarPorId(idBien);
		model.addAttribute("bien", bien);
		model.addAttribute("tipo", serviceBienes.buscarTipo());
		model.addAttribute("usuario", serviceBienes.quienUsa());
		edicion = "si";
		return "bienes/editBien";
	}

	// Eliminar por id
	@GetMapping(value = "delete/{id}")
	public String eliminar(@PathVariable("id") int idBien, RedirectAttributes attributes) {

		Bien bien = serviceBienes.buscarPorId(idBien);
		int a = 0;
		Bienes_Estaciones asignacion = serviceAsignacion.buscarIdPorIdBien(idBien);
		if (asignacion == null) {
			a = 0;
		} else {
			a = asignacion.getId();
		}
		System.out.println(asignacion + " paso 1 ");
		System.out.println(a);

		if (serviceAsignacion.existe(a)) {
			serviceAsignacion.eliminar(a);
			serviceBienes.eliminar(idBien);
			serviceDetalles.eliminar(bien.getDetalle().getId());
			attributes.addFlashAttribute("mensaje", mensajeEliminar);
			System.out.println("entra");
		} else {
			System.out.println("no entra");
			serviceBienes.eliminar(idBien);
			serviceDetalles.eliminar(bien.getDetalle().getId());
			attributes.addFlashAttribute("mensaje", mensajeEliminar);
		}

		return "redirect:/bienes/indexPaginate";
	}

	@GetMapping(value = "/cancelReporte")
	public String mostrarAcerca() {
		busqueda = "";
		paginado = "";
		bienesPorPeriodo = null;
		return "redirect:/admin/index";
	}

	@GetMapping(value = "/cancel")
	public String mostrarLista() {
		busqueda = "";
		paginado = "";
		bienesPorPeriodo = null;
		return "redirect:/bienes/indexPaginate";
	}
	
	@GetMapping(value = "/listarTodos")
	public String mostrarTodos() {
		busqueda = "";
		paginado = "";
		bienesPorPeriodo = null;
		return "redirect:/bienes/indexPaginate";
	}

	@GetMapping(value = "/personalizado")
	public String mostrarPeriodo() {

		return "redirect:/bienes/periodPaginate";
	}

	/**
	 * Personalizamos el Data Binding p ara todas las propiedades de tipo Date
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	// Reportes por periodo
	@GetMapping(value = "/periodo")
	public ModelAndView getPeriodo(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
		List<Bien> bienes = bienesPorPeriodo;
		if (reportType != null && reportType.equals("excel")) {
			return new ModelAndView(new ExcelBuilder(), "bienes", bienes);

		} else if (reportType != null && reportType.equals("pdf")) {
			return new ModelAndView(new PDFBuilder(), "bienes", bienes);
		}
		return new ModelAndView("listBienes", "bienes", bienes);
	}

	// Reporte Todos los bienes
	@GetMapping(value = "/downloadTotal")
	public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
		List<Bien> bienes = bienesBuscados;
		if (reportType != null && reportType.equals("excel")) {
			return new ModelAndView(new ExcelBuilder(), "bienes", bienes);

		} else if (reportType != null && reportType.equals("pdf")) {
			return new ModelAndView(new PDFBuilder(), "bienes", bienes);
		}
		bienes = null;
		return new ModelAndView("listBienes", "bienes", bienes);
	}

}
