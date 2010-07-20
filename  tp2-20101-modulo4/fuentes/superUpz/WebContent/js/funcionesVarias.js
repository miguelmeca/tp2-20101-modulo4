document.onkeydown=function(){
  var keycode = event.keyCode;
    if(window.event){
      if((keycode >= 113) && (keycode <= 123)){
        //Bloquear las teclas de funcion excepto F1
        window.event.cancelBubble = true;
        window.event.keyCode = 8;
        window.event.returnValue = false;
        return false;
      }
    }
    //Bloquear la tecla de Backspace
    if ((keycode == 8) && (event.srcElement.form == null || event.srcElement.isTextEdit == false)){
      window.event.cancelBubble = true;
      window.event.returnValue = false;
    }
    var teclaU=85; 
    teclactrl=event.ctrlKey;
    //cancela la opcion nueva ventana Ctrl+U
    if ((teclactrl)&&(keycode==teclaU)){ 
      return false; 
    } 
    var teclaalt = event.altKey;
    if ((teclaalt)&&(keycode==37 || keycode==39)){//cancela la opcion Alt + Atras y Alt + Adelante
      return false;
    }
    return true;
  }
  
document.oncontextmenu=new Function("return false");

function RegresarPagePrincipal(){
  location.href="../frame/principal.htm";
}

/**
  * Ejecuta la función recursiva.
  * obj, objeto a modificar.
  * tope, limite de profundidad.
  * deshabilitar, flag bloquear / desbloquear.
  */
  function ejecutarFuncionRecursiva(obj, tope, deshabilitar){
    var num = 0;
    var index;
    try{
      num = obj.children.length;
    }catch(e){
      num = 0;
    }
    if (tope <= 20 && num > 0){              
      for (index = 0; index < num; index++) {
        ejecutarFuncionRecursiva(obj.children[index], tope + 1, deshabilitar);
      }
    }
    verificarBloqueoControl(obj, deshabilitar);
  }
  
/**
  * Verifica el bloqueo de los controles.
  * obj, objeto a bloquear / desbloquear.
  * deshabilitar, flag bloquear / desbloquear.
  */
  function verificarBloqueoControl(obj, deshabilitar){
    if(obj.tagName == 'INPUT' || obj.tagName == 'input'){
      obj.disabled = deshabilitar;
    }
    if (obj.tagName == 'option' || obj.tagName == 'OPTION'){
      obj.disabled = deshabilitar;
    }
    if (obj.tagName == 'Select' || obj.tagName == 'SELECT'){
      obj.disabled = deshabilitar;
    }
    if(obj.tagName == 'A' || obj.tagName == 'a'){
      obj.disabled = deshabilitar; 
      obj.href = "javascript:reDireccion();";
      obj.onclick = reDireccion;
    }
  }
  
/**
  * Habilita / inhabilita los controles.
  * objName, nombre de los objetos.
  * deshabilitar, deshabilita los controles.
  */
  function inhabilitarControles(objName, deshabilitar){
    var obj = document.getElementById(objName);            
    ejecutarFuncionRecursiva(obj, 0, deshabilitar);
  }

//---------------------------------------------------
function fncLongitudMinima(strTexto, intLongitud)
{    
    if (strTexto) {
        if (strTexto.length >= intLongitud){return true;}
        else{return false;}        
    }    
    else{return false;}    
} 
function SoloCorreo(texto){

    if(window.event.keyCode!=13)
    { var Tecla;
        Tecla = String.fromCharCode(window.event.keyCode);
    texto=texto+Tecla;
    
        if ( ! ( IsNumeric( Tecla ) || IsLetter( Tecla ) || Tecla=="_"  || Tecla=="." || Tecla=="@" )  )
        {        window.event.keyCode = 0;        }

    if(Tecla==".")
    {  var myArray = texto.split("@");
        var arrPunto = myArray[0].split(".");
        if(texto.substring(texto.length-2,texto.length)=="..")
            {        window.event.keyCode = 0;        }
    }

    if(Tecla=="_")
    {  var myArray = texto.split("@");
        if(myArray.length > 1)
        {  var arrGuion = myArray[1].split("_");
            if(arrGuion.length > 1)
            {        window.event.keyCode = 0;        }
        }
    }

    if(IsLetter(texto.substring(0,1))==false)
    {        window.event.keyCode = 0;        }

    if(Tecla=="@")
    {  var myArray = texto.split("@");
        if(myArray.length > 2)
        {        window.event.keyCode = 0;        }
    }

    var myArray = texto.split("@");
    if(myArray.length > 1)      
    { if(myArray[1]!="")
      { if(!IsNumeric(myArray[1].substring(0,1)) && !IsLetter(myArray[1].substring(0,1)))
        {        window.event.keyCode = 0;        }
      }
    }

    }    
}


