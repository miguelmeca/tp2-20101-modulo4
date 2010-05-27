/**
 * Resumen.
 * Objeto                     : BProducto.
 * Descripci�n                : Bean de productos de fidelizacion.
 * Fecha de Creaci�n          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de productos de fidelizacion.
 *
 */
public class BProducto extends Bean{

	/**
	 * codigo del producto
	 */
	private int codigo;
	/**
	 * descripcion del producto
	 */
	private String descripcion;
	/**
	 * estado de vigencia del producto
	 */
	private int estado;
	/**
	 * stock del producto
	 */
	private long stock;
	/**
	 * tipo de producto
	 */
	private BTipoProducto tipo;

	/**
	 * Retorna codigo del producto.
	 * @return codigo del producto.
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Setea codigo del producto.
	 * @param codigo codigo del producto.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna descripcion del producto.
	 * @return descripcion del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setea descripcion del producto.
	 * @param descripcion descripcion del producto.
	 */ 
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * retorna estado de vigencia del producto.
	 * @return estado de vigencia del producto.
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Setea estado de vigencia del producto.
	 * @param estado estado de vigencia del producto.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Retorna tipo del producto.
	 * @return tipo del producto.
	 */
	public BTipoProducto getTipo() {
		return tipo;
	}

	/**
	 * Setea tipo del producto.
	 * @param tipo tipo del producto.
	 */
	public void setTipo(BTipoProducto tipo) {
		this.tipo = tipo;
	}
	/**
	 * Retorna stock del producto. 
	 * @return stock del producto. 
	 */
	public long getStock() {
		return stock;
	}
	/**
	 * Setea stock del producto. 
	 * @param stock stock del producto. 
	 */
	public void setStock(long stock) {
		this.stock = stock;
	}	
	
	/**
	 * Realiza el calculo de la cantidad maxia a solicitar en una orden de pedido.
	 * @return cantidad maxima a solcitar.
	 */
	public long obtenerMaximoSolicitar(){
		return 50;
	}
	/**
	 * Retorna el codigo formateado con ceros
	 * @return codigo formateado con ceros, tipo String.
	 */
	public String obtenerCodigoCadena(){
		return ""+codigo;
	}
}