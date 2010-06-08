/**
 * Resumen.
 * Objeto                     : Lista.
 * Descripción                : Clase que implementa la estructura de una lista 
 *								para el uso comun dentro del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.util;

import java.util.ArrayList;

/**
 * Clase para el manejo de listas y paginacion en los jsp.
 */
public class Lista {

	/**
	 * lista de elmentos
	 */
	protected ArrayList lista = new ArrayList();

	/**
	 * indica si existe un elemeto anterior.
	 */
	private boolean anterior;
	/**
	 * indica el index del elemento disponible.
	 */
	private int disponible;

	/*tamaño del 1 al numPagina*/
	/**
	 * indica el numero de pagina actual.
	 */
	private int numPagina;
	/**
	 * indica si existe un elemeto siguiente.
	 */
	private boolean siguiente;
	/**
	 * tamanio de los elementos para la paginacion.
	 */
	private int tamPagina;

	/**
	 * Cantidad de paginas postradas en la web.
	 */
	private int cantidadPaginasMostradas;
	
	/**
	 * Elimina el primer elemento de la lista.
	 */
	public void eliminarElemento() {
		try {
			lista.remove(0);
		} catch (Exception e) {
		}
	}
	/**
	 * Elimina el elemento indicado de la lista.
	 * @param index indice del elemento a eliminar, tipo int.
	 */
	public void eliminarElemento(int index) {
		try {
			lista.remove(index);
		} catch (Exception e) {
		}
	}
	/**
	 * indica la existencia de un elemento en el listado.
	 * @param elemento elemento a buscar, tipo Bean.
	 * @return verdad o falso de existencia del elemento.
	 */
	public boolean existeElemento(Bean elemento) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equals(elemento))
				return true;
		}
		return false;
	}
	/**
	 * Obtiene el indice del elemento disponible.
	 * @return elemento disponible.
	 */
	public int getDisponible() {
		return disponible;
	}

	/**
	 * Retorna la cantidad de elementos de la lista. 
	 * @return cantidda de elementos
	 */
	public int getNumeroElementos() {
		return lista.size();
	}

	/**
	 * Obtiene el primer elemento de la lista.
	 * @return primer elemento de la lista.
	 */
	public Bean getElemento() {
		Bean elemento = null;

		try {
			elemento = (Bean) lista.get(0);
		} catch (Exception e) {
		}
		return elemento;
	}
	/**
	 * Obtiene el elemento indicado de la lista por pagina.
	 * @param index indice del elemento a retornar.
	 * @return
	 */
	public Bean getElementoEnPagina(int index) {
		Bean elemento = null;
		try {
			elemento = (Bean) lista.get(index + tamPagina * numPagina);
		} catch (Exception e) {
			System.out.println("getElemento" + e);
		}
		return elemento;
	}
	/**
	 * Obtiene el elemento indicado de la lista.
	 * @param index indice del elemento a retornar.
	 * @return
	 */
	public Bean getElemento(int index) {
		Bean elemento = null;
		try {
			elemento = (Bean) lista.get(index);
		} catch (Exception e) {
			System.out.println("getElemento" + e);
		}
		return elemento;
	}	
	
	/** 
	 * Obtiene la posición del primer elemento de la pagina.
	 * @param page posicion del primer elemento.
	 * @return
	 */
	public int getFirstElementPage(int page) {
		return (page - 1) * getTamPagina() + 1;
	}
	
	/**
	 * Obtiene la posición del último elemento de la pagina.
	 * @param page posicion del ultimo elemento.
	 * @return
	 */
	public int getLastElementPage(int page) {

		int intPos = 0;

		if (page * getTamPagina() > getTamanio()) {
			intPos =
				(page - 1) * getTamPagina() + getTamanio() % getTamPagina();
		} else {
			intPos = page * getTamPagina();
		}

		return intPos;
	}
	/**
	 * Procedimiento que actualiza los datos en la navegacion de una página siguiente.
	 */
	public void getNextPage() {

		int total;
		int restantes;
		total = getTamanio();
		if (total > (numPagina + 1) * tamPagina) {
			numPagina++;

			restantes = total - numPagina * tamPagina;

			if (restantes >= tamPagina)
				disponible = tamPagina;
			else
				disponible = restantes;

			anterior = (numPagina > 0);

			if (restantes > tamPagina)
				siguiente = true;
			else
				siguiente = false;

		}
	}
	/**
	 * Obtiene el numero inicial de las paginas a mostrar.
	 * @return numero de paginas a mostrar, tipo int.
	 */
	public int getNumPaginaInicialEnPaginacion() {
		int intPaginas;
		int totalPaginas = (int) Math.ceil((double) getTamanio() / (double) getTamPagina());
		
		int numero;
		
		if(numPagina - 2 <= 1){
			numero = 1;
		}else if (numPagina + 2 > totalPaginas){
			numero = (numPagina-cantidadPaginasMostradas)+(totalPaginas- numPagina + 1);
		}else{
			numero = numPagina-2;
		}
		
		return numero;
		
		
		//return (int) Math.ceil((double) getTamanio() / (double) getTamPagina());

	}
	/**
	 * Procedimiento que actualiza los datos en la navegacion de una página anterior.
	 */
	public void getPrevPage() {
		if (numPagina > 0) {
			numPagina--;
			disponible = tamPagina;
			siguiente = true;
			anterior = (numPagina > 0);
		}
	}
	/**
	 * Retorna el tamaño de la lista.
	 * @return tamanio de la lista.
	 */
	public int getTamanio() {
		return lista.size();
	}
	/**
	 * Retorna el tamamanio de elementos dentro de una pagina.
	 * @return cantidadde elementos en una pagina.
	 */
	public int getTamPagina() {
		return tamPagina;
	}
	/**
	 * Inicia la paginacion.
	 */
	public void init() {
		tamPagina = 3;
		numPagina = -1;
		getNextPage();
	}
	/**
	 * Retorna el indicador del elemento anterior en la lista.
	 * @return indicador si es anterior.
	 */
	public boolean isAnterior() {
		return anterior;
	}
	/**
	 * Retorna el indicador del elemento siguiente en la lista.
	 * @return valor de verdadero o falso de existir una anterior pagina.
	 */
	public boolean isSiguiente() {
		return siguiente;
	}
	/**
	 * Limpia la lista de elementos.
	 */
	public void limpiarLista() {
		lista.clear();
	}
	/**
	 * Setea el valor verdadero o falso de la existencia de una anterior pagina.
	 * @param newAnterior indica si existe una anterior pagina, tipo boolean.
	 */
	public void setAnterior(boolean newAnterior) {
		anterior = newAnterior;
	}
	/**
	 * Setea el valor de la pagina disponible
	 * @param newDisponible valor de la pagina disponible, tipo int.
	 */
	public void setDisponible(int newDisponible) {
		disponible = newDisponible;
	}
	/**
	 * Setea un nuevo elemento en el final de la lista.
	 * @param elemento bean a setear en la lista, tipo Bean.
	 */
	public void setElemento(Bean elemento) {
		try {
			lista.set(lista.size(), elemento);
		} catch (Exception e) {
			lista.add(elemento);
		}
	}
	/**
	 * Setea un nuevo elemento en la lista indicando su ubicacion.
	 * @param elemento bean a setear en la lista, tipo Bean.
	 * @param index indice para el elemento a setear, tipo int.
	 * @return elemento seteado.
	 */
	public Bean setElemento(Bean elemento, int index) {
		try {
			lista.set(index, elemento);
		} catch (Exception e) {
			lista.add(elemento);
		}
		return elemento;
	}
	/**
	 * Setea el numero de la pagina.
	 * @param newNumPagina numero de la pagina, tipo int.
	 */
	public void setNumPagina(int newNumPagina) {
		numPagina = newNumPagina;
	}
	/**
	 * Retorna el numero de la pagina.
	 * @param newNumPagina numero de la pagina, tipo int.
	 */
	public int getNumPagina() {
		return numPagina;
	}
	/**
	 * Setea el valor verdadero o falso de la existencia de una siguiente pagina.
	 * @param newSiguiente indica si exiiste una siguiente pagina, tipo boolean.
	 */
	public void setSiguiente(boolean newSiguiente) {
		siguiente = newSiguiente;
	}
	/**
	 * Setea el nuevo tamamanio de elementos dentro de una pagina.
	 * @param newTamPagina tamanio de paginacion, tipo int.
	 */
	public void setTamPagina(int newTamPagina) {
		tamPagina = newTamPagina;
	}
	/**
	 * Retorna cantidad de paginas mostradas en la web.
	 * @return cantidadPaginasMostradas cantidad de paginas mostradas en la web, tipo int.
	 */
	public int getCantidadPaginasActual() {
		int totalPaginas = (int) Math.ceil((double) getTamanio() / (double) getTamPagina());
		return (cantidadPaginasMostradas >totalPaginas?totalPaginas:cantidadPaginasMostradas );
	}
	/**
	 * Retorna cantidad de paginas mostradas en la web.
	 * @return cantidadPaginasMostradas cantidad de paginas mostradas en la web, tipo int.
	 */
	public int getCantidadPaginasMostradas() {
		return cantidadPaginasMostradas;
	}
	/** 
	 * Setea cantidad de paginas mostradas en la web.
	 * @param cantidadPaginasMostradas cantidad de paginas mostradas en la web, tipo int.
	 */
	public void setCantidadPaginasMostradas(int cantidadPaginasMostradas) {
		this.cantidadPaginasMostradas = cantidadPaginasMostradas;
	}
	/**
	 * Obtiene el total de paginas de la lista.
	 * @return  cantidad de paginas, tipo int.
	 */
	public int getCantidadPaginasDeListado() {
		return   (int) Math.ceil((double) getTamanio() / (double) getTamPagina());
	}	
	
	
}
