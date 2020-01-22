package uce.edu.ec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import uce.edu.ec.app.model.Perfil;
import uce.edu.ec.app.model.Usuario;
import uce.edu.ec.app.service.IPerfilesService;
import uce.edu.ec.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Value("${usuario.guardar}")
	private String mensajeGuardar;
	
	@Value("${usuario.eliminar}")
	private String mensajeEliminar;
	
	@Value("${usuario.editar}")
	private String mensajeEdicion;
	
	@Value("${usuario.repetido}")
	private String mensajeRepetido;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IUsuariosService serviceUsuarios;

	@Autowired
	private IPerfilesService servicePerfiles;
	
	@GetMapping("/createNewUser")
	public String crearNew(@ModelAttribute Usuario usuario) {
		return "usuarios/newUser";
	}

	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Usuario> usuarios = serviceUsuarios.buscarTodos(page);
		model.addAttribute("usuarios", usuarios);
		return "usuarios/listUsuarios";
	}

	@PostMapping("/saveNewUser")
	public String guardarNewUser(@ModelAttribute Usuario usuario, Model model, @RequestParam("perfil") String perfil,
			BindingResult result, RedirectAttributes attributes, @RequestParam("cuenta") String cuenta,
			@RequestParam("email") String email) {

		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "usuarios/newUser";
		}

		if (serviceUsuarios.existePorCunetaEmail(cuenta, email)) {
			model.addAttribute("alerta", mensajeRepetido+" " + cuenta + " y email: " + email);
			return "usuarios/newUser";

		} else {
			System.out.println("Usuario: " + usuario);
			System.out.println("Perfil: " + perfil);

			String tmpPass = usuario.getPwd();
			String encriptado = encoder.encode(tmpPass);
			usuario.setPwd(encriptado);
			serviceUsuarios.guardar(usuario);

			Perfil perfilTmp = new Perfil();
			perfilTmp.setCuenta(usuario.getCuenta());
			perfilTmp.setPerfil(perfil);
			servicePerfiles.guardar(perfilTmp);
			attributes.addFlashAttribute("mensaje", mensajeGuardar);
			return "redirect:/formLogin";

		}

	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Usuario usuario, Model model, @RequestParam("perfil") String perfil,
			BindingResult result, RedirectAttributes attributes, @RequestParam("cuenta") String cuenta,
			@RequestParam("email") String email, @RequestParam("id") int id, @RequestParam("activo") String activo) {

		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "usuarios/formUsuario";
		}
		usuario = serviceUsuarios.buscarPorId(id);
		System.out.println("Cuenta por id: " + usuario.getCuenta());
		Perfil p = servicePerfiles.buscarPorCuneta(usuario.getCuenta());
		System.out.println(p.toString());

		System.out.println("Entro a la edicion");
		usuario.setActivo(Integer.parseInt(activo));
		usuario.setEmail(email);
		serviceUsuarios.guardar(usuario);
		p.setCuenta(usuario.getCuenta());
		p.setPerfil(perfil);
		servicePerfiles.guardar(p);
		attributes.addFlashAttribute("mensaje", mensajeEdicion);
		return "redirect:/usuarios/indexPaginate";

	}

	// editar por ID
	@GetMapping(value = "edit/{id}")
	public String Editar(@PathVariable("id") int idUsuario, Model model) {
		Usuario usuario = serviceUsuarios.buscarPorId(idUsuario);
		model.addAttribute("usuario", usuario);
		return "usuarios/editUsuario";
	}

	// Eliminar por id
	@GetMapping(value = "delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		Usuario usuario = serviceUsuarios.buscarPorId(idUsuario);
		Perfil perfil = servicePerfiles.buscarPorCuneta(usuario.getCuenta());
		servicePerfiles.eliminar(perfil.getId());
		serviceUsuarios.eliminar(idUsuario);
		attributes.addFlashAttribute("mensaje", mensajeEliminar);
		return "redirect:/usuarios/indexPaginate";
	}

	@RequestMapping(value = "/cancel")
	public String cancelar() {
		return "redirect:/usuarios/indexPaginate";
	}

	@RequestMapping(value = "/cancelNewUser")
	public String cancelarNewUser() {
		return "redirect:/formLogin";
	}

	@RequestMapping(value = "/changePassword")
	public String cambiarContrasena(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios/editPassword";
	}

	@GetMapping(value = "/buscar")
	public String buscarUsuario(Model model, @RequestParam("cuenta1") String cuenta1,
			@RequestParam("email1") String email1) {

		if (serviceUsuarios.existePorCunetaEmail(cuenta1, email1)) {
			Usuario usuario = serviceUsuarios.buscarCuentaAndEmail(cuenta1, email1);
			
			System.out.println(usuario.toString());
			model.addAttribute("usuario", usuario);
			model.addAttribute("mensaje", "Ingrese la nueva clave para el usuario: "+usuario.getCuenta());

		} else {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("alerta", "No existe el Usuario: "+ cuenta1);
		}
		return "usuarios/editPassword";
	}

	@PostMapping("/savePassword")
	public String guardarClave(@ModelAttribute Usuario usuario, Model model, BindingResult result,
			RedirectAttributes attributes, @RequestParam("id") int id, @RequestParam("pwdNew") String pwd) {

		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "usuarios/formUsuario";
		}
		try {
			usuario = serviceUsuarios.buscarPorId(id);
			System.out.println("Entro a la edicion");
			String tmpPass = pwd;
			String encriptado = encoder.encode(tmpPass);
			usuario.setPwd(encriptado);
			serviceUsuarios.guardar(usuario);
			model.addAttribute("alerta", "Contrase�a cambiada para la cuenta: " + usuario.getCuenta());
		} catch (Exception e) {

		}
		return "redirect:/formLogin";

	}
}
