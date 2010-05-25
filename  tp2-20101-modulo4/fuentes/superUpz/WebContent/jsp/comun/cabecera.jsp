<%--
*Resumen
*Objeto                 : cabecera.jsp.
* Descripcion           : Cabecera de las paginas del sistema.
* Fecha de Creacion     : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>

<%@page import="pe.com.upz.bean.BUsuario"%>
<%@page import="pe.com.upz.bean.BOpcion"%>
<%@page import="pe.com.upz.util.Lista"%>
<%
BUsuario usuario = ((BUsuario)request.getSession().getAttribute("usuarioSesion"));
Lista listaMenu = ((Lista)request.getSession().getAttribute("menu"));
String ruta = request.getContextPath(); 
String variable = request.getParameter("mostrar");
boolean mostrarDiv=true;
if(variable !=null && variable.equals("0")){
    mostrarDiv = false;
}
%>
<link rel="stylesheet" type="text/css"
    href="<%=ruta%>/submenu/jqueryslidemenu.css" />

<script type="text/javascript" src="<%=ruta%>/JQUERYMIN/jquery.min.js"></script>

<script type="text/javascript" src="<%=ruta%>/submenu/jqueryslidemenu.js"></script>
<link type="text/css" href="<%=ruta%>/themes/redmond/ui.all.css"
    rel="stylesheet" />
<div id="cabecera" align="center"  <%= mostrarDiv?"":"style='display:none;'"%> >
<table width="1010px" border="0"
    class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
    <tr>
        <td width="187"><a href="principal.html"
            onClick="return (false);"> <img src="<%=ruta%>/images/logotipo.jpg" alt="Volver a página principal"
            width="150" height="80" border="0"></a></td>
        <td align="center" width="560"><span class="style6 style2">SUPERMERCADOS
        UPZ</span></td>
        <td width="247"><strong>Usuario: <%=usuario.getNombre() %> <%=usuario.getApellidoPaterno() %> </strong></td>
    </tr>
</table>
<strong></strong></div>
<div align="center" <%= mostrarDiv?"":"style='display:none;'"%> >
<div id="myslidemenu" class="jqueryslidemenu"
    style="width: 1010px; height: 60px">
<% 
int nivel,nivelAnterior,codigoPadre,codigoPadreAnterior, flagIngreso, flagIngresoHijo;
flagIngreso = 0;
flagIngresoHijo=0;
boolean esPrimerHijo = true;
%>
<ul>
<%for(int i=0; i<listaMenu.getTamanio();i++ ){ %>
    <% 
    BOpcion opcion = (BOpcion)listaMenu.getElemento(i);
    codigoPadre=opcion.getCodigoPadre();
    nivel=opcion.getNivel();
    if(nivel == 1){    
        if(!esPrimerHijo){
        %>    
        </ul>
    </li>
        <%}else if(esPrimerHijo && i!=0){%>
    </li>
        <%}
    %>
    <li><a href="<%=opcion.getRuta()%>" ><%=opcion.getDescripcionOpcion()%> </a>
    <%  esPrimerHijo = true;
    }else{%>
    <%if(esPrimerHijo){ %>
        <ul>
    <%esPrimerHijo = false;
    } %>
            <li><a href="<%=ruta%><%=opcion.getRuta()%>"><%=opcion.getDescripcionOpcion()%></a></li>
<%}%>
<%}%>
<%if(!esPrimerHijo){ %>
        </ul>
    </li>
    <%esPrimerHijo = false;
    } %>
</ul>
<br style="clear: left" />
</div>
</div>