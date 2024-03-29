/**
 * Resumen.
 * Objeto                     : DProducto.
 * Descripci�n                : Clase DAO de productos del sistema.
 * Fecha de Creaci�n          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BImagenProducto;
import pe.com.upz.bean.BOpcion;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de productos del sistema.
 *
 */
public class DProducto implements IProducto{

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerListadoProductos(boolean, int)
	 */
	@Override
	public Lista obtenerListadoProductos(boolean soloActivos,int filtro, String valorAux)
			throws SQLException {
		
		Connection conn = ConnectDS.obtenerConeccion();
		BProducto producto;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT   PD.PRODUCTO_ID      AS CODIGO     , \n");
		sql.append("         PD.TIPO_PRODUCTO_ID AS TIPO       , \n");
		sql.append("         TP.DESCRIPCION      AS DESTIPO    , \n");
		sql.append("         PD.NOMBRE      AS NOMBRE, \n");
		sql.append("         PD.DESCRIPCION      AS DESCRIPCION, \n");
		sql.append("         PD.STOCK            AS STOCK,       \n");
		sql.append("         PD.ESTADO AS ESTADO                 \n");
		sql.append("FROM     FIDELIZACION.PRODUCTO PD, \n");
		sql.append("         FIDELIZACION.TIPO_PRODUCTO TP \n");
		sql.append("WHERE    PD.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		if(soloActivos){
			sql.append("AND      PD.ESTADO           = 1 \n");
		}
		if(filtro == 1){
			sql.append("AND      PD.TIPO_PRODUCTO_ID = ? \n");
		}else if(filtro == 2){
			sql.append("AND      UPPER(PD.NOMBRE) LIKE '%'||?||'%' \n");
		}else if(filtro == 3){
			sql.append("AND      PD.PRODUCTO_ID = ? \n");
		}
		sql.append("ORDER BY PD.NOMBRE");
		
		pstm = conn.prepareStatement(sql.toString());
		if(filtro == 1){
			pstm.setInt(1, Integer.parseInt(valorAux));
		}else if(filtro == 2){
			pstm.setString(1, valorAux);
		}else if(filtro == 3){
			pstm.setInt(1, Integer.parseInt(valorAux));
		}
		//pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		while(rs.next()){
			producto = new BProducto();
			
			producto.setCodigo(rs.getInt("CODIGO"));
			producto.setNombre(rs.getString("NOMBRE"));
			producto.setDescripcion(rs.getString("DESCRIPCION"));
			producto.setEstado(rs.getInt("ESTADO"));
			tipo = new BTipoProducto();
			tipo.setCodigo(rs.getInt("TIPO"));
			tipo.setDescripcion(rs.getString("DESTIPO"));
			producto.setTipo(tipo);
			producto.setStock(rs.getLong("STOCK"));
			lista.setElemento(producto);
		}
		
		rs.close();
		pstm.close();
		conn.close();
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#validarFechaGeneracionOrden(java.lang.String)
	 */
	@Override
	public boolean validarFechaGeneracionOrden(String fechaHoy)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#alamacenarProducto(pe.com.upz.bean.BProducto, pe.com.upz.bean.BUsuario, java.sql.Connection)
	 */
	@Override
	public int alamacenarProducto(BProducto producto, BUsuario usuario, Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximoNumeroProducto(conn)+1;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   PRODUCTO \n");
		sql.append("       ( \n");
		sql.append("              PRODUCTO_ID         , \n");
		sql.append("              TIPO_PRODUCTO_ID    , \n");
		sql.append("              DESCRIPCION         , \n");
		sql.append("              STOCK               , \n");
		sql.append("              USUARIO_CREACION    , \n");
		sql.append("              USUARIO_MODIFICACION, \n");
		sql.append("              FECHA_CREACION      , \n");
		sql.append("              FECHA_MODIFICACION  , \n");
		sql.append("              ESTADO              , \n");
		sql.append("              codigo_imagen              , \n");
		sql.append("              NOMBRE \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              0      , \n");
		sql.append("              ?      , \n");
		sql.append("              NULL   , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              NULL   , \n");
		sql.append("              1      , \n");
		sql.append("              ?     , \n");
		sql.append("              ? \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codigo);
		pstm.setInt(2,producto.getTipo().getCodigo());
		pstm.setString(3,producto.getDescripcion());
		pstm.setString(4,usuario.getLogin());
		pstm.setInt(5,producto.getImagen().getCodigo());
		pstm.setString(6,producto.getNombre());
		pstm.executeUpdate();
		
		return codigo;
		
	}

	
	
	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerMaximoNumeroProducto(java.sql.Connection)
	 */
	public int obtenerMaximoNumeroProducto(Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigoProducto=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.PRODUCTO_ID),0) AS MAXIMO \n");
		sql.append("FROM   FIDELIZACION.PRODUCTO PE");
		
