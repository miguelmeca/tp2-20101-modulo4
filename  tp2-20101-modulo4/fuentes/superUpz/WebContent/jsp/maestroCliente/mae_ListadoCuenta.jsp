<%--
*Resumen
*Objeto                 : mae_ListadoCuenta.jsp.
* Descripcion           : pagina para listar las cuentas.
* Fecha de Creacion     : 16/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BCuenta"%>
<%@page import="pe.com.upz.bean.BCliente"%>
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
Lista listadoCuenta = (Lista)request.getAttribute("listadoCuenta");
if(listadoCuenta ==null){
	listadoCuenta = new Lista();
}

//mensaje a mostrar
String mensajeMantenimiento = (String)request.getAttribute("mensajeMantenimiento");
if(mensajeMantenimiento == null){
	mensajeMantenimiento = "";
}
//Para el Nro. de Pagina.
int numPagina=0;
//Si la pagina es nula entonces debe ser 1;
numPagina=(request.getAttribute("pagina")==null?1:Integer.parseInt((String)request.getAttribute("pagina")));

//Numero de registro por páginas.
int tamanoPagina=10;
//iTamanoPagina=Integer.parseInt(String.valueOf(application.getAttribute("AppTamanoPagina")));
listadoCuenta.setTamPagina(tamanoPagina);
listadoCuenta.setNumPagina(numPagina);
listadoCuenta.setCantidadPaginasMostradas(5);


//parametro del filtro
String filtroPagina = (String)request.getAttribute("filtroPagina");
String valorAuxiliar = (String)request.getAttribute("valorAuxiliar");

