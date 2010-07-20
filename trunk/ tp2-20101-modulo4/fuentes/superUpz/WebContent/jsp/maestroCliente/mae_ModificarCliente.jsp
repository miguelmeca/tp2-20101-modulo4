<%--
*Resumen
*Objeto                 : mae_MantenerCliente.jsp.
* Descripcion           : pagina para el mantenimiento de clientes.
* Fecha de Creacion     : 10/06/2010
* Autor                 : Gonzalo Azabache Carrillo
--%>
<%@page import="pe.com.upz.util.Lista"%>
<%@page import="pe.com.upz.bean.BUbigeo"%>
<%@page import="pe.com.upz.bean.BCliente"%>
<%
String ruta = request.getContextPath(); 

BCliente cliente = (BCliente)request.getAttribute("cliente");
BUbigeo ubigeoCliente = (BUbigeo)request.getAttribute("ubigeo");
Lista listaDepartamento = (Lista)request.getAttribute("listaDepartamento");
Lista listaProvincia = (Lista)request.getAttribute("listaProvincia");
Lista listaDistrito= (Lista)request.getAttribute("listaDistrito");
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
									value="<%=cliente.getNumeroDocumento() %>" 
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
									value="<%=cliente.getApellidoPaterno() %>" 
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
									value="<%=cliente.getApellidoMaterno() %>" 
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
									value="<%=cliente.getNombre() %>" 
									onKeyPress="Upper();permitirLetrasEspeciales();"  
									maxlength="100" /></div>
								</td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Departamento: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selDepartamento" name="selDepartamento" onChange="buscaProvincia()" >
									<option value='-1'>--Seleccionar--</option>
									<%for(int i=0;i< listaDepartamento.getTamanio();i++){ %>
										<%BUbigeo ubigeo = (BUbigeo)listaDepartamento.getElemento(i); %>
										<%System.out.println(ubigeo.getDepartamento()+"-"+ubigeoCliente.getDepartamento()); %>
										<option value='<%=ubigeo.getDepartamento()%>' <%=ubigeo.getDepartamento().equals(ubigeoCliente.getDepartamento())?"SELECTED":""%> ><%=ubigeo.getNombre()%></option>
									<%} %>
								</select></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">e-mail:</div>
								</td>
								<td><input type="text" name="txtEMail" id="txtEMail"
									value="<%=cliente.getCorreo()==null?"":cliente.getCorreo() %>" 
									onKeyPress="Upper();SoloCorreo(this.value);"  
									style="width: 150px" class="text  ui-corner-all" /></td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Provincia: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selProvincia" onChange="buscarDistrito()" name="selProvincia" >
									<option value='-1'>--Seleccionar--</option>
									<%for(int i=0;i< listaProvincia.getTamanio();i++){ %>
										<%BUbigeo ubigeo = (BUbigeo)listaProvincia.getElemento(i); %>
										<option value='<%=ubigeo.getProvincia()%>' <%=ubigeo.getProvincia().equals(ubigeoCliente.getProvincia())?"SELECTED":""%> ><%=ubigeo.getNombre()%></option>
									<%} %>
								</select></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="left">Tel&eacute;fono casa:</div>
								</td>
								<td><input name="txtTelefono" type="text"
									class="text  ui-corner-all" id="txtTelefono"
									value="<%=cliente.getTelefono()==null?"":cliente.getTelefono() %>" 
									onKeyPress="Upper();SoloNumeros();" 
									style="width: 150px" maxlength="20" /></td>
								<td>&nbsp;</td>
								<td>
								<div align="left">Distrito: (*)</div>
								</td>
								<td>
								<div align="left"><select id="selDistrito" name="selDistrito" >
									<option value='-1'>--Seleccionar--</option>
									<%for(int i=0;i< listaDistrito.getTamanio();i++){ %>
										<%BUbigeo ubigeo = (BUbigeo)listaDistrito.getElemento(i); %>
										<option value='<%=ubigeo.getDistrito()%>' <%=ubigeo.getDistrito().equals(ubigeoCliente.getDistrito())?"SELECTED":""%> ><%=ubigeo.getNombre()%></option>
									<%} %>
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
									value="<%=cliente.getTelefonoDos()==null?"":cliente.getTelefonoDos() %>" 
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
									value="<%=cliente.getDireccion()==null?"":cliente.getDireccion() %>" 
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
					onClick="cerrar()" 
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
<input type="hidden" name="hddOperacion" id="hddOperacion" value="almacenarClienteModificado" />
<input type="hidden" name="hddCodigo" id="hddCodigo" value="<%=cliente.getCodigo()%>" />
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
	var email = frmNuevoCliente.txtEMail.value;
	var fono1 = frmNuevoCliente.txtTelefono.value;
	var fono2 = frmNuevoCliente.txtCelular.value;
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

	if(email != "" && !fncEsEmail(email)){
		alert("Debe ingresar un correo válido.");
		frmNuevoCliente.txtEMail.focus();
		return;
	}

	if(fono1 != "" && fono1.length<7){
		alert("El telefono de casa debe tener mínimo 7 dígitos");
		frmNuevoCliente.txtTelefono.focus();
		return;
	}	
	if(fono2 != "" && fono1.length<9){
		alert("El telefono celular debe tener mínimo 9 dígitos");
		frmNuevoCliente.txtCelular.focus();
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
function cerrar(){
	frmNuevoCliente.action="<%=ruta%>/jsp/comun/cuerpo.jsp";
	frmNuevoCliente.submit();
}
</script>
</html>
