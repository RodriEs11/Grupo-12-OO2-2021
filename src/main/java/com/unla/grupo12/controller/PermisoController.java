package com.unla.grupo12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.service.IPermisoPeriodoService;
import com.unla.grupo12.service.IPermisoService;


@Controller
@RequestMapping("/permiso")
public class PermisoController {

	
	
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


