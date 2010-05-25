<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String ruta = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Catalogo</title>

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
-->
</style>
</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<script>

jQuery(document).ready(function(){
								
//funciones del boton aceptar					  
	$('.btnCancelar').click(
		function()
			{
				frmCatalogo.action="../comun/cuerpo.jsp";
				frmCatalogo.submit();
			});		
});
</script>
<div class="demos-nav" style="width:100%">

 <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>

<table width="1010px" border="0" align="center" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Cat&aacute;logo de Productos </td>
</tr>
<tr>
    <td >
	<center>
		<br>
		<form name="frmCatalogo" >
		<table width="700" border="0">
          <tr>
            <td><div align="right" class="ui-state-default" > total de items : 100 &lt;&lt;Anterior [ <span class="ui-state-active">1</span>, 2, 3, 4, 5] Siguiente &gt;&gt; </div></td>
          </tr>
        </table>
		<br>
		<table width="558" border="1">
          <tr>
            <td width="30%" align="center" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
				Imagen 
			</td>
            <td width="70%" align="center" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
				Datos del Producto </td>
          </tr>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3">(Nombre del producto)</td>
                </tr>
              <tr>
                <td colspan="3"> 100 ptos + 100 soles &oacute;<br> 
								200 ptos + 50 soles 
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3">(Nombre del producto)</td>
                </tr>
              <tr>
                <td colspan="3"> 100 ptos + 100 soles &oacute;<br> 
								200 ptos + 50 soles 
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3">(Nombre del producto)</td>
                </tr>
              <tr>
                <td colspan="3"> 100 ptos + 100 soles &oacute;<br> 
								200 ptos + 50 soles 
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3">(Nombre del producto)</td>
                </tr>
              <tr>
                <td colspan="3"> 100 ptos + 100 soles &oacute;<br> 
								200 ptos + 50 soles 
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3">(Nombre del producto)</td>
                </tr>
              <tr>
                <td colspan="3"> 100 ptos + 100 soles &oacute;<br> 
								200 ptos + 50 soles 
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>		 		  		 
        </table>
        </form >
	    <p>&nbsp;</p>
	</center>	</td>
</tr>
<tr>
    <td class="ui-widget-header">
		<div align="right">
          <input type="button" name="btnCancelar" value="Cancelar" style="width:120px" class="ui-state-default btnCancelar"  />
       </div></td>
</tr>
</table>

<!--*****************FORMULARIO PARA REGISTRAR********************--> 
 
<!-- zona de mensajes -->
 
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
