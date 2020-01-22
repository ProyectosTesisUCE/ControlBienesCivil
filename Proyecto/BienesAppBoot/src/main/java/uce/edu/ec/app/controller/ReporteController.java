package uce.edu.ec.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uce.edu.ec.app.model.Bienes_Estaciones;
import uce.edu.ec.app.model.Estacion;
import uce.edu.ec.app.service.IBienes_Estaciones;
import uce.edu.ec.app.service.IEstacionService;
import uce.edu.ec.app.util.ExcelBuilderDetalle;
import uce.edu.ec.app.util.PDFBuilderDetalle;

@Controller
@RequestMapping(value = "/reportes")
public class ReporteController {

	@Autowired
	private IEstacionService serviceEstaciones;

	@Autowired
	private IBienes_Estaciones serviceAsignaciones;

	private int idEstacionB = 0;

	private int numEquipos = 0;

	private String busqueda = "";

	private String paginado = "";

	private String token = "";

	private Date inicio = null;

	private Date fin = null;

	private List<Bienes_Estaciones> cambioPeriodoDetalle;
	private List<Bienes_Estaciones> BienEstacionBuscados ;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@GetMapping(value = "/detail")
	public String mostrarDetalle(Model model, @RequestParam("idEstacion") int idEstacion, Pageable page) {
		//Renderizo todos las vista con los datos
		Page<Bienes_Estaciones> bienes_Estaciones = serviceAsignaciones.buscarPorIdEstacion(idEstacion, page);
		List<Bienes_Estaciones> numBienesEstaciones = serviceAsignaciones.buscarIdPorIdEstacion(idEstacion);
		numEquipos = numBienesEstaciones.size();
		BienEstacionBuscados = numBienesEstaciones;
		model.addAttribute("numEquipo", numEquipos);
		model.addAttribute("bienes_Estaciones", bienes_Estaciones);
		model.addAttribute("estacion", serviceEstaciones.buscarPorId(idEstacion));
		idEstacionB = idEstacion;
		return "reportes/detalleEstacion";// Buscar en la base de datos la conformacion de los numero de equipos con
											// los
		// bienes asignados
	}

	// Paginacion de los bienes buscados por id de estacion
	@GetMapping(value = "/detailPaginate")
	public String mostrarDetallePaginado(Model model, Pageable page) {
		model.addAttribute("numEquipo", numEquipos);
		model.addAttribute("estacion", serviceEstaciones.buscarPorId(idEstacionB));
		if (busqueda == "") {
			Page<Bienes_Estaciones> bienes_Estaciones = serviceAsignaciones.buscarPorIdEstacion(idEstacionB, page);
			List<Bienes_Estaciones> numBienesEstaciones = serviceAsignaciones.buscarIdPorIdEstacion(idEstacionB);
			BienEstacionBuscados = numBienesEstaciones;
			model.addAttribute("bienes_Estaciones", bienes_Estaciones);

		} else {
			Page<Bienes_Estaciones> bienes_Estaciones = serviceAsignaciones
					.buscarPorEstacion_IdAndBien_Alta(idEstacionB, token, page);

			if (bienes_Estaciones.isEmpty()) {
				BienEstacionBuscados = bienes_Estaciones.getContent();
				model.addAttribute("alerta", "No existe el registro con Alta Nueva: " + token);
				busqueda = "";
			} else {
				List<Bienes_Estaciones> reporte = serviceAsignaciones.buscarPorEstacion_IdAndBien_AltaSinPaginar(idEstacionB, token);
				BienEstacionBuscados = reporte;
				model.addAttribute("bienes_Estaciones", bienes_Estaciones);
				busqueda = "";
			}

		}
		return "reportes/detalleEstacion";
	}

	@GetMapping(value = "/personalizado")
	public String mostrarPeriodo() {
		return "redirect:/reportes/detallePeriodoPaginate";
	}

