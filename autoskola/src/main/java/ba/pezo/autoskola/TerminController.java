package ba.pezo.autoskola;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/termini")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TerminController {
	
		
		@Autowired
	    private TerminService terminService;

	    @GetMapping
	    public ResponseEntity<List<Termin>> findAll() {
	        return ResponseEntity.ok(terminService.dobaviSve());
	    }

	    @PostMapping
	    public ResponseEntity<Termin> create(@Valid @RequestBody Termin termin) {
	        return ResponseEntity.ok(terminService.dodajNoviTermin(termin));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Termin> findById(@PathVariable Long id) {
	        Termin termin = terminService.dobaviTermin(id);
	        
	        return ResponseEntity.ok(termin);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Termin> delete(@PathVariable Long id) {

	        terminService.izbrisiPoId(id);

	        return ResponseEntity.ok().build();
	    }
	    
	    @GetMapping("/{Pocetni}/{Krajnji}")
	    public List<List<TerminDTO>> getByDate(@PathVariable("Pocetni") @DateTimeFormat(pattern="ddMMyyyy") Date Pocetak,
	    											  @PathVariable("Krajnji") @DateTimeFormat(pattern="ddMMyyyy") Date Kraj){
	    	return terminService.dobaviTermineuSedmici(Kraj, Pocetak);
	    }
	    		
}

