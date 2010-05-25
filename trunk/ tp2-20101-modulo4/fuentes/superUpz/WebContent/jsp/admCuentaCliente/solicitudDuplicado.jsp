<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Registrar Solicitud de Duplicado</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

<!--SUB MENU -->
<link rel="stylesheet" type="text/css" href="submenu/jqueryslidemenu.css" />
<script type="text/javascript" src="JQUERYMIN/jquery.min.js"></script> 
<script type="text/javascript" src="submenu/jqueryslidemenu.js"></script> 

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
	$('.btnGenerar').click(
		function()
		{
			frmDuplicado.txtNumero.value = "00002";
		});		

	$('.btnCancelar').click(
		function()
		{
			frmDuplicado.action="../comun/cuerpo.jsp";
			frmDuplicado.submit();
		});	
    });
</script>
<div class="demos-nav" style="width:100%" align="center" >

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>

<form name = "frmDuplicado" id="frmDuplicado" method="post" action="">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Solicitud de duplicado</td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="40%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">
                Registro de solicitud</div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
		  <tr >
            <td width="49%" align="left" > Fecha y Hora Actual:</td>
            <td width="51%" align="left" ><input type="text" name="txtFecha" id="txtFecha" value="dd/mm/yyy hh24:mi:ss" style="width:150px" class="text  ui-corner-all" /></td>
          </tr>
          <tr >
            <td align="left" >&nbsp;</td>
            <td align="left" >&nbsp;</td>
          </tr>
		  <tr >
            <td align="left" > N&uacute;mero de Solicitud:</td>
			<td align="left" ><input type="text" name="txtNumero" id="txtNumero" value="00001" style="width:150px" class="text  ui-corner-all" /></td>
          </tr>
          <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3"> </span></td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>  &nbsp;</td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <input type="button" name="btnGenerar" value="Generar" style="width:120px" class="ui-state-default btnGenerar"  />
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
<!--*****************FORMULARIO PARA EDICION ********************--> 

<!-- zona de mensajes -->

</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
