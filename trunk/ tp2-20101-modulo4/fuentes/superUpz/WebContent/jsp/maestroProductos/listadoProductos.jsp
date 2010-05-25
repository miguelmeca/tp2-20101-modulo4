<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
String variable = request.getParameter("mostrar");
String mantenimiento = request.getParameter("mantenimiento");
boolean mostrarDiv=true;
boolean mostrarMantenimineto=true;
if(variable !=null && variable.equals("0")){
	mostrarDiv = false;
}
if(mantenimiento !=null && mantenimiento.equals("0")){
	mostrarMantenimineto = false;
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Mantener Producto</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

<!--SUB MENU -->
<link rel="stylesheet" type="text/css" href="submenu/jqueryslidemenu.css" />
<script type="text/javascript" src="JQUERYMIN/jquery.min.js"></script> 
<script type="text/javascript" src="submenu/jqueryslidemenu.js"></script> 





<!--**JQUERY**-->
<!--** ACTIVAR CUANDO SEA LOCAL**-->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js" ></script>
 
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />
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
-->
</style>
</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<script>

jQuery(document).ready(function(){
	
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
	$('.btnOpenMensajeConfirma').click(
		function()
		{
				$("#formMensajeConfirma").dialog( 'open' );
						
		});					
		
		$('.btnNuevo').click(
		function()
		{
			//var url = "mantenerCliente.jsp?mostrar=0"
			//var popupWin = window.open(url,"Detalle","scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no,directories=no,resizable=yes, width=900, height=450, scrolling='YES',left=50, top=100, screenX=50, screenY=100");
			frmListaProducto.action="mantenerProducto.jsp";
			frmListaProducto.submit();

		});					
		
	$('.btnEditar').click(
		function()
		{
			var id = document.getElementById("id").value;
				if (""==id){
					$("#formMensaje").dialog( 'open' );
				} else {
					frmListaProducto.action="modificarProducto.jsp";
					frmListaProducto.submit();
				}
		});					

	$('.btnEliminar').click(
		function()
		{
			var id = document.getElementById("id").value;
				if (""==id){
					$("#formMensaje").dialog( 'open' );
				} else {
					$("#formMensajeConfirma").dialog( 'open' );
				}
						
		});										  
	$('.btnCancelar').click(
		function()
			{
			<% if (mostrarMantenimineto){ %>
				frmListaProducto.action="../comun/cuerpo.jsp";
				frmListaProducto.submit();
			<%}else{%>
				window.close();
			<%}%>
		});		

	});
	
	function obtenerId(id){
		document.getElementById("id").value = id;
	}
</script>
<div class="demos-nav" style="width:100%" align = "center">
<form action="" method="get" name="frmListaProducto">
<% if(mostrarDiv){%>
<table width="1010px" border="0" >
<%}else{ %>
<table width="900px" border="0" >
<%} %>
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Buscar Producto </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      <tr class="ui-widget">
        <td><table width="100%" border="0" class="ui-priority-primary"  >
			<td width="14%" class="ui-accordion-content" ><div align="center" class="demo-config-on Estilo1">
              <div align="left">TIPO DE B&Uacute;SQUEDA </div>
            </div></td>
            <td width="24%" class="ui-accordion-content" ><div align="center" class="Estilo1">
                <div align="left">
                  <select name="select" class="ui-icon-document" style="width:200PX;heigth:10px"  >
                      <option value="01">tipo</option>
                      <option value="02">descripción</option>
					  <option value="02">Código</option>
                        </select>
                </div>
            </div></td>
            <td width="62%" class="ui-accordion-content"><table width="100%" border="0">
              <tr>
                <td height="38" class="Estilo1"><div align="right"> </div>                  
                  <div align="right"> DESCRIPCION: (*) </div></td>
                <td class="Estilo1"><div align="center">
                  <input type="text" name="textfield2" style="width:150px" class="text  ui-corner-all" />                
                </div>
                <div align="right"> </div>                </td>
                <td width="19%" class="Estilo1"><input name="btnBuscar" type="button"   id="btnBuscar" style="width:120px" class="ui-state-default" value="Buscar"/></td>
              </tr>
            </table></td>
            </tr>

        </table></td>
      </tr>
          <tr>
            <td><div align="right" class="ui-state-default" > total de items : 100 &lt;&lt;Anterior [ <span class="ui-state-active">1</span>, 2, 3, 4, 5] Siguiente &gt;&gt; </div></td>
          </tr>
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav" align="center"><table width="764" border="1">
          <tr>
            <td width="6%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
            <td width="16%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Codigo</div></td>
            <td width="25%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Tipo</div></td>
            <td width="34%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Descripci&oacute;n</div></td>
            <td width="19%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Stock unidades </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('1')" /></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">tipo 1 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 1</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 100 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('2')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 2 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 2</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 400</div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('3')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 3 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 3</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 300</div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('4')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><span class="Estilo4">12345678</span></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 4 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 4</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 400 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('5')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><span class="Estilo4">12345678</span></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 5 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 5</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 500 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('6')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 6 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 6</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 600 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('7')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 7 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 7</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 700 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('14')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 8 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 8</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 800 </div>
            </div></td>
            </tr>
          <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('15')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 9 </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 9</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 90 </div>
            </div></td>
            </tr>
           <tr >
            <td class="ui-accordion-content"><input name="radiobutton" type="radio" value="radiobutton" onclick="obtenerId('15')"/></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">12345678</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> tipo 10</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"> descripci&oacute;n 10</div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <div align="center"> 100 </div>
            </div></td>
            </tr>   
        </table>          
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <% if (mostrarMantenimineto){ %>
          <input type="button" name="btnNuevo" value="Nuevo" style="width:120px" class="ui-state-default btnNuevo"  />
		  <input type="button" name="btnEditar" value="Editar" style="width:120px" class="ui-state-default btnEditar"  />
		  <input type="button" name="btnEliminar" value="Eliminar" style="width:120px" class="ui-state-default btnEliminar"  />
          <%}else{ %>
		  <input type="button" name="btnAceptar" value="Aceptar" style="width:120px" class="ui-state-default btnCancelar"  />          
          <%}%>
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

  <div id="formMensaje" title=" Mensaje del Sistema">
      <p id="validateTips">&nbsp;</p>
      <input type="hidden" name="formMensaje" id="formMensaje" value="insertar">
      <table width="100%" >
        <tbody>

          <tr>
            <td class="ui-state-error"><div align="center" class="ui-state-error-text">Debe seleccionar un registro para efectuar esta operaci&oacute;n </div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <div align="center"><br />
    	<input type="button" name="Submit222222" value="Aceptar" style="width:120px" class="ui-state-default btnExitMensaje" />
  	  </div>
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
