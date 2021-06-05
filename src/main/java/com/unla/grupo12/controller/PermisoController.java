package com.unla.grupo12.controller;

import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.service.IPermisoPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



	@PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
	@GetMapping("")
	public String index() {
		
		
		return ViewRouteHelper.PERMISOS_AGREGAR;
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
	@GetMapping("ver")
	public ModelAndView mostrarPermisosActivos() {
		
		ModelAndView mov = new ModelAndView(ViewRouteHelper.PERMISOS_INDEX);
		
		
		return mov;
	}
	
}