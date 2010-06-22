package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

public class BCuenta extends Bean {

	private int codigo;
	private int puntosAcumulados;
	private int puntosVencidos;
	private int puntosCanjeados;
	private int estado;
	
	private Lista tarjeta;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}
	public int getPuntosVencidos() {
		return puntosVencidos;
	}
	public void setPuntosVencidos(int puntosVencidos) {
		this.puntosVencidos = puntosVencidos;
	}
	public int getPuntosCanjeados() {
		return puntosCanjeados;
	}
	public void setPuntosCanjeados(int puntosCanjeados) {
		this.puntosCanjeados = puntosCanjeados;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Lista getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Lista tarjeta) {
		this.tarjeta = tarjeta;
	}
	public BCliente obtenerClientePrincipal(){
		BCliente clinete = new BCliente();
		for(int i = 0; i < tarjeta.getTamanio();i++){
			BTarjetaFidelizacion miTarjeta = (BTarjetaFidelizacion)tarjeta.getElemento(i);
			
			if(miTarjeta.getTipoCliente() == 1){
				clinete = miTarjeta.getCliente();
			}
			
		}
	
		return clinete;
	}
	public String obtenerTarjetaPrincipal(){
		String numero="";
		for(int i = 0; i < tarjeta.getTamanio();i++){
			BTarjetaFidelizacion miTarjeta = (BTarjetaFidelizacion)tarjeta.getElemento(i);
			
			if(miTarjeta.getTipoCliente() == 1){
				numero = miTarjeta.getNumero();
				return numero;
			}
			
		}
		return numero;
	}
}
