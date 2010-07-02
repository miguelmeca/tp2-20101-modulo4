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
