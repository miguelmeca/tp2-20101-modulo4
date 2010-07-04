<%--
*Resumen
*Objeto                 : aba_ListadoOrdenes.jsp.
* Descripcion           : pagina para listar las ordenes.
* Fecha de Creacion     : 10/07/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BPedido"%>
<%
String ruta = request.getContextPath(); 
Lista listaOrdenes = (Lista)request.getAttribute("listaOrdenes");
if(listaOrdenes ==null){
	listaOrdenes = new Lista();
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Actualiza Stock</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />
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
-->
</style>
</head>

<body onload="javascript:mostrarMensaje();">
<jsp:include page="../comun/cabecera.jsp"></jsp:include>

<div class="demos-nav" style="width:100%" align = "center">
<form action="" method="get" name="frmListaProducto"  >
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Ordenes Generadas </td>
</tr>
  <tr>
    <td height="268" valign="top" ><table width="100%" border="0">
         
      <tr>
        <td valign="top" class="ui-tabs-nav" align="center"><table width="348" border="1">
          <tr>
            <td width="12%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Num</td>
            <td width="28%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Orden</div></td>
            <td width="49%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Fecha de Generaci&oacute;n </div></td>
            <td width="11%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
            </tr>
 			<%for(int i=0;i<listaOrdenes.getTamanio();i++){%>
 			<%	BPedido pedido = (BPedido) listaOrdenes.getElemento(i); %> 
          <tr >
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=(i+1)%></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=pedido.getCodigo()%></div>
            <input type="hidden" name="hddCodigoPedido<%=pedido.getCodigo()%>" value="<%=pedido.getCodigo()%>" />
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=pedido.getFechaPedido()%></div>
            <input type="hidden" name="hddFecha<%=pedido.getCodigo()%>" value="<%=pedido.getFechaPedido()%>" />
            </td>
            <td class="ui-accordion-content"><input name="chkPedido" type="radio" value ="<%=pedido.getCodigo()%>" /></td>
            </tr>
            <%} %>
        </table>          
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
		  <input type="button" name="btnEditar" value="Editar" style="width:120px" class="ui-state-default"  />
          <input type="button" name="btnAceptar" value="Aceptar" 
		  onclick="javascript:verOrden()" 
		  style="width:120px" class="ui-state-default"  />          
          <input type="button" name="btnCancelar" 
          onclick="javascript:cerrar()" 
          value="Cancelar" style="width:120px" class="ui-state-default" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
<input type="hidden" name="hddOrden" id="hddOrden" value="" />
</form>
</div>
</body>
<script language="JavaScript">
function cerrar(){
	frmListaProducto.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmListaProducto.submit();
}

function mostrarMensaje(){
	var resultado = "<%=""%>";
	if(resultado == "nuevoOK"){
		alert("Se agregó un nuevo producto.");
	}else if (resultado== "actualizadoOK"){
		alert("Se actualizó el producto.");
	}
}

function agregarNuevo(){
		frmListaProducto.hddOperacion.value="ingresoNuevoProducto";
		frmListaProducto.action="SMantenimiento?hddOperacion=ingresoNuevoProducto";
		frmListaProducto.submit();
	}
function verOrden(){
	if(!validarOrdenSeleccionados()){
		alert("Debe seleccionar una orden.");
		return;
	}
	
	frmListaProducto.hddOperacion.value="mostrarOrdenActualizar";
	frmListaProducto.action="SMantenimiento?hddOperacion=mostrarOrdenActualizar";
	frmListaProducto.submit();
}
function validarOrdenSeleccionados(){
	var seleccionado = false;
	
	for (i=0; i < frmListaProducto.chkPedido.length; i++) {
		if (frmListaProducto.chkPedido[i].checked) {
			seleccionado = true;
			break;
		}
	}	
	return seleccionado;
}
</script>
</html>
