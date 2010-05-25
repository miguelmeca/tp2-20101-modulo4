<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Canjear Puntos</title>

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
<script>

jQuery(document).ready(function(){
		

//funciones del boton aceptar					  
	
	$('.btnBuscarCliente').click(
			function()
				{
					var url = "../maestroCliente/listadoClientes.jsp?mostrar=0&mantenimiento=0"
					var popupWin = window.open(url,"Detalle","scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no,directories=no,resizable=yes, width=900, height=500, scrolling='YES',left=50, top=100, screenX=50, screenY=100");
					frmCanje.txtCliente.value = "Cliente seleccionado";
				});	
	$('.btnBuscarProducto').click(
		function()
			{
				var url = "../maestroProductos/listadoProductos.jsp?mostrar=0&mantenimiento=0"
				var popupWin = window.open(url,"Detalle","scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no,directories=no,resizable=yes, width=900, height=500, scrolling='YES',left=50, top=100, screenX=50, screenY=100");
				frmCanje.txtProducto.value = "Producto seleccionado";
			});	
	$('.btnCancelar').click(
		function()
			{
				frmCanje.action="../comun/cuerpo.jsp";
				frmCanje.submit();
			});			

});
</script>
<div class="demos-nav" style="width:100%" align="center">
<form name="frmCanje" id="frmCanje" method="post" action="">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Registro de Canje&nbsp;</td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="70%" border="1">
          <tr>
            <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Datos para el  Canje</td>
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
            <td align="left" style="width: 420px; height: 21px;" ><input type="text" name="txtCliente" id="txtCliente" style="width:250px" class="text  ui-corner-all" readonly="readOnly" />                  
              <input type="button" name="btnBuscarCliente" value="Buscar" style="width:70px" class="ui-state-default btnBuscarCliente"  /></td></tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" > Producto: (*) </td>
			<td align="left" style="height: 26px; width: 420px;" ><input type="text" name="txtProducto" id="txtProducto" style="width:250px" class="text  ui-corner-all" readonly="readOnly" />
                <input type="button" name="btnBuscarProducto" value="Buscar" style="width:70px" class="ui-state-default btnBuscarProducto"  /></td>
          </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" > Descripción:</td>
			<td align="left" style="height: 31px; width: 420px;" >
                <textarea class="text  ui-corner-all" name="textarea" style="width: 200px"></textarea></td>
          </tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" > 
                Stock Actual:</td>
			<td align="left" style="height: 26px; width: 420px;" ><input name="txtDni" type="text" class="text  ui-corner-all" id="Text2" style="width:54px" value="100" readonly="readOnly" /></td>
          </tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" > Monto:(*)
            </td>
			<td align="left" style="height: 26px; width: 420px;" >
                <select id="selMonto" name="selMonto" style="width: 79px">
                  <option value="0">-Seleccione-</option>
                  <option value="1">monto 1</option>
                  <option value="2">monto 2</option>
                  <option value="3">monto3</option>
                </select>
                Soles</td>
          </tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" > puntos</td>
			<td align="left" style="height: 26px; width: 420px;" ><input type="text" name="txtDni" id="Text4" style="width:56px" class="text  ui-corner-all" readonly="readOnly" /></td>
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
<!--*****************FORMULARIO PARA REGISTRAR********************--> 

<!--*****************FORMULARIO PARA EDICION ********************--> 
 
<!-- zona de mensajes -->

</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
