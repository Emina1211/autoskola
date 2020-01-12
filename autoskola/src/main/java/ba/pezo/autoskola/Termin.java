package ba.pezo.autoskola;

import java.util.Date;


import javax.persistence.*;


@Entity
@Table (name="termini")

public class Termin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Date datum=new Date ();
	private int pocetak;
	private int kraj;
	
	public Termin () {}
    public Termin (Date datum, int pocetak, int kraj) {
    	this.datum=datum;
    	this.pocetak=pocetak;
    	this.kraj=kraj;
    }
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getPocetak() {
		return pocetak;
	}
	public void setPocetak(int pocetak) {
		this.pocetak = pocetak;
	}
	public int getKraj() {
		return kraj;
	}
	public void setKraj(int kraj) {
		this.kraj = kraj;
	}
    
    

}
