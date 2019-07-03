package com.pf.boardingreactive.collection;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprasTotalesDTO {

	private String _id;
    private Integer sale;
}
