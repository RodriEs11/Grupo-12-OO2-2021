package com.unla.grupo12.controller;

import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PerfilModel;
import com.unla.grupo12.service.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/perfiles")
public class PerfilController {

  @Autowired
  private IPerfilService perfilService;


  @PreAuthorize("hasAnyAuthority('Admin', 'Auditoria')")
  @GetMapping("/lista-perfiles")
  public ModelAndView listaPerfil() {
    ModelAndView mov = new ModelAndView(ViewRouteHelper.PERFILES);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    List<PerfilModel> list = perfilService.listPerfil();
    mov.addObject("listaPerfiles", list);
    return mov;
  }

  @PreAuthorize("hasAnyAuthority('Auditoria')")
  @GetMapping(value = "/perfiles-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<InputStreamResource> perfilReporte()
      throws IOException {
    ByteArrayInputStream pdf = perfilService.generacionPdf();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=ListaPerfiles.pdf");
    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(pdf));
  }


  @PreAuthorize("hasAuthority('Admin')")
  @GetMapping("editar/{id}")
  public ModelAndView editarPerfil(@PathVariable("id") Long id) {
    ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERFILES_EDITAR);

    PerfilModel perfilModel = new PerfilModel();
    if( id!=0){
      perfilModel = perfilService.findById(id);
    }else{
      perfilModel.setId(0L);

    }
    modelo.addObject("perfil", perfilModel);
    return modelo;
  }

  @PreAuthorize("hasAuthority('Admin')")
  @PostMapping("actualizar/{id}")
  public RedirectView actualizarPerfil(@PathVariable("id") Long id, @ModelAttribute("perfil") PerfilModel perfilModel) throws Exception {

    if ( id==0){
      perfilService.agregar(perfilModel);
    }else{
      perfilService.modificar(perfilModel);
    }
    RedirectView redirect = new RedirectView("/perfiles/lista-perfiles", false);
    return redirect;

  }

  @PreAuthorize("hasAuthority('Admin')")
  @GetMapping("eliminar/{id}")
  public RedirectView eliminarPerfil(@PathVariable("id") Long id) throws Exception {
    perfilService.darDeBaja(id);
    RedirectView redirect = new RedirectView("/perfiles/lista-perfiles", false);
    return redirect;

  }


}