package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.model.PermisoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("permisoConverter")
public class PermisoConverter {

  @Autowired
  private PersonaConverter personaConverter;

  @Autowired
  private LugarConverter lugarConverter;


  public List<PermisoModel> listPermisoModel(List<Permiso> listPermiso) {

    List<PermisoModel> permisoModelList = new ArrayList<PermisoModel>();
    for (Permiso p : listPermiso) {
      PermisoModel permisoModel = new PermisoModel();
      permisoModel.setIdPermiso(p.getIdPermiso());
      permisoModel.setFecha(p.getFecha());
      permisoModel.setPedido(p.getPedido());
      permisoModel.setDesdeHasta(p.getDesdeHasta());
      permisoModelList.add(permisoModel);
    }
    return permisoModelList;
  }

  public List<Permiso> listPermiso(List<PermisoModel> listPermiso) {

    List<Permiso> permisoList = new ArrayList<Permiso>();
    for (PermisoModel p : listPermiso) {
      Permiso permiso = new Permiso();
      permiso.setIdPermiso(p.getIdPermiso());
      permiso.setFecha(p.getFecha());
      permiso.setPedido(p.getPedido());
      permiso.setDesdeHasta(p.getDesdeHasta());
      permisoList.add(permiso);
    }
    return permisoList;
  }

  public Permiso modelToEntity(PermisoModel permisoModel) {

    return new Permiso(permisoModel.getIdPermiso(), permisoModel.getPedido(),
        permisoModel.getFecha(), permisoModel.getDesdeHasta());
  }

  public PermisoModel entityToModel(Permiso permiso) {

    return new PermisoModel(permiso.getIdPermiso(), permiso.getPedido()
    , permiso.getFecha(), permiso.getDesdeHasta());
  }
}
