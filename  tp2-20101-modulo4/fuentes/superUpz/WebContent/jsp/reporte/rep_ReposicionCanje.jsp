<%--
*Resumen
*Objeto                 : rep_ReposicionCanje.jsp.
* Descripcion           : Pagina para reporte de reposicion.
* Fecha de Creacion     : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>

<% 
String mensaje = request.getParameter("mensaje");

mensaje = (mensaje==null?"":mensaje);
String ruta = request.getContextPath(); 
%>


<%@page import="pe.com.upz.util.Parametros"%><html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Autorizar</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

</head>

<body >
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form name="frmLogin" id="frmLogin" method="post" action="">
<table width="500" border="1">
	<tr>
		<td width="100%"
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
		<div align="center">Reporte de Reposición</div>
		</td>
	</tr>
	<tr>
		<td align="center">
		<table border="0">
			<tr>
				<td colspan="2" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="49%" align="left">Mes:</td>
				<td width="51%" align="left"><select name="selMes" id="selMes">
				<%for(int i=0;i<Parametros.NOMBRE_MES.length;i++){ %>
					<option value="<%=i %>"><%=Parametros.NOMBRE_MES[i] %></option>
				<%} %>
			    </select></td>
			</tr>
			<tr>
				<td colspan="2" align="left">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="ui-widget-header">
		<div align="right"><input type="button" name="btnAceptar"
			value="Aceptar" style="width: 120px" OnClick="JavaScript:ingresar()"
			class="ui-state-default" /> <input type="button" name="btnCancelar"
			value="Cancelar" style="width: 120px"
			OnClick="JavaScript:cerrar()" 
			class="ui-state-default btnOpenEdicion" /></div>
		</td>
	</tr>
</table>

<input type="hidden" name="hddOperacion" id="hddOperacion"
	value="mostrarReporteCliente">
</form>
</div>
</body>
<script language="JavaScript">
	function cerrar(){
		frmLogin.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
		frmLogin.submit();
	}
	/*
	 * Ingreso al sistema.
	 */
	function ingresar(){
	
		frmLogin.action="SReporte";
		frmLogin.submit();
	}
</script>
</html>
