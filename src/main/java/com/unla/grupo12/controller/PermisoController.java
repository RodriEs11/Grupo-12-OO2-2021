package com.unla.grupo12.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12.auxiliar.Fecha;
import com.unla.grupo12.converter.LugarConverter;
import com.unla.grupo12.converter.PermisoConverter;
import com.unla.grupo12.converter.PermisoDiarioConverter;
import com.unla.grupo12.converter.PermisoPeriodoConverter;
import com.unla.grupo12.converter.PersonaConverter;
import com.unla.grupo12.converter.RodadoConverter;
import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.LugarModel;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.service.ILugarService;
import com.unla.grupo12.service.IPermisoDiarioService;
import com.unla.grupo12.service.IPermisoPeriodoService;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IPersonaService;
import com.unla.grupo12.service.IRodadoService;
import com.unla.grupo12.service.IUsuarioService;
import com.unla.grupo12.service.impl.PermisoServiceImpl;


@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoDiarioService")
	private IPermisoDiarioService permisoDiarioService;
	
	@Autowired
	@Qualifier("permisoPeriodoService")
	private IPermisoPeriodoService permisoPeriodoService;
	
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
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
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;

	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	
	@Autowired  
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
	
	@Autowired  
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;
	
	private LocalDate fecha;
	
	//*********************************fechas******************************
	@GetMapping("/pedirfecha")
	public ModelAndView pedirFecha() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PEDIR_FECHA);			
		
		modelo.addObject("fecha", new Fecha());
				
		return modelo;
	}
	
	@PostMapping("/agregarfecha")
	public RedirectView agregarPermisoDiario(@ModelAttribute("fecha") Fecha fecha)  {
		RedirectView redirect = new RedirectView("/permiso/tipopermiso", false);
		
		
		this.fecha = fecha.fechaToLocalDate(fecha.getTipoDate());
		
		return redirect;			
	}

	//*****************************************PERMISO**************************
	
	@GetMapping("/tipopermiso")
	public ModelAndView tipoPermiso() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.TIPO_PERMISO);
		
		String tipo="";
		modelo.addObject("tipo", tipo);
		
		return modelo;
	}
	
	
	@GetMapping("/pedidodiario")
	public ModelAndView formPermisoDiario() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_AGREGAR);			
		
		List<Persona> personas = personaService.getAll();		
		List<Lugar> lugares = lugarService.getAll();
		
		modelo.addObject("personas", personas);		
		modelo.addObject("lugares", lugares);			
		
		
		PermisoDiario permiso =  new PermisoDiario();
		List<Lugar> listLugares = new ArrayList<Lugar>();
		listLugares.add(new Lugar());
		listLugares.add(new Lugar());

		permiso.setDesdeHasta(listLugares);
		
		modelo.addObject("permiso",permiso);
		
		return modelo;
	}
	
	
	
	@GetMapping("/pedidoperiodo")
	public ModelAndView formPermisoPeriodo() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_AGREGAR);
		
		List<Persona> personas = personaService.getAll();
		List<Rodado> rodados = rodadoService.getAll();
		List<Lugar> lugares = lugarService.getAll();
		
		modelo.addObject("personas", personas);
		modelo.addObject("rodados", rodados);
		modelo.addObject("lugares", lugares);
		modelo.addObject("permiso", new PermisoPeriodo());
		
		return modelo;
	}
	

	@PostMapping("/agregarpermisodiario")
	public RedirectView agregarPermisoDiario(@ModelAttribute("permiso") PermisoDiarioModel permisoDiarioModel) {
		RedirectView redirect = new RedirectView("/lista-permisos", false);
		
		permisoDiarioModel.setFecha(this.fecha);
		this.fecha = null;
			
		int idPersona= permisoDiarioModel.getPedido().getIdPersona();	
		permisoDiarioModel.setPedido(personaConverter.modelToEntity(personaService.findByIdPersona(idPersona)));
		
		List<Lugar> listLugar = permisoDiarioModel.getDesdeHasta();
		List<Lugar> nuevaListLugar= new ArrayList<Lugar>();
		for (Lugar l : listLugar)
			nuevaListLugar.add(lugarConverter.modelToEntity(lugarService.findByIdLugar(l.getIdLugar())));
		
		permisoDiarioModel.setDesdeHasta(nuevaListLugar);
		permisoDiarioService.agregar(permisoDiarioModel );
		
		return redirect;			
	}
	

	@PostMapping("/agregarpermisoPeriodo")
	public RedirectView agregarPermisoPeriodo(@ModelAttribute("permiso") PermisoPeriodoModel permisoPeriodoModel) {
		RedirectView redirect = new RedirectView("/lista-permisos", false);
		
		permisoPeriodoModel.setFecha(this.fecha);
		this.fecha = null;
		
		
		int idPersona = permisoPeriodoModel.getPedido().getIdPersona();		
		permisoPeriodoModel.setPedido(personaConverter.modelToEntity(personaService.findByIdPersona(idPersona)));
		
		int idRodado = permisoPeriodoModel.getRodado().getIdRodado();
		permisoPeriodoModel.setRodado(rodadoConverter.modelToEntity(rodadoService.findByIdRodado(idRodado)));
		
		List<Lugar> listLugar = permisoPeriodoModel.getDesdeHasta();
		List<Lugar> nuevaListLugar= new ArrayList<Lugar>();
		for (Lugar l : listLugar)
			nuevaListLugar.add(lugarConverter.modelToEntity(lugarService.findByIdLugar(l.getIdLugar())));
		
		permisoPeriodoModel.setDesdeHasta(nuevaListLugar);
		permisoPeriodoService.agregar(permisoPeriodoModel );
		
		return redirect;			
	}
	
	
	//*************************************TRAER PERMISO POR FECHAS*****************************************
	//NO ANDA, NO TRAE LAS FECHAS
	
	@PreAuthorize("hasAnyAuthority('Auditoria')")
	@GetMapping("/pedirFechasPermiso")
	public ModelAndView perdirFechasPermido() {
		ModelAndView model = new ModelAndView(ViewRouteHelper.PEDIR_FECHAS_PERMISO);
		List<Fecha> fechas = new ArrayList<Fecha>();
		Fecha fecha = new Fecha();
			
		model.addObject("fecha", fecha);
		
		
		return model;
	}
	
	@PreAuthorize("hasAnyAuthority('Auditoria')")
	@PostMapping("/buscarPermisosxFechas")
	public ModelAndView buscarPermisoxFechas(@ModelAttribute("fechas")Fecha fecha) {
		RedirectView redirect = new RedirectView(ViewRouteHelper.PERMISO_X_FECHAS, false);			
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERMISO_X_FECHAS);
		LocalDate fechaInicial =fecha.fechaToLocalDate(fecha.getFechaInicial());
		LocalDate fechaFinal = fecha.fechaToLocalDate(fecha.getFechaFinal());
		List<Permiso> permisos = permisoService.findByFecha(fechaInicial,fechaFinal);
		
		modelo.addObject("permisos", permisos);
		
		return modelo;
	}
}