function fncEsEmail(string) //string = cadena que representa al correo electronico
{//valida si la entrada es un correo electronico si es cierto devuelve true
      if (string.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
          return true;
        else
          return false;
}

function IsNumeric(ch){
	return  (ch>="0" && ch<="9") == true;
}

function llenarComboListaCodTexto(listValores, separador1, separador2,
		comboDestino) {
	var arrElementos = listValores.split(separador1);
	var i = 0, objElemento = null, arrCodTexto = null;
	var codigo, texto;

	comboDestino.length = 0; //Borra los datos

	//Agrega la primera fila con un texto para seleccionar
	comboDestino.length = comboDestino.length + 1;
	comboDestino.options[comboDestino.length - 1].value = -1;
	comboDestino.options[comboDestino.length - 1].text = "-Seleccione-";

	for (i = 0; i < arrElementos.length; i++) {
		objElemento = arrElementos[i];
		arrCodTexto = objElemento.split(separador2);

		if (arrCodTexto.length == 2) {
			codigo = arrCodTexto[0];
			texto = arrCodTexto[1];

			comboDestino.length = comboDestino.length + 1;
			comboDestino.options[comboDestino.length - 1].value = codigo;
			comboDestino.options[comboDestino.length - 1].text = texto;
		}
	}
}
function LCHTrim(vString, ch) {
	var j;
	for (j = 0; j < vString.length && (vString.charAt(j) == ch); j++)
		;

	return vString.substr(j);
}

function RCHTrim(vString, ch) {
	var j;
	for (j = vString.length; j > 0 && (vString.charAt(j - 1) == ch); j--)
		;

	return vString.substring(0, j);
}

function RTrim(vString) {
	return RCHTrim(vString, ' ');
}

function LTrim(vString) {
	return LCHTrim(vString, ' ');
}

function Trim(vString) {
	return LTrim(RTrim(String(vString)));
}

function FiltroDecimal(texto) {
	if (window.event.keyCode != 13) {
		var Tecla;
		Tecla = String.fromCharCode(window.event.keyCode);
		var porc = texto;
		porc = porc + Tecla;

		if (porc.length == 1) {
			if (Tecla == ".") {
				window.event.keyCode = 0;
			}
		}

		var myArray = texto.split(".");
		if (myArray.length >= 2) {
			if (myArray.length == 2) {
				if (IsNumeric(Tecla) == false) {
					window.event.keyCode = 0;
				} else {
					if (myArray[1].length >= 2) {
						window.event.keyCode = 0;
					}
				}
			} else {
				window.event.keyCode = 0;
			}
		}
	}
}
function Upper() {

	if (String.fromCharCode(window.event.keyCode) >= "a"
			&& String.fromCharCode(window.event.keyCode) <= "z") {
		window.event.keyCode = window.event.keyCode - 32;
	}

	if (String.fromCharCode(window.event.keyCode) == "ñ") {
		window.event.keyCode = window.event.keyCode - 32;
	}

	if (String.fromCharCode(window.event.keyCode) == "á"
			|| String.fromCharCode(window.event.keyCode) == "é"
			|| String.fromCharCode(window.event.keyCode) == "í"
			|| String.fromCharCode(window.event.keyCode) == "ó"
			|| String.fromCharCode(window.event.keyCode) == "ú") {
		window.event.keyCode = window.event.keyCode - 32;
	}

}

function SoloNumeros() {
	
	if (window.event.keyCode != 13) {
		var Tecla;
		Tecla = String.fromCharCode(window.event.keyCode);
		if (!(Tecla >= "0" && Tecla <= "9")) {
			window.event.keyCode = 0;
		}
	}
}
/*indica si el caracter en . ' ´
 * tecla, tecla pulsada
 */
function validarCaracterPersonaNatural(tecla) {
	if ((tecla == ".") || (tecla == "'") || (tecla == "´") || (tecla == " ")) {
		return true;
	}
	return false;
}
/*indica si el caracter en una letra
 * tecla, tecla pulsada
 */
function validarLetra(tecla) {
	if ((tecla >= "A" && tecla <= "Z") || (tecla >= "a" && tecla <= "z")
			|| (tecla == "Á") || (tecla == "É") || (tecla == "Í")
			|| (tecla == "Ó") || (tecla == "Ú") || (tecla == "á")
			|| (tecla == "é") || (tecla == "í") || (tecla == "ó")
			|| (tecla == "ú") || (tecla == "ñ") || (tecla == "Ñ")
			|| (tecla == "ü") || (tecla == "Ü") || (tecla == "À")
			|| (tecla == "È") || (tecla == "Ì") || (tecla == "Ò")
			|| (tecla == "Ù") || (tecla == "à") || (tecla == "è")
			|| (tecla == "ì") || (tecla == "ò") || (tecla == "ù")) {
		return true;
	}
	return false;
}

/*indica si el caracter en . , / \ - _
 * tecla, tecla pulsada
 */
function validarCaracterEspecial(tecla) {
	if ((tecla == "/") || (tecla == "\\") || (tecla == "-") || (tecla == "_")
			|| (tecla == ".") || (tecla == ",")) {
		return true;
	}
	return false;
}

/*indica si el caracter en un numero
 * tecla, tecla pulsada
 */
function validarNumeros(tecla) {
	if (tecla >= "0" && tecla <= "9") {
		return true;
	}
	return false;
}

function permitirLetrasEspeciales() {
	if (window.event.keyCode != 13) {
		var tecla;
		tecla = String.fromCharCode(window.event.keyCode);
		if (!(validarLetra(tecla) || (tecla == " ") || validarCaracterEspecial(tecla))) {
			window.event.keyCode = 0;
		}
	}
}

/*Permite el ingreso de letras, numero, coma, punto, guion y barra inclinada
 */
function permitirLetraNumeroEspeciales() {
	if (window.event.keyCode != 13) {
		var tecla;
		tecla = String.fromCharCode(window.event.keyCode);
		if (!(validarNumeros(tecla) || (tecla == " ")
				|| validarCaracterEspecial(tecla) || (validarLetra(tecla)))) {
			window.event.keyCode = 0;
		}
	}
}

/*valida el ingreso de un nombe de persona natural.
 */
function filtrarNombrePersonaNatural() {
	if (window.event.keyCode != 13) {
		var tecla;
		tecla = String.fromCharCode(window.event.keyCode);
		if (!(validarLetra(tecla) || validarCaracterPersonaNatural(tecla))) {
			window.event.keyCode = 0;
		}
	}

}

//
//<input name="txtPagoPorc" type="text" 
//                                            maxlength="3" 
//                                            onKeyPress="SoloNumeros()">
//
//<input type="text" name="txtNombreCorto" 
//                         width="280" size="20" 
//                         maxlength="20" 
//                         value="" 
//                         onKeyPress="Upper();
//                                permitirLetrasEspeciales();">
//
//<input name="txtPagoMonto" type="text" 
//                                    maxlength="10" 
//                                    onKeyPress="FiltroDecimal(this.value); 
//                                            activarBotonAceptar()">