	// Redireccion formulario de busqueda por periodo
	@GetMapping(value = "/detallePeriodoPaginate")
	public String mostrarPeriodoPaginado(Model model, Pageable page) {

		if (busqueda == "") {

			System.out.println("Inicio: " + busqueda);
			// Formulario en blanco
			model.addAttribute("numEquipo", 0);
			model.addAttribute("estacion", serviceEstaciones.buscarPorId(idEstacionB));

		} else if (paginado == "si") {
			// Formulario con busqueda personalizada

			model.addAttribute("estacion", serviceEstaciones.buscarPorId(idEstacionB));

			Page<Bienes_Estaciones> bienes_Estaciones_Paginado = serviceAsignaciones
					.buscarCambiosPorPeriodoAndIdEstacionPaginado(idEstacionB, inicio, fin, page);

			if (bienes_Estaciones_Paginado.isEmpty()) {
				// Mensaje de no encontrado
				model.addAttribute("alerta", "No existen registros para el período comprendido entre: "
						+ dateFormat.format(inicio) + " & " + dateFormat.format(fin));
				busqueda = "";
			} else {
				List<Bienes_Estaciones> bienes_Estaciones = serviceAsignaciones
						.buscarCambiosPorPeriodoAndIdEstacion(idEstacionB, inicio, fin);
				for (Bienes_Estaciones l : bienes_Estaciones) {
					System.out.println(l.toString());
				}
				cambioPeriodoDetalle = bienes_Estaciones;
				model.addAttribute("numEquipo", bienes_Estaciones.size());
				System.out.println("Tamaño de lista: " + bienes_Estaciones.size());
				model.addAttribute("bienes_Estaciones", bienes_Estaciones_Paginado);
				System.out.println("Primer paginado: " + busqueda + paginado);
			}
		}
		return "reportes/detalleEstacionPeriodo";
	}

	// Busqueda por fecha inicio and fin

	@PostMapping(value = "/buscar")
	public String buscarPeriodo(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate)
			throws ParseException {
		System.out.println("fecha inicio:" + startDate + " fecha fin:" + endDate);
		inicio = dateFormat.parse(startDate);
		fin = dateFormat.parse(endDate);
		busqueda = "si";
		paginado = "si";
		return "redirect:/reportes/detallePeriodoPaginate";
	}

	// Reporte Todos los bienes por salas
	@GetMapping(value = "/downloadTotalDetalle")
	public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
		// Todos los bienes pero por id de estacion
		List<Bienes_Estaciones> bienes_Estaciones = BienEstacionBuscados;

		if (reportType != null && reportType.equals("excel")) {
			return new ModelAndView(new ExcelBuilderDetalle(), "bienes_Estaciones", bienes_Estaciones);

		} else if (reportType != null && reportType.equals("pdf")) {
			return new ModelAndView(new PDFBuilderDetalle(), "bienes_Estaciones", bienes_Estaciones);
		}
		return new ModelAndView("detalle", "bienes_Estaciones", bienes_Estaciones);
	}

	@GetMapping(value = "/cambioDetalle")
	public ModelAndView getDetallePeriodo(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
		// Todos los bienes pero por id de estacion
		List<Bienes_Estaciones> bienes_Estaciones = cambioPeriodoDetalle;
		if (reportType != null && reportType.equals("excel")) {
			return new ModelAndView(new ExcelBuilderDetalle(), "bienes_Estaciones", bienes_Estaciones);

		} else if (reportType != null && reportType.equals("pdf")) {
			return new ModelAndView(new PDFBuilderDetalle(), "bienes_Estaciones", bienes_Estaciones);
		}
		return new ModelAndView("detalle", "bienes_Estaciones", bienes_Estaciones);

	}

	// Busqueda por alta
	@PostMapping(value = "/search")
	public String buscar(@RequestParam("campo") String campo) {
		System.out.println("alta: " + campo);
		busqueda = "si";
		token = campo;
		return "redirect:/reportes/detailPaginate";
	}

	@GetMapping(value = "/cancel")
	public String Cancelar() {
		busqueda = "";
		paginado = "";
		cambioPeriodoDetalle = null;
		return "redirect:/reportes/detailPaginate";
	}

	@GetMapping(value = "/estaciones")
	public String reporteEstaciones() {
		return "reportes/reporteEstaciones";
	}

	@ModelAttribute("estaciones")
	public List<Estacion> getEstaciones() {
		return serviceEstaciones.busacarTodas();
	}
}
