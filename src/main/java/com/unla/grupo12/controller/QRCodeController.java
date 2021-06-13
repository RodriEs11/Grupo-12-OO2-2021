package com.unla.grupo12.controller;



import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.service.IPermisoService;
import com.unla.grupo12.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.unla.grupo12.service.QRCodeGenerator;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/qr")
public class QRCodeController {

  @Autowired
  @Qualifier("personaService")
  private IPersonaService personaService;
  
  @Autowired
  @Qualifier("permisoService")
  private IPermisoService permisoService;

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
    
    List<Permiso> listaPermisos = permisoService.traerPermisoxDni(persona.getDni());
    
    //SOLO MUESTRA UN PERMISO
    Permiso permiso1 = listaPermisos.get(0);
    
    Set<Lugar> listaLugares =  permiso1.getDesdeHasta(); 
    
    String desde = "";
    String hasta = "";
    
    for(Lugar lugar: listaLugares) {
    	
    	desde = lugar.getLugar();
    	hasta = lugar.getLugar();
    }
    
    
    
    
    String url = "https://rodries11.github.io/grupo-12-OO2-2021/";

    String tipoPermiso = "";
    String motivo = "";
    int cantidadDeDias = 0;
    String vacaciones = "0";
    String dominio = "";
    String vehiculo = "";
    
    if(permiso1 instanceof PermisoDiario) {
    	tipoPermiso = "1";
    	motivo = ((PermisoDiario) permiso1).getMotivo();
    	
    	url = url + "?nombre=" + nombre + "&apellido=" + apellido + "&dni=" + dni + "&tipoPermiso=" + tipoPermiso + "&motivo=" + motivo + "&desde=" +desde + "&hasta=" + hasta;
    }
    
    if(permiso1 instanceof PermisoPeriodo){
    	tipoPermiso = "2";
    	cantidadDeDias = ((PermisoPeriodo) permiso1).getCantDias();
    	
    	if(((PermisoPeriodo) permiso1).isVacaciones()){
    		vacaciones = "1";
    	}else {
    		vacaciones = "0";
    	}
    	
    	
    	
    	dominio = ((PermisoPeriodo) permiso1).getRodado().getDominio();
    	vehiculo = ((PermisoPeriodo) permiso1).getRodado().getVehiculo();
    	
    	url = url + "?nombre=" + nombre + "&apellido=" + apellido + "&dni=" + dni + "&tipoPermiso=" + tipoPermiso + "&desde=" + desde + "&hasta=" + hasta + "&dias=" + cantidadDeDias + "&vacaciones=" + vacaciones + "&dominio=" + dominio + "&vehiculo=" + vehiculo;
    	
    }


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
