package com.unla.grupo12.controller;

import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.service.IRodadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rodado")
public class RodadoController {

  @Autowired
  private IRodadoService rodadoService;

  @GetMapping("")
  public ModelAndView registrarRodadoNuevo(@RequestParam(name="resultado", required = false) String resultado) {

    ModelAndView mov = new ModelAndView(ViewRouteHelper.RODADO);

    mov.addObject("rodadonuevo", new Rodado());
    mov.addObject("resultado", resultado);
    return mov;
  }

  @PostMapping("/agregar")
  public String agregarRodado(@ModelAttribute("rodado") RodadoModel rodadoModel, Model model) {
    rodadoService.agregarRodado(rodadoModel);

    return "redirect:/rodado?resultado";

  }

}