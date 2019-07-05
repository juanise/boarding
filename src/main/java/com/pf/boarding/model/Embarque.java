package com.pf.boarding.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

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
    private Long tiempoTotal;

}
