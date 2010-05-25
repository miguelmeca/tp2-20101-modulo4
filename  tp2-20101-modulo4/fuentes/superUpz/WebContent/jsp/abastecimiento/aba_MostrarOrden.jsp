<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%--
*Resumen
*Objeto                 : aba_MostrarOrden.jsp.
* Descripcion           : pagina para mostrar una orden depedido.
* Fecha de Creacion     : 24/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BProducto"%>
<%@page import="pe.com.upz.bean.BUsuario"%>
<%
	
	BUsuario usuario = ((BUsuario)request.getSession().getAttribute("usuarioSesion"));
	String ruta = request.getContextPath();
	String fecha = (String) request.getAttribute("fecha");
	Lista listaProductos = (Lista) request
			.getAttribute("listaProductos");

	if (listaProductos == null) {
		listaProductos = new Lista();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Orden De Pedido</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
#divCuerpo {
    margin:10px; /*para que no se pegue al borde*/ 
    padding:30px; /*algo de relleno*/
    width:800px; /*este ancho es para que cuadre con el texto*/ 
    border:1px solid #333333; /*decoracion*/ 
    background-color:white; /*more*/ 
    float:left; /*lo flotamos a la izquierda*/ 
    display:inline; /*(*)*/ 
}
#divFecha {
    margin:10px; /*para que no se pegue al borde*/ 
    padding:10px; /*algo de relleno*/
    background-color:white; /*more*/ 
    float:right; /*lo flotamos a la izquierda*/ 
}
#divGeneracion {
    margin:10px; /*para que no se pegue al borde*/ 
    padding:10px; /*algo de relleno*/
    background-color:white; /*more*/ 
    float:left; /*lo flotamos a la izquierda*/ 
}
.style1 {font-family: Arial, Helvetica, sans-serif}
.style4 {font-size: 12px; font-weight: bold; }
.style5 {font-size: 10px}
.style6 {font-size: 14px}
.style7 {font-size: 12px}
-->
</style>
</head>

<body bgcolor="#999999">
<div class="style1" id="divCuerpo">
  <table width="800" border="0">
        <tr>
          <td colspan="5">
		    <div class="style7" id="divFecha"><%=fecha %></div>
			<div class="style7" id="divGeneracion"><%=usuario.getLogin() %></div>
		  </td>
        </tr>
        <tr>
          <td colspan="5"><div align="center" class="style5">
            <h2 class="style1 style6">ORDEN DE PEDIDO </h2>
          </div></td>
        </tr>
        <tr>
          <td width="43"><span class="style4">ITEM</span></td>
          <td width="92"><span class="style4">C&Oacute;DIGO</span></td>
          <td width="199"><span class="style4">TIPO DEL PRODUCTO </span></td>
          <td width="351"><span class="style4">DESCRIPCI&Oacute;N DEL PRODUCTO </span></td>
          <td width="93"><div align="right"><span class="style4">CANTIDAD</span></div></td>
        </tr>
        <%
        for(int i=0; i< listaProductos.getTamanio();i++){ 
        	BProducto producto = (BProducto)listaProductos.getElemento(i); 
        %>
	        <tr>
	          <td><%=((i+1)+"")%></td>
	          <td><%=producto.obtenerCodigoCadena()%></td>
	          <td><%=producto.getTipo().getDescripcion()%></td>
	          <td><%=producto.getDescripcion()%></td>
	          <td><div align="right"><%=producto.getStock()%></div></td>
	        </tr>
        <%} %>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
  </table>
      <p>&nbsp;</p>
</div>

</body>
</html>
