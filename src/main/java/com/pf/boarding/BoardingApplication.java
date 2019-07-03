package com.pf.boarding;

import com.pf.boarding.model.Embarque;
import com.pf.boarding.service.EmbarqueTemplateOperations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.ZoneId;
import java.util.*;

@SpringBootApplication
public class BoardingApplication {

	@Bean
	CommandLineRunner embarques(EmbarqueTemplateOperations embarqueRepository) {
		Random r = new Random();
		List<Embarque> lis = new ArrayList<Embarque>();
		for (int i = 0; i < 100; i++) {
			int hour = r.nextInt(2) + 1;// create a hour between 0 and 2
			int min = r.nextInt(60) + 1;
			int prob = r.nextInt(3) + 1;
			int sale = 0;
			if (prob != 1) {
				sale = r.nextInt(1000) + 1;//crete a new sale between 0 (dont sale) and 1000 euros sale
			}
			//System.out.println(hour + "'''" + min + "''' " + new Date());
			Date dateIni = getComponerFechaEmbarque(hour, min, new Date());

			hour = r.nextInt(2) + 1;// create a hour between 0 and 2
			min = r.nextInt(60) + 1;
			Date dateFin = getComponerFechaEmbarque(hour, min, dateIni);
			long tiempoTotal = givenTiempoTotal(dateIni, dateFin);

			int valorDado = r.nextInt(6) + 1;
			int valorDado2 = r.nextInt(10) + 1;
			
			lis.add(new Embarque(UUID.randomUUID().toString(), dateIni, "A" + valorDado, getCiudad(valorDado), dateFin, sale, getTienda(valorDado2), tiempoTotal));
		}
		return arg -> {
			embarqueRepository.deleteAll().subscribe(null, null, () -> {
				lis.forEach(embarque -> {
					embarqueRepository.save(embarque).subscribe(System.out::println);
				});
			});
		};
	};

	private Date getComponerFechaEmbarque(int hour, int min, Date dateIni) {
		@SuppressWarnings("deprecation")
		Date embarque = new Date(dateIni.getYear(), dateIni.getMonth(), dateIni.getDate(), dateIni.getHours() + hour, dateIni.getMinutes() + min);
		return embarque;
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
	
	public String getTienda(int select) {
		String tienda = "";
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
		return tienda;
	}

}
