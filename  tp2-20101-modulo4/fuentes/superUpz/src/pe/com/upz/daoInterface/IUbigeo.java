package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.bean.BUbigeo;
import pe.com.upz.util.Lista;

public interface IUbigeo {

	public abstract Lista obtenerListadoUbigeo(String departamento, String provincia, String distrito)throws SQLException;

	public abstract Lista obtenerDepartamentos()throws SQLException;
	
	public abstract Lista obtenerProvinciasDeDepartamento(String departamento)throws SQLException;
	
	public abstract Lista obtenerDistritosDeprovincia(String departamento,String provincia)throws SQLException;

	public abstract int obtenerCodigoUbigeo(String departamento,String provincia, String distrito)throws SQLException;
}
