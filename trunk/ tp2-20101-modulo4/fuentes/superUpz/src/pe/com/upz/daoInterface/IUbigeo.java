package pe.com.upz.daoInterface;

import pe.com.upz.util.Lista;

public interface IUbigeo {

	public abstract Lista obtenerProvinciasDeDepartamento(String departamento);
	
	public abstract Lista obtenerDistritosDeprovincia(String departamento,String provincia);
}