if(filtroPagina == null){
	filtroPagina = "0";
}
if(valorAuxiliar == null){
	valorAuxiliar = "";
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Mantener Cliente</title>

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

<body onload="javascript:mostrarMensaje();ocultarPaneles();">
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width:100%" align="center">

<form name="frmListaClientes">
<% if(mostrarDiv){%>
<table width="1010px" border="0" >
<%}else{ %>
<table width="900px" border="0" >
<%} %>
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Mantener Cuenta</td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      <tr class="ui-widget">
        <td><table width="100%" border="0" class="ui-priority-primary"  >
			<td width="14%" class="ui-accordion-content" ><div align="center" class="demo-config-on Estilo1">
              <div align="left">TIPO DE BUSQUEDA </div>
            </div></td>
            <td width="24%" class="ui-accordion-content" ><div align="center" class="Estilo1">
                <div align="left">
                  <select name="selTipoBusqueda" class="ui-icon-document" style="width:200PX;heigth:10px" onChange="javascript: ocultarPaneles();" >
                      <option value="0" <%=filtroPagina.equals("0")?"Selected":"" %> >--Seleccione--</option>
                      <option value="1" <%=filtroPagina.equals("1")?"Selected":"" %> >Núm. Documento</option>
                      <option value="2" <%=filtroPagina.equals("2")?"Selected":"" %> >Núm. Tarjeta</option>
                        </select>
                </div>
            </div></td>
            <td width="62%" class="ui-accordion-content"><table width="100%" border="0">
              <tr>
                <td height="38" class="Estilo1"> 
				<div id="divDocumento" style="position:relative;top: 0px;left: 0px ">N&uacute;mero:
                      <input name="txtDNIBuscar" 
					  onKeyPress="Upper();SoloNumeros();" 
					  type="text" size="25px"  maxlength="100" value="" >
                </div>
				<div id="divNombres" style="position:relative;top: 0px;left: 0px ">
					<div id="divNombreCliente">N&uacute;mero:
						  <input name="txtNumeroTarjeta" 
						  onKeyPress="Upper();SoloNumeros();" 
						  type="text" size="25px"  maxlength="100" value="" >
					</div>
                </div>
				<div id="divVacio" style="position:relative;top: 0px;left: 0px "></div>
				</td>
                <td width="19%" class="Estilo1"><input name="btnBuscar" type="button"   
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
            <div align="right" class="ui-state-default" > total de items : <%=listadoCuenta.getTamanio()%> &lt;&lt; Tolal de paginas : 
            <%if(listadoCuenta.getNumPagina() > 1 ){ %>
            <a onClick="javascript:paginar('<%=listadoCuenta.getNumPagina()-1%>')" style='cursor:hand' >Anterior</a> 
            <%} %>
            [ 
            <%for(long i=listadoCuenta.getNumPaginaInicialEnPaginacion();i< listadoCuenta.getNumPaginaInicialEnPaginacion() + listadoCuenta.getCantidadPaginasMostradas() && i <= listadoCuenta.getCantidadPaginasDeListado() ;i++){ 
            	if(i==listadoCuenta.getNumPagina()){%>
            		<span class="ui-state-active">
            		<%=i %>
            		</span>&nbsp;
            		<%
            	}else{%>
            		<a  onClick="javascript:paginar('<%=i%>');" style='cursor:hand' ><%=i%></a>&nbsp;
            	<%}%>
            	
            <%} %>
            ] 
            <%if(listadoCuenta.getNumPagina() < listadoCuenta.getCantidadPaginasDeListado() ){ %>
            <a onClick="javascript:paginar('<%=listadoCuenta.getNumPagina()+1%>')" style='cursor:hand' >Siguiente </a>
            <%} %>
            &gt;&gt; </div>
		 </td>
      </tr>
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav" align="center"><table width="877" border="1">
          <tr>
            <td width="4%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Num</td>
            <td width="21%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">N&uacute;mero</div></td>
            <td width="25%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Apellido Paterno</div></td>
            <td width="22%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Apellido Materno </div></td>
            <td width="24%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Nombres</div></td>
            <td width="4%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
			</tr>
 			<%for(int i=listadoCuenta.getFirstElementPage(numPagina)-1;i<=listadoCuenta.getLastElementPage(numPagina)-1;i++){%>
 			<%	BCuenta cuenta = (BCuenta) listadoCuenta.getElemento(i); %> 
 			<%	BCliente miCliente = cuenta.obtenerClientePrincipal(); %> 
			<%	String numeroTarjeta = cuenta.obtenerTarjetaPrincipal(); %> 
          <tr >
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=(i+1)%></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=numeroTarjeta%></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getApellidoPaterno()%> </div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getApellidoMaterno()%></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getNombre()%> </div></td>
            <td class="ui-accordion-content"><input name="chkProducto" type="radio" value ="<%=cuenta.getCodigo()%>" onclick="obtenerId('1')" /></td>
            </tr>   
            <%} %>
        </table>          
         </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <% if (mostrarMantenimineto){ %>
          <input type="button" name="btnNuevo" value="Nuevo" style="width:120px" 
          onclick="javascript:agregarNuevo()" 
          class="ui-state-default btnNuevo"  />
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
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
</form>
</div>
</body>
<script language="JavaScript">
function buscarPorParametro(){
	
	var seleccion = frmListaClientes.selTipoBusqueda.value;
	
	if(seleccion == 1){
		if(frmListaClientes.txtDNIBuscar.value == ""){
			alert("Debe ingresar un número de documento");
			frmListaClientes.txtDNIBuscar.focus();
			return;
		}
	}
	if(seleccion == 2){
		if(frmListaClientes.txtNumeroTarjeta.value == ""){
			alert("Debe ingresar un número de tarjeta");
			frmListaClientes.txtNumeroTarjeta.focus();
			return;
		}
	}

	
	frmListaClientes.hddOperacion.value="ingresoMantenerCuenta";
	frmListaClientes.action="SMantenimientoCliente";
	frmListaClientes.submit();
}
function agregarNuevo(){
	frmListaClientes.hddOperacion.value="nuevoCuenta";
	frmListaClientes.action="SMantenimientoCliente?hddOperacion=nuevoCuenta";
	frmListaClientes.submit();
}
function mostrarMensaje(){
	var resultado = "<%=mensajeMantenimiento%>";
	if(resultado == "nuevoOK"){
		alert("Se agregó un nuevo Cliente.");
	}else if (resultado== "actualizadoOK"){
		alert("Se actualizó el Cliente.");
	}
}
function ocultarPaneles(){
	var seleccion = frmListaClientes.selTipoBusqueda.value;

	if(seleccion == "0"){
		MM_showHideLayers('divDocumento','','hide','divNombres','','hide','divVacio','','show');
	}else if(seleccion == "1"){
		MM_showHideLayers('divDocumento','','show','divNombres','','hide','divVacio','','hide');
	}else if(seleccion == "2"){
		MM_showHideLayers('divDocumento','','hide','divNombres','','show','divVacio','','hide');
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
