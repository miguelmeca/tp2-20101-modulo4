<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Consultar Puntos x Producto</title>

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
	$('.btnBuscar').click(
			function()
			{
		
			var url = "../maestroProductos/listadoProductos.jsp?mostrar=0&mantenimiento=0"
			var popupWin = window.open(url,"Detalle","scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no,directories=no,resizable=yes, width=900, height=500, scrolling='YES',left=50, top=100, screenX=50, screenY=100");
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
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Consultar Puntos x Producto</td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="40%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Equivalecia de puntos </div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="4" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td width="23%" align="left" >Producto</td>
            <td width="49%" align="left" ><input type="text" name="txtDescipcionProducto" id="txtDescipcionProducto" style="width:250px" class="text  ui-corner-all" /></td>
            <td width="3%" align="left" >&nbsp;</td>
            <td width="25%" align="left" ><input type="button" name="btnBuscar" value="Buscar" style="width:120px" class="ui-state-default btnBuscar" /></td>
          </tr>
		  <tr >
            <td colspan="4" align="left" >&nbsp;</td>
            </tr>
          <tr align="center" >
            <td colspan="4" ><table width="459" border="0">
              <tr>
                <td width="117"><div align="left">1ra equivalencia: </div></td>
                <td width="99"><div align="left">Monto S/. :</div></td>
                <td width="73"><div align="left">25
                </div></td>
                <td width="81"><div align="left">Puntos:</div></td>
                <td width="67"><div align="left">740
                </div></td>
              </tr>
              <tr>
                <td align="left"><div align="left">2da equivalencia</div></td>
                <td align="left" ><div align="left">Monto S/. : </div></td>
                <td><div align="left">50
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">500
                </div></td>
              </tr>
              <tr>
                <td><div align="left">3ra equivalencia</div></td>
                <td><div align="left">Monto S/. : </div></td>
                <td><div align="left">100
                </div></td>
                <td><div align="left">Puntos:</div></td>
                <td><div align="left">25
                </div></td>
              </tr>
            </table></td>
            </tr>
          <tr >
            <td colspan="4" align="left" >&nbsp;</td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>  
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
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
