<%--
*Resumen
*Objeto                 : equ_ListadoPuntaje.jsp.
* Descripcion           : pagina para listar los productos para el puntaje.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%@page import="pe.com.upz.bean.BEquivalencia"%>
<%
String ruta = request.getContextPath(); 

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
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Asignar Puntos x Producto</td>
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
            <a href="SEquivalencia?hddOperacion=inicioAsignacion&pagina=<%=listadoProducto.getNumPagina()-1%>">Anterior</a> 
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
            		<a href="SEquivalencia?hddOperacion=inicioAsignacion&pagina=<%=i%>"><%=i%></a>
            		<%if(i+1 < listadoProducto.getNumPaginaInicialEnPaginacion() + listadoProducto.getCantidadPaginasMostradas()){ %>
            			,
            		<%} %>
            	<%}%>
            	
            <%} %>
            ] 
            <%if(listadoProducto.getNumPagina() < listadoProducto.getCantidadPaginasDeListado() ){ %>
            <a href="SEquivalencia?hddOperacion=inicioAsignacion&pagina=<%=listadoProducto.getNumPagina()+1%>">Siguiente </a>
            <%} %>
            &gt;&gt; </div></td>
          </tr>
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav" align="center"><table width="860" border="1">
          <tr>
            <td width="5%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Num</td>
            <td width="9%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Codigo</div></td>
            <td width="21%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Tipo</div></td>
            <td width="30%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Nombre Producto</div></td>
            <td width="10%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Puntaje 1 </div></td>
			<td width="10%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Puntaje 2 </div></td>
			<td width="10%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Puntaje 3 </div></td>
            <td width="5%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
            </tr>
 			<%for(int i=listadoProducto.getFirstElementPage(numPagina)-1;i<=listadoProducto.getLastElementPage(numPagina)-1;i++){%>
 			<%	BProducto producto = (BProducto) listadoProducto.getElemento(i); %> 
 			<%	BEquivalencia equivalencia = (BEquivalencia) producto.getListaPuntaje().getElemento(0); %>
          <tr >
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=(i+1)%></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getCodigo()%></div>
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getTipo().getDescripcion()%></div>
            <input type="hidden" name="hddTipo<%=producto.getCodigo()%>" value="<%=producto.getTipo().getDescripcion()%>" />
			</td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=producto.getNombre()%></div>
            <input type="hidden" name="hddNombre<%=producto.getCodigo()%>" value="<%=producto.getNombre()%>" />
            </td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4">
              <%=equivalencia.getCantidadPuntoUno()%> ptos + S/.<%=equivalencia.getMontoUno()%>
              <input type="hidden" name="hddPuntoUno<%=producto.getCodigo()%>" value="<%=equivalencia.getCantidadPuntoUno()%>" />
			  <input type="hidden" name="hddMontoUno<%=producto.getCodigo()%>" value="<%=equivalencia.getMontoUno()%>" />
            </div></td>
			<td class="ui-accordion-content"><div align="center" class="Estilo4">
              <%=equivalencia.getCantidadPuntoDos()%> ptos + S/.<%=equivalencia.getMontoDos()%>
              <input type="hidden" name="hddPuntoDos<%=producto.getCodigo()%>" value="<%=equivalencia.getCantidadPuntoDos()%>" />
			  <input type="hidden" name="hddMontoDos<%=producto.getCodigo()%>" value="<%=equivalencia.getMontoDos()%>" />
            </div></td>
			<td class="ui-accordion-content"><div align="center" class="Estilo4">
              <%=equivalencia.getCantidadPuntoTres()%> ptos + S/.<%=equivalencia.getMontoTres()%>
              <input type="hidden" name="hddPuntoTres<%=producto.getCodigo()%>" value="<%=equivalencia.getCantidadPuntoTres()%>" />
			  <input type="hidden" name="hddMontoTres<%=producto.getCodigo()%>" value="<%=equivalencia.getMontoTres()%>" />
            </div></td>
            <td class="ui-accordion-content"><input name="chkProducto" type="radio" value ="<%=producto.getCodigo()%>"  /></td>
            </tr>
            <%} %>
        </table>          
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
         
          <input type="button" name="btnPuntaje" value="Asignar Puntos" 
          onclick="javascript:agregarPuntaje()" 
          style="width:120px" class="ui-state-default" />
          <input type="button" name="btnCancelar" 
          onclick="javascript:cerrar()" 
          value="Cancelar" style="width:120px" class="ui-state-default" />
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
function cerrar(){
	frmListaProducto.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmListaProducto.submit();
}

function mostrarMensaje(){
	var resultado = "<%=mensajeMantenimiento%>";
	if(resultado == "nuevoOK"){
		alert("Se agregó un nuevo producto.");
	}else if (resultado== "actualizadoOK"){
		alert("Se actualizó el producto.");
	}
}

function agregarPuntaje(){
	frmListaProducto.hddOperacion.value="agregarEquivalencia";
	frmListaProducto.action="SEquivalencia";
	frmListaProducto.submit();
}
</script>
</html>
