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
<title>Nuevo Producto</title>

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
<form action="" method="get" name="frmNuevo">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Nuevo Producto </td>
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
              <option value="1">tipo 1</option>
              <option value="2">tipo 2</option>
            </select></td>
          </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Producto: (*) </td>
            <td align="left" style="height: 31px; width: 420px;" ><input type="text" name="txtNombre" id="txtNombre" style="width:150px" class="text  ui-corner-all"  /></td>
          </tr>
		  <tr >
            <td align="left" style="height: 31px; width: 175px;" >imagen: (*) </td>
            <td align="left" style="height: 31px; width: 420px;" ><input type="file" name="txt" /></td>
          </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" > Descripción: (*) </td>
			<td align="left" style="height: 31px; width: 420px;" >
                <textarea class="text  ui-corner-all" name="txtArea" id="txtArea" style="width: 200px"></textarea></td>
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
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
