package com.unla.grupo12.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.unla.grupo12.converter.PerfilConverter;
import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.model.PerfilModel;
import com.unla.grupo12.repository.IPerfilRepository;
import com.unla.grupo12.service.IPerfilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service("perfilService")
public class PerfilServiceImpl implements IPerfilService {

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;

	@Autowired
	@Qualifier("perfilConverter")
	private PerfilConverter perfilConverter;

	@Override
	public List<Perfil> getAll() {

		return perfilRepository.findAll();

	}

	@Override
	public PerfilModel modificar(PerfilModel perfilModel) throws Exception{
		PerfilModel modelo = this.findById(perfilModel.getId());
		if(modelo == null)throw new Exception("Perfil no encontrado");
		Perfil perfil = new Perfil(modelo.getId(), perfilModel.getNombre());
		return perfilConverter.entityToModel(perfilRepository.save(perfil));
	}

	@Override
	public PerfilModel findById(Long id) {
		Optional<Perfil> resultado = perfilRepository.findById(id);
		if(resultado.isPresent()){
			return perfilConverter.entityToModel(resultado.get());
		}
		return null;
	}
	
	
	@Override
	public PerfilModel findByNombre(String name) {
		Perfil resultado = perfilRepository.findByNombre(name);
		
		return perfilConverter.entityToModel(resultado);
	}


	@Override
	public boolean darDeBaja(Long id) throws Exception {
		PerfilModel modelo = this.findById(id);
		if(modelo == null)throw new Exception("Perfil no encontrado");

		perfilRepository.delete(perfilConverter.modelToEntity(modelo));
		return true;
	}

	// IMPLEMENTACION DEL SERVICIO PARA AGREGAR UN PerfilModel A LA BD

	@Override
	public PerfilModel agregar(PerfilModel perfilModel) {

		Perfil p = perfilRepository.save(perfilConverter.modelToEntity(perfilModel));

		return perfilConverter.entityToModel(p);
	}

	private static Logger logger = LoggerFactory.getLogger(PerfilServiceImpl.class);

	public List<PerfilModel> listPerfil() {
    List<PerfilModel> perfilList = new ArrayList<PerfilModel>();
    perfilList = perfilConverter.listPerfil(perfilRepository.findAll());
    return perfilList;

  }

	public ByteArrayInputStream generacionPdf() {
		List<PerfilModel> listaPerfil = this.listPerfil();
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();

			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph(" Lista de Perfiles", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(2);

			Stream.of("Id", "Nombre").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(1);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (PerfilModel perfilModel : listaPerfil) {
				PdfPCell idCell = new PdfPCell(new Phrase(perfilModel.getId().toString()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell nombreCell = new PdfPCell(new Phrase(perfilModel.getNombre()));
				nombreCell.setPaddingLeft(4);
				nombreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nombreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(nombreCell);

			}
			document.add(table);
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	

	
}
