/**
 * Resumen.
 * Objeto                     : BProductoSucursal.
 * Descripción                : Clase DAO de sucursal - producto
 * Fecha de Creación          : 01/07/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.util.Bean;

public class BProductoSucursal extends Bean {

	private BSucursal sucursal;
	private BProducto producto;
	int stock;
	public BSucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(BSucursal sucursal) {
		this.sucursal = sucursal;
	}
	public BProducto getProducto() {
		return producto;
	}
	public void setProducto(BProducto producto) {
		this.producto = producto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
