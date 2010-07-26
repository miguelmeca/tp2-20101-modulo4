/**
 * Resumen.
 * Objeto                     : BProducto.
 * Descripción                : Bean de productos de fidelizacion.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import java.sql.SQLException;

import pe.com.upz.comun.ConnectDS;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

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
	 * nombre del producto
	 */
	private String nombre;
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
	 * ruta de la imagen del producto.
	 */
	private String rutaImagen;
	
	/**
	 * Listado de equivalencia de puntos.
	 */
	private Lista listaPuntaje;

	/**
	 * Codigo Imagen
	 */
	private BImagenProducto imagen;
	/**
	 * Retorna codigo del producto.
	 * @return codigo del producto.
	 */
	/**
	 * @return
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
	public long obtenerMaximoSolicitar(int codSucursal){
		int cantidad =0;

			IProducto daoProducto = new DProducto();
			try {
				int mes= Integer.parseInt( ConnectDS.obtenerFechaFormato("MM"))-1;
				int anio = Integer.parseInt( ConnectDS.obtenerFechaFormato("YYYY"));
				cantidad = daoProducto.obtenerSucursalProductoPromedio(codSucursal, codigo, anio, mes);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return cantidad;
	}
	/**
	 * Realiza el calculo de la cantidad maxia a solicitar en una orden de pedido.
	 * @return cantidad maxima a solcitar.
	 */
	public boolean obtenerSiExisteMovimiento(int codSucursal){
		boolean cantidad =false;

			IProducto daoProducto = new DProducto();
			try {
			
				cantidad = (daoProducto.consultarSalida(codSucursal, codigo));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return cantidad;
	}
	/**
	 * Retorna el codigo formateado con ceros
	 * @return codigo formateado con ceros, tipo String.
	 */
	public String obtenerCodigoCadena(){
		return ""+codigo;
	}

	/**
	 * Retorna ruta de la imagen, tipo String.
	 * @return ruta de la imagen, tipo String.
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * Setea ruta de la imagen, tipo String.
	 * @param rutaImagen ruta de la imagen, tipo String.
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public BImagenProducto getImagen() {
		return imagen;
	}

	public void setImagen(BImagenProducto codigoImagen) {
		this.imagen = codigoImagen;
	}
	/**
	 * Retorna nombre del producto
	 * @return nombre nombre del producto, tipo String.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea nombre del producto
	 * @param nombre nombre del producto, tipo String.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna listado con los puntajes del producto.
	 * @return listado con los puntajes del producto, tipo Lista.
	 */
	public Lista getListaPuntaje() {
		return listaPuntaje;
	}

	/**
	 * Setea listado con los puntajes del producto.
	 * @param listaPuntaje listado con los puntajes del producto, tipo Lista.
	 */
	public void setListaPuntaje(Lista listaPuntaje) {
		this.listaPuntaje = listaPuntaje;
	}


	
}
