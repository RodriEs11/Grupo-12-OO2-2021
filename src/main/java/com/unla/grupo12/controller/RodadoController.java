package com.unla.grupo12.controller;


import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.service.IRodadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo12.helpers.ViewRouteHelper;


@Controller
@RequestMapping("/rodado")
public class RodadoController {

  @Autowired
  private IRodadoService rodadoService;

  @GetMapping("")
  public ModelAndView registrarRodadoNuevo(@RequestParam(name = "resultado", required = false) String resultado) {

    ModelAndView mov = new ModelAndView(ViewRouteHelper.RODADO);

    mov.addObject("rodadonuevo", new Rodado());
    mov.addObject("resultado", resultado);
    return mov;
  }


  @PostMapping("/agregar")
  public RedirectView agregarRodado(@ModelAttribute("rodado") RodadoModel rodadoModel, RedirectAttributes atribute) {
    RedirectView rv = new RedirectView("/rodado?resultado", true);
    Rodado rodado = rodadoService.buscar(rodadoModel.getDominio());
    if (rodado != null) {
      atribute.addFlashAttribute("mensajeRodado", "El Rodado que intenta ingresar ya se encuentra registrado");
    }else {
      rodadoService.agregarRodado(rodadoModel);
    }
    return rv;
  }

  @PreAuthorize("hasAnyAuthority('Auditoria')")
  @GetMapping("/buscar")
  public String index(Model model, @ModelAttribute("dominio") PermisoPeriodo dominio, @ModelAttribute("mensaje") String mensaje) {
    model.addAttribute("resultado", null);

    model.addAttribute("mensaje", mensaje);
    if (dominio == null || dominio.getIdPermiso() == 0) {
      model.addAttribute("dominio", null);

    } else {
      model.addAttribute("dominio", dominio);

    }

    return ViewRouteHelper.BUSCAR_RODADO;
  }



	
}

