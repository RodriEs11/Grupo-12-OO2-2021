package com.unla.grupo12.controller;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.service.IRodadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/rodado")
public class RodadoController {

  @Autowired
  private IRodadoService rodadoService;

  @GetMapping("")
  public ModelAndView registrarRodadoNuevo() {

    ModelAndView mov = new ModelAndView(ViewRouteHelper.RODADO);

    List<Rodado> rodados = rodadoService.getAll();
    mov.addObject("rodados", rodados);
    mov.addObject("rodadonuevo", new Rodado());

    return mov;
  }

  @PostMapping("/agregar")
  public ModelAndView agregarRodado(@ModelAttribute("rodado") RodadoModel rodadoModel) {
    RedirectView redirect = new RedirectView("rodado");
    ModelAndView modelo = new ModelAndView(ViewRouteHelper.RODADO);
    rodadoService.agregarRodado(rodadoModel);
    modelo.addObject("resultado", "Agregado exitosamente");
    return modelo;
  }

}