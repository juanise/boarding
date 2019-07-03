package com.pf.boarding.model;
import java.util.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class Pases {

	private Embarque embarque;
	private Date date;

}
