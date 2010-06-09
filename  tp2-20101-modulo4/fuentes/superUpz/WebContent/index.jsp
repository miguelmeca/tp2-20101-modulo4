<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
*Resumen
*Objeto                 :  index.jsp.
* Descripcion          : Pagina inicial de logeo la sistema.
* Fecha de Creacion    : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>

<% 
String mensaje = request.getParameter("mensaje");

%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Ingreso al sistema</title>

<link type="text/css" href="css/demos.css" rel="stylesheet" />
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />

<!--**JQUERY**-->
<!--** ACTIVAR CUANDO SEA LOCAL**-->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js" ></script>
 
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />

</head>

<body >
<div id="cabecera" align="center">
<table width="1010px" border="0"
	class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
	<tr>
		<td width="21%"> <img src="images/logotipo.jpg"
			width="150" height="80" ></td>
		<td align="center"><span class="style6 style2">SUPERMERCADOS
		UPZ</span></td>
	</tr>
</table>
</div>

<div align="center">
<form name="frmLogin" id="frmLogin" method="get" action="">
<table width="1010px" border="0">
	
	<tr>
		<td height="327" valign="top">
		<table width="100%" border="0">

			<tr>
				<td height="171" valign="top" class="ui-tabs-nav">
				<center>
				<table width="600" height="386" border="0">
					<tr>
					  <td align="center"><a onClick='javascript:abrirVentana();' 
                                        style='cursor:hand' ><img src="images/fondo.jpg" border="0" /></a>
					  
					  </td>
					</tr>
				</table>
				</center>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>

<script language="JavaScript">
    function abrirVentana() 
    {
          var sAncho=screen.width, sAlto=screen.height-100;
          var sTop=0;    
          var sLeft=(screen.width-sAncho)/2;    
          var sFeatures="status=yes, left=" + sLeft + ",top=" + sTop + ", width=" + sAncho + ", height=" + sAlto + ", resizable=no, toolbar=no,menubar=no,scrollbars=yes";
              open("SLogeo?hddOperacion=inicioLogeo","VentanaPrincipal",sFeatures);
    }
</script>
</html>
