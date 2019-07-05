package com.pf.boarding.controller;

import com.pf.boarding.model.Average;
import com.pf.boarding.model.Embarque;
import com.pf.boarding.model.ImporteTienda;
import com.pf.boarding.model.Tienda;
import com.pf.boarding.service.EmbarqueTemplateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/embarque")
public class EmbarqueController {
	
	@Autowired
	private EmbarqueTemplateOperations embarqueService;
	
	@GetMapping("/all")
	public Flux<Embarque> getAll() {
		return embarqueService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Embarque> getId(@PathVariable("id") final String embId){
		return embarqueService.findById(embId);
	}

	@GetMapping("/{destino}/tiempo-medio/{anyo}")
	public Mono<Average> getTiempoMedio(@PathVariable("destino") final String destino, @PathVariable("anyo") @DateTimeFormat(pattern = "yyyy") Date anyo)  {
		return embarqueService.getAverageTiempoMedioDestino(destino, anyo);
	}

	@GetMapping("/{puerta}/tiempo-puertas/{anyo}")
	public Mono<Average> getAverageTiempoMedioEmbarque(@PathVariable("puerta") String puerta, @PathVariable("anyo") @DateTimeFormat(pattern = "yyyy") Date anyo){
		return embarqueService.getAverageTiempoMedioEmbarque(puerta, anyo);
	}

	@GetMapping("{nombreTienda}/indresos/{anyo}")
	public Flux<ImporteTienda> getIngresosPorTienda(@PathVariable("nombreTienda") final String nombreTienda, @PathVariable("anyo") @DateTimeFormat(pattern = "yyyy") Date anyo){
		return embarqueService.getIngresosPorTienda(nombreTienda, anyo);
	}
	
}
