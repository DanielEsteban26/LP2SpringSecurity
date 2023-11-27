package com.db.farmacia.util;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.db.farmacia.model.Producto;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("productos/show")
public class ListarProductos extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		 * List<Producto> productos= productoservice.findAll();
		 * model.addAttribute("productos", productos); return "administrador/home";
		 */

		List<Producto> productos = (List<Producto>) model.get("productos");

		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, Color.WHITE);
		Font fuentetituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.white);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		PdfPCell celda = null;

		/* tabla para el titulo del PDF */
		PdfPTable tablaTitulo = new PdfPTable(1);

		celda = new PdfPCell(new Phrase("LISTADO DE LOS PRODUCTOS", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(0, 0, 0));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(15);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		/* tabla para mostrar listado productos */
		PdfPTable tablaProductos = new PdfPTable(4);
		tablaProductos.setWidths(new float[] { 2f, 3f, 2f, 2f });

		celda = new PdfPCell(new Phrase("NOMBRE", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);

		celda = new PdfPCell(new Phrase("DESCRIPCION", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);

		celda = new PdfPCell(new Phrase("INVENTARIO", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);

		celda = new PdfPCell(new Phrase("PRECIO", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);

		for (Producto producto : productos) {
			celda = new PdfPCell(new Phrase(producto.getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);

			celda = new PdfPCell(new Phrase(producto.getDescripcion(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);

			celda = new PdfPCell(new Phrase(String.valueOf(producto.getCantidad()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);

			celda = new PdfPCell(new Phrase(String.valueOf(producto.getPrecio()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);

		}

		/*
		 * productos.forEach(detalle->{ tablaProductos.addCell(detalle.getNombre());
		 * tablaProductos.addCell(detalle.getDescripcion());
		 * tablaProductos.addCell(String.valueOf(detalle.getCantidad()));
		 * tablaProductos.addCell(String.valueOf(detalle.getPrecio())); });
		 */
		document.add(tablaTitulo);
		document.add(tablaProductos);

	}

}
