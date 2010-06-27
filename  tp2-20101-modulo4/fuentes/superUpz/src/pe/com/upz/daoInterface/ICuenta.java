package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Lista;

public interface ICuenta {
	public abstract Lista obtenerListadoCuenta(boolean soloActivos,
			int filtro, String valorAux, String valorAux2)
			throws SQLException;
	
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario)throws SQLException;
	
	public abstract int obtenerMaximoNumeroCuenta(Connection conn)throws SQLException;
	
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario, BSucursal sucursal)throws SQLException;

	public abstract void actualizarPuntaje (Connection conn, int codcuenta, int cantidadPuntos,BUsuario usuario)throws SQLException;
}
