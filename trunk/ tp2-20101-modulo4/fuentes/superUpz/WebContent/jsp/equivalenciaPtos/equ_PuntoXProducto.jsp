<%--
*Resumen
*Objeto                 : equi_PuntoXProducto.jsp.
* Descripcion           : pagina para el puntaje de productos.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%@page import="pe.com.upz.bean.BEquivalencia"%>
<%
String ruta = request.getContextPath(); 
String mensaje = (String)request.getAttribute("mensaje");
BProducto producto = (BProducto)request.getAttribute("producto");

if(producto == null){
	producto = new BProducto();
}

%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Asignar Puntos x Producto</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

<style type="text/css">
<!--
.Estilo1 {font-size: 10px}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.Estilo4 {font-size: 11px; color: #000033; }
.style3 {font-size: 11px}
-->
</style>
</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width:100%" align="center">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>
<form name="frmMinimo" >
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Asignar Puntos x Producto</td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="40%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Equivalecia de puntos </div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td width="23%" align="left" >Producto : </td>
            <td align="left" ><%=producto.getNombre() %></td>
            </tr>
		  <tr >
		    <td align="left" >Tipo : </td>
		    <td align="left" ><%=producto.getTipo().getDescripcion() %></td>
		  </tr>
		  <tr >
            <td colspan="2" align="left" >&nbsp;
            <input type="hidden" name="hddCodigoProducto" id="hddCodigoProducto" 
                  value = "<%=producto.getCodigo()%>"  />
            </td>
            </tr>
          <tr align="center" >
            <td colspan="2" ><table width="459" border="0">
              <tr>
                <td width="117"><div align="left">1ra equivalencia: </div></td>
                <td width="99"><div align="left">Monto S/. :(<span class="style3">*</span>) </div></td>
                <td width="73"><div align="left">
                  <input type="text" name="txtMonto1" id="txtMonto1" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoUno()%>" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  style="width:60px" class="text  ui-corner-all" />
                </div></td>
                <td width="81"><div align="left">Puntos:(<span class="style3">*</span>) </div></td>
                <td width="67"><div align="left">
                  <input type="text" name="txtPuntos1" id="txtPuntos1" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoUno()%>" 
                  onKeyPress="Upper();SoloNumeros();" 
				  style="width:60px" class="text  ui-corner-all" />
                </div></td>
              </tr>
              <tr>
                <td align="left"><div align="left">2da equivalencia</div></td>
                <td align="left" ><div align="left">Monto S/. </div></td>
                <td><div align="left">
                  <input type="text" name="txtMonto2" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoDos()%>" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  id="txtMonto2" style="width:60px" class="text  ui-corner-all" />
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">
                  <input type="text" name="txtPuntos2" id="txtPuntos2" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoDos()%>" 
                  onKeyPress="Upper();SoloNumeros();" 
				  style="width:60px" class="text  ui-corner-all" />
                </div></td>
              </tr>
              <tr>
                <td><div align="left">3ra equivalencia</div></td>
                <td><div align="left">Monto S/. </div></td>
                <td><div align="left">
                  <input type="text" name="txtMonto3" id="txtMonto3" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoTres()%>" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  style="width:60px" class="text  ui-corner-all" />
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">
                  <input type="text" name="txtPuntos3" id="txtPuntos3" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoTres()%>" 
                  onKeyPress="Upper();SoloNumeros();" 
				  style="width:60px" class="text  ui-corner-all" />
                </div></td>
              </tr>
            </table></td>
            </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Debe realizar el ingreso de al menos un valor del monto y punto. </span></td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>  
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <input type="button" name="btnAceptar" value="Aceptar" 
          onclick="javascript:agregarPuntaje()" 
          style="width:120px" class="ui-state-default btnCancelar"  />
          <input type="button" name="btnCancelar" 
          onclick="javascript:cerrar()" 
          value="Cancelar" style="width:120px" class="ui-state-default btnCancelar" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="">
</form>
 
</div>
</body>
<script language="JavaScript">
function cerrar(){
	frmMinimo.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmMinimo.submit();
}

function agregarPuntaje(){
	frmMinimo.hddOperacion.value="almacenarEquivalencia";
	frmMinimo.action="SEquivalencia";
	frmMinimo.submit();
}
</script>
</html>
