package customclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonBerechtigte {
	
	private String pattern = "yyyy-MM-dd";
	private DateTimeFormatter dateFormatter = null;
	
	private Integer bid = 0;
	
	private String nachname;
	private String vorname;
	private LocalDate geburtsdatum;
	private Boolean sorgeberechtigt;
	private Boolean kontaktperson;
	private String notiz;
	private String strasse;
	private String hausnummer;
	private String plz;
	private String ort;
	private String privat;
	private String arbeit;
	private String mobil;
	private String mail;
	
	public PersonBerechtigte() {
	    this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.geburtsdatum = LocalDate.parse("0001-01-01", dateFormatter);		
	}
	
	public void setBID(Integer bid) {
		this.bid = bid;
	}
	
	public Integer getBID() {
		return this.bid;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String getNachname() {
		return this.nachname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getVorname() {
		return this.vorname;
	}
	
	public void setGeburtsdatum(String geburtsdatum) {
		if(geburtsdatum == null)
			return;
		this.geburtsdatum = LocalDate.parse(geburtsdatum, this.dateFormatter);
	}
	
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	
	public LocalDate getGeburtsdatum() {
		return this.geburtsdatum;
	}
	
	public String getGeburtsdatum(boolean dummy) {
		return this.geburtsdatum.getYear() + "-" + this.geburtsdatum.getMonthValue() + "-" + this.geburtsdatum.getDayOfMonth();
	}
	
	public void setSorgeberechtigt(boolean aktiv) {
		this.sorgeberechtigt = aktiv;
	}
	
	public boolean getSorgeberechtigt() {
		return this.sorgeberechtigt;
	}
	
	public int getSorgeberechtigt(boolean dummy) {
		if(this.sorgeberechtigt) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void setKontaktperson(boolean aktiv) {
		this.kontaktperson = aktiv;
	}
	
	public boolean getKontaktperson() {
		return this.kontaktperson;
	}
	
	public int getKontaktperson(boolean dummy) {
		if(this.kontaktperson) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void setNotiz(String notiz) {
		this.notiz = notiz;
	}
	
	public String getNotiz() {
		return this.notiz;
	}
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public String getStrasse() {
		if(this.strasse == null)
			return "";
		
		return this.strasse;
	}
	
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public String getHausnummer() {
		if(this.hausnummer == null)
			return "";
		
		return this.hausnummer;
	}
	
	public void setPlz(String plz) {
		this.plz = plz;
	}
	
	public String getPlz() {
		if(this.plz == null)
			return "";
		
		return this.plz;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getOrt() {
		if(this.ort == null)
			return "";
		
		return this.ort;
	}
	
	public void setPrivat(String privat) {
		this.privat = privat;
	}
	
	public String getPrivat() {
		if(this.privat == null)
			return "";
		
		return this.privat;
	}
	
	public void setArbeit(String arbeit) {
		this.arbeit = arbeit;
	}
	
	public String getArbeit() {
		if(this.arbeit == null)
			return "";
		
		return this.arbeit;
	}
	
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	
	public String getMobil() {
		if(this.mobil == null)
			return "";
		
		return this.mobil;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMail() {
		if(this.mail == null)
			return "";
		
		return this.mail;
	}

}
