<%
String ruta = request.getContextPath(); 
String variable = request.getParameter("mostrar");
boolean mostrarDiv=true;
if(variable !=null && variable.equals("0")){
	mostrarDiv = false;
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Asignar Tarjeta</title>

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
<div class="demos-nav" style="width:100%" align="center">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>
<form name="frmTarjeta" >
<% if(mostrarDiv){%>
<table width="1010px" border="0" >
<%}else{ %>
<table width="500px" border="0" >
<%} %>
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Registrar N&uacute;mero de Tarjeta </td>
</tr>
  <tr>
    <td height="196" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="139" valign="top" class="ui-tabs-nav">
		<center>
			<table width="68%" border="1">
          <tr>
            <td width="100%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Asignar Tarjeta </div></td>
            </tr>
          <tr >
            <td align="center" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
		  <tr >
            <td width="49%" align="left" > N&uacute;mero de Tarjeta: (*) </td>
            <td width="51%" align="left" ><input type="text" name="txtNumeroTarjeta" id="txtNumeroTarjeta" style="width:150px" class="text  ui-corner-all" /></td>
          </tr>
          <tr >
            <td align="left" >&nbsp;</td>
            <td align="left" >&nbsp;</td>
          </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Debe realizar el ingreso de la tarjeta.</span></td>
            </tr>
        </table>			</td>
            </tr>
        </table>        
		</center>        </td>
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
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
