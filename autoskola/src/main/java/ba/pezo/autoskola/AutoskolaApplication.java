package ba.pezo.autoskola;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
		
		
	}

}
