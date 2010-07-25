package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BImagenProducto;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IImagenProducto;
import pe.com.upz.util.Lista;

public class DImagenProducto implements IImagenProducto{

	@Override
	public Lista obtenerListadoImagenes() throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BImagenProducto imagen;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select codigo,        nombre_imagen,       archivo  from fidelizacion.imagen_producto");

		pstm = conn.prepareStatement(sql.toString());
	
		rs = pstm.executeQuery();
		
		
		while(rs.next()){
			imagen = new BImagenProducto();
			imagen.setCodigo(rs.getInt("codigo"));
			imagen.setNombre(rs.getString("nombre_imagen"));
			imagen.setArchivo(rs.getString("archivo"));
			
			lista.setElemento(imagen);
		}
		
		rs.close();
		pstm.close();
		conn.close();
		
		return lista;
	}

}
