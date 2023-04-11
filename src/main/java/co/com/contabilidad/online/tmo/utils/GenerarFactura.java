package co.com.contabilidad.online.tmo.utils;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.FacturaDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;

import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerarFactura {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(GenerarFactura.class);
	private static final String FACTURA_VENTA = "FACTURA VENTA";
	private static final String NO_RESPONSABLE_IVA = "NO RESPONSABLE DE IVA";
	private static final String NO_FACTURA = "NO. ";
	private static final String FECHA = "FECHA: ";
	private static final String NOMBRE_CLIENTE = "NOMBRE CLIENTE: ";
	private static final String FORMATO_FECHA = "yyyy-MM-dd hh:mm:ss";
	private static final String DESCRIPCION = "DESCRIPCION ";
	private static final String CANTIDAD = "CANTIDAD ";
	private static final String UNIDAD = "UNIDAD ";
	private static final String VALOR = "VALOR ";
	private static final String TOTAL = "TOTAL ";
	private static final String AUTOR = "dchavarro";
	
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLDITALIC);
	private static final Font paragraphFontDesc = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLDITALIC);
	private static final String pattern = "###,###.###";

	public FacturaDTO crearFactura(final List<VentaDTO> lstVentas, final EmpresaDTO empresa , final List<DireccionDTO> lstDirecciones, final List<TelefonoDTO> lstTelefonos, final String numeroFactura, final String nombreCliente, final Double paganCon) {

		FacturaDTO factura = new FacturaDTO();
		ByteArrayOutputStream bos = null;
		
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		try {
			Rectangle pageSize = new Rectangle(350f, 600f);
			Document document = new Document(pageSize);
			bos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document,bos);

			document.open();
			document.addTitle(FACTURA_VENTA);
			document.addAuthor(AUTOR);
			//Chunk chunk = new Chunk(empresa.getRazonSocial(), chapterFont);
			//chunk.setBackground(BaseColor.GRAY);
			Paragraph para = new Paragraph();
			// add text
			para.setLeading(0, 1);
			para.setAlignment(Element.ALIGN_CENTER);
			Chapter chapter = new Chapter(para, 0);
			chapter.setNumberDepth(0);
			chapter.add(new Paragraph(empresa.getRazonSocial(), paragraphFont));
			chapter.add(new Paragraph(empresa.getNit() + ' ' + NO_RESPONSABLE_IVA, paragraphFont));
			chapter.add(new Paragraph(lstDirecciones.get(0).getDescripcion(), paragraphFont));
			chapter.add(new Paragraph(Utils.obtenerTelefonos(lstTelefonos), paragraphFont));
			chapter.add(new Paragraph(lstDirecciones.get(0).getDepartamento().getDescripcion() + "-"
					+ lstDirecciones.get(0).getMunicipio().getDescripcion(), paragraphFont));
			chapter.add(new Paragraph(FACTURA_VENTA, paragraphFont));
			chapter.add(new Paragraph(NO_FACTURA + numeroFactura, paragraphFont));
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
			String strDate = dateFormat.format(date);
			chapter.add(new Paragraph(FECHA + strDate, paragraphFont));
			chapter.add(new Paragraph(NOMBRE_CLIENTE + nombreCliente, paragraphFont));
			chapter.add(new Paragraph("\n"));
			// table with 2 columns:
			PdfPTable table = new PdfPTable(5);
			float[] anchos = {30f,20f, 20f, 20f, 20f};
			Rectangle pageSizeTbl = new Rectangle(100f, 100f);
			table.setWidthPercentage(anchos, pageSizeTbl);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			// header row:
			PdfPCell cellDesc = new PdfPCell(new Phrase(DESCRIPCION, paragraphFont));
			cellDesc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellDesc);
			
			PdfPCell cellCant = new PdfPCell(new Phrase(CANTIDAD, paragraphFont));
			cellCant.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellCant);
			
			PdfPCell cellUnid = new PdfPCell(new Phrase(UNIDAD, paragraphFont));
			cellUnid.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellUnid);
			
			PdfPCell cellVal = new PdfPCell(new Phrase(VALOR, paragraphFont));
			cellVal.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellVal);
			
			PdfPCell cellTotal = new PdfPCell(new Phrase(TOTAL, paragraphFont));
			cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellTotal);

			System.out.println("lstVentas.size() " + lstVentas.size());
			for (int i = 0; i < lstVentas.size(); i++) {
				PdfPCell cellDescV = new PdfPCell(new Phrase(lstVentas.get(i).getProducto().getDescripcion(), paragraphFontDesc));
				cellDescV.setBorder(Rectangle.NO_BORDER);
				cellDescV.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellDescV);
				
				PdfPCell cellCantV = new PdfPCell(new Phrase(String.valueOf(lstVentas.get(i).getCantidad()), paragraphFont));
				cellCantV.setBorder(Rectangle.NO_BORDER);
				cellCantV.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellCantV);
				
				PdfPCell cellUnidV = new PdfPCell(new Phrase(String.valueOf(lstVentas.get(i).getProducto().getTipoUnidad().getDescripcion().substring(0, 3)), paragraphFont));
				cellUnidV.setBorder(Rectangle.NO_BORDER);
				cellUnidV.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellUnidV);
				
				PdfPCell cellValV = new PdfPCell(new Phrase(decimalFormat.format(lstVentas.get(i).getPrecioVenta()), paragraphFont));
				cellValV.setBorder(Rectangle.NO_BORDER);
				cellValV.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellValV);
				
				PdfPCell cellTotalV = new PdfPCell(new Phrase(decimalFormat.format(lstVentas.get(i).getValorTotal()), paragraphFont));
				cellTotalV.setBorder(Rectangle.NO_BORDER);
				cellTotalV.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellTotalV);

			}
			chapter.add(table);
			chapter.add(new Paragraph("--------------------------------------------------------------------------------------------------------", paragraphFont));
			double subTotal = Utils.obtenerSubTotal(lstVentas);
			Paragraph pgTotal = new Paragraph("TOTAL: " + decimalFormat.format(subTotal), paragraphFont); 
			pgTotal.setAlignment(Element.ALIGN_RIGHT);
			chapter.add(pgTotal);
			lOGGER.info("pagan con " + paganCon);
			if(paganCon != null && paganCon !=0) {
				Paragraph pgEfectivo = new Paragraph("PAGA CON: " + decimalFormat.format(paganCon), paragraphFont); 
				pgEfectivo.setAlignment(Element.ALIGN_RIGHT);
				chapter.add(pgEfectivo);
				
				Paragraph pgCambio = new Paragraph("CAMBIO: " + decimalFormat.format((paganCon - subTotal)), paragraphFont); 
				pgCambio.setAlignment(Element.ALIGN_RIGHT);
				chapter.add(pgCambio);
				
			}
			
			Paragraph pgFooter = new Paragraph("GRACIAS POR SU VISITA", paragraphFont); 
			pgFooter.setAlignment(Element.ALIGN_CENTER);
			chapter.add(pgFooter);
			
			document.add(chapter);
			document.close();
			factura.setArchivo(bos.toByteArray());
			System.out.println("Factura archivo " +  factura.getArchivo());
		} catch (DocumentException e) {
			System.out.println("Error generando el documento " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error generico " + e.getMessage());
			e.printStackTrace();
		}finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
            	lOGGER.error("Error guardarVenta: " + e.getMessage());
    			e.printStackTrace();
            }
		}
		return factura;
	}

}
