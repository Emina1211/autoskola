package ba.pezo.autoskola;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication


public class AutoskolaApplication implements CommandLineRunner {
	@Autowired
	TerminService terminService;

	public static void main(String[] args) {
		SpringApplication.run(AutoskolaApplication.class, args);
	}
	
	@Override
	
	public void run (String...args) {
		


		LocalDate date = LocalDate.now();
		LocalDate date2 = date.minusDays(7);
		
		Date datum = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date datum2 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Termin> listaTerminaUSedmici = terminService.dobaviTermineuSedmici(datum , datum2);
		
		System.out.println(listaTerminaUSedmici.size());
		
	}

}