		pstm = conn.prepareStatement(sql.toString());
		
		rs = pstm.executeQuery();
		
		if (rs.next()) {
			codigoProducto=(rs.getInt("MAXIMO"));
			
		}
		rs.close();
		pstm.close();
		
		return codigoProducto;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerListadoProductosPuntaje(boolean, int)
	 */
	public Lista obtenerListadoProductosPuntaje(boolean soloActivos, int filtro, String valorAuxiliar)
			throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BProducto producto;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PR.PRODUCTO_ID  AS CODIGO            , \n");
		sql.append("       PR.TIPO_PRODUCTO_ID  AS TIPO        , \n");
		sql.append("       nvl(IM.ARCHIVO,'NODISPONIBLE.jpg')  AS ARCHIVO        , \n");
		sql.append("       TP.DESCRIPCION  AS DESTIPO            , \n");
		sql.append("       PR.NOMBRE    AS NOMBRE               , \n");
		sql.append("       NVL(EQ.MONTO_UNO,0)   AS   EQM1    , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_UNO,0) AS EQP1, \n");
		sql.append("       NVL(EQ.MONTO_DOS,0)    AS   EQM2     , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_DOS,0) AS EQP2, \n");
		sql.append("       NVL(EQ.MONTO_TRES,0)   AS   EQM3     , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_TRES,0) AS EQP3 \n");
		sql.append("FROM   FIDELIZACION.PRODUCTO PR     , \n");
		sql.append("       FIDELIZACION.TIPO_PRODUCTO TP, \n");
		sql.append("       FIDELIZACION.EQUIVALENCIA EQ, \n");
		sql.append("       FIDELIZACION.IMAGEN_PRODUCTO IM \n");
		sql.append("WHERE  PR.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		sql.append("AND    PR.CODIGO_IMAGEN      = IM.CODIGO(+) \n");
		if(soloActivos){
			sql.append("AND    PR.ESTADO           = 1 \n");
		}
		sql.append("AND    PR.PRODUCTO_ID      = EQ.PRODUCTO_ID(+) \n");
		sql.append("AND    EQ.ESTADO(+)        = 1");
		
		if(filtro == 1){
			sql.append("AND      PR.TIPO_PRODUCTO_ID = ? \n");
		}else if(filtro == 2){
			sql.append("AND      UPPER(PR.NOMBRE) LIKE '%'||?||'%' \n");
		}else if(filtro == 3){
			sql.append("AND      PR.PRODUCTO_ID = ? \n");
		}
		sql.append("ORDER BY PR.NOMBRE");
		
		pstm = conn.prepareStatement(sql.toString());

		if(filtro == 1){
			pstm.setInt(1, Integer.parseInt(valorAuxiliar));
		}else if(filtro == 2){
			pstm.setString(1, valorAuxiliar);
		}else if(filtro == 3){
			pstm.setInt(1, Integer.parseInt(valorAuxiliar));
		}
		
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		BImagenProducto imagen;
		Lista equivalenciaListado;
		BEquivalencia equivalencia;
		while(rs.next()){
			producto = new BProducto();
			
			producto.setCodigo(rs.getInt("CODIGO"));
			producto.setNombre(rs.getString("NOMBRE"));
			tipo = new BTipoProducto();
			tipo.setCodigo(rs.getInt("TIPO"));
			tipo.setDescripcion(rs.getString("DESTIPO"));
			producto.setTipo(tipo);
			imagen = new BImagenProducto();
			imagen.setArchivo(rs.getString("ARCHIVO"));
			producto.setImagen(imagen);
			equivalenciaListado = new Lista();
			equivalencia = new BEquivalencia();
			equivalencia.setCantidadPuntoUno(rs.getInt("EQP1"));
			equivalencia.setCantidadPuntoDos(rs.getInt("EQP2"));
			equivalencia.setCantidadPuntoTres(rs.getInt("EQP3"));
			equivalencia.setMontoUno(rs.getDouble("EQM1"));
			equivalencia.setMontoDos(rs.getDouble("EQM2"));
			equivalencia.setMontoTres(rs.getDouble("EQM3"));
			equivalenciaListado.setElemento(equivalencia);
			producto.setListaPuntaje(equivalenciaListado);
			lista.setElemento(producto);
		}
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerStockLocalProducto(pe.com.upz.bean.BProducto, pe.com.upz.bean.BSucursal)
	 */
	@Override
	public int obtenerStockLocalProducto(BProducto bProducto,
			BSucursal bSucursal) throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int cantidad=-1;
		sql.append("SELECT ps.stock AS cantidad\n");
		sql.append("FROM   fidelizacion.producto_sucursal ps \n");
		sql.append("WHERE  ps.sucursal_id = ? \n");
		sql.append("AND    ps.producto_id = ? \n");
		sql.append("AND    ps.estado      = 1");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, bSucursal.getCodigo());
		pstm.setInt(2, bProducto.getCodigo());
		
