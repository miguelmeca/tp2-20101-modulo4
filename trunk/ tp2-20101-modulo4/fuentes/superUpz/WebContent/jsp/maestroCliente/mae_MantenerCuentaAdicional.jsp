<%--
*Resumen
*Objeto                 : mae_MantenerCuentaAdicional.jsp.
* Descripcion           : pagina para el mantenimiento de cuenta.
* Fecha de Creacion     : 10/07/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<html>
<%
String ruta = request.getContextPath(); 
String numTarjeta= request.getParameter("txtNumeroTarjeta");
String nombreCliente= request.getParameter("txtCliente");
String codigoCliente = request.getParameter("hddCodigoCliente");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Canjear Puntos</title>
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
<form name="frmCanje" id="frmCanje" method="get" action="">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Mantenimiento de Cuenta Adicional </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="70%" border="1">
          <tr>
            <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Datos de la cuenta Adicional </td>
            </tr>
          <tr >
            <td align="center" style="width: 702px" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >
            </td>
            </tr>
		  <tr >
            <td align="left" style="height: 21px; width: 175px;" > Cliente: (*) </td>
            <td align="left" style="width: 420px; height: 21px;" >
			<input name="hddCodigoCliente" type="hidden" class="text  ui-corner-all" id="hddCodigoCliente" style="width:150px" readonly="true"  /> 
			<input name="txtCliente" type="text" class="text  ui-corner-all" id="txtCliente" style="width:250px" readonly="true"  />                  
              <input type="button" name="btnBuscarCliente" value="Buscar" 
			  onclick="javascript:mostrarClientes()" 
			  style="width:70px" class="ui-state-default"  /></td></tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" >&nbsp; </td>
			<td align="left" style="height: 26px; width: 420px;" >
				<input name="hddClienteNombre" type="hidden" id="hddClienteNombre"    /> 
				<input name="hddClientePaterno" type="hidden" id="hddClientePaterno"   /> 
				<input name="hddClienteMaterno" type="hidden" id="hddClienteMaterno"   /> 
			</td>
		  </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Asignar tarjeta  (*)  </td>
			<td align="left" style="height: 31px; width: 420px;" ><input name="txtNumeroTarjeta" type="text" class="text  ui-corner-all" id="txtNumeroTarjeta" style="width:150px" readonly="true" />&nbsp;&nbsp;
			<input type="button" name="btnAsignar" id="btnAsignar" 
			onclick="javascript:agregarTarjeta()" 
			value="Asignar" style="width:120px" class="ui-state-default btnAsignar" /></td>
          </tr>

          <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Campos obligatórios.&nbsp;</span></td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>   </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <input type="button" name="btnAceptar" value="Aceptar" 
          onclick="javascript:guardar()" 
          style="width:120px" class="ui-state-default btnAceptar"  />
          <input type="button" name="btnCancelar" value="Cancelar" 
          onclick="javascript:cerrar()" 
          style="width:120px" class="ui-state-default" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
<input type="text" name="hddCodigoCli" id="hddCodigoCli" value="<%=codigoCliente%>" />
<input type="text" name="hddNombreCliente" id="hddNombreCliente" value="<%=nombreCliente%>" />
<input type="text" name="hddNumeroTarjeta" id="hddNumeroTarjeta" value="<%=numTarjeta%>" />
<input type="hidden" name="hddMantenimiento" id="hddMantenimiento" value="0" />
</form>
</div>
</body>
<script language="JavaScript">
function cerrar(){
	frmCanje.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmCanje.submit();
}
function guardar(){
	frmCanje.target="_top";
	frmCanje.hddOperacion.value="almacenarUnAdicional";
	var codigoCuenta = frmCanje.hddCodigoCliente.value;
	var numTarjeta = frmCanje.txtNumeroTarjeta.value;
	if(codigoCuenta==""){
		alert("Debe seleccionar un cliente");
		return;
	}
	if(numTarjeta==""){
		alert("Debe ingresar una tarjeta");
		return;
	}
	frmCanje.hddMantenimiento.value="-1";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=almacenarUnAdicional";
	frmCanje.submit();
}
function agregarTarjeta(){
	frmCanje.target="VENTANA";
	frmCanje.hddOperacion.value="asignarTarjeta";
	//frmCanje.action="<%=ruta%>/jsp/maestroCliente/mae_AsignarTarjeta.jsp?mostrar=0";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=asignarTarjeta";

	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 520 + 
	                 ",height=" + 250; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
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
</script>
</html>
