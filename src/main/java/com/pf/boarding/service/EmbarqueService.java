package com.pf.boarding.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.pf.boarding.model.Embarque;
import com.pf.boarding.repository.EmbarqueRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmbarqueService{
	
	@Autowired
	private EmbarqueRepository embarqueRepository;
	
	public EmbarqueService(EmbarqueRepository embarqueRepository) {
		this.embarqueRepository = embarqueRepository;
	}

	public Mono<Embarque> save(Embarque entity) {
		return embarqueRepository.save(entity);
	}

	public Mono<Void> deleteAll() {
		return embarqueRepository.deleteAll();
	}

	public Mono<Embarque> findById(String embId) {
		return embarqueRepository.findById(embId);
	}
//@Query("{$match:{destino:{$eq: \"Valencia\"}}}, {$match: {fechaPasoSeguridad:{\"$gte\":ISODate(\"2019-01-01 00:00:00.000Z\"), \"$lte\":ISODate(\"2019-12-31 23:59:59.999Z\")}}}, {$group:{_id: \"\", tiempoMedio: { $avg: \"$tiempoTotal\" }}};")
	public Flux<Embarque> getAverageTimpoMedio(String destino, Date fecha) throws ParseException {
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("destino", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
				.withMatcher("fechaPasoSeguridad", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()
				.);

		Example<Embarque> example = Example.of(new Embarque(null, null, null, destino, null, fecha, null, null), customExampleMatcher);
		return embarqueRepository.findAll(example);
	}


	
	//public Mono<Integer> getAverageTimpoMedio(String destino){
	//	return embarqueRepository.getAverageTimpoMedio(destino);
	//}

	public Flux<Embarque> findAll() {
		return embarqueRepository.findAll();
	}
}
