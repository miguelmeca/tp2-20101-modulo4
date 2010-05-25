<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
String variable = request.getParameter("mostrar");
boolean mostrarDiv=true;
if(variable !=null && variable.equals("0")){
	mostrarDiv = false;
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Modificar Producto</title>

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

	$("#vntMensajeNoTipo").dialog({
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
	$('.btnExitMensajeNoTipo').click(
		function()
		{
				$("#vntMensajeNoTipo").dialog( 'close' );
						
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
$('.btnAceptar').click(
		function()
		{
			var nombre = document.getElementById("txtNombre").value;
			var descripcion = document.getElementById("txtArea").value;
			var seleccion = document.getElementById("selTipo").value;
			
				if (seleccion == "0"){
					$("#vntMensajeNoTipo").dialog( 'open' );
					return;
				}
				if (""==nombre){
					$("#vntMensajeNotarjeta").dialog( 'open' );
					return;
				} 
				if (""==descripcion){
					$("#vntMensajeNotarjeta").dialog( 'open' );
					return;
				} 
			frmNuevo.action="listadoProductos.jsp";
			frmNuevo.submit();
		});		
						  
	$('.btnCancelar').click(
		function()
			{
			frmNuevo.action="listadoProductos.jsp";
			frmNuevo.submit();

		});						  
	
	});

</script>
<div class="demos-nav" style="width:100%" align="center">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>
<form action="" method="get" name="frmNuevo">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Modificar Producto </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="70%" border="1">
          <tr>
            <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" style="width: 702px" ></td>
            </tr>
          <tr >
            <td align="center" style="width: 702px" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
		  <tr >
            <td align="left" style="height: 21px; width: 175px;" > C&oacute;digo: </td>
            <td align="left" style="width: 420px; height: 21px;" > XXXXXXXXX                  </td>
		  </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Tipo:(*) </td>
            <td align="left" style="height: 31px; width: 420px;" ><select id="selTipo" name="selTipo" style="width: 150px">
              <option value="0">-Seleccione-</option>
              <option value="1" selected="selected">tipo 1</option>
              <option value="2">tipo 2</option>
            </select></td>
          </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Producto: (*) </td>
            <td align="left" style="height: 31px; width: 420px;" ><input name="txtNombre" type="text" class="text  ui-corner-all" id="txtNombre" style="width:250px" value="nombre producto"  /></td>
          </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" > Descripción: (*) </td>
			<td align="left" style="height: 31px; width: 420px;" >
                <textarea class="text  ui-corner-all" name="txtArea" id="txtArea" style="width: 200px">Escribir aca una descripción.</textarea></td>
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

<!-- zona de mensajes -->
  <div id="vntMensajeNoTipo" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Falta seleccionar el Tipo de Producto.</div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br />
    <center>
		<input type="button" name="btnAceptarMensajeNoTipo" value="Aceptar" style="width:120px" class="ui-state-default btnExitMensajeNoTipo" />
	</center>
  </div>
  <div id="vntMensajeNotarjeta" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <table width="100%" >
        <tbody>
          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Falta registrar nombre o descripci&oacute;n del producto.</div></td>
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
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
