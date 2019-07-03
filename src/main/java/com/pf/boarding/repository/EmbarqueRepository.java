package com.pf.boarding.repository;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.pf.boarding.model.Embarque;

//import es.uv.twcam.cloudingreactive.collection.GateControlDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmbarqueRepository extends ReactiveMongoRepository<Embarque, String> {
	Flux<Embarque> findByDestino(String destino);
	Flux<Embarque> findAll();
	Flux<Embarque> findAllByDestinoIs(String destino);
	@Query("{$match:{destino:{$eq: \"Valencia\"}}}, {$match: {fechaPasoSeguridad:{\"$gte\":ISODate(\"2019-01-01 00:00:00.000Z\"), \"$lte\":ISODate(\"2019-12-31 23:59:59.999Z\")}}}, {$group:{_id: \"\", tiempoMedio: { $avg: \"$tiempoTotal\" }}};")
	Mono<Embarque> getAverageTimpoMedio(String destino);
}

//
/*@Override
public Flux<ComprasTotalesDto> getAvgByAirport(String airpots) {

    // MatchOperation match = Aggregation.match(new Criteria().andOperator(
    // Criteria.where("airportStart").is(airpotStart),
    // Criteria.where("airportEnd").is(airportEnd)));

    ProjectionOperation project = Aggregation.project("airportStart").and("boarding").extractYear().as("year")
            .andExpression("(boarding - securityCheck)/[0]", 60000).as("avgTime");

    GroupOperation group = Aggregation.group("airportStart", "year").avg("avgTime").as("avgTime");

    Aggregation aggregation = Aggregation.newAggregation(project, group);

    return rmongo.aggregate(aggregation, "airportcontrol", GateControlDto.class);
}*/