package com.unla.grupo12.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.unla.grupo12.converter.UsuarioConverter;
import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.model.UsuarioModel;
import com.unla.grupo12.repository.IUsuarioRepository;
import com.unla.grupo12.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service("usuarioService")
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;

	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;

	@Override
	public UsuarioModel agregarOActualizar(UsuarioModel usuarioModel) {

		Usuario usuario = usuarioRepository.save(usuarioConverter.modelToEntity(usuarioModel));

		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public List<Usuario> getAll() {

		return usuarioRepository.findAll();

	}

	@Override
	public UsuarioModel findById(Long id) {

		Usuario usuario = usuarioRepository.findById(id);

		return usuarioConverter.entityToModel(usuario);

	}

	@Override
	public boolean darDeBaja(Long id) {

		usuarioRepository.findById(id).setActivo(false);
		usuarioRepository.save(usuarioRepository.findById(id));

		return true;
	}

	/*
	 * 
	 * METODOS PARA EL INICIO DE SESION
	 * 
	 */

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByNombreUsuarioYPerfil(nombreUsuario);
		
		Set<Perfil> perfilesDeUsuario = new HashSet<>();
		
		perfilesDeUsuario.add(usuario.getPerfil());
		
		return buildUser(usuario, buildGrantedAuthorities(perfilesDeUsuario));
	}

	private User buildUser(Usuario usuario, List<GrantedAuthority> grantedAuthorities) {

		return new User(usuario.getUsuario(), usuario.getClave(), usuario.isActivo(), true, true, true, // accountNonExpired,
																										// credentialsNonExpired,
																										// accountNonLocked
				grantedAuthorities);

	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<Perfil> perfiles) {

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		for (Perfil p : perfiles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(p.getNombre()));
		}

		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}

	private static Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	public List<UsuarioModel> listUsuarios() {
		List<UsuarioModel> usuarioList = new ArrayList<UsuarioModel>();
		usuarioList = usuarioConverter.listUsuario(usuarioRepository.findAll());
		return usuarioList;
	}

	@Override
	public ByteArrayInputStream generacionPdf() {
		List<UsuarioModel> listaUsuario = this.listUsuarios();
		Document document = new Document(PageSize.A3.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph(" Lista de Usuarios", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(9);
			Stream.of("Id", "Nombre", "Apellido", "Documento", " Tipo Doc", "E-mail", "Usuario ", "Perfil")
					.forEach(headerTitle -> {
						PdfPCell header = new PdfPCell();
						Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						header.setBackgroundColor(BaseColor.LIGHT_GRAY);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.setBorderWidth(1);
						if (headerTitle.equalsIgnoreCase("E-mail")) {
							header.setColspan(2);
						}
						header.setPhrase(new Phrase(headerTitle, headFont));
						table.addCell(header);
					});

			for (UsuarioModel usuarioModel : listaUsuario) {
				PdfPCell idCell = new PdfPCell(new Phrase(usuarioModel.getId().toString()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell nombreCell = new PdfPCell(new Phrase(usuarioModel.getNombre()));
				nombreCell.setPaddingLeft(4);
				nombreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nombreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(nombreCell);

				PdfPCell apellidoCell = new PdfPCell(new Phrase(usuarioModel.getApellido()));
				apellidoCell.setPaddingLeft(4);
				apellidoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				apellidoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(apellidoCell);

				PdfPCell nDocCell = new PdfPCell(new Phrase(usuarioModel.getNroDocumento().toString()));
				nDocCell.setPaddingLeft(4);
				nDocCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nDocCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(nDocCell);

				PdfPCell tipoCell = new PdfPCell(new Phrase(usuarioModel.getTipoDocumento()));
				tipoCell.setPaddingLeft(4);
				tipoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tipoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tipoCell);

				PdfPCell emailCell = new PdfPCell(new Phrase(usuarioModel.getEmail()));
				emailCell.setPaddingLeft(4);
				emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				emailCell.setColspan(2);
				table.addCell(emailCell);

				PdfPCell usuarioCell = new PdfPCell(new Phrase(usuarioModel.getUsuario()));
				usuarioCell.setPaddingLeft(4);
				usuarioCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				usuarioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(usuarioCell);

				PdfPCell perfilCell = new PdfPCell(new Phrase(usuarioModel.getPerfil().getNombre()));
				perfilCell.setPaddingLeft(4);
				perfilCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				perfilCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(perfilCell);
			}
			document.add(table);
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	@Override
	public boolean logoutUsuario() {
		SecurityContextHolder.getContext().setAuthentication(null);
		
		return true;
	}

	

}
