/**
 * Resumen.
 * Objeto                     : BCuenta.
 * Descripción                : Bean de cuenta del programa.
 * Fecha de Creación          : 01/07/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

/**
 * Bean de cuenta del programa.
 *
 */
public class BCuenta extends Bean {

	/**
	 * codigo de la cuenta
	 */
	private int codigo;
	/**
	 * cantidad de puntos acumulados
	 */
	private int puntosAcumulados;
	/**
	 * cantidad de puntos vencidos
	 */
	private int puntosVencidos;
	/**
	 * cantidad de puntos canjeados
	 */
	private int puntosCanjeados;
	/**
	 * estado de la cuenta
	 */
	private int estado;
	
	/**
	 * Listado de tarjetas
	 */
	private Lista tarjeta;
	
	/**
	 * Retorna codigo de la cuenta.
	 * @return codigo de la cuenta, tipo int.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo de la cuenta.
	 * @param codigo codigo de la cuenta, tipo int.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna puntos acumulados.
	 * @return puntos acumulados, tipo int.
	 */
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}
	/**
	 * Setea puntos acumulados.
	 * @param puntosAcumulados puntos acumulados, tipo int.
	 */
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}
	/**
	 * Retorna puntos vencidos.
	 * @return puntos vencidos, tipo int.
	 */
	public int getPuntosVencidos() {
		return puntosVencidos;
	}
	/**
	 * Setea puntos vencidos.
	 * @param puntosVencidos puntos vencidos, tipo int.
	 */
	public void setPuntosVencidos(int puntosVencidos) {
		this.puntosVencidos = puntosVencidos;
	}
	/**
	 * Retorna cantidad de puntos canjeados.
	 * @return cantidad de puntos canjeados, tip int.
	 */
	public int getPuntosCanjeados() {
		return puntosCanjeados;
	}
	/**
	 * Setea cantidad de puntos canjeados.
	 * @param puntosCanjeados cantidad de puntos canjeados, tip int.
	 */
	public void setPuntosCanjeados(int puntosCanjeados) {
		this.puntosCanjeados = puntosCanjeados;
	}
	/**
	 * Retorna estado de la cuenta.
	 * @return estado de la cuenta, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea estado de la cuenta.
	 * @param estado estado de la cuenta, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * Obtiene listado de tarjetas de la cuenta.
	 * @return listado de tarjetas de la cuenta, tipo Lista
	 */
	public Lista getTarjeta() {
		return tarjeta;
	}
	/**
	 * Retorna listado de tarjetas de la cuenta.
	 * @param tarjeta listado de tarjetas de la cuenta, tipo Lista
	 */
	public void setTarjeta(Lista tarjeta) {
		this.tarjeta = tarjeta;
	}
	/**
	 * Retorna el cliente principal de la cuenta
	 * @return cliente principal de la cuenta, tipo BCliente.
	 */
	public BCliente obtenerClientePrincipal(){
		BCliente clinete = new BCliente();
		for(int i = 0; i < tarjeta.getTamanio();i++){
			BTarjetaFidelizacion miTarjeta = (BTarjetaFidelizacion)tarjeta.getElemento(i);
			
			if(miTarjeta.getTipoCliente() == 1){
				clinete = miTarjeta.getCliente();
			}
			
		}
	
		return clinete;
	}
	/**
	 * Retorna el nñumero de la tarjeta del titular de la cuenta.
	 * @return numero de tarjeta del titular, tipo String.
	 */
	public String obtenerTarjetaPrincipal(){
		String numero="";
		for(int i = 0; i < tarjeta.getTamanio();i++){
			BTarjetaFidelizacion miTarjeta = (BTarjetaFidelizacion)tarjeta.getElemento(i);
			
			if(miTarjeta.getTipoCliente() == 1){
				numero = miTarjeta.getNumero();
				return numero;
			}
			
		}
		return numero;
	}
}
