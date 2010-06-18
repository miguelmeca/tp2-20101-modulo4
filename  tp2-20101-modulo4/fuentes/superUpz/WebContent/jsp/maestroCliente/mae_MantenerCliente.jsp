<%--
*Resumen
*Objeto                 : mae_MantenerCliente.jsp.
* Descripcion           : pagina para el mantenimiento de clientes.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BUbigeo"%>
<%
String ruta = request.getContextPath(); 
Lista listaDepartamento = (Lista)request.getAttribute("listaDepartamento");
if(listaDepartamento ==null){
	listaDepartamento = new Lista();
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Nuevo Cliente</title>

<link type="text/css" href="<%=ruta%>/css/demos.css" rel="stylesheet" />

<style type="text/css">
<!--
.Estilo1 {
	font-size: 10px
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.Estilo4 {
	font-size: 11px;
	color: #000033;
}

.style4 {
	color: #FF0000
}
-->
</style>

</head>

<body>
<jsp:include page="../comun/cabecera.jsp"></jsp:include>
<div class="demos-nav" style="width: 100%" align="center">
<form name="frmNuevoCliente" action="" method="get">
<table width="900px" border="0">
	<tr>
		<td
			class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
			align="center">Mantener Cliente</td>
	</tr>
	<tr>
		<td height="327" valign="top">
		<table width="100%" border="0">

			<tr>
				<td height="171" valign="top" class="ui-tabs-nav">
				<center>
				<table width="80%" border="1">
					<tr>
						<td width="100%"
							class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
						<div align="center">Datos del Cliente</div>
						</td>
					</tr>
					<tr>
						<td align="center">
						<table width="729" border="0">
							<tr>
								<td width="164">
								<div align="left">N&uacute;mero de documento: (*)</div>
								</td>
								<td width="150">
								<div align="left"><input name="txtNumeroDocumento"
									type="text" class="text  ui-corner-all" id="txtNumero22"
									onKeyPress="Upper();SoloNumeros();" 
									style="width: 150px" maxlength="10" /></div>
								</td>
								<td colspan="2"><input name="btnValidar" type="button"
									class="ui-state-default btnValidadr" id="btnValidar"
									style="width: 120px" value="Validar" /></td>
								<td width="186">
								<div align="left" class="style4" id="mensajeMostrar"
									style="display: none;">DOCUMENTO V&Aacute;LIDO</div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">Apellido paterno: (*)</div>
								</td>
								<td>
								<div align="left"><input name="txtApellidoPaterno"
									type="text" class="text  ui-corner-all" id="txtApellidoPaterno"
									onKeyPress="Upper();permitirLetrasEspeciales();"  
									style="width: 150px" maxlength="100" /></div>
								</td>
								<td width="55">&nbsp;</td>
								<td width="140">
								<div align="left">Apellido materno: (*)</div>
								</td>
								<td>
								<div align="left"><input name="txtApellidoMaterno"
									type="text" class="text  ui-corner-all" id="txtApellidoMaterno"
									onKeyPress="Upper();permitirLetrasEspeciales();"  
									style="width: 150px" maxlength="100" /></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">Nombres: (*)</div>
								</td>
								<td>
								<div align="left"><input name="txtNombre" type="text"
									class="text  ui-corner-all" id="txtNombre" style="width: 150px"
									onKeyPress="Upper();permitirLetrasEspeciales();"  
									maxlength="100" /></div>
								</td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Departamento: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selDepartamento" name="selDepartamento" onChange="buscaProvincia()" >
									<option selected="selected" value='-1'>--Seleccionar--</option>
									<%for(int i=0;i< listaDepartamento.getTamanio();i++){ %>
										<%BUbigeo ubigeo = (BUbigeo)listaDepartamento.getElemento(i); %>
										<option value='<%=ubigeo.getDepartamento()%>'><%=ubigeo.getNombre()%></option>
									<%} %>
								</select></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">e-mail:</div>
								</td>
								<td><input type="text" name="txtEMail" id="txtEMail"
									onKeyPress="Upper();"  
									style="width: 150px" class="text  ui-corner-all" /></td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Provincia: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selProvincia" onChange="buscarDistrito()" name="selProvincia" >
									<option selected="selected"></option>
								</select></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">Tel&eacute;fono casa:</div>
								</td>
								<td><input name="txtTelefono" type="text"
									class="text  ui-corner-all" id="txtTelefono"
									onKeyPress="Upper();SoloNumeros();" 
									style="width: 150px" maxlength="20" /></td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Distrito: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selDistrito" name="selDistrito" >
									<option selected="selected"></option>
								</select></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">Tel&eacute;fono celular:</div>
								</td>
								<td>
								<div align="left"><input name="txtCelular" type="text"
									class="text  ui-corner-all" id="txtCelular"
									onKeyPress="Upper();SoloNumeros();" 
									style="width: 150px" maxlength="20" /></div>
								</td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Direcci&oacute;n: (*)</div>
								</td>
								<td>
								<div align="left"><input name="txtDireccion" type="text"
									class="text  ui-corner-all" id="txtDireccion"
									onKeyPress="Upper();permitirLetraNumeroEspeciales();"  
									style="width: 150px" maxlength="200" /></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
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
				<div align="right"><input type="button" name="btnAceptar"
					value="Aceptar" onClick="guardar()" style="width: 120px"
					class="ui-state-default btnAceptar" /> <input type="button"
					name="btnCancelar" value="Cancelar" style="width: 120px"
					class="ui-state-default btnCancelar" /></div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="hddOperacion" id="hddOperacion" value="almacenarCliente" />
</form>
</div>
</body>
<script language="JavaScript">
function guardar(){
	var dni = frmNuevoCliente.txtNumeroDocumento.value;
	var nombre = frmNuevoCliente.txtNombre.value;
	var paterno = frmNuevoCliente.txtApellidoPaterno.value;
	var materno = frmNuevoCliente.txtApellidoMaterno.value;
	var direccion = frmNuevoCliente.txtDireccion.value;
	if(dni == ""){
		alert("Debe ingresar un número de documento.");
			frmNuevoCliente.txtNumeroDocumento.focus();
			return;
	}
	if(paterno == ""){
		alert("Debe ingresar un apellido paterno.");
			frmNuevoCliente.txtApellidoPaterno.focus();
			return;
	}
	if(materno == ""){
		alert("Debe ingresar un apellido materno.");
			frmNuevoCliente.txtApellidoMaterno.focus();
			return;
	}
	if(nombre == ""){
		alert("Debe ingresar un nombre.");
			frmNuevoCliente.txtNombre.focus();
			return;
	}
	 if(frmNuevoCliente.selDepartamento.selectedIndex<=0){
	     alert("Debe seleccionar un departamento.");
	     frmNuevoCliente.selDepartamento.focus();
	     return false;
	}
	 if(frmNuevoCliente.selProvincia.selectedIndex<=0){
	     alert("Debe seleccionar una provincia.");
	     frmNuevoCliente.selProvincia.focus();
	     return false;
	}
	 if(frmNuevoCliente.selDistrito.selectedIndex<=0){
	     alert("Debe seleccionar un distrito.");
	     frmNuevoCliente.selDistrito.focus();
	     return false;
	}
	if(direccion == ""){
		alert("Debe ingresar la dirección.");
			frmNuevoCliente.txtDireccion.focus();
			return;
	}
	frmNuevoCliente.action="SMantenimientoCliente";
	frmNuevoCliente.submit();
}

function buscaProvincia(){   

    frmNuevoCliente.selProvincia.length = 0; //Borra los datos
	frmNuevoCliente.selDistrito.length = 0; //Borra los datos
    if(frmNuevoCliente.selDepartamento.selectedIndex==0){
        frmNuevoCliente.selProvincia.length = 0; //Borra los datos
        return;
    }
    
    var departamento = 
            frmNuevoCliente.selDepartamento.
                    options[frmNuevoCliente.selDepartamento.selectedIndex].value;
    //colocaNombre(frmNuevoCliente.selDepartamento, frmNuevoCliente.txtNomUbigeoDest)
    var url = "ServicioUtilitario?" +
                  "metodo=requestRefrescaProvincia" +
                  "&selDepartamento="+ departamento; 
    
    var msxml = new ActiveXObject("msxml2.XMLHTTP");
        
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	 
        
    if(ret!="OK__NoExiste")   {
        ret = ret.substr(3);
        llenarComboListaCodTexto(ret,";","|",frmNuevoCliente.selProvincia);
    }
     
}
function buscarDistrito(){    
    
    if(frmNuevoCliente.selProvincia.selectedIndex==0){
        frmNuevoCliente.selDistrito.length = 0; //Borra los datos
        return;
    }
    
    var departamento = frmNuevoCliente.selDepartamento.value;
    var provincia = frmNuevoCliente.selProvincia.value;
    var url = "ServicioUtilitario?" +
                  "metodo=requestRefrescaDistrito" +
                  "&selDepartamento="+ departamento+
				  "&selProvincia="+provincia;
	
    var msxml = new ActiveXObject("msxml2.XMLHTTP");
        
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	 
        
    if(ret!="OK__NoExiste")   {
        ret = ret.substr(3);
        llenarComboListaCodTexto(ret,";","|",frmNuevoCliente.selDistrito);
    }
}
</script>
</html>
