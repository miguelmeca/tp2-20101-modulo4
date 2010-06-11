package pe.com.upz.bean;

import pe.com.upz.util.Bean;

public class BEquivalencia extends Bean{

	private double montoUno;			
	private int cantidadPuntoUno;			
	private double montoDos;		
	private int cantidadPuntoDos;			
	private double montoTres;		
	private int cantidadPuntoTres	;		
	private String fechaExpiracion;		
	private int estado;		
	private int codigo	;
	public double getMontoUno() {
		return montoUno;
	}
	public void setMontoUno(double montoUno) {
		this.montoUno = montoUno;
	}
	public int getCantidadPuntoUno() {
		return cantidadPuntoUno;
	}
	public void setCantidadPuntoUno(int cantidadPuntoUno) {
		this.cantidadPuntoUno = cantidadPuntoUno;
	}
	public double getMontoDos() {
		return montoDos;
	}
	public void setMontoDos(double montoDos) {
		this.montoDos = montoDos;
	}
	public int getCantidadPuntoDos() {
		return cantidadPuntoDos;
	}
	public void setCantidadPuntoDos(int cantidadPuntoDos) {
		this.cantidadPuntoDos = cantidadPuntoDos;
	}
	public double getMontoTres() {
		return montoTres;
	}
	public void setMontoTres(double montoTres) {
		this.montoTres = montoTres;
	}
	public int getCantidadPuntoTres() {
		return cantidadPuntoTres;
	}
	public void setCantidadPuntoTres(int cantidadPuntoTres) {
		this.cantidadPuntoTres = cantidadPuntoTres;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}						
	
	
}
