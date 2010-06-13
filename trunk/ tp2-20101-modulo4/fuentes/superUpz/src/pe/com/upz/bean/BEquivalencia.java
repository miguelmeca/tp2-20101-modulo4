package pe.com.upz.bean;

import pe.com.upz.util.Bean;

public class BEquivalencia extends Bean{

	/**
	 * Primer monto en soles de la equivalencia.
	 */
	private double montoUno;			
	/**
	 * Primera equivalenci de puntos.
	 */
	private int cantidadPuntoUno;			
	/**
	 * segundo monto en soles de la equivalencia.
	 */
	private double montoDos;		
	/**
	 * Primera equivalenci de puntos.
	 */
	private int cantidadPuntoDos;			
	/**
	 * Segunda monto en soles de la equivalencia.
	 */
	private double montoTres;		
	/**
	 * Tercer equivalenci de puntos.
	 */
	private int cantidadPuntoTres	;		

	/**
	 * Estado de la equivalencia.
	 */
	private int estado;		
	/**
	 * codigo de la equivalencia.
	 */
	private int codigo	;
	/**
	 * Retorna Primer monto en soles de la equivalencia
	 * @return Primer monto en soles de la equivalencia.
	 */
	public double getMontoUno() {
		return montoUno;
	}
	/**
	 * Setea Primer monto en soles de la equivalencia.
	 * @param montoUno Primer monto en soles de la equivalencia,tipo double.
	 */
	public void setMontoUno(double montoUno) {
		this.montoUno = montoUno;
	}
	/**
	 * Retorna Primera equivalencia de puntos.
	 * @return cantidadPuntoUno Primera equivalencia de puntos, tipo int.
	 */
	public int getCantidadPuntoUno() {
		return cantidadPuntoUno;
	}
	/**
	 * Setea Primera equivalenci de puntos.
	 * @param cantidadPuntoUno Primera equivalencia de puntos.
	 */
	public void setCantidadPuntoUno(int cantidadPuntoUno) {
		this.cantidadPuntoUno = cantidadPuntoUno;
	}
	/**
	 * Retorna Segundo monto en soles de la equivalencia.
	 * @return montoDos Segundo monto en soles de la equivalencia,tipo double.
	 */
	public double getMontoDos() {
		return montoDos;
	}
	/**
	 * Setea Segundo monto en soles de la equivalencia.
	 * @param montoDos Segundo monto en soles de la equivalencia,tipo double.
	 */
	public void setMontoDos(double montoDos) {
		this.montoDos = montoDos;
	}
	/**
	 * Retorna cantidad dos de los puntos de equivalencia.
	 * @return cantidadPuntoDos cantidad dos de los puntos de equivalencia, tipo int.
	 */
	public int getCantidadPuntoDos() {
		return cantidadPuntoDos;
	}
	/**
	 * Setea cantidad dos de los puntos de equivalencia.
	 * @param cantidadPuntoDos cantidad dos de los puntos de equivalencia, tipo int.
	 */
	public void setCantidadPuntoDos(int cantidadPuntoDos) {
		this.cantidadPuntoDos = cantidadPuntoDos;
	}
	/**
	 * Retorna cantidad en soles tres de la equivalencia.
	 * @return montoTres cantidad en soles tres de la equivalencia, tipo double.
	 */
	public double getMontoTres() {
		return montoTres;
	}
	/**
	 * Setea cantidad en soles tres de la equivalencia, tipo double.
	 * @param montoTres cantidad en soles tres de la equivalencia, tipo double.
	 */
	public void setMontoTres(double montoTres) {
		this.montoTres = montoTres;
	}
	/**
	 * Retorna cantidad tres de los puntos de equivalencia.
	 * @return cantidadPuntoTres cantidad tres de los puntos de equivalencia, tipo int.
	 */
	public int getCantidadPuntoTres() {
		return cantidadPuntoTres;
	}
	/**
	 * Setea cantidad tres de los puntos de equivalencia.
	 * @param cantidadPuntoTres cantidad tres de los puntos de equivalencia, tipo int.
	 */
	public void setCantidadPuntoTres(int cantidadPuntoTres) {
		this.cantidadPuntoTres = cantidadPuntoTres;
	}
	/**
	 * Retorna el estado de la equivalencia.
	 * @return estado de la equivalencia, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea el estado de la equivalencia.
	 * @param estado estado de la equivalencia, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * Retorna el codigo de la equivalencia.
	 * @return codigo codigo de la equivalencia, tipo int.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea el codigo de la equivalencia.
	 * @param codigo el codigo de la equivalencia, tipo int.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}						
	
	
}
