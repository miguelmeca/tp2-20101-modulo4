<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Registrar monto mínimo</title>

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

	$("#vntMensajeNoMonto").dialog({
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

	$("#vntMensajeCero").dialog({
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

	$('.btnAceptarMensajeCero').click(
		function()
		{
				$("#vntMensajeCero").dialog( 'close' );
						
		});
					  
	$('.btnAceptarMensajeNoMonto').click(
		function()
		{
				$("#vntMensajeNoMonto").dialog( 'close' );
		});		
		
//funciones del boton aceptar					  
	$('.btnAceptar').click(
			function()
			{
				var monto = frmMinimo.txtMonto.value;
				if(monto == ""){
					$("#vntMensajeNoMonto").dialog( 'open' );
					return;
				}
				if(monto <= 0){
					$("#vntMensajeCero").dialog( 'open' );
					return;
				}
				frmMinimo.action="../comun/cuerpo.jsp";
				frmMinimo.submit();				
			});		

		$('.btnCancelar').click(
			function()
			{
				frmMinimo.action="../comun/cuerpo.jsp";
				frmMinimo.submit();
			});	
	    });
</script>
<div class="demos-nav" style="width:100%" align="center">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>
<form name="frmMinimo" >
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Registrar Monto M&iacute;nimo </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="40%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Monto para la acumulaci&oacute;n </div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
		  <tr >
            <td width="49%" align="left" > Monto m&iacute;nimo en soles: (*) </td>
            <td width="51%" align="left" ><input type="text" name="txtMonto" id="txtMonto" style="width:150px" class="text  ui-corner-all" /></td>
          </tr>
          <tr >
            <td align="left" >&nbsp;</td>
            <td align="left" >&nbsp;</td>
          </tr>
          <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Debe realizar el ingreso del valor del monto </span></td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>  
         </td>
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
<!--*****************FORMULARIO PARA EDICION ********************--> 

<!-- zona de mensajes -->
  <div id="vntMensajeNoMonto" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Ingrese el valor del monto.</div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br />
    <center>
		<input type="button" name="btnAceptarMensajeNoMonto" value="Aceptar" style="width:120px" class="ui-state-default btnAceptarMensajeNoMonto" />
	</center>
  </div>
  <div id="vntMensajeCero" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <!--<input type="hidden" name="formMensaje" id="formMensaje" value="insertar">-->
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">El valor ingresado debe ser mayor a 0. </div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br />
    <center>
		<input type="button" name="btnAceptarMensajeCero" value="Aceptar" style="width:120px" class="ui-state-default btnAceptarMensajeCero" />
	</center>
  </div>
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
