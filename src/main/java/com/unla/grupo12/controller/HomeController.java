package com.unla.grupo12.controller;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.model.PerfilModel;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.service.IPerfilService;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IPersonaService;
import com.unla.grupo12.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

//REALIZA UN MAPEO HACIA localhost:8080/

@Controller
@RequestMapping("/")

public class HomeController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;

	@PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
	@GetMapping("/")
	public String index() {

		return ViewRouteHelper.INDEX;

	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		model.addAttribute("error", error);
		model.addAttribute("logout", logout);

		return ViewRouteHelper.LOGIN;

	}

	@GetMapping("/logout")
	public String logout(Model model) {
		
		usuarioService.logoutUsuario();
		return ViewRouteHelper.LOGOUT;

	}

	@GetMapping("/loginsuccess")
	public String loginCheck(Model model) {

		return ViewRouteHelper.INDEX;

	}

	

	
	@GetMapping("/lista-usuarios")
	public ModelAndView listaUsuario() {
		ModelAndView mov = new ModelAndView(ViewRouteHelper.USUARIOS);
		List<UsuarioModel> list = usuarioService.listUsuarios();
		mov.addObject("listaUsuarios", list);
		
		return mov;
	}

	
	@GetMapping(value = "/usuarios-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> usuarioReporte() throws IOException {
		ByteArrayInputStream pdf = usuarioService.generacionPdf();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ListaUsuarios.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdf));
	}
	
	@GetMapping("/lista-personas")
	public ModelAndView listaPersonas() {
		ModelAndView mov = new ModelAndView(ViewRouteHelper.PERSONAS);
		List<PersonaModel> list = personaService.listPersonaModel();
		mov.addObject("listaPersonas", list);
		return mov;
	}
	
	@GetMapping("/lista-permisos")
	public ModelAndView listaPermisos() {
		ModelAndView mov = new ModelAndView(ViewRouteHelper.ListPERMISOS);
		List<PermisoModel> list = permisoService.listPermisoModel();
		mov.addObject("listPermisos", list);
		return mov;
	}
	
	
	
}
