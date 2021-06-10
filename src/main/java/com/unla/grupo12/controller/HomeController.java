package com.unla.grupo12.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PerfilModel;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.service.IPerfilService;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12.helpers.ViewRouteHelper;

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
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")	
	@GetMapping("/lista-permisos")
	public ModelAndView listaPermiso() {
		ModelAndView mov = new ModelAndView(ViewRouteHelper.PERMISO);
		List<Permiso> list = permisoService.getAll();
		mov.addObject("listaPermisos", list);
		
		return mov;
	} 

	
	/*@GetMapping(value = "/usuarios-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> usuarioReporte() throws IOException {
		ByteArrayInputStream pdf = usuarioService.generacionPdf();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ListaUsuarios.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdf));
	}*/
}
