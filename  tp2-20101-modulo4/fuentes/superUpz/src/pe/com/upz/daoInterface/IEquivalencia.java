package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BUsuario;

public interface IEquivalencia {

	public abstract void almacenarEquivalencia(BUsuario usuario,Connection conn,BEquivalencia equivalencia, int codProducto)throws SQLException;
	
	public abstract int obtenerMaximaCodificacion(Connection conn)throws SQLException;
	
	public abstract void eliminarEquivalenciasActivasProducto(BUsuario usuario,
			Connection conn, int codProducto) throws SQLException;
	
}
