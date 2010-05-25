<%--
*Resumen
*Objeto                 : aba_autorizacion.jsp.
* Descripcion           : Pagina para aoutorizar la orden.
* Fecha de Creacion     : 05/05/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>

<% 
String mensaje = request.getParameter("mensaje");

mensaje = (mensaje==null?"":mensaje);
String ruta = request.getContextPath(); 
%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Autorizar</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

</head>

<body onload="JavaScript:mostrarMensaje()">
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form name="frmLogin" id="frmLogin" method="post" action="">
<table width="500" border="1">
	<tr>
		<td width="100%"
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
		<div align="center">Autorizaci&oacute;n de Orden</div>
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
				<td width="51%" align="left"><input type="text"
					name="txtUsuario" id="txtUsuario" style="width: 150px"
					class="text  ui-corner-all" size="20" /></td>
			</tr>
			<tr>
				<td align="left">&nbsp;</td>
				<td align="left">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">Contrase&ntilde;a:</td>
				<td align="left"><input type="password" name="txtClave"
					id="txtClave" style="width: 150px" class="text  ui-corner-all"
					size="100" /></td>
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
			value="Aceptar" style="width: 120px" OnClick="JavaScript:ingresar()"
			class="ui-state-default" /> <input type="button" name="btnCancelar"
			value="Cancelar" style="width: 120px"
			class="ui-state-default btnOpenEdicion" /></div>
		</td>
	</tr>
</table>

<input type="hidden" name="hddOperacion" id="hddOperacion"
	value="validarPermiso">
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
			alert("Ingrese una contraseÃ±a.");
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
		
		frmLogin.action="SAbastecimiento";
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
		}else if(mensajeMostrar == "usuarioNoJefe"){
			alert("El usuario ingresado no tiene el rol de jefe de fidelización.");
		}else if(mensajeMostrar == "aprobacion"){
			alert("No se permite generar una orden de pedido fuera de la fecha establecida. "+ 
					"Será necesario una aprobación.");
		}
		document.getElementById("txtUsuario").value="";
		document.getElementById("txtClave").value ="";
	}
</script>
</html>
