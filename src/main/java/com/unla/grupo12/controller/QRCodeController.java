package com.unla.grupo12.controller;



import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.unla.grupo12.service.impl.QRCodeGenerator;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalTime;

@Controller
//@RequestMapping("/qr")
public class QRCodeController {

  @Autowired
  @Qualifier("personaService")
  private IPersonaService personaService;

  private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/images/QRCode";

  private static final String EXTENCION = ".png";


  //@GetMapping(value = "/generateAndDownloadQRCode/{id}")
  //(@ModelAttribute("permiso") PermisoDiarioModel permisoDiarioModel) {
  @PostMapping("/generateAndDownloadQRCode")
  public RedirectView download2(@ModelAttribute("persona") PersonaModel p, RedirectAttributes atribute) throws Exception{
    RedirectView redirect = new RedirectView("/verqr");

    String url = this.generarUrlPersona(p.getDni());
    int width = 350;
    int height = 350;
    LocalTime hora= LocalTime.now();
    String nameQr = "QRCode" + +hora.getHour()+hora.getMinute() + EXTENCION;
    String imageQr = QR_CODE_IMAGE_PATH +hora.getHour()+hora.getMinute() + EXTENCION;
    QRCodeGenerator.generateQRCodeImage(url, width, height, imageQr);
    atribute.addAttribute("qr", nameQr);
    return redirect;
  }


  //	@GetMapping(value = "/generateAndDownloadQRCode/{codeText}/{width}/{height}")
//	public void download(@PathVariable("codeText") String codeText,
//						 @PathVariable("width") Integer width,
//						 @PathVariable("height") Integer height) throws Exception{
//		QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
//	}
//
  @GetMapping(value = "/generateQRCode/{codeText}/{width}/{height}")
  public ResponseEntity<byte[]> generateQRCode(@PathVariable("codeText") String codeText,
                                               @PathVariable("width") Integer width,
                                               @PathVariable("height")Integer height) throws  Exception{
    return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
  }

  public String generarUrlPersona( long dni) {

    PersonaModel persona = personaService.findByDni(dni);

    String nombre = persona.getNombre();
    String apellido = persona.getApellido();
    //long dni = persona.getDni();

    String url = "https://rodries11.github.io/grupo-12-OO2-2021/" + "?nombre=" + nombre + "&apellido=" + apellido + "&dni=" + dni ;

    return url;

  }

  //**********
  @GetMapping("/pedirDniQR")
  public ModelAndView pedirDniQr() {
    ModelAndView model = new ModelAndView(ViewRouteHelper.PEDIR_DNI_QR);

    model.addObject("persona", new Persona());
    return model;
  }



}
