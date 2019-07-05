package com.pf.boarding.service;

import com.pf.boarding.model.Average;
import com.pf.boarding.model.Embarque;
import com.pf.boarding.model.ImporteTienda;
import com.pf.boarding.model.Tienda;
import org.apache.commons.lang3.time.DateUtils;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

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
    public Mono<Tienda> save(Tienda tienda){

        return template.save(tienda);
    }
    public Mono<Average> getAverageTiempoMedioDestino(String destino, Date fecha){
        return tiempoMedio(Aggregation.match(new Criteria("destino").is(destino)), fecha);
    }

    private Mono<Average> tiempoMedio(MatchOperation match, Date fecha){
        final GroupOperation sumTotalCityPop = group("tiempoTotal")
                .sum("tiempoTotal").as("pop");
        final GroupOperation groupByStateAndSumPop = group("_id.idTarjetaEmbarque")
                .avg("pop").as("media");

        final MatchOperation matchFechaUp = Aggregation.match(new Criteria("fechaPasoSeguridad").gte(DateUtils.truncate(fecha, Calendar.YEAR)));

        final MatchOperation matchFechaDown = Aggregation.match(new Criteria("fechaPasoSeguridad").lt(DateUtils.ceiling(fecha, Calendar.YEAR)));
        final TypedAggregation<Average> aggregation
                = new TypedAggregation(Embarque.class, match, matchFechaUp, matchFechaDown, sumTotalCityPop, groupByStateAndSumPop);
        final Flux<Average> result = template.aggregate(aggregation, Average.class);
        return result.next();
    }

    public Mono<Average> getAverageTiempoMedioEmbarque(String puertaEmbarque, Date fecha){
        return tiempoMedio(Aggregation.match(new Criteria("puertaEmbarqueUtilizada").is(puertaEmbarque)), fecha);
    }

    public Mono<Void> deleteAll(){
        return template.dropCollection(Embarque.class);
    }

    public Flux<ImporteTienda> getIngresosPorTienda(String tienda, Date anyo) {
        final MatchOperation matchFechaUp = Aggregation.match(new Criteria("fechaCompra").gte(DateUtils.truncate(anyo, Calendar.YEAR)));

        final MatchOperation matchFechaDown = Aggregation.match(new Criteria("fechaCompra").lt(DateUtils.ceiling(anyo, Calendar.YEAR)));

        final GroupOperation groupDestino = group("nombreTienda").count().as("numeroPasajeros").sum("sale").as("importeTotal");
        final GroupOperation groupSale = group("nombreTienda").sum("sale").as("importeTotal");
        final MatchOperation matchTienda = Aggregation.match(new Criteria("nombreTienda").is(tienda));

        final TypedAggregation<Tienda> aggregation
                = new TypedAggregation(Tienda.class, matchFechaUp, matchFechaDown, matchTienda, groupDestino);
        return template.aggregate(aggregation, ImporteTienda.class);
    }

    public Mono<Void> deleteAllTiendas() {
        return template.dropCollection(Tienda.class);
    }
}