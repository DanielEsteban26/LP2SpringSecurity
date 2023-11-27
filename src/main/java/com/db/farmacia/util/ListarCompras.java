package com.db.farmacia.util;


import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.db.farmacia.model.DetalleOrden;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("usuario/detallecompra")
public class ListarCompras extends AbstractPdfView{

	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		List<DetalleOrden> detalleOrden= (List<DetalleOrden>) model.get("detalles");
		
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, Color.WHITE);
		Font fuentetituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.white);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		PdfPCell celda = null;

		/* tabla para el titulo del PDF */
		PdfPTable tablaTitulo = new PdfPTable(1);

		celda = new PdfPCell(new Phrase("LISTADO DE LAS COMPRAS", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(0, 0, 0));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(15);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		/* tabla para mostrar listado productos */
		PdfPTable tablaCompras = new PdfPTable(4);
		tablaCompras.setWidths(new float[] { 2f, 3f, 2f, 2f });

		celda = new PdfPCell(new Phrase("NOMBRE", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaCompras.addCell(celda);

		celda = new PdfPCell(new Phrase("DESCRIPCION", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaCompras.addCell(celda);

		celda = new PdfPCell(new Phrase("INVENTARIO", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaCompras.addCell(celda);

		celda = new PdfPCell(new Phrase("PRECIO", fuentetituloColumnas));
		celda.setBackgroundColor(Color.RED);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaCompras.addCell(celda);

		for ( DetalleOrden detalles : detalleOrden) {
			celda = new PdfPCell(new Phrase(String.valueOf(detalles.getCantidad()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCompras.addCell(celda);

			celda = new PdfPCell(new Phrase(String.valueOf(detalles.getProducto().getDescripcion()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCompras.addCell(celda);

			celda = new PdfPCell(new Phrase(String.valueOf(detalles.getPrecio()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCompras.addCell(celda);

			celda = new PdfPCell(new Phrase(String.valueOf(detalles.getTotal()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCompras.addCell(celda);

		}

		/*
		 * productos.forEach(detalle->{ tablaProductos.addCell(detalle.getNombre());
		 * tablaProductos.addCell(detalle.getDescripcion());
		 * tablaProductos.addCell(String.valueOf(detalle.getCantidad()));
		 * tablaProductos.addCell(String.valueOf(detalle.getPrecio())); });
		 */
		document.add(tablaTitulo);
		document.add(tablaCompras);
		
	}
	


	/*
	 * productos.forEach(detalle->{ tablaProductos.addCell(detalle.getNombre());
	 * tablaProductos.addCell(detalle.getDescripcion());
	 * tablaProductos.addCell(String.valueOf(detalle.getCantidad()));
	 * tablaProductos.addCell(String.valueOf(detalle.getPrecio())); });
	 */
	


}
