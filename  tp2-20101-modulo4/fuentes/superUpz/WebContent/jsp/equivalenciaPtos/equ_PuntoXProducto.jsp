<%--
*Resumen
*Objeto                 : equ_PuntoXProducto.jsp.
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
                  <input name="txtMonto1" type="text" class="text  ui-corner-all" id="txtMonto1" 
                  style="width:60px" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoUno()%>" maxlength="7" />
                </div></td>
                <td width="81"><div align="left">Puntos:(<span class="style3">*</span>) </div></td>
                <td width="67"><div align="left">
                  <input name="txtPuntos1" type="text" class="text  ui-corner-all" id="txtPuntos1" 
				  style="width:60px" 
                  onKeyPress="Upper();SoloNumeros();" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoUno()%>" maxlength="5" />
                </div></td>
              </tr>
              <tr>
                <td align="left"><div align="left">2da equivalencia</div></td>
                <td align="left" ><div align="left">Monto S/. </div></td>
                <td><div align="left">
                  <input name="txtMonto2" type="text" class="text  ui-corner-all" 
                  id="txtMonto2" style="width:60px" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoDos()%>" maxlength="7" />
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">
                  <input name="txtPuntos2" type="text" class="text  ui-corner-all" id="txtPuntos2" 
				  style="width:60px" 
                  onKeyPress="Upper();SoloNumeros();" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoDos()%>" maxlength="5" />
                </div></td>
              </tr>
              <tr>
                <td><div align="left">3ra equivalencia</div></td>
                <td><div align="left">Monto S/. </div></td>
                <td><div align="left">
                  <input name="txtMonto3" type="text" class="text  ui-corner-all" id="txtMonto3" 
                  style="width:60px" 
				  onKeyPress="FiltroDecimal(this.value);" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getMontoTres()%>" maxlength="7" />
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">
                  <input name="txtPuntos3" type="text" class="text  ui-corner-all" id="txtPuntos3" 
				  style="width:60px" 
                  onKeyPress="Upper();SoloNumeros();" 
                  value = "<%=((BEquivalencia)producto.getListaPuntaje().getElemento(0)).getCantidadPuntoTres()%>" maxlength="5" />
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
	
	if(Trim(frmMinimo.txtMonto1.value) == ""){
		frmMinimo.txtMonto1.value="0.0";
	}
	if(Trim(frmMinimo.txtMonto2.value) == ""){
		frmMinimo.txtMonto2.value="0.0";
	}
	if(Trim(frmMinimo.txtMonto3.value) == ""){
		frmMinimo.txtMonto3.value="0.0";
	}
	if(Trim(frmMinimo.txtPuntos1.value) == ""){
		frmMinimo.txtPuntos1.value="0";
	}
	if(Trim(frmMinimo.txtPuntos2.value) == ""){
		frmMinimo.txtPuntos2.value="0";
	}
	if(Trim(frmMinimo.txtPuntos3.value) == ""){
		frmMinimo.txtPuntos3.value="0";
	}
	
	var monto1 = Trim(frmMinimo.txtMonto1.value);
	var monto2 = Trim(frmMinimo.txtMonto2.value);
	var monto3 = Trim(frmMinimo.txtMonto3.value);
	
	var puntaje1 = Trim(frmMinimo.txtPuntos1.value);
	var puntaje2= Trim(frmMinimo.txtPuntos2.value);
	var puntaje3= Trim(frmMinimo.txtPuntos3.value);
	
	if(monto1 == "" || isNaN(monto1) ){
		alert("Debe ingresar la primera equivalencia.");
		frmMinimo.txtMonto1.focus();
		return;
	}
	if(puntaje1 == ""|| isNaN(puntaje1)|| parseInt(puntaje1)<=0){
		alert("Debe ingresar la primera equivalencia.");
		frmMinimo.txtPuntos1.focus();
		return;
	}
	if( isNaN(monto2)  ){
		alert("Debe ingresar un valor válido para equivalencia.");
		frmMinimo.txtMonto2.focus();
		return;
	}
	if( isNaN(puntaje2)){
		alert("Debe ingresar la primera equivalencia.");
		frmMinimo.txtPuntos2.focus();
		return;
	}
	
	if(parseInt(monto2) > 0 && parseInt(puntaje2) <=0 ){
		alert("Si ingresa un monto en soles, debe ingresar una cantidad de puntos.");
		frmMinimo.txtPuntos2.focus();
		return;
	}
	if( isNaN(monto3)  ){
		alert("Debe ingresar un valor válido para equivalencia.");
		frmMinimo.txtMonto3.focus();
		return;
	}
	if( isNaN(puntaje3)){
		alert("Debe ingresar la primera equivalencia.");
		frmMinimo.txtPuntos3.focus();
		return;
	}
	
	if(parseInt(monto3) > 0 && parseInt(puntaje3) <=0 ){
		alert("Si ingresa un monto en soles, debe ingresar una cantidad de puntos.");
		frmMinimo.txtPuntos3.focus();
		return;
	}	
	frmMinimo.hddOperacion.value="almacenarEquivalencia";
	frmMinimo.action="SEquivalencia";
	frmMinimo.submit();
}
</script>
</html>
