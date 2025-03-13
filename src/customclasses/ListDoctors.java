package customclasses;

public class ListDoctors {
	
	public int did = 0;
	public String anrede = "";
	public String titel = "";
	public String nachname = "";
	public String vorname = "";
	public String adresse = "";
	public String plz = "";
	public String ort = "";
	public String praxis = "";
	public String telefon = "";
	public String fax = "";
	public String email = "";
	
	public ListDoctors() {
		
	}
	
	public ListDoctors(int id, String titel, String nachname) {
		
		this.did = id;
		this.nachname = nachname;
		this.titel = titel;
		
	}
	
	public ListDoctors(int id, String anrede, String titel, String nachname, String vorname, String adresse, String plz, String ort, String praxis, String telefon, String fax, String email) {
		
		this.did = id;
		this.anrede = anrede;
		this.titel = titel;
		this.nachname = nachname;
		this.vorname = vorname;
		this.adresse = adresse;
		this.plz = plz;
		this.ort = ort;
		this.praxis = praxis;
		this.telefon = telefon;
		this.fax = fax;
		this.email = email;
		
	}
	
	public String getDisplayName() {
		return this.titel + " " + this.vorname + " " + this.nachname;
	}
	
	public int getDID() {
		return this.did;
	}
	
	public String getAnrede() {
		return this.anrede;
	}
	
	public String getTitel() {
		return this.titel;
	}
	
	public String getNachname() {
		return this.nachname;
	}
	
	public String getVorname() {
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
	
	public String getPraxis() {
		return this.praxis;
	}
	
	public String getTelefon() {
		return this.telefon;
	}
	
	public String getFax() {
		return this.fax;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setDID(int did) {
		this.did = did;
	}
	
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void setPlz(String plz) {
		this.plz = plz;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public void setPraxis(String praxis) {
		this.praxis = praxis;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
