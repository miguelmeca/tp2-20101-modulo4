<%--
*Resumen
*Objeto                 : equ_Catalogo.jsp.
* Descripcion           : pagina para listar los productos para el catalogo.
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
//Numero de registro por páginas
int tamanoPagina=5;
//iTamanoPagina=Integer.parseInt(String.valueOf(application.getAttribute("AppTamanoPagina")));
listadoProducto.setTamPagina(tamanoPagina);
listadoProducto.setNumPagina(numPagina);
listadoProducto.setCantidadPaginasMostradas(5);
%>
<html >

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
<div class="demos-nav" style="width:100%">
<table width="1010px" border="0" align="center" >
<tr>
  <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Consultar Puntos x Producto </td>
</tr>
<tr>
	<td>
	<table width="100%" border="0" class="ui-priority-primary"  >
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
        </table>
	</td>
</tr>
<tr>
    <td >
	<center>
		<br>
		<form name="frmCatalogo" >
		<table width="700" border="0">
          <tr>
            <tr>
            <td><div align="right" class="ui-state-default" > total de items : <%=listadoProducto.getTamanio()%> &lt;&lt; Tolal de paginas : 
            <%if(listadoProducto.getNumPagina() > 1 ){ %>
            <a href="SEquivalencia?hddOperacion=inicioCatalogo&pagina=<%=listadoProducto.getNumPagina()-1%>">Anterior</a> 
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
            		<a href="SEquivalencia?hddOperacion=inicioCatalogo&pagina=<%=i%>"><%=i%></a>
            		<%if(i+1 < listadoProducto.getNumPaginaInicialEnPaginacion() + listadoProducto.getCantidadPaginasMostradas()){ %>
            			,
            		<%} %>
            	<%}%>
            	
            <%} %>
            ] 
            <%if(listadoProducto.getNumPagina() < listadoProducto.getCantidadPaginasDeListado() ){ %>
            <a href="SEquivalencia?hddOperacion=inicioCatalogo&pagina=<%=listadoProducto.getNumPagina()+1%>">Siguiente </a>
            <%} %>
            &gt;&gt; </div></td>
          
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
          <%for(int i=listadoProducto.getFirstElementPage(numPagina)-1;i<=listadoProducto.getLastElementPage(numPagina)-1;i++){%>
          <%BProducto producto = (BProducto)listadoProducto.getElemento(i); %>
          <%BEquivalencia equiva = (BEquivalencia)producto.getListaPuntaje().getElemento(0); %>
          <tr>
            <td>[imagen]</td>
            <td><table width="349" border="0">
              <tr>
                <td colspan="3"><%=producto.getNombre() %></td>
                </tr>
              <tr>
                <td colspan="3"> <%=equiva.getCantidadPuntoUno() %> ptos + S/. <%=equiva.getMontoUno() %> 
								<%if (equiva.getCantidadPuntoDos() != 0 && equiva.getMontoDos()!=0 ){ %>
								<br><%=equiva.getCantidadPuntoDos() %> ptos + S/. <%=equiva.getMontoDos() %>
								<%} %>
								<%if (equiva.getCantidadPuntoTres() != 0 && equiva.getMontoTres()!=0 ){ %>
								<br><%=equiva.getCantidadPuntoTres() %> ptos + S/. <%=equiva.getMontoTres() %>
								<%} %>
				</td>
                </tr>
              <tr>
                <td width="127"> <div align="right">Stock disponible: </div></td>
                <td width="17">&nbsp;</td>
                <td width="167"><div align="left">100 unidades </div></td>
              </tr>
            </table></td>
          </tr>    
          <%} %>      	 		  		 
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
 
</div>
<input type="hidden" name="id" id="id" value="">
</body>
</html>
