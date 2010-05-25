<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Total de puntos</title>

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
	$('.btnSalir').click(
		function()
		{
			frmPuntos.action="../comun/cuerpo.jsp";
			frmPuntos.submit(); 
		});		
	});

</script>
<div class="demos-nav" style="width:100%" align="center" >

<script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>

<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Consulta de Puntos </td>
</tr>
<tr>
    <td >
	<center>
		<br>
		<form name = "frmPuntos" id="frmPuntos" method="post" action="">
		<table width="700" border="0">
          <tr>
            <td><div align="right">30/03/2010 13:54</div></td>
          </tr>
        </table>
		<br>
		<table width="700" border="1">
          <tr>
            <td width="65%" align="center" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
				Datos del Cliente 
			</td>
            <td width="35%" align="center" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
				Datos de la Cuenta 
			</td>
          </tr>
          <tr>
            <td><table width="440" border="0">
              <tr>
                <td width="103">
                  <div align="right"> N&deg; de Tarjeta :</div></td>
                <td width="17">&nbsp;</td>
                <td width="306"> <div align="left">7027661000040302594</div></td>
              </tr>
              <tr>
                <td>
                  <div align="right"> N&deg; de Cuenta :</div></td>
                <td>&nbsp;</td>
                <td><div align="left">xxxx-xxxx-xxx-xxx</div></td>
              </tr>
              <tr>
                <td>
                  <div align="right">Nombre :</div></td>
                <td>&nbsp;</td>
                <td> <div align="left">AZABACHE CARRILLO, JUAN GONZALO </div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
            <td><table width="217" border="0">
              <tr>
                <td width="123"> <div align="right">Saldo Vigente :</div></td>
                <td width="17">&nbsp;</td>
                <td width="50"><div align="left">100</div></td>
              </tr>
              <tr>
                <td> <div align="right">Puntaje usado : </div></td>
                <td>&nbsp;</td>
                <td><div align="left">100</div></td>
              </tr>
              <tr>
                <td> <div align="right">Puntaje a vencer : </div></td>
                <td>&nbsp;</td>
                <td><div align="left">100</div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
          </tr>
        </table>
	    <p>&nbsp;</p>
	</center>	</td>
</tr>
<tr>
    <td class="ui-widget-header">
		<div align="right">
          <input type="button" name="btnSalir" value="Salir" style="width:120px" class="ui-state-default btnSalir"  />
       </div></td>
</tr>
</table>
</form>
<!--*****************FORMULARIO PARA EDICION ********************--> 
 
<!-- zona de mensajes -->


</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
