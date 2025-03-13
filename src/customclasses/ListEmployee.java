package customclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListEmployee {

	private String pattern = "yyyy-MM-dd";
	private DateTimeFormatter dateFormatter = null;
	
	private Integer eid = 0;
	
	private String anrede;
	private String titel;
	private String nachname;
	private String vorname;
	private String adresse;
	private String plz;
	private String ort;
	private LocalDate geburtsdatum;
	private String telefon;
	private String arbeit;
	private String mobil;
	private String email;
	private String personalnummer;
	private String login_name;
	private String login_passwort;
	
	public ListEmployee() {
	    this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.geburtsdatum = LocalDate.parse("0001-01-01", dateFormatter);		
	}
	
	public ListEmployee(int id, String nachname, String vorname) {
	    this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.geburtsdatum = LocalDate.parse("0001-01-01", dateFormatter);
	    
		this.eid = id;
		this.nachname = nachname;
		this.vorname = vorname;
	}
	
	public ListEmployee(int id, String anrede, String titel, String nachname, String vorname, String adresse, String plz, String ort, String geburtsdatum, String telefon, String arbeit, String mobil, String email, String personalnummer, String login_name, String login_passwort) {
	    this.eid = id;
		this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.geburtsdatum = LocalDate.parse(geburtsdatum.toString(), dateFormatter);	 
	    this.anrede = anrede;
	    this.titel = titel;
	    this.nachname = nachname;
	    this.vorname = vorname;
	    this.adresse = adresse;
	    this.plz = plz;
	    this.ort = ort;
	    this.telefon = telefon;
	    this.mobil = mobil;
	    this.arbeit = arbeit;
	    this.email = email;
	    this.personalnummer = personalnummer;
	    this.login_name = login_name;
	    this.login_passwort = login_passwort;
	}
	
	public int getEID() {
		return this.eid;
	}
	
	public void setEID(int eid) {
		this.eid = eid;
	}
	
	public String getFullName() {
		if(eid == 0) {
			return this.nachname + " " + this.vorname;
		} else {
			return this.nachname + ", " + this.vorname;
		}		
	}
	
	public void setAnrede(String tmp) {
		this.anrede = tmp;
	}
	
	public void setTitel(String tmp) {
		this.titel = tmp;
	}
	
	public void setLastname(String name) {
		this.nachname = name;
	}
	
	public void setFirstname(String name) {
		this.vorname = name;
	}
	
	public void setAdresse(String tmp) {
		this.adresse = tmp;
	}
	
	public void setPlz(String tmp) {
		this.plz = tmp;
	}
	
	public void setOrt(String tmp) {
		this.ort = tmp;
	}
	
	public void setGeburtsdatum(String geburtsdatum) {
		if(geburtsdatum == null)
			return;
		this.geburtsdatum = LocalDate.parse(geburtsdatum, this.dateFormatter);
	}
	
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	
	public void setTelefon(String tmp) {
		this.telefon = tmp;
	}
	
	public void setArbeit(String tmp) {
		this.arbeit = tmp;
	}
	
	public void setMobil(String tmp) {
		this.mobil = tmp;
	}
	
	public void setEmail(String tmp) {
		this.email = tmp;
	}
	
	public void setPersonalnummer(String tmp) {
		this.personalnummer = tmp;
	}
	
	public void setLoginName(String tmp) {
		this.login_name = tmp;
	}
	
	public void setLoginPasswort(String tmp) {
		this.login_passwort = tmp;
	}
	
	public String getAnrede() {
		return this.anrede;
	}
	
	public String getTitel() {
		return this.titel;
	}
	
	public String getLastname() {
		return this.nachname;
	}
	
	public String getFirstname() {
		return this.vorname;
	}
	
	public String getAdresse() {
		return this.adresse;
	}
	
	public String getPlz() {
		return this.plz;
	}
	
	public String getOrt() {
		return this.ort;
	}
		
	public LocalDate getGeburtsdatum() {
		return this.geburtsdatum;
	}
	
	public String getGeburtsdatum(boolean dummy) {
		return this.geburtsdatum.getYear() + "-" + this.geburtsdatum.getMonthValue() + "-" + this.geburtsdatum.getDayOfMonth();
	}
	
	public String getTelefon() {
		return this.telefon;
	}
	
	public String getArbeit() {
		return this.arbeit;
	}
	
	public String getMobil() {
		return this.mobil;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPersonalnummer() {
		return this.personalnummer;
	}
	
	public String getLoginName() {
		return this.login_name;
	}
	
	public String getLoginPasswort() {
		return this.login_passwort;
	}

}
