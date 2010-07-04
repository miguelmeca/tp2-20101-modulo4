<%--
*Resumen
*Objeto                 : aba_OrdenGenerada.jsp.
* Descripcion           : pagina para actualizar stock en la orden.
* Fecha de Creacion     : 22/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BPedido"%>
<%@page import="pe.com.upz.bean.BPedidoDetalle"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%@page import="pe.com.upz.util.Parametros"%>

<%
	String ruta = request.getContextPath();
	String fecha = (String) request.getAttribute("fecha");
	Lista listaDetalle = (Lista) request
			.getAttribute("listaDetalle");

	if (listaDetalle == null) {
		listaDetalle = new Lista();
	}
%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Generar Orden de Pedido</title>
<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />
<style type="text/css">
<!--
.Estilo1 {
	font-size: 10px
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.Estilo4 {
	font-size: 11px;
	color: #000033;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>
<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form action="" method="post" name="frmListaProducto">
<table width="1010px" border="0">
		<tr>
			<td
				class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
				align="center">Orden de Pedido</td>
		</tr>
		<tr>
			<td height="327" valign="top">
			<table width="100%" border="0">
				<tr class="ui-widget">
					<td align="center">
					<div class="ui-state-default" style="width:780px;padding-right: 10px;padding-left: 10px">
					<div style="float: left" >FECHA DE GENERACI&Oacute;N:
					<%=fecha%>
					</div>
					<div id="divTotal" style="float: right;" >TOTAL DE SELECCIONADOS: 0
					</div>
					</div>
					</td>
				</tr>

				<tr>
					<td height="171" valign="top" class="ui-tabs-nav" align="center">
					<div id="tblCabecera" style="width: 790px;">
					<div align="left" style="position: relative; left: 5px; top: 0px;">
					<table width="764" border="1">
						<tr>
							<td width="6%"
								class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
							<td width="10%"
								class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<div align="center">Codigo</div>
							</td>
							<td width="25%"
								class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<div align="center">Tipo</div>
							</td>
							<td width="40%"
								class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<div align="center">Descripci&oacute;n</div>
							</td>
							<td width="19%"
								class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<div align="center">Stock unidades</div>
							</td>
						</tr>
					</table>
					</div>
					</div>
					<div id="tblPrincipal"
						style="border-width: 1; border-color: gray; width: 790px; height: 200px; overflow-y: scroll">
					<table width="764" border="1">
						<%
							for (int i = 0; i < listaDetalle.getTamanio(); i++) {
								BPedidoDetalle detalle = (BPedidoDetalle) listaDetalle.getElemento(i);
								BProducto producto = detalle.getProducto();
						%>
						<tr>
							<td width="6%" class="ui-accordion-content"><input name="chkProducto" value ="<%=producto.getCodigo()%>" type="checkbox" value="0" onclick="javascript:validarNoMasDeXProductos(this,'<%=producto.getCodigo()%>')" /></td>
							<td width="10%" class="ui-accordion-content">
							<div align="center" class="Estilo4"><%=producto.getCodigo()%></div>
							</td>
							<td width="25%" class="ui-accordion-content">
							<div align="center" class="Estilo4"><%=producto.getTipo().getDescripcion()%></div>
							<input name="hddTipo<%=producto.getCodigo()%>" type="hidden" value="<%=producto.getTipo().getDescripcion()%>" />
							</td>
							<td width="40%" class="ui-accordion-content">
							<div align="center" class="Estilo4"><%=producto.getNombre()%></div>
							<input name="hddDescripcion<%=producto.getCodigo()%>" type="hidden" value="<%=producto.getNombre()%>" />
							</td>
							<td width="19%" class="ui-accordion-content">
							<div align="center" class="Estilo4">
							<div align="center"><input name="txtCantidad<%=producto.getCodigo()%>" type="text"
								onKeyPress="Upper();SoloNumeros();" 
								class="text  ui-corner-all" id="txtCantidad<%=producto.getCodigo()%>" style="width: 50px"
								value="<%=detalle.getCantidad()%>" size="5" maxlength="5"  />
								<input name="hddCantidad<%=producto.getCodigo()%>" type="hidden"
								id="hddCantidad<%=producto.getCodigo()%>" 
								value="<%=producto.obtenerMaximoSolicitar()%>" /></div>
							</div>
							</td>
						</tr>
						<%
							}
						%>
					</table>

					</div>

					 </td>
				</tr>

				<tr>
					<td class="ui-widget-header">
					  <div align="right">					  <input type="button" name="btnAceptar" value="Aceptar"
						onclick="javascript:aceptar()" 
						style="width: 120px" class="ui-state-default" /> <input
						type="button" name="btnCancelar" value="Cancelar"
						onclick="javascript:cerrar()" 
						style="width: 120px" class="ui-state-default" /></div></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<input type="hidden" name="hddOperacion" id="hddOperacion" value="mostrarOrden">
	</form>
	</div>
</body>

<script language="JavaScript">
function cerrar(){
	frmListaProducto.target="_top";
	frmListaProducto.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmListaProducto.submit();
}
function aceptar(){
	if(!validarProductosSeleccionados()){
		alert("Debe selecccionar productos para la orden de pedido.");
		return;
	}
	frmListaProducto.target="_top";
	frmListaProducto.hddOperacion.value="almacenarOrden";
	frmListaProducto.action="SAbastecimiento?hddOperacion=almacenarOrden";
	frmListaProducto.submit();
}
function validarNoMasDeXProductos(check, codigo){
	if(contarSeleccionados()><%=Parametros.CANTIDAD_PRODUCTOS_X_ORDEN%>){
		alert("Solo se permite un total de <%=Parametros.CANTIDAD_PRODUCTOS_X_ORDEN%>"+
				" productos en una orden.");
		check.checked = false;
		contarSeleccionados();
	}else{
		habilitarCajaTexto(check, codigo);
	}
}

function habilitarCajaTexto(check, codigo){

	var cajaTexto = document.getElementById("txtCantidad"+codigo);
	//si esta seleccionado, se habilita la caja
	var habilitarCaja = check.checked;
	cajaTexto.disabled = (!habilitarCaja);
	if(habilitarCaja){
		cajaTexto.focus();
	}
}
function validarCantidadIngresada(codigo){
	var valor = document.getElementById("txtCantidad"+codigo);
	var valorMaximo = document.getElementById("hddCantidad"+codigo);
	var ingresado =valor.value;
	if(parseInt(valor.value) > parseInt(valorMaximo.value)){
		valor.value=valorMaximo.value;
		alert("Cantidad ingresada de "+ingresado+" unidades no válida");
		valor.focus();
	}
	
}

function validarProductosSeleccionados(){
	var seleccionado = false;
	
	for (i=0; i < frmListaProducto.chkProducto.length; i++) {
		if (frmListaProducto.chkProducto[i].checked) {
			seleccionado = true;
			break;
		}
	}	
	return seleccionado;
}

function mostrarEnDiv(nombreDiv, textoMostrar){
	var elementoDiv = document.getElementById(nombreDiv);
	elementoDiv.innerHTML = textoMostrar;
}

function contarSeleccionados(){
	var contador =0;
	for (i=0; i < frmListaProducto.chkProducto.length; i++) {
		if (frmListaProducto.chkProducto[i].checked) {
			contador ++;
		}
	}
	mostrarEnDiv("divTotal","TOTAL DE SELECCIONADOS: "+contador);
	return contador;
	
}

function mostrarOrden(){

	if(!validarProductosSeleccionados()){
		alert("Debe selecccionar productos para la orden de pedido.");
		return;
	}

	frmListaProducto.target="VENTANA";

	frmListaProducto.action="<%=ruta%>/SAbastecimiento";
	
	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 900 + 
	                 ",height=" + 700; 
	window.open("","VENTANA",opciones,1); 
	frmListaProducto.submit();
	//frmLogin.submit();
}

</script>
</html>
