package com.pf.boarding.controller;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf.boarding.model.Embarque;
import com.pf.boarding.model.Pases;
import com.pf.boarding.service.EmbarqueService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/embarques")
public class EmbarqueController {
	
	@Autowired
	private EmbarqueService embarqueService;
	
	@GetMapping("/all")
	public Flux<Embarque> getAll() {
		return embarqueService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Embarque> getId(@PathVariable("id") final String embId){
		return embarqueService.findById(embId);
	}
	
	@GetMapping("/{id}/pases")
	public Flux<Pases> getPases(@PathVariable("id") final String embId){
		
		return embarqueService.findById(embId)
		.flatMapMany(embarque->{
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<Pases> pasesFlux=
					Flux.fromStream(Stream.generate(()->new Pases(embarque, new Date()))
					);
		return Flux.zip(interval, pasesFlux).map(Tuple2::getT2);
		});		
	}
	@GetMapping("/{destino}/tiempo-medio/{anyo}")
	public Flux<Embarque> getTiempoMedio(@PathVariable("destino") final String destino, @PathVariable("anyo") @DateTimeFormat(pattern = "yyyy") Date fecha) throws ParseException {
		return embarqueService.getAverageTimpoMedio(destino, fecha);
	}
	
	
}
