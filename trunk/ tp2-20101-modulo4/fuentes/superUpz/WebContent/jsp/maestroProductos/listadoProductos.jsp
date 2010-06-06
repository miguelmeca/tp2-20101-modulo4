<%--
*Resumen
*Objeto                 : listadoProductos.jsp.
* Descripcion           : pagina para liatar los productos.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
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
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
