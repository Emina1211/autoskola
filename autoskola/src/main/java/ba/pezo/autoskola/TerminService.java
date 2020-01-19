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
    
    public List<List<TerminDTO>> dobaviTermineuSedmici (Date Krajnji, Date Pocetni){
    	List<List<TerminDTO>> sviTerminiZaSedmicu = new ArrayList<>();
    	for (int j = 8; j <= 16; j++) {
    		List<TerminDTO> terminiZaOvajDan = new ArrayList<>();
    		
			for (int i = 0; i < 7; i++) {
				TerminDTO t = new TerminDTO();
				Date datumTermina = DateUtil.addDays(Pocetni, i);
				t.setDatum(datumTermina);
				t.setPocetak(j);
				t.setKraj(j + 1);
				t.setZauzet(daLiJeZauzetTermin(t));
				terminiZaOvajDan.add(t);
	    	}
	    	sviTerminiZaSedmicu.add(terminiZaOvajDan);
    	}
    	
    	return sviTerminiZaSedmicu;
    }
    
    private boolean daLiJeZauzetTermin(TerminDTO termin) {
    	List<Termin> sviTermini = terminRepository.findAll();
    	for(Termin t : sviTermini) {
    		if (t.getPocetak() == termin.getPocetak()
    				&& t.getKraj() == termin.getKraj()
    				&& t.getDatum().getDate() == termin.getDatum().getDate()
    				&& t.getDatum().getMonth() == termin.getDatum().getMonth()
    				&& t.getDatum().getYear() == termin.getDatum().getYear()) {
    			System.out.println(termin.getDatum());
    			return true;
    		}
    	}
    	return false;
    }
	 

}
