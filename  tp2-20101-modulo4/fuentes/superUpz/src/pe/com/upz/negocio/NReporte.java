/**
 * Resumen.
 * Objeto                     : NReporte.
 * Descripción                : Clase que contiene metodos del negocio para la generacion de reportes 
 * Fecha de Creación          : 256/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.negocio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.util.ReporteConstrucctor;
import pe.com.upz.util.Parametros;

/**
 * Clase que contiene metodos del negocio para la generacion de reportes
 * 
 */
public class NReporte {

	/**
	 * Genera el listado de procesos con expediente administrativo de la carta.
	 * 
	 * @param usuario
	 *            usuario de la sesión, tipo Usuario.
	 * @param fechaActual
	 *            fecha actual, tipo String.
	 * @param rutaExcelPlantilla
	 *            ruta de plantilla del reporte, tipo String.
	 * @param rutaInicial
	 *            ruta del contexto, tipo String.
	 * @param codEstAbogado
	 *            codigo del estudio a buscar, tipo long.
	 * @param codGruProcesos
	 *            codigo del grupo a buscar, tipo long.
	 * @param fecInicio
	 *            fecha de inicio para el reporte, tipo String.
	 * @param fecFin
	 *            fecha de fin para el reporte, tipo String.
	 * @return libro archivo xls Generado, tipo HSSFWorkbook.
	 * @return numeroMes indica el numero de mes a generar, tipo int.
	 * @return nombreMes indica el nombre del mes a generar, tipo nombreMes.
	 */
	public HSSFWorkbook generarReporteExpAdministrativo(BUsuario usuario,
			String rutaExcelPlantilla, String rutaInicial,
			int numeroMes) {
		HSSFWorkbook libro = null;
		try {
			FileInputStream fisExcel = new FileInputStream(rutaInicial
					+ rutaExcelPlantilla + "plantillaReporteClienteMe.xls");
			POIFSFileSystem fsExcel = new POIFSFileSystem(fisExcel);

			ReporteConstrucctor xls = new ReporteConstrucctor();

			HSSFRow fila = null;
			HSSFCell celda = null;

			ArrayList arrProcesos = new ArrayList();

			libro = new HSSFWorkbook(fsExcel);
			HSSFSheet hoja = libro.getSheet("REPORTE_CLIENTES");
			// cabecera de reporte
			xls.cabeceraReporte(hoja, Parametros.ENTERPRISE_NOMBRE, "",
					Parametros.S_APP_NOMBRE, ConnectDS.obtenerFecha(),
					ConnectDS.obtenerFechaFormato(ConnectDS.FORMATO_HHMI),
					usuario.getLogin(), "finReporte");

			// estilo
			HSSFFont fuente = xls.getFuente(libro, (short) 9, "Verdana", false);
			HSSFCellStyle estilo = xls.getBordeCerrado(libro);
			HSSFCellStyle estCentro = libro.createCellStyle();
			estCentro.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			estilo.setFont(fuente);

			fila = hoja.createRow(5);
			xls.setCelda(libro, hoja, fila, celda, (short) 0,
					"generado al mes de " + Parametros.NOMBRE_MES[numeroMes], estCentro);
			int cantidadColumnas = (numeroMes+1) * 2;
			for (int i = 0; i < 20; i++) {
				fila = hoja.createRow((int) i + 8);
				// correlativo
				xls.setCelda(libro, hoja, fila, celda, (short) 0, String
						.valueOf(i + 1), estilo);
				xls.setCelda(libro, hoja, fila, celda, (short) 1, "Sucursal "+(i+1),
						estilo);
				for (int j = 0; j < 24; j++) {
					if (j < cantidadColumnas) {
						xls.setCelda(libro, hoja, fila, celda, (short) (2 + j),
								"0", estilo);
					} else {
						xls.setCelda(libro, hoja, fila, celda, (short) (2 + j),
								"-", estilo);
					}
				}

				/*
				 * xls.setCelda(libro, hoja, fila, celda, (short)2, "0",
				 * estilo); xls.setCelda(libro, hoja, fila, celda, (short)3,
				 * "0", estilo); xls.setCelda(libro, hoja, fila, celda,
				 * (short)4, ((Pensionista)proceso.getPensionistas(usuario).
				 * get(0)).getDesParte(), estilo); xls.setCelda(libro, hoja,
				 * fila, celda, (short)5,
				 * (((Pensionista)proceso.getPensionistas(usuario).
				 * get(0)).getCodPensionista()!=0?
				 * ""+((Pensionista)proceso.getPensionistas(usuario).
				 * get(0)).getApePaterno() +" "+
				 * ((Pensionista)proceso.getPensionistas(usuario).get(0)).
				 * getApeMaterno()+", "+
				 * ((Pensionista)proceso.getPensionistas(usuario).get(0)).
				 * getNombres():""), estilo); xls.setCelda(libro, hoja, fila,
				 * celda, (short)6,
				 * ((Pensionista)proceso.getPensionistas(usuario).get(0)).
				 * getRegimen().getDesRegimen(), estilo); xls.setCelda(libro,
				 * hoja, fila, celda, (short)7,
				 * ((Pensionista)proceso.getPensionistas(usuario).get(0)).
				 * getNumExpAdm(), estilo); hoja.getRow((int)
				 * i+8).getCell((short)7). getCellStyle().setDataFormat(
				 * hoja.getRow(7).getCell((short)7).
				 * getCellStyle().getDataFormat());
				 * 
				 * xls.setCelda(libro, hoja, fila, celda, (short)8,
				 * proceso.getSucursalActual().getEstudioAbogado().
				 * getNomEstAbo(), estilo); xls.setCelda(libro, hoja, fila,
				 * celda, (short)9, proceso.getSucursalActual().getNomSucEst(),
				 * estilo); xls.setCelda(libro, hoja, fila, celda, (short)10,
				 * proceso.getOrgVeredicto(), estilo);
				 */

			}
			// fin de reporte
			xls.finReporte(libro, hoja, "fin del reporte", 6);
			fisExcel.close();
			return libro;
		} catch (IOException e) {
			e.printStackTrace();
			libro = null;
			return libro;
		} catch (Exception e) {
			e.printStackTrace();
			libro = null;
			return libro;
		}
	}
	/* PR251 - Item 1 - Fin */

}
