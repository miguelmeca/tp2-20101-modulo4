<%--
*Resumen
*Objeto                 : mae_MantenerProducto.jsp.
* Descripcion           : pagina para agregar/mantener un producto.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%@page import="pe.com.upz.bean.BTipoProducto"%>
<%@page import="pe.com.upz.bean.BImagenProducto"%>
<%
	String ruta = request.getContextPath();
	Lista listadoTipoProducto = (Lista) request
			.getAttribute("listadoTipoProducto");
	Lista listadoImagen = (Lista) request.getAttribute("listadoImagen");
	BProducto producto = (BProducto) request.getAttribute("producto");

	if (producto == null) {
		producto = new BProducto();
		producto.setCodigo(-1);
		producto.setNombre("");
		producto.setDescripcion("");
		producto.setRutaImagen("");
		producto.setTipo(new BTipoProducto());
	}
	if (listadoTipoProducto == null) {
		listadoTipoProducto = new Lista();
	}
	if (listadoImagen == null) {
		listadoImagen = new Lista();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Nuevo Producto</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />



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

.style3 {
	font-size: 11px
}
-->
</style>
</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form action="" method="get" name="frmNuevoActualizar">
<table width="1010px" border="0">
	<tr>
		<td
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
			align="center">Agregar Producto</td>
	</tr>
	<tr>
		<td height="327" valign="top">
		<table width="100%" border="0">

			<tr>
				<td height="171" valign="top" class="ui-tabs-nav">
				<center>
				<table width="70%" border="1">
					<tr>
						<td
							class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
							style="width: 702px"></td>
					</tr>
					<tr>
						<td align="center" style="width: 702px">
						<table border="0">
							<tr>
								<td colspan="2" align="left">&nbsp; <input name="hddCodigo"
									type="hidden" id="hddCodigo" value="<%=producto.getCodigo()%>" />
									<input name="hddCodigoSeleccionado"
									type="hidden" id="hddCodigoSeleccionado" value="-1" />
								</td>
							</tr>
							<%
								if (producto.getCodigo() != -1) {
							%>
							<tr>
								<td align="left" style="height: 21px; width: 175px;">
								C&oacute;digo:</td>
								<td align="left" style="width: 420px; height: 21px;"><%=producto.getCodigo()%>
								</td>
							</tr>
							<%
								}
							%>
							<tr>
								<td align="left" style="height: 31px; width: 175px;">Tipo:(*)
								</td>
								<td align="left" style="height: 31px; width: 420px;"><select
									id="selTipo" name="selTipo" style="width: 150px">
									<option value="0">-Seleccione-</option>
									<%
										for (int i = 0; i < listadoTipoProducto.getTamanio(); i++) {
									%>
									<%
										BTipoProducto tipo = (BTipoProducto) listadoTipoProducto
													.getElemento(i);
									%>
									<option value="<%=tipo.getCodigo()%>"
										<%=producto.getTipo().getCodigo() == tipo.getCodigo()? "Selected": ""%>><%=tipo.getDescripcion()%>
									</option>
									<%
										}
									%>
								</select></td>
							</tr>
							<tr>
								<td align="left" style="height: 31px; width: 175px;">Producto:
								(*)</td>
								<td >
								<input type="text" name = "txtNombre" class="text  ui-corner-all" id="txtNombre" style="width: 150px" onKeyPress="Upper();permitirLetraNumeroEspeciales();" value="<%=producto.getNombre()%>" maxlength="100" /></td>
							</tr>
							<tr>
								<td align="left" style="height: 31px; width: 175px;">
								Descripción: (*)</td>
								<td align="left" style="height: 31px; width: 420px;"><textarea
									class="text  ui-corner-all" name="txtDescripcion"
									id="txtDescripcion" style="width: 200px"><%=producto.getDescripcion()%></textarea>
								<input type="hidden" name="hddOperacion" id="hddOperacion"
									value="<%=producto.getCodigo() == -1? "almacenarProducto": "actualizarProducto"%>" />
								</td>

							</tr>
							<tr>
								<td colspan="2" align="left"><span class="style3">(*)
								Campos obligatórios.&nbsp;</span></td>
							</tr>
							<!--
							<tr>
								<td align="left" style="height: 31px; width: 175px;">imagen:
								(*)</td>
								<td align="left" style="height: 31px; width: 420px;"><input
									type="file" name="filRutaImagen" id ="filRutaImagen" /></td>
							</tr>
							-->

							<tr>
								<td colspan="2" align="left">

								<div id="tblPrincipal"
									style="border-width: 1; border-color: gray; width: 600px; height: 300px; overflow-y: scroll">
								<table width="583" border="1">
									<%
										for (int i = 0; (i*3) < listadoImagen.getTamanio(); i++) {
											BImagenProducto imagen1 = (i*3 < listadoImagen.getTamanio())
											? (BImagenProducto) listadoImagen.getElemento(i*3)
											: null;
											BImagenProducto imagen2 = ((i*3 + 1) < listadoImagen.getTamanio())
													? (BImagenProducto) listadoImagen.getElemento(i*3 + 1)
													: null;
											BImagenProducto imagen3 = ((i*3 + 2) < listadoImagen.getTamanio())
													? (BImagenProducto) listadoImagen.getElemento(i*3 + 2)
													: null;
									%>
									<tr>
										<td width="33%" class="ui-accordion-content">
										<%if(imagen1 !=null){%>
										<div align="center" class="Estilo4"><img
											src="<%=ruta%>/images/productos/<%=imagen1.getArchivo()%>" 
											width="100" height="100">
											</div>
										<%} %>
										</td>
										<td width="40%" class="ui-accordion-content">
										<%if(imagen2 !=null){%>
										<div align="center" class="Estilo4"><img
											src="<%=ruta%>/images/productos/<%=imagen2.getArchivo()%>"
											width="100" height="100"></div>
										<%
										System.out.println(ruta+"/images/productos/"+imagen2.getArchivo());
										} %>
										</td>
										<td width="33%" class="ui-accordion-content">
										<%if(imagen3 !=null){%>
										<div align="center" class="Estilo4"><img
											src="../../images/productos/<%=imagen3.getArchivo()%>"
											width="100" height="100"></div>
										<%} %>
										</td>
									</tr>
									<tr>
										<td class="ui-accordion-content">
										<div align="center" class="Estilo4"><%=imagen1!=null?imagen1.getNombre():""%></div>
										<%if(imagen1 !=null){%> <input name="chkProducto" type="radio"
											value="<%=imagen1.getCodigo()%>"
											onclick="javascript:seleccionar('<%=imagen1.getCodigo()%>')" />
										<%} %>
										</td>
										<td class="ui-accordion-content">
										<div align="center" class="Estilo4"><%=imagen2!=null?imagen2.getNombre():""%></div>
										<%if(imagen2 !=null){%> <input name="chkProducto" type="radio"
											value="<%=imagen1.getCodigo()%>"
											onclick="javascript:seleccionar('<%=imagen2.getCodigo()%>')" />
										<%} %>
										</td>
										<td class="ui-accordion-content">
										<div align="center" class="Estilo4"><%=imagen3!=null?imagen3.getNombre():""%></div>
										<%if(imagen3 !=null){%> <input name="chkProducto" type="radio"
											value="<%=imagen1.getCodigo()%>"
											onclick="javascript:seleccionar('<%=imagen1.getCodigo()%>')" />
										<%} %>
										</td>
									</tr>
									<%
										}
									%>
								</table>

								</div>

								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</center>
				 </td>
			</tr>

			<tr>
				<td class="ui-widget-header">
				<div align="right"><input type="button" name="btnAceptar"
					onclick="javascript: almacenar();" value="Aceptar"
					style="width: 120px" class="ui-state-default btnAceptar" /> <input
					type="button" name="btnCancelar" value="Cancelar"
					style="width: 120px" class="ui-state-default btnCancelar" /></div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</tr>
</table>

</form>
</div>
</body>
<script language="JavaScript">
function seleccionar(codigo){
	frmNuevoActualizar.hddCodigoSeleccionado.value=codigo;
}
function almacenar() {
		var tipo = frmNuevoActualizar.selTipo.value;
		var nombre = frmNuevoActualizar.txtNombre.value;
		var descripcion = frmNuevoActualizar.txtDescripcion.value;

		if (tipo == "0") {
			alert("Debe seleccionar un tipo de producto.");
			frmNuevoActualizar.selTipo.focus();
			return;
		}
		if (nombre == "") {
			alert("Debe ingresar un nombre para el producto.");
			frmNuevoActualizar.txtNombre.focus();
			return;
		}
		if (descripcion == "") {
			alert("Debe ingresar una descripción para el producto.");
			frmNuevoActualizar.txtDescripcion.focus();
			return;
		}

		frmNuevoActualizar.action = "SMantenimiento";
		frmNuevoActualizar.submit();
	}
</script>
</html>
