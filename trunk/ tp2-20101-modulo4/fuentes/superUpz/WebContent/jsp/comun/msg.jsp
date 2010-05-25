<%--
*Resumen
*Objeto                 : msgjsp.
* Descripcion           : pagina para mostrar mensajes de la aplicacion.
* Fecha de Creacion     : 22/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%
	String ruta = request.getContextPath();
	String mensaje = (String) request.getAttribute("mensajeSistema");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Generar Orden de Pedido</title>
<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />
<title>Mensaje del sistema</title>
</head>
<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form action="" method="get" name="frmMensaje">
<table width="1010px" height="245" border="0">
	<tr>
		<td height="37" align="center"
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Mensaje</td>
	</tr>
	<tr>
		<td height="174" align="center">
		<div class="ui-state-default"
			style="width: 780px; height: 100px; padding: 10px">
		<div style="padding: 20px;"><%=mensaje %></div>
		</div>
		</td>
	</tr>
	<tr>
		<td height="26" class="ui-widget-header">
		<div align="right"><input type="button" name="btnAceptar"
			value="Aceptar" onclick="javascript:aceptar()" style="width: 120px"
			class="ui-state-default" /></div>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script language="JavaScript">
function aceptar(){
	frmMensaje.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmMensaje.submit();
}
</script >
</html>