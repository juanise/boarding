package com.pf.boarding.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document
public class Tienda {
    @Id
    private String uid;
    private String nombreTienda;
    private Date fechaCompra;
    private Double sale;
    private String destino;
}
