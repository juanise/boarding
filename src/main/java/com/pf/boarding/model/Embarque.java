package com.pf.boarding.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

	public Integer getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public Long getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(Long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}
}
