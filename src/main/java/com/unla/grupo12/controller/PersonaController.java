package com.unla.grupo12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;

	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;

	@GetMapping("/agregarPersona")
	public ModelAndView agregarPersona(@RequestParam(name = "resultado", required = false) String resultado) {

		ModelAndView model = new ModelAndView(ViewRouteHelper.PERSONA_AGREGAR);

		model.addObject("resultado", resultado);
		model.addObject("persona", new Persona());
		return model;
	}

	@PostMapping("/agregarPersona")
	public RedirectView agregarPersona(@ModelAttribute("persona") PersonaModel personaModel,
			RedirectAttributes atribute) {

		PersonaModel persona = null;
		RedirectView rv = new RedirectView("/persona/agregarPersona?resultado", true);
		try {

			if (personaService.findByDni(personaModel.getDni()) != null) {

				atribute.addFlashAttribute("mensajePersona", "El dni que intenta ingresar, ya se encuentra registrado");
			}

		} catch (Exception e) {

			if (persona == null) {
				personaService.agregarPersona(personaModel);

			} 
			
		}

		return rv;

	}

	@GetMapping("/permisoPersona")
	public ModelAndView traerPermiso() {
		ModelAndView model = new ModelAndView(ViewRouteHelper.PERMISO_X_PERSONA);
		model.addObject("persona", new Persona());
		return model;
	}

	@PostMapping("/permisoPer")
	public ModelAndView traerPermiso(@ModelAttribute("persona") PersonaModel personaModel) {
		ModelAndView model = new ModelAndView(ViewRouteHelper.ListPERMISOS);
		List<Permiso> lista = permisoService.traerPermisoxDni(personaModel.getDni());
		model.addObject("permisos", lista);
		return model;
	}
	
	
	
	@GetMapping("generarUrl/{id}")
	public RedirectView generarUrlPersona(@PathVariable("id") int id) {

		RedirectView redirect = new RedirectView();
		
		PersonaModel persona = personaService.findByIdPersona(id);
		
		String nombre = persona.getNombre();
		String apellido = persona.getApellido();
		long dni = persona.getDni();
		
		
		String url = "https://rodries11.github.io/grupo-12-OO2-2021/" + "?nombre=" + nombre + " " + apellido + "&dni=" + dni ;
		
		
		
		
		redirect.setUrl(url);
		
		return redirect;

	}

}
