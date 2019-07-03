package com.pf.boarding.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Data
@Document
public class Embarque {
	
    @Id
    private String idTarjetaEmbarque;
	private Date fechaPasoSeguridad;
    private String puertaEmbarqueUtilizada;
    private String destino;
    private Date fechaEmbarque;
    private Integer sale;
    private String tienda;
    private Long tiempoTotal;

    public Embarque(String idTarjetaEmbarque, Date fechaPasoSeguridad, String puertaEmbarqueUtilizada, String destino, String tienda, Date fechaEmbarque, Integer sale, Long tiempoTotal) {
		super();
		this.idTarjetaEmbarque = idTarjetaEmbarque;
		this.fechaPasoSeguridad = fechaPasoSeguridad;
		this.puertaEmbarqueUtilizada = puertaEmbarqueUtilizada;
		this.destino = destino;
		this.fechaEmbarque = fechaEmbarque;
		this.sale = sale;
		this.tienda = tienda;
		this.tiempoTotal = tiempoTotal;
	}

	public Embarque (Long tiempoMedio){
    	super();
    	this.tiempoTotal = tiempoMedio;
	}

	public String getIdTarjetaEmbarque() {
		return idTarjetaEmbarque;
	}

	public void setIdTarjetaEmbarque(String idTarjetaEmbarque) {
		this.idTarjetaEmbarque = idTarjetaEmbarque;
	}

	public Date getFechaPasoSeguridad() {
		return fechaPasoSeguridad;
	}

	public void setFechaPasoSeguridad(Date fechaPasoSeguridad) {
		this.fechaPasoSeguridad = fechaPasoSeguridad;
	}

	public String getPuertaEmbarqueUtilizada() {
		return puertaEmbarqueUtilizada;
	}

	public void setPuertaEmbarqueUtilizada(String puertaEmbarqueUtilizada) {
		this.puertaEmbarqueUtilizada = puertaEmbarqueUtilizada;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(Date fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	
	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public long getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

}
