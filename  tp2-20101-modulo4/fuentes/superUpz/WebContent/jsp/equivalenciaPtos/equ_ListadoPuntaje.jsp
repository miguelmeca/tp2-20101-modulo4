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
<%@page import="pe.com.upz.bean.BTipoProducto"%>
<%
String ruta = request.getContextPath(); 

Lista listadoProducto = (Lista)request.getAttribute("listadoProducto");
Lista listadoTipoProducto = (Lista)request.getAttribute("listadoTipoProducto");
if(listadoProducto ==null){
	listadoProducto = new Lista();
}
if(listadoTipoProducto ==null){
	listadoTipoProducto = new Lista();
}
//Para el Nro. de Pagina.
int numPagina=0;
//Si la pagina es nula entonces debe ser 1;
numPagina=(request.getAttribute("pagina")==null?1:Integer.parseInt((String)request.getAttribute("pagina")));

//Numero de registro por páginas.
int tamanoPagina=10;
//iTamanoPagina=Integer.parseInt(String.valueOf(application.getAttribute("AppTamanoPagina")));
listadoProducto.setTamPagina(tamanoPagina);
listadoProducto.setNumPagina(numPagina);
listadoProducto.setCantidadPaginasMostradas(5);

//parametro del filtro
String filtroPagina = (String)request.getAttribute("filtroPagina");
String valorAuxiliar = (String)request.getAttribute("valorAuxiliar");

if(filtroPagina == null){
	filtroPagina = "0";
}
if(valorAuxiliar == null){
	valorAuxiliar = "";
}

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

