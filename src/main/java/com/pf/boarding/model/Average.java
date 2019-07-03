package com.pf.boarding.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Average {
    @Id
    private String id;
    private Double media;

}
