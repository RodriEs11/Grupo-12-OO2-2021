package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("usuarioConverter")
public class UsuarioConverter {

	 public List<UsuarioModel> listUsuario(List<Usuario> listUsuario) {

		    List<UsuarioModel> usuarioModelList = new ArrayList<UsuarioModel>();

		    for (Usuario u : listUsuario) {
		      UsuarioModel usuarioModel = new UsuarioModel();
		      usuarioModel.setId(u.getId());
		      usuarioModel.setNombre(u.getNombre());
		      usuarioModel.setApellido(u.getApellido());
		      usuarioModel.setEmail(u.getEmail());
		      usuarioModel.setNroDocumento(u.getNroDocumento());
		      usuarioModel.setTipoDocumento(u.getTipoDocumento());
		      usuarioModel.setUsuario(u.getUsuario());
		      usuarioModel.setClave(u.getClave());
		      usuarioModel.setPerfil(u.getPerfil());
		      usuarioModelList.add(usuarioModel);
		    }
		    return usuarioModelList;
		  }

		  public List<Usuario> listUsuarioModel(List<UsuarioModel> listUsuarioModels) {

		    List<Usuario> usuarioList = new ArrayList<Usuario>();

		    for (UsuarioModel u : listUsuarioModels) {
		      Usuario usuario = new Usuario();
		      usuario.setId(u.getId());
		      usuario.setNombre(u.getNombre());
		      usuario.setApellido(u.getApellido());
		      usuario.setEmail(u.getEmail());
		      usuario.setNroDocumento(u.getNroDocumento());
		      usuario.setTipoDocumento(u.getTipoDocumento());
		      usuario.setUsuario(u.getUsuario());
		      usuario.setClave(u.getClave());
		      usuario.setPerfil(u.getPerfil());
		      usuarioList.add(usuario);
		    }
		    return usuarioList;
		  }
	
	
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		
		return new Usuario(usuarioModel.getId(), usuarioModel.getNombre(), usuarioModel.getApellido(), usuarioModel.getNroDocumento(), 
				usuarioModel.getTipoDocumento(), usuarioModel.getEmail(), usuarioModel.getUsuario(), usuarioModel.getClave(),usuarioModel.isActivo(), usuarioModel.getPerfil());
	}

	public UsuarioModel entityToModel(Usuario usuario) {
		
		
		return new UsuarioModel(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getNroDocumento(), 
				usuario.getTipoDocumento(), usuario.getEmail(), usuario.getUsuario(), usuario.getClave(), usuario.isActivo(), usuario.getPerfil());
	}

}
