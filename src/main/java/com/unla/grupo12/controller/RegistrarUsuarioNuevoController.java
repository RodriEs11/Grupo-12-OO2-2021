package com.unla.grupo12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12.converter.PerfilConverter;
import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.service.IPerfilService;
import com.unla.grupo12.service.IUsuarioService;

@Controller
@RequestMapping("/registrarse")
public class RegistrarUsuarioNuevoController {

	@Autowired
	@Qualifier("perfilService")
	private IPerfilService perfilService;
	
	@Autowired
	@Qualifier("perfilConverter")
	private PerfilConverter perfilConverter;
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	public ModelAndView registrarUsuarioNuevo() {
		
		ModelAndView mov = new ModelAndView(ViewRouteHelper.REGISTRARSE);

		List<Perfil> perfiles = perfilService.getAll();
		mov.addObject("perfiles", perfiles);
		mov.addObject("usuarionuevo", new Usuario());
		
		return mov;

	}
	
	@PostMapping("")
	public RedirectView registrarUsuarioNuevo(@ModelAttribute("usuario") UsuarioModel usuarioModel) {
		
		RedirectView redirect = new RedirectView("login");
		
		/*
		 
		//Registra el nuevo usuario con el tipo de perfil Usuario de la bd
		usuarioModel.setPerfil(perfilConverter.modelToEntity(perfilService.findByNombre("Usuario")));
		
		*/
		
		long idPerfil = usuarioModel.getPerfil().getId();
		Perfil perfil = perfilConverter.modelToEntity(perfilService.findById(idPerfil));
		
		usuarioModel.setPerfil(perfil);
		usuarioService.agregarOActualizar(usuarioModel);

		return redirect;

	}

	
}
