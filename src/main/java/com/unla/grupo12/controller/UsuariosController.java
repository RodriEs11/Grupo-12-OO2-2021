package com.unla.grupo12.controller;

import com.unla.grupo12.converter.PerfilConverter;
import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.service.IPerfilService;
import com.unla.grupo12.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;



@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;

	@Autowired
	@Qualifier("perfilService")
	private IPerfilService perfilService;
	
	@Autowired
	@Qualifier("perfilConverter")
	private PerfilConverter perfilConverter;

	
	
	/*  EN LA PESTAÑA USUARIOS SE MUESTRAN EN UNA TABLA 
	 *  TODOS LOS USUARIOS Y PERFILES QUE HAY EN LA BASE DE DATOS
	 *  
	 */
	
	@GetMapping("")
	public ModelAndView index() {
		
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.USUARIOS);
		
		List<Usuario> usuarios = usuarioService.getAll();
		List<Perfil> perfiles = perfilService.getAll();
		modelo.addObject("usuarios", usuarios);
		modelo.addObject("perfiles", perfiles);
		return modelo;
	}
	
	/* PESTAÑA AGREGAR USUARIO A BD
	 * SE TRAEN, A TRAVES DEL SERVICIO DE PERFIL, TODOS LOS PERFILES CARGADOS EN LA BD,
	 * SE AGREGAN AL ModelAndView PARA MOSTRARLOS EN UNA LISTA DESPLEGABLE
	 * 
	 * 
	 */
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("agregar")
	public ModelAndView agregarUsuario() {

		ModelAndView modelo = new ModelAndView(ViewRouteHelper.USUARIOS_AGREGAR);
		
		List<Perfil> perfiles = perfilService.getAll();
		
		modelo.addObject("perfiles", perfiles);
		modelo.addObject("usuario", new Usuario());

		return modelo;

	}

	
	
	/* UNA VEZ REALIZADA LA SOLICITUD POST
	 * SE AGREGA EL UsuarioModel A LA BD
	 * 
	 */
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("agregar")
	public RedirectView agregarUsuario(@ModelAttribute("usuario") UsuarioModel usuarioModel) {
		
		
		//REDIRECCIONA A LA VISTA DE USUARIOS
		RedirectView redirect = new RedirectView("/lista-usuarios", false);
		
		//AGREGA EL MODELO A LA BASE DE DATOS A TRAVES DEL SERVICIO 
		long idPerfil = usuarioModel.getPerfil().getId();
		Perfil perfil = perfilConverter.modelToEntity(perfilService.findById(idPerfil));
		
		usuarioModel.setPerfil(perfil);
		
		usuarioService.agregarOActualizar(usuarioModel);
		
		
		return redirect;
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("editar/{id}")
	public ModelAndView editarUsuario(@PathVariable("id") Long id) {

		ModelAndView modelo = new ModelAndView(ViewRouteHelper.USUARIOS_EDITAR);
		
		UsuarioModel usuario = usuarioService.findById(id);
		modelo.addObject("usuario", usuario);
		
		
		//LISTADO DE TIPOS DE PERFILES EN LA LISTA DESPLEGABLE DENTRO DE LA VSITA Usuarios -> EditarUsuario
		List<Perfil> perfiles = perfilService.getAll();
		modelo.addObject("perfiles", perfiles);
		
		
		
		return modelo;

	}
	
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("actualizar/{id}")
	public RedirectView actualizarUsuario(@PathVariable("id") Long id, @ModelAttribute("usuario") UsuarioModel usuarioModel) {
		
		
		long idPerfil = usuarioModel.getPerfil().getId();
		Perfil perfil = perfilConverter.modelToEntity(perfilService.findById(idPerfil));
		
		usuarioModel.setPerfil(perfil);
		
		
		usuarioService.agregarOActualizar(usuarioModel);
		
		//REDIRECCIONA A LA VISTA DE USUARIOS
		RedirectView redirect = new RedirectView("/lista-usuarios/", false);
		
		
		
		return redirect;
		
	}
	
	//BAJA LOGICA DE LA BASE DE DATOS
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("eliminar/{id}")
	public RedirectView actualizarUsuario(@PathVariable("id") Long id) {
		
		
		usuarioService.darDeBaja(id);
		
		//REDIRECCIONA A LA VISTA DE USUARIOS
		RedirectView redirect = new RedirectView("/lista-usuarios/", false);
		
		
		
		return redirect;
		
	}
	

}
