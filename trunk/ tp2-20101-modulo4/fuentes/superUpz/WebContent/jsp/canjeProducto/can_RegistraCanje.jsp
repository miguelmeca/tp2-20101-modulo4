<html>
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Canjear Puntos</title>

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
<form name="frmCanje" id="frmCanje" method="get" action="">
<table width="1010px" border="0">
	<tr>
		<td
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
			align="center">Registro de Canje&nbsp;</td>
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
							align="center">Datos para el Canje</td>
					</tr>
					<tr>
						<td align="center" style="width: 702px">
						<table border="0">
							<tr>
								<td colspan="2" align="left"></td>
							</tr>
							<tr>
								<td align="left" style="height: 21px; width: 175px;">
								Cliente: (*)</td>
								<td align="left" style="width: 420px; height: 21px;"><input name="hddCodigoCliente" type="hidden" class="text  ui-corner-all" id="hddCodigoCliente" style="width:150px" readonly="true"  />
                                  <input name="txtCliente" type="text" class="text  ui-corner-all" id="txtCliente" style="width:250px" readonly="true"  />
                                  <input type="button" name="btnBuscarCliente" value="Buscar" 
			  onclick="javascript:mostrarClientes()" 
			  style="width:70px" class="ui-state-default"  /></td>
							</tr>
							<tr>
								<td align="left" style="height: 26px; width: 175px;">
								Producto: (*)</td>
								<td align="left" style="height: 26px; width: 420px;"><input
									type="hidden" name="hddCodigoProducto"> <input
									name="txtProducto" type="text" class="text  ui-corner-all"
									id="txtProducto" style="width: 250px" readonly="true" /> <input
									type="button" name="btnBuscarProducto" value="Buscar"
									onclick="javascript:mostrarProductos()" 
									style="width: 70px" class="ui-state-default btnBuscarProducto" /></td>
							</tr>
							<tr>
								<td align="left" style="height: 31px; width: 175px;">
								Descripción:</td>
								<td align="left" style="height: 31px; width: 420px;"><textarea
									name="txtDescripcionProducto" cols="" rows="" readonly="readonly"
									class="text  ui-corner-all" id="txtDescripcionProducto" style="width: 200px"></textarea></td>
							</tr>
							<tr>
								<td align="left" style="height: 26px; width: 175px;">
								Stock Actual:</td>
								<td align="left" style="height: 26px; width: 420px;"><input
									name="txtStockProducto" type="text" class="text  ui-corner-all"
									id="txtStockProducto" style="width: 54px" value="100" readonly="readOnly" />
								</td>
							</tr>
							<tr>
								<td align="left" style="height: 26px; width: 175px;">Puntos:(*)
								</td>
								<td align="left" style="height: 26px; width: 420px;"><select
									id="selPuntaje" 
									onChange="javascript:mostrarMonto();"
									name="selPuntaje" style="width: 79px">
									<option value="0">-Seleccione-</option>
								</select> </td>
							</tr>
							<tr>
								<td align="left" style="height: 26px; width: 175px;">
								Monto S/. :</td>
								<td align="left" style="height: 26px; width: 420px;"><input
									type="text" name="txtMonto" id="txtMonto" style="width: 56px"
									class="text  ui-corner-all" readonly="true" /> 
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" align="left"><span class="style3">(*)
								Campos obligatórios.&nbsp;</span></td>
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
					value="Aceptar" style="width: 120px"
					class="ui-state-default btnAceptar" /> <input type="button"
					name="btnCancelar" value="Cancelar" style="width: 120px"
					class="ui-state-default btnCancelar" /></div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
<input type="hidden" name="hddMantenimiento" id="hddMantenimiento" value="" />
</form>
</div>
</body>
<script language="JavaScript">
function mostrarMonto(){
	var monto = frmCanje.selPuntaje.value;
	frmCanje.txtMonto.value=monto;
}
function buscarStock(){   

	var codigoProducto = frmCanje.hddCodigoProducto.value;
    
    var url = "ServicioUtilitario?" +
                  "metodo=requestObtenerStock" +
                  "&codProducto="+ codigoProducto; 
    
    var msxml = new ActiveXObject("msxml2.XMLHTTP");
        
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	 
        
    if(ret!="OK__NoExiste")   {
    	frmCanje.txtStockProducto.value = ret.substr(3);
    }
     
}
function buscarPuntaje(){   

	frmCanje.selPuntaje.length = 0; //Borra los datos
   
    var codigoProducto = frmCanje.hddCodigoProducto.value;
    
    var url = "ServicioUtilitario?" +
                  "metodo=requestObtenerPuntaje" +
                  "&codProducto="+ codigoProducto; 
    
    var msxml = new ActiveXObject("msxml2.XMLHTTP");
        
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	 
        
    if(ret!="OK__NoExiste")   {
        ret = ret.substr(3);
        llenarComboListaCodTexto(ret,";","|",frmCanje.selPuntaje);
    }
     
}

function mostrarClientes(){
	frmCanje.target="VENTANA";
	frmCanje.hddOperacion.value="buscarCliente";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=buscarCliente&hddMantenimiento=0";

	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 950 + 
	                 ",height=" + 600; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
}
function mostrarProductos(){
	frmCanje.target="VENTANA";
	frmCanje.hddOperacion.value="buscarProducto";
	frmCanje.hddMantenimiento.value="0";
	frmCanje.action="<%=ruta%>/SMantenimiento?hddOperacion=buscarProducto&hddMantenimiento=0";

	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 950 + 
	                 ",height=" + 600; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
}
</script>
</html>
