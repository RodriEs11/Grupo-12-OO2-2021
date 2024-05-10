package com.unla.grupo12.configuration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unla.grupo12.model.PerfilModel;
import com.unla.grupo12.service.impl.PerfilServiceImpl;

@Component
public class DatabaseConfiguration implements CommandLineRunner{

	@Autowired
	@Qualifier("perfilService")
	private PerfilServiceImpl perfilService;

	// Al iniciar la aplicacion, chequea si existen los perfiles en la DB, sino los
	// agrega
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		String[] requiredRoles = { "Admin", "Usuario", "Auditoria" };

		for (String roleName : requiredRoles) {

			if (perfilService.findByNombre(roleName) == null) {
				PerfilModel nuevoPerfil = new PerfilModel();
				nuevoPerfil.setNombre(roleName);
				perfilService.agregar(nuevoPerfil);
			}
		}
	}

}
