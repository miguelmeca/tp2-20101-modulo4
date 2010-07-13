<%--
*Resumen
*Objeto                 : mae_MantenerCuentaAdicional.jsp.
* Descripcion           : pagina para el mantenimiento de cuenta.
* Fecha de Creacion     : 10/07/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BCuenta"%>
<%@page import="pe.com.upz.bean.BCliente"%>
<%
String ruta = request.getContextPath(); 

//Lista listadoCuenta = (Lista)request.getAttribute("listadoCuenta");
Lista listadoCuenta = (Lista)request.getSession().getAttribute("listadoCuenta");
String codCuenta = (String)request.getAttribute("codigoCuenta");
String nombreCliente = (String)request.getAttribute("nombreCliente");
String numTarjeta = (String)request.getAttribute("numTarjeta");
int codigoCuenta =0;
if(listadoCuenta ==null){
	listadoCuenta = new Lista();
}
if(nombreCliente ==null){
	nombreCliente = "";
}
if(codCuenta !=null){
	codigoCuenta = Integer.parseInt(codCuenta);
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Canjear Puntos</title>
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
<form name="frmCanje" id="frmCanje" method="get" action="">
<table width="1010px" border="0" >
<tr>
	<td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center"> Mantenimiento de Cuenta </td>
</tr>
  <tr>
    <td height="327" valign="top" ><table width="100%" border="0">
      
      <tr>
        <td height="171" valign="top" class="ui-tabs-nav">
		<center>
			<table width="85%" border="1">
          <tr>
            <td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">Datos de la cuenta </td>
            </tr>
          <tr >
            <td align="center" style="width: 702px" >
			<table border="0">
           <tr >
            <td colspan="2" align="left" >
            </td>
            </tr>
		  <tr >
            <td align="left" style="height: 21px; width: 175px;" > Cliente:</td>
            <td align="left" style="width: 420px; height: 21px;" >
			<input name="hddCodigoCliente" type="hidden" class="text  ui-corner-all" id="hddCodigoCliente" style="width:150px" readonly="true"  /> 
			<input name="txtCliente" type="text" class="text  ui-corner-all" id="txtCliente" style="width:350px" value="<%=nombreCliente%>" readonly="true"  />                  
              </td>
		  </tr>
		  <tr >
            <td align="left" style="height: 26px; width: 175px;" >&nbsp; </td>
			<td align="left" style="height: 26px; width: 420px;" >&nbsp;                </td>
		  </tr>
          <tr >
            <td align="left" style="height: 31px; width: 175px;" >Asignar tarjeta  (*)  </td>
			<td align="left" style="height: 31px; width: 420px;" ><input name="txtNumeroTarjeta" type="text" class="text  ui-corner-all" id="txtNumeroTarjeta" style="width:250px" value="<%=numTarjeta%>" readonly="true" />
			&nbsp;&nbsp;
			<input type="button" name="btnAsignar" id="btnAsignar" 
			onclick="javascript:agregarTarjeta()" 
			value="Asignar" style="width:120px" class="ui-state-default btnAsignar" /></td>
          </tr>

          <tr >
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr >
            <td colspan="2" align="left" >
			</td>
          </tr>
          <tr >
            <td colspan="2" align="left" ><span class="style3">(*) Campos obligat&oacute;rios.&nbsp;</span></td>
          </tr>
          <tr >
            <td colspan="2" align="left" ><input type="button" name="btnAgregarAdicional" id="btnAgregarAdicional" 
			onclick="javascript:agregarAdicional()" 
			value="Nuevo Adicional" style="width:150px" class="ui-state-default" />
			<input type="button" name="btnEliminarAdicional" id="btnEliminarAdicional" 
			onclick="javascript:agregarTarjeta()" 
			value="Eliminar Adicional" style="width:150px" class="ui-state-default" />
			</td>
            </tr>
        </table>			
		
		</td>
		</tr>
		<tr><td class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" align="center">
		<table width="877" border="1">
          <tr>
            <td width="4%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Num</td>
            <td width="21%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">N&uacute;mero</div></td>
            <td width="25%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Apellido Paterno</div></td>
            <td width="22%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Apellido Materno </div></td>
            <td width="24%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><div align="center">Nombres</div></td>
            <td width="4%" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">Sel</td>
			</tr>
 			<%for(int i=0;i<listadoCuenta.getTamanio();i++){%>
 			<%	BCuenta cuenta = (BCuenta) listadoCuenta.getElemento(i); %> 
 			<%	BCliente miCliente = cuenta.obtenerClientePrincipal(); %> 
			<%	String numeroTarjeta = cuenta.obtenerTarjetaPrincipal(); %> 
          <tr >
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=(i+1)%></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=numeroTarjeta%></div></td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getApellidoPaterno()%> </div>
			<input type="hidden" name="hddApellidoPaterno<%=cuenta.getCodigo()%>" value="<%=miCliente.getApellidoPaterno()%>" />
			</td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getApellidoMaterno()%></div>
			<input type="hidden" name="hddApellidoMaterno<%=cuenta.getCodigo()%>" value="<%=miCliente.getApellidoMaterno()%>" />
			</td>
            <td class="ui-accordion-content"><div align="center" class="Estilo4"><%=miCliente.getNombre()%> </div>
			<input type="hidden" name="hddNombre<%=cuenta.getCodigo()%>" value="<%=miCliente.getNombre()%>" />
			</td>
            <td class="ui-accordion-content"><input name="chkProducto" type="radio" value ="<%=cuenta.getCodigo()%>" 
			onclick="javascript:seleccionar('<%=cuenta.getCodigo()%>')"  /></td>
            </tr>   
            <%} %>
        </table>     
		</td>
            </tr>
        </table>        
		</center>   </td>
      </tr>
      
      <tr>
        <td class="ui-widget-header"><div align="right">
          <input type="button" name="btnAceptar" value="Aceptar" 
          onclick="javascript:guardar()" 
          style="width:120px" class="ui-state-default btnAceptar"  />
          <input type="button" name="btnCancelar" value="Cancelar" 
          onclick="javascript:cerrar()" 
          style="width:120px" class="ui-state-default" />
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="" />
<input type="hidden" name="hddMantenimiento" id="hddMantenimiento" value="0" />
</form>
</div>
</body>
<script language="JavaScript">
function cerrar(){
	frmCanje.target="_top";
	frmCanje.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmCanje.submit();
}
function agregarAdicional(){
	frmCanje.hddOperacion.value="agregarAdicional";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=agregarAdicional";
	frmCanje.submit();
}
function guardar(){
	frmCanje.target="_top";
	frmCanje.hddOperacion.value="almacenarCuenta";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=almacenarCuenta";
	frmCanje.submit();
}
function agregarTarjeta(){
	frmCanje.target="VENTANA";
	frmCanje.hddOperacion.value="asignarTarjeta";
	//frmCanje.action="<%=ruta%>/jsp/maestroCliente/mae_AsignarTarjeta.jsp?mostrar=0";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=asignarTarjeta";

	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 520 + 
	                 ",height=" + 250; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
}
function mostrarClientes(){
	frmCanje.target="VENTANA";
	frmCanje.hddOperacion.value="buscarCliente";
	frmCanje.action="<%=ruta%>/SMantenimientoCliente?hddOperacion=buscarCliente&hddMantenimiento=0";

	var opciones = "fullscreen=" + 0 + 
	                 ",toolbar=" + 0 + 
	                 ",status=" + 0 + 
	                 ",menubar=" + 0 + 
	                 ",scrollbars=" + 1 + 
	                 ",resizable=" + 0 + 
	                 ",width=" + 950 + 
	                 ",height=" + 600; 
	window.open("","VENTANA",opciones,1); 
	frmCanje.submit();
}
</script>
</html>
