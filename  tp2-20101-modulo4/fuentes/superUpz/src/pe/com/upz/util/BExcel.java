/**
 * Resumen.
 * Objeto                     : Bean.
 * Descripción                : Clase que contiene metodos generales para manipular archivos Excel 
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */

package pe.com.upz.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.Region;


/**
 * Clase que contiene metodos generales para manipular archivos Excel
 *
 */
public class BExcel
{

  /**
  * Constructor por defecto de la clase. Sin parámetros. Sin Exception.
  */
  public BExcel()
  {
  }
 
 
 
  /**
  * Método para poner el mismo estilo a un conjunto de celdas.
  * Si alguna de las filas o celdas de la hoja incluidas en el intervalo no
  * existen entonces se crean, si ya existen solo se les asigna el estilo.
  * @param sHoja hoja excel, de tipo HSSFSheet.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param filaIni fila de inicio, de tipo int.
  * @param colIni columna de inicio, de tipo short.
  * @param filaFin fila final, de tipo int.
  * @param colFin columna final, de tipo short.
  */ 
  public void ponerEstiloRango(HSSFSheet sHoja, HSSFCellStyle estilo,
                          int filaIni, short colIni,
                          int filaFin, short colFin)
  {
      HSSFRow objFila=null;
      HSSFCell cCelda=null;
     
      for(int fila=filaIni;fila<=filaFin;fila++)
      {                                   
        objFila = sHoja.getRow(fila);
        if(objFila==null)
          objFila = sHoja.createRow(fila);
       
        for (short columna = colIni; columna <= colFin; columna++)
        {
          cCelda = objFila.getCell(columna);               
          if(cCelda==null)
            cCelda=objFila.createCell(columna);               
           
          cCelda.setCellStyle(estilo);                                 
        }
      }   
  }
  /**
  * Método para poner el mismo estilo a un conjunto de celdas de una fila.
  * Si alguna de las filas o celdas de la hoja incluidas en el intervalo no
  * existen entonces se crean, si ya existen solo se les asigna el estilo.
  * @param sHoja hoja excel, de tipo HSSFSheet.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param fila fila de la hoja, de tipo int.
  * @param colIni columna de inicio, de tipo short.
  * @param colFin columna final, de tipo short.
  */ 
  public void ponerEstiloFila(HSSFSheet sHoja, HSSFCellStyle estilo,
                          int fila, short colIni, short colFin)
  {
      HSSFRow objFila=null;
      HSSFCell cCelda=null;
                       
      objFila = sHoja.getRow(fila);
      if(objFila==null)
        objFila = sHoja.createRow(fila);
     
      for (short columna = colIni; columna <= colFin; columna++)
      {
        cCelda = objFila.getCell(columna);               
        if(cCelda==null)
          cCelda=objFila.createCell(columna);               
           
        cCelda.setCellStyle(estilo);                                 
      }

  }
  /**
  * Método para poner el mismo estilo a un conjunto de celdas de una fila.
  * Si alguna de las filas o celdas de la hoja incluidas en el intervalo no
  * existen entonces se crean, si ya existen solo se les asigna el estilo.
  * @param objFila fila de la hoja, de tipo HSSFRow.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param colIni columna de inicio, de tipo short.
  * @param colFin columna final, de tipo short.
  */ 
  public void ponerEstiloFila(HSSFRow objFila, HSSFCellStyle estilo,
                          short colIni, short colFin)
  {     
      HSSFCell cCelda=null;
                             
      for (short columna = colIni; columna <= colFin; columna++)
      {
        cCelda = objFila.getCell(columna);               
        if(cCelda==null)
          cCelda=objFila.createCell(columna);               
           
        cCelda.setCellStyle(estilo);                                 
      }

  }
  /**
  * Método para poner el mismo estilo a un conjunto de celdas de una columna.
  * Si alguna de las filas o celdas de la hoja incluidas en el intervalo no
  * existen entonces se crean, si ya existen solo se les asigna el estilo.
  * @param sHoja hoja excel, de tipo HSSFSheet.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param columna columna de la hoja, de tipo short.
  * @param filaIni fila de inicio, de tipo int.
  * @param filaFin fila final, de tipo int.
  */ 
  public void ponerEstiloColumna(HSSFSheet sHoja, HSSFCellStyle estilo,
                          short columna, int filaIni, int filaFin)
  {
      HSSFRow objFila=null;
      HSSFCell cCelda=null;
                             
      for (int fila = filaIni; fila <= filaFin; fila++)
      {
        objFila = sHoja.getRow(fila);
        if(objFila==null)
          objFila = sHoja.createRow(fila);
         
        cCelda = objFila.getCell(columna);               
        if(cCelda==null)
          cCelda=objFila.createCell(columna);               
           
        cCelda.setCellStyle(estilo);                                 
      }

  }
  /**
  * Método para asignar un valor y un estilo a una celda.
  * Si la celda no existe entonces se crea, si ya existe solo se le asigna el
  * estilo y el valor.
  * @param objFila fila de la celda, de tipo HSSFRow.
  * @param columna columna de la hoja, de tipo short.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param valor valor a colocar, de tipo String.
  */ 
  public void ponerCelda(HSSFRow objFila, short columna,
                                HSSFCellStyle estilo, String valor
                                )
  {
      HSSFCell cCelda=null;
                             
      cCelda = objFila.getCell(columna);               
      if(cCelda==null)
        cCelda=objFila.createCell(columna);               
         
      cCelda.setCellStyle(estilo);                                 
      cCelda.setCellValue(valor);
  }
 


