package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.util.Lista;

public interface IImagenProducto {

	/**
	 * Obtiene las imagenes de la galeria
	 * @return Lista de imagenes
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoImagenes()throws SQLException;

}
