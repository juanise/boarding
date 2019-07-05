package com.pf.boarding.model;
import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class ImporteTienda {

	@Id
	private String name;
	private Double importeTotal;
	private Integer numeroPasajeros;


}
