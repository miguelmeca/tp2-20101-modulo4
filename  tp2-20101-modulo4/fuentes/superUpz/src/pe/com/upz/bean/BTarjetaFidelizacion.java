package pe.com.upz.bean;

import pe.com.upz.util.Bean;

public class BTarjetaFidelizacion extends Bean{

	private BCliente cliente;
	private String numero;
	private int estado;
	public BCliente getCliente() {
		return cliente;
	}
	public void setCliente(BCliente cliente) {
		this.cliente = cliente;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
