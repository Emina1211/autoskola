package ba.pezo.autoskola;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TerminService {
	@Autowired
	TerminRepository terminRepository;

	public Termin dodajNoviTermin(Termin t){
	return terminRepository.save(t);
	}
	
	public void izbrisiTermin (Termin t) {
		terminRepository.delete(t);
	}
	
	public Termin dobaviTermin (Long id) {
		return terminRepository.findById (id).orElse(null);
	}
	
	public List<Termin> dobaviSve () {
		return terminRepository.findAll();
	}

    public void izbrisiPoId (Long id) {
    	terminRepository.deleteById (id);
    	
    }
    
    public List<Termin> dobaviTermineuSedmici (Date Krajnji, Date Pocetni){
    	List<Termin> sviTermini=terminRepository.findAll ();
    	List<Termin> zaVratiti = new ArrayList<Termin>();
    	for (Termin termin:sviTermini) {
    		if (termin.getDatum().after(Pocetni) && termin.getDatum().before(Krajnji))
    		{
    			zaVratiti.add(termin);
    		}
    	}
    	
    	return zaVratiti;
    }
	 

}
