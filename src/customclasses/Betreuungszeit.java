package customclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Betreuungszeit {
	
	private String pattern = "yyyy-MM-dd";
	private DateTimeFormatter dateFormatter = null;
	
	private int zid;
	
	private LocalDate beginn;
	private LocalDate ende;
	private int stunden;
	
	public Betreuungszeit(String beginn, String ende, int stunden) {
		
		this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.beginn = LocalDate.parse("0001-01-01", dateFormatter);
	    this.ende = LocalDate.parse("0001-01-01", dateFormatter);
	    
	    this.beginn = LocalDate.parse(beginn, dateFormatter);
	    this.ende = LocalDate.parse(ende, dateFormatter);
	    this.stunden = stunden;
		
	}	
	
	public Betreuungszeit(int zid, String beginn, String ende, int stunden) {
		
		this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    this.beginn = LocalDate.parse("0001-01-01", dateFormatter);
	    this.ende = LocalDate.parse("0001-01-01", dateFormatter);
	    
	    this.zid = zid;
	    
	    this.beginn = LocalDate.parse(beginn, dateFormatter);
	    this.ende = LocalDate.parse(ende, dateFormatter);
	    this.stunden = stunden;
		
	}	
	
	public LocalDate getBeginn() {
		return this.beginn;
	}
	
	public LocalDate getEnde() {
		return this.ende;
	}
	
	public int getStunden() {
		return this.stunden;
	}
	
	public int getZid() {
		return this.zid;
	}

}
