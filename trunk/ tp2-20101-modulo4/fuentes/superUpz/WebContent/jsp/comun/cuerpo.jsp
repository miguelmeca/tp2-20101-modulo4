<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
*Resumen
*Objeto                 : cuerpo.jsp.
* Descripcion           : pagina del cuerpo del sistema.
* Fecha de Creacion     : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>

<html>
<%
String ruta = request.getContextPath(); 
%>
<head>
<title>UPZ- Fidelización</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
<jsp:include page="cabecera.jsp"></jsp:include>
<div align="center">
	<div class="demos-nav" style="width:100%">
	
	<table width="1010px" border="0" >
	<tr>
		<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">&nbsp;  </td>
	</tr>
	  <tr>
		<td height="327" valign="top" ><table width="100%" border="0">
		  
		  <tr>
			<td height="171" valign="top" class="ui-tabs-nav">
			<center>
				<table width="600" height="386" border="0" background="<%=ruta%>/images/fondo.jpg">
				  <tr>
					<td align="center">&nbsp;</td>
				  </tr>
				</table>
				</center>   
			 </td>
		  </tr>
		  
		  <tr>
			<td class="ui-widget-header">&nbsp;</td>
		  </tr>
		</table></td>
	  </tr>
	</table>
	</div>
</div>
</body>
</html>