  /**
  * Método para asignar un valor y un estilo a una celda.
  * Si la celda no existe entonces se crea, si ya existe solo se le asigna el
  * estilo y el valor.
  * @param objFila fila de la celda, de tipo HSSFRow.
  * @param columna columna de la hoja, de tipo short.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param valor valor a colocar, de tipo double.
  */ 
  public void ponerCelda(HSSFRow objFila, short columna,
                                HSSFCellStyle estilo, double valor
                                )
  {
      HSSFCell cCelda=null;
                             
      cCelda = objFila.getCell(columna);               
      if(cCelda==null)
        cCelda=objFila.createCell(columna);               
         
      cCelda.setCellStyle(estilo);                                 
      cCelda.setCellValue(valor);
  }


  /**
  * Método para asignar un valor y un estilo a una celda.
  * Si la celda no existe entonces se crea, si ya existe solo se le asigna el
  * estilo y el valor.
  * @param objFila fila de la celda, de tipo HSSFRow.
  * @param columna columna de la hoja, de tipo short.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param valor valor a colocar, de tipo Date.
  */ 
  public void ponerCelda(HSSFRow objFila, short columna,
                                HSSFCellStyle estilo, Date valor
                                )
  {
      HSSFCell cCelda=null;
                             
      cCelda = objFila.getCell(columna);               
      if(cCelda==null)
        cCelda=objFila.createCell(columna);               
         
      cCelda.setCellStyle(estilo);                                 
      cCelda.setCellValue(valor);
  }
 
 
  /**
  * Método para asignar un valor y un estilo a una celda.
  * Si la celda no existe entonces se crea, si ya existe solo se le asigna el
  * estilo y el valor.
  * @param objFila fila de la celda, de tipo HSSFRow.
  * @param columna columna de la hoja, de tipo short.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param valor valor a colocar, de tipo boolean.
  */ 
  public void ponerCelda(HSSFRow objFila, short columna,
                                HSSFCellStyle estilo, boolean valor
                                )
  {
      HSSFCell cCelda=null;
                             
      cCelda = objFila.getCell(columna);               
      if(cCelda==null)
        cCelda=objFila.createCell(columna);               
         
      cCelda.setCellStyle(estilo);                                 
      cCelda.setCellValue(valor);
  }
 


  /**
  * Método para asignar un valor y un estilo a una celda.
  * Si la celda no existe entonces se crea, si ya existe solo se le asigna el
  * estilo y el valor.
  * @param objFila fila de la celda, de tipo HSSFRow.
  * @param columna columna de la hoja, de tipo short.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param valor valor a colocar, de tipo Calendar.
  */ 
  public void ponerCelda(HSSFRow objFila, short columna,
                                HSSFCellStyle estilo, Calendar valor
                                )
  {
      HSSFCell cCelda=null;
                             
      cCelda = objFila.getCell(columna);               
      if(cCelda==null)
        cCelda=objFila.createCell(columna);               
         
      cCelda.setCellStyle(estilo);                                 
      cCelda.setCellValue(valor);
  }
 


  /**
  * Método para ponerle borde a un estilo de celda.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  */ 
  public void ponerBorde(HSSFCellStyle estilo)
  {
    estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    estilo.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    estilo.setBorderTop(HSSFCellStyle.BORDER_THIN);
    estilo.setBorderRight(HSSFCellStyle.BORDER_THIN);
  }


