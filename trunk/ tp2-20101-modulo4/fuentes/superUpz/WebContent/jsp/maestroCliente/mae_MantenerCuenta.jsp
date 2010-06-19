<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
String ruta = request.getContextPath(); 
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
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Mantenimiento de Cuenta </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="70%" border="1">
          <tr>
            <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Datos de la cuenta </td>
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
            <td align="left" style="width: 420px; height: 21px;" ><input name="txtCliente" type="text" class="text  ui-corner-all" id="txtCliente" style="width:250px" readonly="true"  />                  
              <input type="button" name="btnBuscarCliente" value="Buscar" 
			  onclick="javascript:mostrarClientes()" 
			  style="width:70px" class="ui-state-default"  /></td></tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" >&nbsp; </td>
			<td align="left" style="height: 26px; width: 420px;" >&nbsp;                </td>
		  </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Asignar tarjeta  (*)  </td>
			<td align="left" style="height: 31px; width: 420px;" ><input type="text" name="txtNumeroTarjeta" id="txtNumeroTarjeta" style="width:150px" class="text  ui-corner-all" />&nbsp;&nbsp;<input type="button" name="btnAsignar" id="btnAsignar" value="Asignar" style="width:120px" class="ui-state-default btnAsignar" /></td>
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
          <input type="button" name="btnAceptar" value="Aceptar" style="width:120px" class="ui-state-default btnAceptar"  />
          <input type="button" name="btnCancelar" value="Cancelar" style="width:120px" class="ui-state-default btnCancelar" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</div>
</body>
<script language="JavaScript">
function mostrarClientes(){


	frmCanje.target="VENTANA";

	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=buscarCliente&mantenimiento=0";
	alert("aca ta");
	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 900 + 
	                 ",height=" + 700; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
}
</script>
</html>
