<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Modificar Cliente</title>

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
.style4 {color: #FF0000}
-->
</style>

</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<script>

jQuery(document).ready(function(){
	
		
	$("#formEdicionBien").dialog({
						  bgiframe: true
						  , height: 300
						  , width: 600
						  , modal: false
						  //, draggable: false
						  , closeOnEscape: false
						  , resizable: false
						  //,hide: 'fold'
						  ,autoOpen: false 
					  });		
					  
	$("#formMensaje").dialog({
						  bgiframe: true
						  , height: 200
						  , width: 500
						  , modal: false
						  //, draggable: false
						  , closeOnEscape: false
						  , resizable: false
						  //,hide: 'fold'
						  ,autoOpen: false 
					  });							  
		
	$("#formMensajeConfirma").dialog({
						  bgiframe: true
						  , height: 200
						  , width: 500
						  , modal: false
						  //, draggable: false
						  , closeOnEscape: false
						  , resizable: false
						  //,hide: 'fold'
						  ,autoOpen: false 
					  });			
		
	$('.btnExit').click(
		function()
		{
				$("#formNuevoBien").dialog( 'close' );
						
		});
					  
	$('.btnOpen').click(
		function()
		{
				$("#formNuevoBien").dialog( 'open' );
		});		
		
	$('.btnExitEdicion').click(
		function()
		{
				$("#formEdicionBien").dialog( 'close' );
						
		});
					  
	$('.btnOpenEdicion').click(
		function()
		{
			var id = document.getElementById("id").value;
				if (""==id){
					$("#formMensaje").dialog( 'open' );
				} else {
					$("#formEdicionBien").dialog( 'open' );
				}
		});		
		
		
		
	$('.btnOpenMensaje').click(
		function()
		{
				$("#formMensaje").dialog( 'open' );
						
		});				
	$('.btnExitMensaje').click(
		function()
		{
				$("#formMensaje").dialog( 'close' );
						
		});		
		
	$('.btnExitMensajeConfirma').click(
		function()
		{
				$("#formMensajeConfirma").dialog( 'close' );
						
		});	
	$('.btnValidadr').click(
		function()
		{
			document.getElementById("mensajeMostrar").style.display="block";
			frmNuevo.txtApellidoPaterno.value="apellido 1";
			frmNuevo.txtApellidoMaterno.value="apellido 2";
			frmNuevo.txtNombre.value="nombre";
		});					
	$('.btnAsignar').click(
		function()
		{
			var url = "asignarTarjeta.jsp?mostrar=0"
			var popupWin = window.open(url,"asignar","scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no,directories=no,resizable=yes, width=550, height=250, scrolling='YES',left=50, top=100, screenX=50, screenY=100");

		});
	$('.btnAceptar').click(
		function()
		{
			frmNuevo.action="listadoClienteAdicional.jsp";
			frmNuevo.submit();

		});										  
	$('.btnCancelar').click(
		function()
			{
					frmNuevo.action="listadoClienteAdicional.jsp";
					frmNuevo.submit();
		});		

	});
</script>
<div class="demos-nav" style="width:100%" align="center">
<form name="frmNuevo" action="" method="get">
<table width="900px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Modificar Cliente Adicional </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="80%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Datos del Cliente Adicional </div></td>
            </tr>
          <tr >
            <td align="center" ><table width="729" border="0">
              <tr>
                <td width="164"><div align="left">Apellido paterno: (*)</div></td>
                <td width="150"><div align="left">
                  <input name="txtApellidoPaterno" type="text" class="text  ui-corner-all" id="txtApellidoPaterno" style="width:150px" value="ape paterno" />
                </div></td>
                <td width="55">&nbsp;</td>
                <td width="140"><div align="left">Apellido materno: (*) </div></td>
                <td width="186"><div align="left">
                  <input name="txtApellidoMaterno" type="text" class="text  ui-corner-all" id="txtApellidoMaterno" style="width:150px" value="ape materno" />
                </div></td>
              </tr>
              <tr>
                <td><div align="left">Nombres: (*) </div></td>
                <td><div align="left">
                  <input name="txtNombre" type="text" class="text  ui-corner-all" id="txtNombre" style="width:150px" value="nombres" />
                </div></td>
                <td>&nbsp;</td>
                <td><div align="left">Departamento: (*)</div></td>
                <td><div align="left">
                  <select id="select4" name="selMonto" style="width: 79px">
                    <option>departamento 1</option>
                  </select>
                </div></td>
              </tr>
              <tr>
                <td><div align="left">e-mail:</div></td>
                <td><input name="txtNumero8" type="text" class="text  ui-corner-all" id="txtNumero82" style="width:150px" value="correo@coreo.com" /></td>
                <td>&nbsp;</td>
                <td><div align="left">Provincia: (*)</div></td>
                <td><div align="left">
                  <select id="select" name="select" style="width: 79px">
                    <option>provincia 1</option>
                    <option>provincia 2</option>
                  </select>
                </div></td>
              </tr>
              <tr>
                <td><div align="left">Tel&eacute;fono casa: </div></td>
                <td><input name="txtNumero7" type="text" class="text  ui-corner-all" id="txtNumero7" style="width:150px" value="999-9999" /></td>
                <td>&nbsp;</td>
                <td><div align="left">Distrito: (*)</div></td>
                <td><div align="left">
                  <select id="select2" name="select3" style="width: 79px">
                    <option>distrto 1</option>
                    <option>distrto 2</option>
                  </select>
                </div></td>
              </tr>
              <tr>
                <td><div align="left">Tel&eacute;fono celular:</div></td>
                <td><div align="left">
                  <input name="txtNumero6" type="text" class="text  ui-corner-all" id="txtNumero6" style="width:150px" value="999999999" />
                </div></td>
                <td>&nbsp;</td>
                <td><div align="left">Direcci&oacute;n: (*) </div></td>
                <td><div align="left">
                  <input name="txtNumero5" type="text" class="text  ui-corner-all" id="txtNumero5" style="width:150px" value="direccion" />
                </div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
			</td>
            </tr>
        </table>        
		</center>  
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <input type="button" name="btnAceptar" value="Aceptar" style="width:120px" class="ui-state-default btnAceptar" />
          <input type="button" name="btnCancelar" value="Cancelar" style="width:120px" class="ui-state-default btnCancelar"  />
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


<!-- zona de mensajes -->



</div>

<input type="hidden" name="id" id="id">
</body>
</html>