  /**
  * Método para ponerle color de fondo a un estilo de celda.
  * @param estilo estilo a aplicar, de tipo HSSFCellStyle.
  * @param colorindex indice del color, de tipo short.
  */ 
  public void ponerColorFondo(HSSFCellStyle estilo, short colorindex)
  {
    estilo.setFillForegroundColor(colorindex);       
    estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
  }


  /**
  * Método para establecer el mismo ancho a un rango de columnas.
  * @param sHoja Hoja Excel que contiene a las columnas, de tipo HSSFSheet.
  * @param colIni Numero de columna inicial, de tipo short.
  * @param colFin Numero de columna final, de tipo short.
  * @param ancho ancho a colocar en las columnas, de tipo short.
  */ 
  public void ponerAnchoColumnas(HSSFSheet sHoja, short colIni, short colFin, short ancho)
  {   
    for (short colAct = colIni; colAct <= colFin; colAct++)
    {
        sHoja.setColumnWidth(colAct, ancho);
    }   
  }


  /**
  * Método para combinar verticalmente celdas de un rango de columnas.
  * @param sHoja  Hoja Excel, de tipo HSSFSheet.
  * @param filaIni Numero de fila inicial, de tipo int.
  * @param colIni  Numero de columna inicial, de tipo short.
  * @param filaFin Numero de fila final, de tipo int.
  * @param colFin  Numero de columna final, de tipo short.
  */ 
  public void combinarColumnas(HSSFSheet sHoja, int filaIni,short colIni, int filaFin, short colFin)
  {   
      for (short colAct = colIni; colAct <= colFin; colAct++)
      {         
          sHoja.addMergedRegion(new Region(filaIni,colAct,filaFin,colAct) );
      }
  } 


  /**
  * Método para combinar horizontalmente celdas de un rango de filas.
  * @param sHoja  Hoja Excel, de tipo HSSFSheet.
  * @param filaIni Numero de fila inicial, de tipo int.
  * @param colIni  Numero de columna inicial, de tipo short.
  * @param filaFin Numero de fila final, de tipo int.
  * @param colFin  Numero de columna final, de tipo short.
  */ 
  public void combinarFilas(HSSFSheet sHoja, int filaIni,short colIni, int filaFin, short colFin)
  {   
      for (int filaAct = filaIni; filaAct <= filaFin; filaAct++)
      {         
          sHoja.addMergedRegion(new Region(filaAct,colIni,filaAct,colFin) );
      }
  }


  /**
  * Busca una cadena en la hoja Excel y devuelve la posicion de
  * la celda que lo contiene en un arreglo de dos elementos: [0] es la fila, y
  * [1] es la columna. Si no lo encuentra devuelve null.
  * @param sHoja  Hoja Excel, de tipo HSSFSheet.
  * @param cadena Texto a buscar, de tipo String.
  * @return Un arreglo de enteros con dos elementos: [0] es la fila, y
  * [1] es la columna. Si no lo encuentra devuelve null.
  */ 
  public int[] buscarCadena(HSSFSheet sHoja, String cadena)
  {
      HSSFRow rFila=null;
      HSSFCell cCelda=null;
      int filaInicio=sHoja.getFirstRowNum();
      int filaFinal=sHoja.getLastRowNum() + 1;
      short colInicio=0;
      short colFin=0;     
     
      //--Busca la celda que contiene la cadena dada.
      for (int filAct = filaInicio; filAct < filaFinal; filAct++)
      {           
          rFila=sHoja.getRow(filAct);
       
          if(rFila!=null)
          {
              colInicio=rFila.getFirstCellNum();
              colFin=(short)(rFila.getLastCellNum() + 1);
       
              for(short colAct = colInicio; colAct < colFin; colAct++)
              {     
                  cCelda = rFila.getCell(colAct);
                  if(cCelda!=null)
                  {   
                  /* PR0129 - Item 1 - Inicio */
                      if(cCelda.getCellType()==HSSFCell.CELL_TYPE_STRING)
                      {
                  /* PR0129 - Item 1 - Fin */
                          if(cCelda.getStringCellValue()!=null)
                          {       
                              if(cCelda.getStringCellValue().equals(cadena))
                              { 
                                int[] posCelda=new int[] { filAct, colAct};

                                return posCelda;
                            /* PR0129 - Item 1 - Inicio */
                              }
                            /* PR0129 - Item 1 - Fin */
                          }
                      }
                  }
              }           
          }
      }   


      return null;
  }


