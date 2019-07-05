package com.pf.boarding;

import com.pf.boarding.model.Embarque;
import com.pf.boarding.model.Tienda;
import com.pf.boarding.service.EmbarqueTemplateOperations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.ZoneId;
import java.util.*;

@SpringBootApplication
public class BoardingApplication {
	private static final SecureRandom RANDOM = new SecureRandom();
	@Bean
	CommandLineRunner embarques(EmbarqueTemplateOperations embarqueRepository) {

		final List<Embarque> lis = new ArrayList<>(100);
		final List<Tienda> tiendasList = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			int hour = RANDOM.nextInt(2) + 1;// create a hour between 0 and 2
			int min = RANDOM.nextInt(60) + 1;

			Date dateIni = getComponerFechaEmbarque(hour, min, new Date());

			hour = RANDOM.nextInt(2) + 1;// create a hour between 0 and 2
			min = RANDOM.nextInt(60) + 1;
			Date dateFin = getComponerFechaEmbarque(hour, min, dateIni);
			long tiempoTotal = givenTiempoTotal(dateIni, dateFin);

			int valorDado = RANDOM.nextInt(6) + 1;
			int valorDado2 = RANDOM.nextInt(10) + 1;
			final String destino = getCiudad(valorDado);
			final Tienda tienda = getTienda(valorDado2);
			tienda.setFechaCompra(calcularHoraCompra(dateIni, dateFin));
			tienda.setDestino(destino);
			tienda.setUid(UUID.randomUUID().toString());
			tiendasList.add(tienda);
			lis.add(new Embarque(UUID.randomUUID().toString(), dateIni, "A" + valorDado, destino, dateFin, tiempoTotal));
		}
		embarqueRepository.deleteAllTiendas().subscribe(null, null, () ->{
			tiendasList.forEach(tienda -> {
				embarqueRepository.save(tienda).subscribe(System.out::println);
			});
		});
		return arg -> {
			embarqueRepository.deleteAll().subscribe(null, null, () -> {
				lis.forEach(embarque -> {
					embarqueRepository.save(embarque).subscribe(System.out::println);

				});
			});

		};
	}

	private Date calcularHoraCompra(Date dateIni, Date dateFin) {
		final Calendar calendarIni = Calendar.getInstance();
		calendarIni.setTime(dateIni);
		final Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(dateFin);
		final int horaIni = calendarIni.get(Calendar.HOUR_OF_DAY);
		final int horaFin = calendarFin.get(Calendar.HOUR_OF_DAY);
		int suma = Integer.MAX_VALUE;
		if (horaFin - horaIni > 1) {
			do {
				suma = RANDOM.nextInt(horaFin - horaIni) + 1;
			} while (suma > horaFin - horaIni);
		}else{
			return dateIni;
		}

		calendarIni.set(Calendar.HOUR_OF_DAY, horaIni + suma);
		return calendarIni.getTime();
	}

	private Double compra (){
		int prob = RANDOM.nextInt(3) + 1;

		if (prob != 1) {
			return Double.valueOf(RANDOM.nextInt(1000) + 1D);//crete a new sale between 0 (dont sale) and 1000 euros sale
		}
		return 0D;
	}

	private Date getComponerFechaEmbarque(int hour, int min, Date dateIni) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateIni);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	public long givenTiempoTotal(Date dateIni, Date dateFin) {

		Duration duration = Duration.between(dateIni.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
				dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		long diff = Math.abs(duration.toMinutes());
		
		return diff;
	}

	public static void main(String[] args) {
		SpringApplication.run(BoardingApplication.class, args);
	}

	public String getCiudad(int select) {
		String ciudad = "";
		switch (select) {
		case 6:
			return "Valencia";
		case 1:
			return "Barcelona";
		case 2:
			return "Madrid";
		case 3:
			return "Sevilla";
		case 4:
			return "Quito";
		case 5:
			return "Viena";
		}
		return ciudad;
	}
	
	public Tienda getTienda(int select) {
		final Tienda tienda = new Tienda();
		tienda.setSale(compra());
		tienda.setNombreTienda(getNombreTienda(select));
		return tienda;
	}

	private String getNombreTienda(int select) {
		switch (select) {
		case 10:
			return "M&M";
		case 9:
			return "Marlboro";
		case 8:
			return "Polo";
		case 7:
			return "Rolex";
		case 6:
			return "Zara";
		case 1:
			return "Candys";
		case 2:
			return "Sony";
		case 3:
			return "Saxoline";
		case 4:
			return "Kodak";
		case 5:
			return "Ray-Ban";
		}
		return null;
	}

}
