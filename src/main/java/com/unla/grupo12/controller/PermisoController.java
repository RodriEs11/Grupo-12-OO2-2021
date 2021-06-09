package com.unla.grupo12.controller;

import com.unla.grupo12.converter.LugarConverter;
import com.unla.grupo12.converter.PersonaConverter;
import com.unla.grupo12.converter.RodadoConverter;
import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.LugarModel;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.service.ILugarService;
import com.unla.grupo12.service.IPermisoDiarioService;
import com.unla.grupo12.service.IPermisoPeriodoService;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IPersonaService;
import com.unla.grupo12.service.IRodadoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

  @Autowired
  @Qualifier("permisoPeriodoService")
  private IPermisoPeriodoService permisoPeriodoService;
  
  @Autowired
  @Qualifier("permisoService")
  private IPermisoService permisoService;
  
  @Autowired
  @Qualifier("permisoDiarioService")
  private IPermisoDiarioService permisoDiarioService;

  @Autowired
  @Qualifier("personaService")
  private IPersonaService personaService;
  
  @Autowired
  @Qualifier("lugarService")
  private ILugarService lugarService;
  
  
  @Autowired
  @Qualifier("lugarConverter")
  private LugarConverter lugarConverter;
  
  @Autowired
  @Qualifier("rodadoService")
  private IRodadoService rodadoService;
  
  @Autowired
  @Qualifier("rodadoConverter")
  private RodadoConverter rodadoConverter;
  
  
  @Autowired
  @Qualifier("personaConverter")
  private PersonaConverter personaConverter;
  
  
  

  

  @PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
  @PostMapping("/buscar")
  public RedirectView  buscarRodado(Model model, @RequestParam(name="resultado", required = false) String resultado,
                                    RedirectAttributes atribute) {
  RedirectView rv = new RedirectView("/rodado/buscar", true);

    PermisoPeriodo permiso = permisoPeriodoService.permisoPorRodado(resultado);

    atribute.addFlashAttribute("dominio", permiso);
    if(permiso==null){
      atribute.addFlashAttribute("mensaje", "El dominio ingresado es inexistente");
    }

    return rv;
  }



	@GetMapping("")
	public ModelAndView agregarPermiso() {
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PERMISOS_AGREGAR);
		
		
		
		List<LugarModel> listaLugares = lugarService.listLugar();
		
		mv.addObject("permisoDiario", new PermisoDiario());
		mv.addObject("permisoPeriodo", new PermisoPeriodo());
		mv.addObject("persona", new Persona());
		mv.addObject("listaLugares", listaLugares);
		mv.addObject("rodado", new Rodado());

		
		
		
		return mv ;
	}
	
	@GetMapping("/diario")
	public ModelAndView agregarPermisoDiario(){
		
		
		ModelAndView mv = new ModelAndView("home/agregarPermisoDiario");
		
		List<LugarModel> listaLugares = lugarService.listLugar();
		
		mv.addObject("listaLugares", listaLugares);
		mv.addObject("permisoDiario", new PermisoDiario());
		mv.addObject("persona", new Persona());
		
		return mv;
		
	}
	
	@GetMapping("/periodo")
	public ModelAndView agregarPermisoPeriodo(){
		
		
		ModelAndView mv = new ModelAndView("home/agregarPermisoPeriodo");
		
		List<LugarModel> listaLugares = lugarService.listLugar();
		mv.addObject("listaLugares", listaLugares);
		mv.addObject("permisoPeriodo", new PermisoPeriodo());
		mv.addObject("rodado", new Rodado());
		mv.addObject("persona", new Persona());
		
		
		return mv;
		
	}
	
	
	
	@PostMapping("/agregarDiario")
	public RedirectView agregarPermisoDiario(@ModelAttribute("persona") PersonaModel personaModel, @ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel) {
		
		RedirectView redirect = new RedirectView(ViewRouteHelper.PERMISOS_AGREGAR, false);
		
		
		PersonaModel personaSeleccionada = personaService.findByDni(personaModel.getDni());

		
		permisoDiarioModel.setPedido(personaSeleccionada);
		
		
		
		permisoDiarioService.agregar(permisoDiarioModel);
		
		
		
		return redirect;
	}
	
	
	@PostMapping("/agregarPeriodo")
	public RedirectView agregarPermisoPeriodo(@ModelAttribute("persona") PersonaModel personaModel, @ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodoModel, @ModelAttribute("rodado") RodadoModel rodadoModel ) {
		
		RedirectView redirect = new RedirectView(ViewRouteHelper.PERMISOS_AGREGAR, false);
		
		Rodado rodadoSeleccionado = rodadoService.buscar(rodadoModel.getDominio());
	
		Persona personaSeleccionada = personaService.buscar(personaModel.getDni());
	
		
		permisoPeriodoModel.setRodado(rodadoConverter.entityToModel(rodadoSeleccionado));
		permisoPeriodoModel.setPedido(personaConverter.entityToModel(personaSeleccionada));
		
		
		permisoPeriodoService.agregar(permisoPeriodoModel);
		
		
		
		return redirect;
	}
	
	
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
	@GetMapping("ver")
	public ModelAndView mostrarPermisosActivos(Model fecha) {
		
		List<PermisoModel> listaDePermisos = permisoService.listPermisoModel();
		List<PermisoModel> listaDePermisosActivos = null;
		
		
		System.out.println(listaDePermisos.size()); 
		
		
		
		 
		
		ModelAndView mov = new ModelAndView(ViewRouteHelper.PERMISOS_INDEX);

		mov.addObject("listaDePermisos", listaDePermisos);	
		
		return mov;
	}
	
	
	
	
	
	
}