  /**
  * Metodo utilizado para generar la cabecera en los reportes Excel.
  * @param sHoja Objeto que representa a la hoja Excel con los datos.
  * @param institucion Valor a colocar como nombre de la institución.
  * @param area Valor a colocar como nombre del área o división.
  * @param sistema Valor a colocar como el nombre del Sistema.
  * @param fecha Fecha a colocar en el reporte.
  * @param hora Hora a colocar en el reporte.
  * @param usuario Login del usuario a colocar en el reporte.
  * @param finReporte Valor a colocar como fin de reporte.
  */
  public void cabeceraReporte(HSSFSheet sHoja,
                            String institucion, String area, String sistema,
                            String fecha, String hora, String usuario,
                            String finReporte)
  {
      //-- 1. Institucion
      int filaIniCab=0;
      short colIniCab=0;
     
      int[] posToken=buscarCadena(sHoja,"$$INSTITUCION$$");
      if(posToken!=null)
      {
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(institucion);
        }
      }

      //-- 2. Area
      posToken=buscarCadena(sHoja,"$$AREA$$");
      if(posToken!=null)
      {
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(area);
        }
      }

      //-- 3. Sistema
      posToken=buscarCadena(sHoja,"$$SISTEMA$$");
      if(posToken!=null)
      {
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(sistema);
        }
      }

      //-- 4. Fecha
      posToken=buscarCadena(sHoja,"$$FECHA$$");
      if(posToken!=null)
      {     
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(fecha);
        }
      }

      //-- 5. Hora
      posToken=buscarCadena(sHoja,"$$HORA$$");
      if(posToken!=null)
      {     
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(hora);
        }
      }

      //-- 6. Usuario
      posToken=buscarCadena(sHoja,"$$USUARIO$$");
      if(posToken!=null)
      {     
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(usuario.toUpperCase());
        }
      }

      //-- 7. Fin de reporte
      posToken=buscarCadena(sHoja,"$$FIN$$");
      if(posToken!=null)
      {     
        filaIniCab=posToken[0];
        colIniCab=(short)posToken[1];

        if(sHoja.getRow(filaIniCab)!=null)
        { if(sHoja.getRow(filaIniCab).getCell(colIniCab)!=null)
            sHoja.getRow(filaIniCab).getCell(colIniCab).setCellValue(finReporte);
        }
      }     
   
  }


  /* INICIO PR 129 */

  /**
  * Metodo utilizado para agregar la etiqueta de fin de reporte
  * en los reportes Excel.
  * @param sHoja Objeto que representa a la hoja Excel con los datos.
  * @param finReporte Valor a colocar como fin de reporte.
  */
  public void finReporte(HSSFWorkbook wb, HSSFSheet sHoja, String finReporte, int columna)
  {
      // FUENTE PARA EL FIN DE REPORTE
      HSSFFont fFinReporte = wb.createFont();
      /* PR0129 - Item 1 - Inicio */
      //fFinReporte.setFontHeightInPoints((short) 10);   
      fFinReporte.setFontHeightInPoints((short) 9);   
      fFinReporte.setColor(HSSFColor.BLACK.index);
      //fFinReporte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      /* PR0129 - Item 1 - Fin */
   
      // ESTILO PARA EL FIN DE REPORTE
      HSSFCellStyle csFinReporte = wb.createCellStyle(); // cell style
      csFinReporte.setFont(fFinReporte); //set cell stlye
      csFinReporte.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
   
      //-- 1. Fin de reporte     
      int fila=sHoja.getLastRowNum(); //-- ultima fila

      HSSFRow objFila=sHoja.createRow(fila + 1);  //-- la siguiente
      HSSFCell celda=objFila.createCell((short)columna);
      celda.setCellValue(finReporte);         
      celda.setCellStyle(csFinReporte);
  }
 
  /* FIN PR 129 */
 
  /* PR137 - Inicio */
  /**
  * Obtiene estilo de borde cerrado de la celda
  * @param libro  libro del archivo excel
  * @return retorna el estilo de celda de borde cerrado
  */ 
  public HSSFCellStyle getBordeCerrado(HSSFWorkbook libro){
        HSSFCellStyle estilo = libro.createCellStyle();           
        estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderRight(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderTop(HSSFCellStyle.BORDER_THIN);
    return estilo;
  }
  /**
  * Obtiene estilo del borde izquierdo de la celda
  * @param libro  libro del archivo excel
  * @return retorna el estilo de celda del borde izquierdo
  */ 
  public HSSFCellStyle getBordeEsqIzq(HSSFWorkbook libro){
        HSSFCellStyle estilo = libro.createCellStyle();           
        estilo.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    estilo.setBorderTop(HSSFCellStyle.BORDER_THIN);
    return estilo;
  }
  /**
  * Obtiene estilo del borde del centro de la celda
  * @param libro  libro del archivo excel
  * @return retorna el estilo de celda del borde del centro de la celda
  */ 
  public HSSFCellStyle getBordeCentro(HSSFWorkbook libro){
        HSSFCellStyle estilo = libro.createCellStyle();           
        estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderTop(HSSFCellStyle.BORDER_THIN);
    return estilo;
  }
  /**
  * Obtiene estilo del borde derecho de la celda
  * @param libro  libro del archivo excel
  * @return retorna el estilo de celda del borde derecho de la celda
  */ 
  public HSSFCellStyle getBordeEsqDer(HSSFWorkbook libro){
        HSSFCellStyle estilo = libro.createCellStyle();           
        estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderRight(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderTop(HSSFCellStyle.BORDER_THIN);
    return estilo;
  }
  /**
  * Obtiene estilo de la fuente de la celda
  * @param libro    libro del archivo excel
  * @param tamano    tamaño de la fuente
  * @param nomFuente nombre de la fuente
  * @param italic    indicador de fuente cursiva
  * @return retorna el estilo de la fuente de la celda
  */ 
  public HSSFFont getFuente(HSSFWorkbook libro, short tamano, String nomFuente, boolean italic){
    HSSFCellStyle estilo = libro.createCellStyle();           
    HSSFFont fuente = libro.createFont();
    fuente.setFontHeightInPoints((short)tamano);
    fuente.setFontName(nomFuente);
    fuente.setItalic(italic);
    return fuente;
  }
  /**
  * Setea el valor de la celda en la posición indicada
  * @param libro      libro del archivo excel
  * @param hoja        hoja del libro
  * @param fila        fila de la hoja
  * @param celda      celda de la fila
  * @param posColumna  posición de la columna
  * @param valor      valor de la celda
  * @param estilo      estilo de la celda
  */ 
  public void setCelda(HSSFWorkbook libro, HSSFSheet hoja, HSSFRow fila, HSSFCell celda,
                      short posColumna, String valor, HSSFCellStyle estilo){
    celda = fila.createCell((short) posColumna);
    celda.setCellValue((valor==null?"":valor));
    if(estilo!=null)
        celda.setCellStyle(estilo);
  }
  /**
  * setea valores vacíos en el rango indicado
  * @param libro          libro del archivo excel
  * @param hoja          hoja del libro
  * @param fila          fila de la hoja
  * @param celda          celda de la fila
  * @param posColInicial  posición de columna inicial
  * @param posColFinal    posición de columna inicial
  * @param estilo        estilo de la celda
  */ 
  public void setRangoCeldaVacias(HSSFWorkbook libro, HSSFSheet hoja, HSSFRow fila, HSSFCell celda,
                                  short posColInicial, short posColFinal, HSSFCellStyle estilo){
    for(int i=posColInicial; i<=posColFinal; i++){
        celda = fila.createCell((short) i); 
        celda.setCellValue("");
        if(estilo!=null)
          celda.setCellStyle(estilo);
    }
  }
  /**
  * setea el estilo de celdas en el rango indicado
  * @param libro          libro del archivo excel
  * @param hoja          hoja del libro
  * @param fila          fila de la hoja
  * @param celda          celda de la fila
  * @param posColInicial  posición de columna inicial
  * @param posColFinal    posición de columna inicial
  * @param estilo        estilo de la celda
  */ 
  public void setEstiloCeldas(HSSFWorkbook libro, HSSFSheet hoja, HSSFRow fila, HSSFCell celda,
                                  short posColInicial, short posColFinal, HSSFCellStyle estilo){
    for(int i=posColInicial; i<=posColFinal; i++){
        celda = fila.createCell((short) i); 
        if(estilo!=null)
          celda.setCellStyle(estilo);
    }
  } 
  /* PR137 - Fin */
 
} //-- Fin de la clase BExcel