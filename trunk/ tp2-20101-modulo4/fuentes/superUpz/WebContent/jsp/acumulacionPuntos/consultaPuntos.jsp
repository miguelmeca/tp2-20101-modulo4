<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Consulta de puntos</title>

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
								
	$('#switcher').themeswitcher({loadTheme :'redmond', buttonPreText: 'Tema: '});
	//$('#switcher').themeswitcher({loadTheme :'Redmond', initialText: 'Seleccione Tema: ', buttonPreText: 'Tema: '});
	
	$(".datepicker").datepicker();
	
	
	$("#formNuevoBien").dialog({
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
					  
	$("#vntMensajeNotarjeta").dialog({
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

	$("#vntMensajeNoDni").dialog({
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
//funciones del boton aceptar					  
	$('.btnAceptar').click(
		function()
		{
			var numero = document.getElementById("txtNumero").value;
				if (""==numero){
					$("#vntMensajeNotarjeta").dialog( 'open' );
					return;
				} 
				frmPuntos.action="totalPuntos.jsp";
				frmPuntos.submit();
		});		

	$('.btnCancelar').click(
			function()
			{
				frmPuntos.action="../comun/cuerpo.jsp";
				frmPuntos.submit();
			});		
		
	$('.btnOpenMensaje').click(
		function()
		{
				$("#vntMensajeNotarjeta").dialog( 'open' );
						
		});				
	$('.btnExitMensajeNoTarjeta').click(
		function()
		{
				$("#vntMensajeNotarjeta").dialog( 'close' );
						
		});		
	$('.btnExitMensajeNoDni').click(
		function()
		{
				$("#vntMensajeNoDni").dialog( 'close' );
						
		});		
		
	$('.btnExitMensajeConfirma').click(
		function()
		{
				$("#formMensajeConfirma").dialog( 'close' );
						
		});	
	$('.btnOpenMensajeConfirma').click(
		function()
		{
				$("#formMensajeConfirma").dialog( 'open' );
						
		});					
		
	$('.btnEliminar').click(
		function()
		{
			var id = document.getElementById("id").value;
				if (""==id){
					$("#vntMensajeNotarjeta").dialog( 'open' );
				} else {
					$("#formMensajeConfirma").dialog( 'open' );
				}
						
		});										  
	
	});
	
		  
	

	

	function obtenerId(id){
		document.getElementById("id").value = id;
	}

</script>
<div class="demos-nav" style="width:100%" align="center">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>
<form name = "frmPuntos" id="frmPuntos" method="post" action="">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Consulta de Puntos </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="40%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Identificaci&oacute;n del Cliente</div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
		  <tr >
            <td width="49%" align="left" > N&uacute;mero de tarjeta: (*) </td>
            <td width="51%" align="left" ><input type="text" name="txtNumero" id="txtNumero" style="width:150px" class="text  ui-corner-all" /></td>
          </tr>
          <tr >
            <td align="left" >&nbsp;</td>
            <td align="left" >&nbsp;</td>
          </tr>
          <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Debe realizar el ingreso del n&uacute;mero de la tarjeta del cliente. </span></td>
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
<!-- zona de mensajes -->
  <div id="vntMensajeNotarjeta" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Ingrese el n&uacute;mero de la tarjeta.</div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br />
    <center>
		<input type="button" name="btnAceptarMensajeNotarjeta" value="Aceptar" style="width:120px" class="ui-state-default btnExitMensajeNoTarjeta" />
	</center>
  </div>
  <div id="vntMensajeNoDni" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <!--<input type="hidden" name="formMensaje" id="formMensaje" value="insertar">-->
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Ingrese el n&uacute;mero del DNI del cliente.</div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br />
    <center>
		<input type="button" name="btnAceptarMensajeNoDni" value="Aceptar" style="width:120px" class="ui-state-default btnExitMensajeNoDni" />
	</center>
  </div>
  <div id="formMensajeConfirma" title="Mensaje de confirmación del Sistema">
    <p id="validateTips">&nbsp;</p>
    <input type="hidden" name="formMensaje2" id="div" value="insertar" />
    <table width="100%" >
      <tbody>
        <tr>
          <td class="ui-state-error"><div align="center" class="ui-state-error-text">&iquest;Esta seguro de realizar esta operación ? </div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </tbody>
    </table>
    <div align="center"><br />
      <input type="button" name="Submit2222222" value="Si" style="width:120px" class="ui-state-default btnExitMensajeConfirma" />
      <input type="button" name="Submit22222222" value="No" style="width:120px" class="ui-state-default btnExitMensajeConfirma" />
    </div>
  </div>
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
