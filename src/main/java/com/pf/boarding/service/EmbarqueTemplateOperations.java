package com.pf.boarding.service;

import com.pf.boarding.model.Average;
import com.pf.boarding.model.Embarque;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;

@Service
public class EmbarqueTemplateOperations {
  
    @Autowired
    ReactiveMongoTemplate template;
 
    public Mono<Embarque> findById(String id) {
        return template.findById(id, Embarque.class);
    }
  
    public Flux<Embarque> findAll() {
        return template.findAll(Embarque.class);
    } 
    public Mono<Embarque> save(Embarque account) {
        return template.save(account);
    }
    public Mono<Average> getAverageTimpoMedio(String destino, Date fecha){
        final GroupOperation sumTotalCityPop = group("tiempoTotal")
                .sum("tiempoTotal").as("pop");
        final GroupOperation groupByStateAndSumPop = group("_id.idTarjetaEmbarque")
                .avg("pop").as("media");
        final MatchOperation matchDestino = Aggregation.match(new Criteria("destino").is(destino));
        final MatchOperation matchFechaUp = Aggregation.match(new Criteria("fechaPasoSeguridad").gte(DateUtils.truncate(fecha, Calendar.YEAR)));

        final MatchOperation matchFechaDown = Aggregation.match(new Criteria("fechaPasoSeguridad").lt(DateUtils.ceiling(fecha, Calendar.YEAR)));
        final TypedAggregation<Average> aggregation
                = new TypedAggregation(Embarque.class, matchDestino, matchFechaUp, matchFechaDown, sumTotalCityPop, groupByStateAndSumPop);
        final Flux<Average> result = template.aggregate(aggregation, Average.class);
        return result.next();
    }

    public Mono<Void> deleteAll(){
        return template.dropCollection(Embarque.class);
    }
}