package customclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import application.EKitaManager;

public class PersonKid {
	
	private String pattern = "yyyy-MM-dd";
	private DateTimeFormatter dateFormatter = null;
	
	private Integer pid = 0;
	
	private String nachname = "";
	private String vorname = "";
	private String strasse = "";
	private String hausnummer = "";
	private String plz = "";
	private String ort = "";
	private LocalDate geburtsdatum;
	private ListGender geschlecht; // 0 = keine Angabe, 1 = männlich, 2 = weiblich, 3 = divers
	private ListGroup gruppe;
	private Boolean aktiv;
	
	private LocalDate vertragsbeginn;
	private LocalDate vertragsende;
	private List<Betreuungszeit> betreuungszeit;
	private String nationalitaet = "";
	private String muttersprache = "";
	private Integer betreuungsart = 0; // 0 = krippe, 1 = kiga, 2 = hort
	
	private String krankenkasse = "";
	private Integer versicherter = 0;
	private String krankheiten = "";
	private String allergien = "";
	private String besonderheiten = "";
	private Integer kinderarzt = 0;
	
	private List<Permission> vollmachten = new ArrayList<Permission>();
	
	
	//public PersonKid(String nachname, String vorname, String strasse, String plz, String ort, String geburtsdatum, String gruppe, boolean aktiv) {
	public PersonKid() {
	    this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.geburtsdatum = LocalDate.parse("0001-01-01", dateFormatter);
	    this.vertragsbeginn = LocalDate.parse("0001-01-01", dateFormatter);
	    this.vertragsende = LocalDate.parse("0001-01-01", dateFormatter);
	    betreuungszeit = new ArrayList<Betreuungszeit>();
		/*this.nachname = nachname;
		this.vorname = vorname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		
		String pattern = "dd.MM.yyyy";
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	    if(geburtsdatum.equalsIgnoreCase(""))
	    	geburtsdatum = "01.01.1971";
		this.geburtsdatum = LocalDate.parse(geburtsdatum, dateFormatter);
		
		this.gruppe = gruppe;
		this.aktiv = aktiv;*/
	}
	
	public void setPID(Integer pid) {
		this.pid = pid;
	}
	
	public Integer getPID() {
		return this.pid;
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
	
	public void setGeschlecht(ListGender geschlecht) {
		this.geschlecht = geschlecht;
	}
	
	public ListGender getGeschlecht() {
		if(this.geschlecht == null)
			return new ListGender(0, "keine Angabe");
		
		return this.geschlecht;
	}
	
	public void setGruppe(ListGroup gruppe) {
		this.gruppe = gruppe;
	}
	
	public ListGroup getGruppe() {
		if(this.gruppe == null)
			return new ListGroup(-1, "", "", "", 0);
		
		return this.gruppe;
	}
	
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	
	public boolean getAktiv() {
		return this.aktiv;
	}
	
	public int getAktiv(boolean dummy) {
		if(this.aktiv) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void setVertragsbeginn(String vertragsbeginn) {
		if(vertragsbeginn == null)
			return;
		this.vertragsbeginn = LocalDate.parse(vertragsbeginn, this.dateFormatter);
	}
	
	public void setVertragsbeginn(LocalDate vertragsbeginn) {
		this.vertragsbeginn = vertragsbeginn;
	}
	
	public LocalDate getVertragsbeginn() {
		return this.vertragsbeginn;
	}
	
	public String getVertragsbeginn(boolean dummy) {
		return this.vertragsbeginn.getYear() + "-" + this.vertragsbeginn.getMonthValue() + "-" + this.vertragsbeginn.getDayOfMonth();
	}
	
	public void setVertragsende(String vertragsende) {
		if(vertragsende == null)
			return;
		this.vertragsende = LocalDate.parse(vertragsende, this.dateFormatter);
	}
	
	public void setVertragsende(LocalDate vertragsende) {
		this.vertragsende = vertragsende;
	}
	
	public LocalDate getVertragsende() {
		return this.vertragsende;
	}
	
	public String getVertragsende(boolean dummy) {
		return this.vertragsende.getYear() + "-" + this.vertragsende.getMonthValue() + "-" + this.vertragsende.getDayOfMonth();
	}
	
	public void cleanBetreuungszeit() {
		this.betreuungszeit.clear();
	}
	
	public void addBetreuungszeit(Betreuungszeit betreuungszeit) {
		this.betreuungszeit.add(betreuungszeit);
	}
	
	public void setBetreuungszeit(List<Betreuungszeit> betreuungszeit) {
		this.betreuungszeit.addAll(betreuungszeit);
	}
	
	public List<Betreuungszeit> getBetreuungszeiten() {
		return this.betreuungszeit;		
	}
	
	public int getBetreuungszeit(LocalDate datum) {
		LocalDate date = datum;
		return calculateBetreuungszeit(date);
	}
	
	public int getBetreuungszeit() {
		LocalDate today = LocalDate.now();
		return calculateBetreuungszeit(today);
	}
	
	private int calculateBetreuungszeit(LocalDate datum) {
		int betreuungszeit = 0;
		
		List<Betreuungszeit> zeitenListe = EKitaManager.getInstance().kidsLogicC.openPersonBetreuungszeit(this.pid);
		
		if(zeitenListe.size() > 0) {
			
			for(int i = 0; i < zeitenListe.size(); i++) {
				if((datum.isAfter(zeitenListe.get(i).getBeginn()) || datum.isEqual(zeitenListe.get(i).getBeginn())) && (datum.isBefore(zeitenListe.get(i).getEnde()) || datum.isEqual(zeitenListe.get(i).getEnde()))) {
					betreuungszeit = zeitenListe.get(i).getStunden();
				}
			}
			
		}
		
		return betreuungszeit;
	}
	
	public void setNationalitaet(String nationalitaet) {
		this.nationalitaet = nationalitaet;
	}
	
	public String getNationalitaet() {
		return this.nationalitaet;
	}
	
	public void setMuttersprache(String muttersprache) {
		this.muttersprache = muttersprache;
	}
	
	public String getMuttersprache() {
		return this.muttersprache;
	}
	
	public int getBetreuungsart() {
		return this.betreuungsart;
	}
	
	 // 0 = krippe, 1 = kiga, 2 = hort
	public void setBetreuungsart(int art) {
		this.betreuungsart = art;
	}
	
	public void setKrankenkasse(String krankenkasse) {
		this.krankenkasse = krankenkasse;
	}
	
	public String getKrankenkasse() {
		return this.krankenkasse;
	}
	
	public void setVersicherter(Integer bid) {
		this.versicherter = bid;
	}
	
	public Integer getVersicherter() {
		if(this.versicherter == null)
			return -1;
		
		return this.versicherter;
	}
	
	public void setKrankheiten(String krankheiten) {
		this.krankheiten = krankheiten;
	}
	
	public String getKrankheiten() {
		return this.krankheiten;
	}
	
	public void setAllergien(String allergien) {
		this.allergien = allergien;
	}
	
	public String getAllergien() {
		return this.allergien;
	}
	
	public void setBesonderheiten(String besonderheiten) {
		this.besonderheiten = besonderheiten;
	}
	
	public String getBesonderheiten() {
		return this.besonderheiten;
	}
	
	public List<Permission> getVollmachten() {
		return this.vollmachten;
	}
	
	public void setVollmachten(List<Permission> vollmachten) {
		this.vollmachten = vollmachten;
	}
	
	public int getKinderarzt() {
		return this.kinderarzt;
	}
	
	public void setKinderarzt(int kinderarzt) {
		this.kinderarzt = kinderarzt;
	}

}