<body onload="javascript:ocultarPaneles();mostrarMensaje()">
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
                  <select name="selTipoBusqueda" class="ui-icon-document" style="width:200PX;heigth:10px" onChange="javascript: ocultarPaneles();" >
                      <option value="0" <%=filtroPagina.equals("0")?"Selected":"" %> >--Seleccione--</option>
                      <option value="1" <%=filtroPagina.equals("1")?"Selected":"" %> >tipo</option>
                      <option value="2" <%=filtroPagina.equals("2")?"Selected":"" %> >descripción</option>
					  <option value="3" <%=filtroPagina.equals("3")?"Selected":"" %> >código</option>
                        </select>
                </div>
            </div></td>
            <td width="62%" class="ui-accordion-content"><table width="100%" border="0">
              <tr>
                <td width="55%" height="50px" class="Estilo1">
				<div id="divDescripcion" style="position:relative;top: 20px;left: 0px ">Nombre de producto:
                      <input name="txtNombreBuscar" type="text" size="300px"  
					  onKeyPress="Upper();permitirLetraNumeroEspeciales();"  
					  maxlength="100" value="<%=filtroPagina.equals("2")?valorAuxiliar:"" %>" >
                </div>                    
                  <div id="divTipo" style="position:relative;top: 0px;left: 0px ">Tipo: <select name="selTipoBuscar">
                      
                      <%for( int i=0 ; i<listadoTipoProducto.getTamanio();i++){ %>
                      <% BTipoProducto tipoProducto = (BTipoProducto)listadoTipoProducto.getElemento(i); %>
                      <option value="<%=tipoProducto.getCodigo() %>" <%=valorAuxiliar.equals(tipoProducto.getCodigo()+"")?"Selected":"" %> ><%=tipoProducto.getDescripcion() %></option>
                      <%} %>
                    </select></div>
                    <div id="divCodigo" style="position:relative;top: -20px;left: 0px ">C&oacute;digo: 
                      <input name="txtCodigoBuscar" type="text"  
					  onKeyPress="Upper();SoloNumeros();" 
					  maxlength="6" value = "<%=filtroPagina.equals("3")?valorAuxiliar:"" %>" >
                    </div>
					  <div id="divVacio" style="position:relative;top: -35px;left: 0px "> </div></td><td width="45%" class="Estilo1">
                      <input name="btnBuscar" type="button"   
                      onclick="javascript:buscarPorParametro()" 
                      id="btnBuscar" style="width:120px" class="ui-state-default" value="Buscar"/></td>
              </tr>
            </table></td>
            </tr>

        </table></td>
      </tr>
          <tr>
            <td>
            <input type="hidden" name="hddPagina" id="hddPagina" value="" />
            <div align="right" class="ui-state-default" > total de items : <%=listadoProducto.getTamanio()%> &lt;&lt; Tolal de paginas : 
            <%if(listadoProducto.getNumPagina() > 1 ){ %>
            <a onClick="javascript:paginar('<%=listadoProducto.getNumPagina()-1%>')" style='cursor:hand' >Anterior</a> 
            <%} %>
            [ 
            <%for(long i=listadoProducto.getNumPaginaInicialEnPaginacion();i< listadoProducto.getNumPaginaInicialEnPaginacion() + listadoProducto.getCantidadPaginasMostradas() && i <= listadoProducto.getCantidadPaginasDeListado() ;i++){ 
            	if(i==listadoProducto.getNumPagina()){%>
            		<span class="ui-state-active">
            		<%=i %>
            		</span>&nbsp;
            		<%
            	}else{%>
            		<a  onClick="javascript:paginar('<%=i%>');" style='cursor:hand' ><%=i%></a>&nbsp;
            	<%}%>
            	
            <%} %>
            ] 
            <%if(listadoProducto.getNumPagina() < listadoProducto.getCantidadPaginasDeListado() ){ %>
            <a onClick="javascript:paginar('<%=listadoProducto.getNumPagina()+1%>')" style='cursor:hand' >Siguiente </a>
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
            <td class="ui-accordion-content">
			<input name="chkProducto" 
			onclick="javascript:seleccionar('<%=producto.getCodigo()%>')" 
			type="radio" value ="<%=producto.getCodigo()%>"  /></td>
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
<input type="hidden" name="hddCodigoSeleccionado" id="hddCodigoSeleccionado" value="" />
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
function validarProductosSeleccionados(){
	var seleccionado = true;
	
	/*for (i=0; i < frmListaProducto.chkProducto.length; i++) {
		if (frmListaProducto.chkProducto[i].checked) {
			seleccionado = true;
			break;
		}
	}	*/
	var codigo = frmListaProducto.hddCodigoSeleccionado.value;
	if(codigo == ""){
		seleccionado = false;
	}
	return seleccionado;
}
function seleccionar(codigo){
	frmListaProducto.hddCodigoSeleccionado.value=codigo;
}
function agregarPuntaje(){
	if(!validarProductosSeleccionados()){
		alert("Debe seleccionar un producto");
		return;
	}
	
	frmListaProducto.hddOperacion.value="agregarEquivalencia";
	frmListaProducto.action="SEquivalencia";
	frmListaProducto.submit();
}
function buscarPorParametro(){
	frmListaProducto.hddOperacion.value="inicioAsignacion";
	frmListaProducto.action="SEquivalencia";
	frmListaProducto.submit();
}
function paginar(pagina){
	frmListaProducto.hddOperacion.value="inicioAsignacion";
	frmListaProducto.hddPagina.value=pagina;
	frmListaProducto.action="SEquivalencia";
	frmListaProducto.submit();
}
function ocultarPaneles(){
	var seleccion = frmListaProducto.selTipoBusqueda.value;

	if(seleccion == "0"){
		MM_showHideLayers('divDescripcion','','hide','divTipo','','hide','divCodigo','','hide','divVacio','','show');
	}else if(seleccion == "1"){
		MM_showHideLayers('divDescripcion','','hide','divTipo','','show','divCodigo','','hide','divVacio','','hide');
	}else if(seleccion == "2"){
		MM_showHideLayers('divDescripcion','','show','divTipo','','hide','divCodigo','','hide','divVacio','','hide');
	}else if(seleccion == "3"){
		MM_showHideLayers('divDescripcion','','hide','divTipo','','hide','divCodigo','','show','divVacio','','hide');
	}
}
//-----------------------------------------------------------
function MM_findObj(n, d)
{ //v4.0
     var p,i,x;
     if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length)
     {
       d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
     }
     if(!(x=d[n])&&d.all) x=d.all[n];

     for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];

     for(i=0;!x&&d.layers&&i<d.layers.length;i++)
       x=MM_findObj(n,d.layers[i].document);

     if(!x && document.getElementById)
       x=document.getElementById(n); return x;
}

function MM_showHideLayers()
{ var i,p,v,obj,args=MM_showHideLayers.arguments;
     for (i=0; i<(args.length-2); i+=3)
       if ((obj=MM_findObj(args[i]))!=null)
       { v=args[i+2];
         if (obj.style)
         {  obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v;  }
         obj.visibility=v;
       }
}
</script>
</html>
