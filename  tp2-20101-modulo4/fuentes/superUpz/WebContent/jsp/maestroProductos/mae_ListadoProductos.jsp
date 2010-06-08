<%--
*Resumen
*Objeto                 : mae_ListadoProductos.jsp.
* Descripcion           : pagina para listar los productos.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%
String ruta = request.getContextPath(); 
String variable = (String)request.getAttribute("mostrar");
String mantenimiento = (String)request.getAttribute("mantenimiento");
String cambiarPuntaje = (String)request.getAttribute("puntaje");
boolean mostrarDiv=true;
boolean mostrarMantenimineto=true;
boolean mostrarPuntaje=false;
if(variable !=null && variable.equals("0")){
	mostrarDiv = false;
}
if(mantenimiento !=null && mantenimiento.equals("0")){
	mostrarMantenimineto = false;
}
if(cambiarPuntaje !=null && cambiarPuntaje.equals("1")){
	mostrarPuntaje = true;
}
//Para el Nro. de Pagina.
int numPagina=0;
//Si la pagina es nula entonces debe ser 1;
numPagina=(request.getAttribute("pagina")==null?1:Integer.parseInt((String)request.getAttribute("pagina")));

Lista listadoProducto = (Lista)request.getAttribute("listadoProducto");
Lista listadoTipoProducto = (Lista)request.getAttribute("listadoTipoProducto");
if(listadoProducto ==null){
	listadoProducto = new Lista();
}
if(listadoTipoProducto ==null){
	listadoTipoProducto = new Lista();
}
//Numero de registro por páginas.
int tamanoPagina=10;
//iTamanoPagina=Integer.parseInt(String.valueOf(application.getAttribute("AppTamanoPagina")));
listadoProducto.setTamPagina(tamanoPagina);
listadoProducto.setNumPagina(numPagina);
listadoProducto.setCantidadPaginasMostradas(5);

//mensaje a mostrar
String mensajeMantenimiento = (String)request.getAttribute("mensajeMantenimiento");
if(mensajeMantenimiento == null){
	mensajeMantenimiento = "";
}

%>
<html>
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

<body onload="javascript:mostrarMensaje()">
<jsp:include page="../comun/cabecera.jsp"></jsp:include>

<div class="demos-nav" style="width:100%" align = "center">
<form action="" method="get" name="frmListaProducto"  >
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
					  <option value="02">código</option>
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
            <td><div align="right" class="ui-state-default" > total de items : <%=listadoProducto.getTamanio()%> &lt;&lt; Tolal de paginas : 
            <%if(listadoProducto.getNumPagina() > 1 ){ %>
            <a href="SMantenimiento?hddOperacion=ingresoMantenerProductos&pagina=<%=listadoProducto.getNumPagina()-1%>">Anterior</a> 
            <%} %>
            [ 
            <%for(int i=listadoProducto.getNumPaginaInicialEnPaginacion();i< listadoProducto.getNumPaginaInicialEnPaginacion() + listadoProducto.getCantidadPaginasMostradas();i++){ 
            	if(i==listadoProducto.getNumPagina()){
            		%>
            		<span class="ui-state-active">
            		<%=i %>
            		</span>,
            		<%
            	}else{%>
            		<a href="SMantenimiento?hddOperacion=ingresoMantenerProductos&pagina=<%=i%>"><%=i%></a>
            		<%if(i+1 < listadoProducto.getNumPaginaInicialEnPaginacion() + listadoProducto.getCantidadPaginasMostradas()){ %>
            			,
            		<%} %>
            	<%}%>
            	
            <%} %>
            ] 
            <%if(listadoProducto.getNumPagina() < listadoProducto.getCantidadPaginasDeListado() ){ %>
            <a href="SMantenimiento?hddOperacion=ingresoMantenerProductos&pagina=<%=listadoProducto.getNumPagina()+1%>">Siguiente </a>
            <%} %>
            &gt;&gt; </div></td>
          </tr>
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav" align="center"><table width="764" border="1">
          <tr>
            <td width="5%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Num</td>
            <td width="15%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Codigo</div></td>
            <td width="24%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Tipo</div></td>
            <td width="33%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Nombre Producto</div></td>
            <td width="18%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Descripci&oacute;n</div></td>
            <td width="5%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
            </tr>
 			<%for(int i=listadoProducto.getFirstElementPage(numPagina)-1;i<=listadoProducto.getLastElementPage(numPagina)-1;i++){%>
 			<%	BProducto producto = (BProducto) listadoProducto.getElemento(i); %> 
          <tr >
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=(i+1)%></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getCodigo()%></div>
            <input type="hidden" name="hddRutaImagen<%=producto.getCodigo()%>" value="<%=producto.getRutaImagen()%>" />
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getTipo().getDescripcion()%></div>
            <input type="hidden" name="hddCodigoTipo<%=producto.getCodigo()%>" value="<%=producto.getTipo().getCodigo()%>" />
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getNombre()%></div>
            <input type="hidden" name="hddNombre<%=producto.getCodigo()%>" value="<%=producto.getNombre()%>" />
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <%=producto.getDescripcion()%>
              <input type="hidden" name="hddDescripcion<%=producto.getCodigo()%>" value="<%=producto.getDescripcion()%>" />
            </div></td>
            <td class="ui-accordion-content"><input name="chkProducto" type="radio" value ="<%=producto.getCodigo()%>" onclick="obtenerId('1')" /></td>
            </tr>
            <%} %>
        </table>          
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <% if (mostrarMantenimineto){ %>
          <input type="button" name="btnNuevo" value="Nuevo" 
          	style="width:120px" 
          	onclick="javascript:agregarNuevo()" 
          	class="ui-state-default btnNuevo"  />
		  <input type="button" name="btnEditar" value="Editar" style="width:120px" class="ui-state-default"  />
		  <input type="button" name="btnEliminar" value="Eliminar" style="width:120px" class="ui-state-default"  />
          <%}else if (false){ %>
		  <input type="button" name="btnAceptar" value="Aceptar" style="width:120px" class="ui-state-default"  />          
          <%}else if(mostrarPuntaje){%>
          <input type="button" name="btnPuntaje" value="Asignar Puntos" 
          onclick="javascript:agregarPuntaje()" 
          style="width:120px" class="ui-state-default" />
          <%} %>
          <input type="button" name="btnCancelar" value="Cancelar" style="width:120px" class="ui-state-default" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
</form>
</div>
</body>
<script language="JavaScript">
function mostrarMensaje(){
	var resultado = "<%=mensajeMantenimiento%>";
	if(resultado == "nuevoOK"){
		alert("Se agregó un nuevo producto.");
	}else if (resultado== "actualizadoOK"){
		alert("Se actualizó el producto.");
	}
}

function agregarNuevo(){
		frmListaProducto.hddOperacion.value="ingresoNuevoProducto";
		frmListaProducto.action="SMantenimiento?hddOperacion=ingresoNuevoProducto";
		frmListaProducto.submit();
	}
function agregarPuntaje(){
	frmListaProducto.hddOperacion.value="agregarPuntaje";
	frmListaProducto.action="SMantenimiento?hddOperacion=ingresoNuevoProducto";
	frmListaProducto.submit();
}
</script>
</html>
