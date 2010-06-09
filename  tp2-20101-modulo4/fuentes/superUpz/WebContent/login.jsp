<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
*Resumen
*Objeto                 :  login.jsp.
* Descripcion          : Pagina inicial de logeo la sistema.
* Fecha de Creacion    : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BSucursal"%>
<% 
String mensaje = request.getParameter("mensaje");

Lista sucursal = (Lista)request.getAttribute("sucursales");

mensaje = (mensaje==null?"":mensaje);
System.out.println(getServletContext().getRealPath("/"));
System.out.println(getServletContext().getInitParameter("RUTA_IMG_PRODUCTO"));

if(sucursal ==null){
	sucursal = new Lista();
}

%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Ingreso al sistema</title>

<link type="text/css" href="css/demos.css" rel="stylesheet" />
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />

<!--**JQUERY**-->
<!--** ACTIVAR CUANDO SEA LOCAL**-->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js" ></script>
 
<link type="text/css" href="themes/redmond/ui.all.css" rel="stylesheet" />

</head>

<body onload="JavaScript:mostrarMensaje()">
<div id="cabecera" align="center">
<table width="1010px" border="0"
	class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
	<tr>
		<td width="21%"><a href="principal.html"
			onClick="return (false);"> <img src="images/logotipo.jpg"
			width="150" height="80" alt="Volver a página principal"></a></td>
		<td align="center"><span class="style6 style2">SUPERMERCADOS
		UPZ</span></td>
	</tr>
</table>
</div>

<div align="center">
<form name="frmLogin" id="frmLogin" method="post" action="">
<table width="1010px" border="0">
	<tr>
		<td
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
			align="center">&nbsp;</td>
	</tr>
	<tr>
		<td height="327" valign="top">
		<table width="100%" border="0">

			<tr>
				<td height="171" valign="top" class="ui-tabs-nav">
				<center>
				<table width="600" height="386" border="0"
					background="images/fondo.jpg">
					<tr>
					  <td align="center">

						<table width="58%" border="1">
							<tr>
								<td width="100%"
									class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
								<div align="center">Ingreso al Sistema</div>
								</td>
							</tr>
							<tr>
								<td align="center">
								<table border="0">
									<tr>
										<td colspan="2" align="left">&nbsp;</td>
									</tr>
									<tr>
										<td width="49%" align="left">Usuario:</td>
										<td width="51%" align="left">
										<input type="text" 
										    value="bcamela" 
											name="txtUsuario" id="txtUsuario" style="width: 150px"
											class="text  ui-corner-all" size="20" /></td>
									</tr>
									<tr>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
									</tr>
									<tr>
										<td align="left">Contrase&ntilde;a:</td>
										<td align="left">
										<input type="password" name="txtClave"
											value ="a" 
											id="txtClave" style="width: 150px" class="text  ui-corner-all" size="100" /></td>
									</tr>
									<tr>
									  <td align="left">&nbsp;</td>
									  <td align="left">&nbsp;</td>
								  </tr>
									<tr>
									  <td align="left">Sucursal: </td>
								      <td align="left"><select name="select">
								        <%for(int i=0;i<sucursal.getTamanio();i++){ %>
								        	<%BSucursal suc = (BSucursal)sucursal.getElemento(i); %>
								        <option value="<%=suc.getCodigo() %>"><%=suc.getDescripcion() %></option>
								        <%}%>
                                                                            </select></td>
								  </tr>
									<tr>
										<td colspan="2" align="left">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="ui-widget-header">
								<div align="right"><input type="button" name="btnAceptar"
									value="Aceptar" style="width: 120px" 
									OnClick="JavaScript:ingresar()" 
									class="ui-state-default" /> <input type="button"
									name="btnCancelar" value="Cancelar" style="width: 120px" 
									class="ui-state-default btnOpenEdicion" /></div>
								</td>
							</tr>
						</table>

						
					    </td>
					</tr>
				</table>
				</center>
				</td>
			</tr>

			<tr>
				<td class="ui-widget-header">
				<div align="right">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="validarIngreso">
</form>
</div>
</body>
<script language="JavaScript">
	/*
	* Valida los datos ingresados.
	*/
	function validarIngreso(){
		var usuario = document.getElementById("txtUsuario").value;
		var clave = document.getElementById("txtClave").value;
		if (""==usuario){
			alert("Ingrese el nombre del usuario.");
			document.getElementById("txtUsuario").value="";
			document.getElementById("txtClave").value ="";
			return false;
		} 
		if (""==clave){
			alert("Ingrese una contraseña.");
			document.getElementById("txtUsuario").value="";
			document.getElementById("txtClave").value ="";
			return false;
		}
		return true;
	}
	/*
	 * Ingreso al sistema.
	 */
	function ingresar(){

		if(!validarIngreso()){
			return;
		}
		
		frmLogin.action="SLogeo";
		frmLogin.submit();
	}
	/*
	* Muetra el menje indicado al momento de cargar la pagina.
	*/
	function mostrarMensaje(){
		var mensajeMostrar = "<%=mensaje%>";
		if(mensajeMostrar == "contraseniaInvalido"){
			alert("La contraseña del usuario es incorrecta. Vuelva a ingresar los datos.");
		}else if(mensajeMostrar == "usuarioInvalido"){
			alert("El nombre del usuario es incorrecto. Vuelva a ingresarlo.");
		}
		//document.getElementById("txtUsuario").value="";
		//document.getElementById("txtClave").value ="";
	}
</script>
</html>