		rs = pstm.executeQuery();

		if (rs.next()) {
			cantidad = rs.getInt("cantidad");
			
		}
		rs.close();
		pstm.close();
		ConnectDS.cerrarConexion(conn);
		return cantidad;
	}

	@Override
	public void actualizarStockProductoSucursal(BProducto bProducto,
			BSucursal bSucursal, int cantidad,Connection conn, BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE fidelizacion.producto_sucursal ps \n");
		sql.append("SET    ps.stock                = (nvl(ps.stock,0) - ?), \n");
		sql.append("       ps.usuario_modificacion = ?             , \n");
		sql.append("       ps.fecha_modificacion   = SYSDATE \n");
		sql.append("WHERE  ps.sucursal_id          = ? \n");
		sql.append("AND    ps.producto_id          = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,cantidad);
		pstm.setString(2,usuario.getLogin());
		pstm.setInt(3,bSucursal.getCodigo());
		pstm.setInt(4,bProducto.getCodigo());
		
		pstm.executeUpdate();
	
		pstm.close();
	}
	public void crearProductoSucursal(BProducto bProducto,
			BSucursal bSucursal, Connection conn, BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   fidelizacion.producto_sucursal \n");
		sql.append("       ( \n");
		sql.append("              sucursal_id         , \n");
		sql.append("              producto_id         , \n");
		sql.append("              stock               , \n");
		sql.append("              usuario_modificacion, \n");
		sql.append("              fecha_creacion      , \n");
		sql.append("              fecha_modificacion  , \n");
		sql.append("              estado              , \n");
		sql.append("              usuario_creacion \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              0      , \n");
		sql.append("              NULL   , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              NULL   , \n");
		sql.append("              1      , \n");
		sql.append("              ? \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,bSucursal.getCodigo());
		pstm.setInt(2,bProducto.getCodigo());
		pstm.setInt(3,usuario.getCodigo());
		
		pstm.executeUpdate();
	
		pstm.close();
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerSucursalProductoStock()
	 */
	public Lista obtenerSucursalProductoStock() throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Lista lista = new Lista();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT   SU.DESCRIPCION AS SUCURSAL   , \n");
		sql.append("         SU.SUCURSAL_ID AS SUCURSALID, \n");
		sql.append("         PR.PRODUCTO_ID AS CODPRODUCTO, \n");
		sql.append("         tp.descripcion AS DESTIPO       , \n");
		sql.append("         PR.NOMBRE      AS PRODUCTO   , \n");
		sql.append("         PS.STOCK       AS STOCK \n");
		sql.append("FROM     FIDELIZACION.PRODUCTO_SUCURSAL PS, \n");
		sql.append("         FIDELIZACION.SUCURSAL SU         , \n");
		sql.append("         FIDELIZACION.PRODUCTO PR         , \n");
		sql.append("         FIDELIZACION.Tipo_Producto tp \n");
		sql.append("WHERE    PS.SUCURSAL_ID      = SU.SUCURSAL_ID \n");
		sql.append("AND      PS.PRODUCTO_ID      = PR.PRODUCTO_ID \n");
		sql.append("and   PR.estado = 1 \n");
		sql.append("and   su.estado = 1 \n");
		sql.append("AND      pr.tipo_producto_id = tp.tipo_producto_id \n");
		sql.append("ORDER BY su.sucursal_id, \n");
		sql.append("         tp.descripcion, \n");
		sql.append("         pr.nombre");

		pstm = conn.prepareStatement(sql.toString());
		
		rs = pstm.executeQuery();
		
		BProductoSucursal productoSucursal = new BProductoSucursal();
		BProducto producto;
		BSucursal sucursal;
		BTipoProducto tipo;
		while(rs.next()){
			productoSucursal = new BProductoSucursal();
			producto = new BProducto();
			sucursal = new BSucursal();
			sucursal.setCodigo(rs.getInt("SUCURSALID"));
			sucursal.setDescripcion(rs.getString("SUCURSAL"));
			producto.setCodigo(rs.getInt("CODPRODUCTO"));
			producto.setNombre(rs.getString("PRODUCTO"));
			tipo = new BTipoProducto();
			tipo.setDescripcion(rs.getString("DESTIPO"));
			producto.setTipo(tipo);
			productoSucursal.setProducto(producto);
			productoSucursal.setSucursal(sucursal);
			productoSucursal.setStock(rs.getInt("STOCK"));
			
			lista.setElemento(productoSucursal);
			
		}
		rs.close();
		pstm.close();
		ConnectDS.cerrarConexion(conn);
		return lista;
	}
	
	public int obtenerSucursalProductoPromedio(int codSucursal, int codProducto, int anio, int mes) throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT AVG(dp.cantidad) AS PROMEDIO\n");
		sql.append("FROM   FIDELIZACION.Pedido PE, \n");
		sql.append("       FIDELIZACION.Detalle_Pedido dp \n");
		sql.append("WHERE  dp.producto_id                  = ? \n");
		sql.append("AND    pe.sucursal_id                  = ? \n");
		sql.append("AND    TO_CHAR(pe.fecha_pedido,'YYYY') = ? \n");
		sql.append("AND    TO_CHAR(pe.fecha_pedido,'MM')   = ? \n");
		sql.append("AND    pe.pedido_id                    = dp.pedido_id \n");
		sql.append("AND    pe.estado                       = 1 \n");
		sql.append("AND    pe.tipo_movimiento              = 3");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codProducto);
		pstm.setInt(2,codSucursal);
		pstm.setString(3,anio+"");
		if((mes+1)<10){
			pstm.setString(4,"0"+(mes+1));
		}else{
			pstm.setString(4,""+(mes+1));
		}
		
		rs = pstm.executeQuery();
		
		int promedio=0;
		if(rs.next()){
			promedio = rs.getInt("PROMEDIO");			
		}
		rs.close();
		pstm.close();
		ConnectDS.cerrarConexion(conn);
		return promedio;
	}
	
	public void eliminarProducto(int codigo, BUsuario usuario, Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE producto \n");
		sql.append("SET    usuario_modificacion = ?      , \n");
		sql.append("       fecha_modificacion   = SYSDATE, \n");
		sql.append("       estado               = 0 \n");
		sql.append("WHERE  producto_id          = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1,usuario.getLogin());
		pstm.setInt(2,codigo);
		pstm.executeUpdate();
		
		pstm.close();
		
	}
	//gonza
	public BProducto obtenerProductos(int codigo) throws SQLException {

		Connection conn = ConnectDS.obtenerConeccion();
		BProducto producto=null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT producto_id                        , \n");
		sql.append("       pr.tipo_producto_id                   , \n");
		sql.append("       pt.descripcion AS dTipo," +
				" pr.descripcion AS desProducto, \n");
		sql.append("       pr.estado                             , \n");
		sql.append("       pr.nombre, NVL(pr.codigo_imagen,-1) AS codIma \n");
		sql.append("FROM   producto pr, \n");
		sql.append("       tipo_producto pt \n");
		sql.append("WHERE  pr.producto_id      = ? \n");
		sql.append("AND    pt.tipo_producto_id = pr.tipo_producto_id");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigo);

		rs = pstm.executeQuery();

		BTipoProducto tipo;
		BImagenProducto imagen;
		while (rs.next()) {
			producto = new BProducto();

			producto.setCodigo(rs.getInt("producto_id"));
			producto.setNombre(rs.getString("nombre"));
			producto.setDescripcion(rs.getString("desProducto"));
			producto.setEstado(rs.getInt("estado"));
			imagen = new BImagenProducto();
			imagen.setCodigo(rs.getInt("codIma"));
			tipo = new BTipoProducto();
			tipo.setCodigo(rs.getInt("tipo_producto_id"));
			tipo.setDescripcion(rs.getString("dTipo"));
			producto.setTipo(tipo);
			producto.setImagen(imagen);
		}

		rs.close();
		pstm.close();
		conn.close();

		return producto;
	}
	
	public void eliminarProductoSucursal(int codigo, BUsuario usuario, Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE producto_sucursal \n");
		sql.append("SET    usuario_modificacion = ?      , \n");
		sql.append("       fecha_modificacion   = SYSDATE, \n");
		sql.append("       estado               = 0 \n");
		sql.append("WHERE  producto_id          = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1,usuario.getLogin());
		pstm.setInt(2,codigo);
		pstm.executeUpdate();
		
		pstm.close();		
	}
	
	public void guardarCambiosProducto(BProducto producto, BUsuario usuario, Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE producto \n");
		sql.append("SET    tipo_producto_id     = ?     , \n");
		sql.append("       descripcion          = ?     , \n");
		sql.append("       usuario_modificacion = ?     , \n");
		sql.append("       fecha_modificacion   = SYSDATE, \n");
		sql.append("       nombre               = ?, \n");
		sql.append("       codigo_imagen        = ? \n");
		sql.append("WHERE  producto_id          = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		
		pstm.setInt(1,producto.getTipo().getCodigo());
		pstm.setString(2,producto.getDescripcion());
		pstm.setString(3,usuario.getLogin());
		pstm.setString(4,producto.getNombre());
		pstm.setInt(5,producto.getImagen().getCodigo());
		pstm.setInt(6,producto.getCodigo());
		pstm.executeUpdate();
		
		pstm.close();
	}
	public boolean consultarSalida(int codSucursal, int codProducto) throws SQLException {
		boolean valor= false;
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cantidad PROMEDIO\n");
		sql.append("FROM   FIDELIZACION.Pedido PE, \n");
		sql.append("       FIDELIZACION.Detalle_Pedido dp \n");
		sql.append("WHERE  dp.producto_id                  = ? \n");
		sql.append("AND    pe.sucursal_id                  = ? \n");
		sql.append("AND    pe.pedido_id                    = dp.pedido_id \n");
		sql.append("AND    pe.estado                       = 1 \n");
		sql.append("AND    pe.tipo_movimiento              = 3");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codProducto);
		pstm.setInt(2,codSucursal);
				
		rs = pstm.executeQuery();
		
		int promedio=0;
		if(rs.next()){
			valor = true;			
		}
		rs.close();
		pstm.close();
		ConnectDS.cerrarConexion(conn);
		return valor;
	}
	//gonza
}
