package com.unla.grupo12.controller;

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
  @Qualifier("rodadoService")
  private IRodadoService rodadoService;
  

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
	
	@GetMapping("/{tipoPermiso}")
	public RedirectView redireccionATipoDePermiso(@PathVariable("tipoPermiso") int tipoPermiso) {
		
		RedirectView redirect = new RedirectView();
		
		if(tipoPermiso == 1) {
			
			redirect.setUrl("permiso/diario");
		}else {
			redirect.setUrl("permiso/periodo");
		}
		
		return redirect;
	}
	
	@PostMapping("/agregar")
	public RedirectView agregarPermiso(@ModelAttribute("persona") PersonaModel personaModel, @ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel, @ModelAttribute("permisoPeriodo") PermisoPeriodo permisoPeriodoModel) {
		
		RedirectView redirect = new RedirectView(ViewRouteHelper.PERMISOS_AGREGAR, false);
		
		
		if(!permisoDiarioModel.getMotivo().isEmpty()) {
			PersonaModel personaSeleccionada = personaService.findByDni(personaModel.getDni());
			
			permisoDiarioModel.setPedido(personaSeleccionada);
			permisoDiarioService.agregar(permisoDiarioModel);
		}
		
		
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